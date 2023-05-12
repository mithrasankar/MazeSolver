package org.example;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static List<Direction> solveMaze(Maze maze, long maxRunTime){
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        Path initialPath = new Path(maze);
        ArrayList<Direction> initialMoves = new ArrayList<>();
        PartialSolution initialPartialSolution = new PartialSolution(initialMoves, initialPath, maze);
        PSSet psSet = new PSSet(initialPartialSolution);

        while (elapsedTime < maxRunTime * 1000) {
            PartialSolution bestPartialSolution = psSet.getBestPartialSolution();
            if (bestPartialSolution.isSolution()) {
                return bestPartialSolution.getMoves();
            }

            psSet.remove(bestPartialSolution);
            PSSet expandedSet = bestPartialSolution.expandPartialSolution(maze);
            psSet = PSSet.union(psSet, expandedSet);

            elapsedTime = System.currentTimeMillis() - startTime;
        }

        throw new MazeException("could not finish by max runtime.");
    }
}
