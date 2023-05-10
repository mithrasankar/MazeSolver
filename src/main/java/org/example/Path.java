package org.example;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Path{
    Maze maze;
    //int rating;
    ArrayList<Square> squares = new ArrayList<>();
    //List<Maze.Direction> moves;
    //Path path;
    Path(Maze maze){
        squares.add(maze.getStart());
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
        return getSquares().get(getSquares().size() - 1).equals(maze.getEnd());
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
        try {
        Square lSquare = getSquares().get(getSquares().size() - 1);
        Square newSquare = maze.squareAdjacent(lSquare, direction);
            if (!maze.isBlockedSquare(newSquare) && isNotRetracing()) {
                this.squares.add(newSquare);
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
}



