package org.example;
import java.util.ArrayList;

public class PartialSolution {
    int rating;
    Path path;
    Maze maze;
    ArrayList<Direction> moves;

    PartialSolution(Maze maze) {
        this.maze = maze;
        this.path = new Path(maze);
    }

    PartialSolution(ArrayList<Direction> moves, Path path, Maze maze) {
        this.moves = moves;
        this.path = path;
        this.maze = maze;
    }

    public ArrayList<Direction> getMoves() {
        return moves;
    }

    public Path getPath() {
        return path;
    }

    public Maze getMaze(){
        return maze;
    }

    public int getRating() {
        return getPath().evalPath(getMaze());
    }

    public boolean isSolution() {
        return getPath().isSolutionPath(getMaze());
    }

    public int hashCode(){
        return 1;
    }

    public PSSet expandPartialSolution(Maze maze) {
        PSSet psSet = new PSSet(this);
        Direction[] directions = Direction.values();
        Path currentPath = this.path;
        for (Direction direction : directions) {
            try {
                Path newPath = new Path(currentPath);
                newPath.move(maze, direction);
                if (newPath.isNotRetracing()) {
                    ArrayList<Direction> newMoves = new ArrayList<>(this.moves);
                    newMoves.add(direction);
                    PartialSolution newPartialSolution = new PartialSolution(newMoves, newPath, maze);
                    psSet.PSet.add(newPartialSolution);
                }
            } catch (UnableToMoveException e) {
                // Handle exception if needed
            }
        }
        psSet.remove(this);
        return psSet;
    }


    public String toString() {
        return getPath().getPathString();
    }
@Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other instanceof PartialSolution) {
            PartialSolution otherPS = (PartialSolution) other;
            return moves.equals(otherPS.moves) && path.equals(otherPS.path) && maze.equals(otherPS.maze);
        }
        return false;
    }
}










