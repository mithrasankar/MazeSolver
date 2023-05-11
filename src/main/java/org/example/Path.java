package org.example;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Path{
    Maze maze;
    ArrayList<Square> squares = new ArrayList<>();
    Path(Maze maze){
        this.maze = maze; squares.add(maze.getStart());
    }
    Path(Path path){
        squares = new ArrayList<>(path.getSquares());
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
        Square sq = getSquares().get(getSquares().size()-1);
        getSquares().remove(sq);
    }

    public int size(){
        return getSquares().size();
    }
    public Path move (Maze maze, Direction direction) throws UnableToMoveException {
        Square newSquare;
        try {
            Square lSquare = this.getSquares().get(getSquares().size() - 1);
            newSquare = maze.squareAdjacent(lSquare, direction);
            if (!maze.isBlockedSquare(newSquare) && isNotRetracing()) {
                this.squares.add(newSquare);
            }
            else{
                throw new UnableToMoveException("none exist");
            }
        }catch(NoSuchElementException e){
            throw new UnableToMoveException("none exist");
        }
        return this;
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



