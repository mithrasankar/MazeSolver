/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Path{
    Maze maze;
    ArrayList<Square> squares = new ArrayList<>();
    Path(Maze maze){
        this.maze = maze;
        this.squares = new ArrayList<>();
        squares.add(maze.getStart());
    }
    Path(Path path){
        this.squares = new ArrayList<>(path.getSquares());
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
    public int evalPath (Maze maze){
        return squares.size()+(2*maze.evalSquare(squares.get(squares.size()-1)));
    }

    public boolean isNotRetracing() {
        ArrayList<Square> withLast = new ArrayList<>(getSquares());
        Square last = getSquares().get(getSquares().size() - 1);
        withLast.remove(last);
        return !withLast.contains(last);
    }

    public boolean isSolutionPath(Maze maze){
        return getSquares().get(0).equals(maze.getStart()) && getSquares().get(getSquares().size() - 1).equals(maze.getEnd());
    }

    public void push(Square square){
        getSquares().add(square);
    }
    public void pop(){
        int index = getSquares().size() - 1;
        getSquares().remove(index);
    }

    public int size(){
        return getSquares().size();
    }
    public Path move (Maze maze, Direction direction) throws UnableToMoveException {
        Square lSquare = this.getSquares().get(getSquares().size() - 1);
        Square newSquare;
        try {
            newSquare = maze.squareAdjacent(lSquare, direction);
        }catch(NoSuchElementException e){
            throw new UnableToMoveException("none exist");
        }
        Path newPath = new Path(this);
        newPath.push(newSquare);

        if (!maze.isBlockedSquare(newSquare) && isNotRetracing()) {
                this.squares.add(newSquare);
            }
            else{
                throw new UnableToMoveException("none exist");
            }
        return newPath;
    }
    public String getPathString(){
        String string = "";
        for (int i =0; i<getSquares().size();i++){
            string = string.concat(getSquares().get(i).toString());
            string = string.concat(" ");
        }
        return string;
    }
    @Override
    public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other instanceof Path) {
                Path otherPath = (Path) other;
                return squares.equals(otherPath.squares);
            }
            return false;
        }
}



