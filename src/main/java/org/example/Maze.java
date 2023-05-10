package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;
enum Direction {UP, DOWN, LEFT, RIGHT}
public class Maze {


    int size; //number (integer) of rows, columns in square maze
    String blocked;
    String start;
    String end;

    Maze(int size, String blocked, String start, String end) {
        this.size = size;
        this.blocked = blocked;
        this.start = start;
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public Square getStart() {
        return parseSquare(start);
    }

    public Square getEnd() {
        return parseSquare(end);
    }

    public ArrayList<Square> getBlocked() {
        return parseSquareList(blocked);
    }

    public int distance(Square one, Square two) { //rating
        int rowD = Math.abs(one.getRow() - two.getRow());
        int colD = Math.abs(one.getCol() - two.getCol());
        return rowD + colD;
    }

    public int evalSquare(Square square) {
        return distance(square, getEnd());
    }

    public boolean isEndPoint(Square square) {
        return square.equals(getEnd());
    }

    public boolean isStartPoint(Square square) {
        return square.equals(getStart());
    }

    public boolean isBlockedSquare(Square square) {
            return getBlocked().contains(square);
        }


    public static Square parseSquare(String square) {
        square = square.replace("[", "");
        int comma = square.indexOf(',');
        int row = Integer.parseInt(square.substring(0, comma).trim());
        int bracket = square.indexOf(']');
        int col = Integer.parseInt(square.substring(comma + 1, bracket).trim());
        return new Square(row, col);
    }

    public static ArrayList<Square> parseSquareList(String square) {
        ArrayList<Square> result = new ArrayList<>();
        String[] list = square.split("\\[");
        for (String i : list) {
            if (!i.isEmpty()) {
                int bracket = square.indexOf('[');
                if (bracket + 1 == square.indexOf(']')) {
                    return result;
                } else {
                    result.add(parseSquare(i));
                }
            }
        }
        return result;
    }

    public Square squareAdjacent(Square square, Direction direction) throws NoSuchElementException{
        int col = square.getCol();
        int row = square.getRow();
            switch (direction) {
                case UP:
                    row -= 1;
                    break;
                case DOWN:
                    row += 1;
                    break;
                case LEFT:
                    col -= 1;
                    break;
                case RIGHT:
                    col += 1;
                    break;
            }
            if (row >= 0 && row < getSize() && col >= 0 && col < getSize()) {
                return new Square(row, col);
            } else {
                throw new NoSuchElementException("none exist");
            }
    }

    public String toString() {
        String string = "";
        Square square;
        for (int r = 0; r < getSize(); r++) {
            for (int c = 0; c < getSize(); c++) {
                square = new Square(r, c);
                if (isBlockedSquare(square)) {
                    string = string.concat("B ");
                } else if (isStartPoint(square)) {
                    string = string.concat("S ");
                } else if (isEndPoint(square)) {
                    string = string.concat("E ");
                } else {
                    string = string.concat("_ ");
                }
            }
            string = string.concat("\n");
        }
        return string;
    }
}






