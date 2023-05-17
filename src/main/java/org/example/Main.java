/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static int count = 0;

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
                System.out.println("elapsed:" + elapsedTime + "\n# of expansions:"+ count+'\n');
                return bestPartialSolution.getMoves();
            }

            psSet.remove(bestPartialSolution);
            PSSet expandedSet = bestPartialSolution.expandPartialSolution(maze);
            count ++;
            psSet = PSSet.union(psSet, expandedSet);

            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println(elapsedTime);
        }

        throw new MazeException("could not finish by max runtime.");
    }
}
