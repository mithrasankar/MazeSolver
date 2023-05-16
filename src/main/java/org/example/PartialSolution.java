/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import java.util.ArrayList;
import java.util.Objects;

public class PartialSolution {
    Path path;
    Maze maze;
    int count = 0;
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

    public int getCount(){return count;}

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
                    count++;
                    PartialSolution newPartialSolution = new PartialSolution(newMoves, newPath, maze);
                    psSet.PSet.add(newPartialSolution);
                }
            } catch (UnableToMoveException e) {

            }
        }
        psSet.remove(this);
        System.out.println(count);
        return psSet;
    }

    public String toString() {
        return getPath().getPathString();
    }

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
    @Override
    public int hashCode(){
        return Objects.hash(moves,path,maze);
    }
}










