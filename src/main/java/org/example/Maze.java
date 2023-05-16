/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;

import java.util.ArrayList;
import java.util.NoSuchElementException;
enum Direction {UP, DOWN, LEFT, RIGHT}
public class Maze {
    int size; //number (integer) of rows, columns in square maze
    char[][] maze;
    String blocked;
    String start;
    String end;

    Maze(int size, String blocked, String start, String end) {
        this.size = size;
        this.blocked = blocked;
        this.start = start;
        this.end = end;
        initializeMaze();
        populateMaze();
    }

    public void initializeMaze() {
        maze = new char[size][size];
        for (char[] row : maze) {
            for (int i = 0; i < row.length; i++) {
                row[i] = 'C';
            }
        }
    }
    Square startSquare;
    Square endSquare;
    public void populateMaze() {
        for (Square square : parseSquareList(blocked)) {
            int row = square.getRow();
            int col = square.getCol();
            if (row >= 0 && row < size && col >= 0 && col < size) {
                maze[row][col] = 'B';
            }
        }
        startSquare = parseSquare(start);
        if (startSquare.getRow() >= 0 && startSquare.getRow() < size && startSquare.getCol() >= 0 && startSquare.getCol() < size) {
            maze[startSquare.getRow()][startSquare.getCol()] = 'S';
        }
        endSquare = parseSquare(end);
        if (endSquare.getRow() >= 0 && endSquare.getRow() < size && endSquare.getCol() >= 0 && endSquare.getCol() < size) {
            maze[endSquare.getRow()][endSquare.getCol()] = 'E';
        }
    }

    public int getSize() {
        return size;
    }

    public Square getStart() {
        int rows = getSize();
        int cols = getSize();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    return new Square(i, j);
                }
            }
        }
        return null;
    }


    public Square getEnd() {
    int rows = getSize();
    int cols = getSize();
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (maze[i][j] == 'E') {
                return new Square(i, j);
            }
        }
    }
    return null;
}

    public char[][] getMaze() {
        return maze;
    }

    public ArrayList<Square> getBlocked() {
       ArrayList<Square> blockedSquares = new ArrayList<>();
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (maze[row][col] == 'B') {
                        blockedSquares.add(new Square(row, col));
                    }
                }
            }
            return blockedSquares;
        }


    public int distance(Square one, Square two) { //rating
        int rowD = Math.abs(one.getRow() - two.getRow());
        int colD = Math.abs(one.getCol() - two.getCol());
        return rowD + colD;
    }

    public int evalSquare(Square square) {
        int distance = distance(square, getEnd());
        int blockageFactor = countBlockedSquares(square, getEnd());

        if (2 * blockageFactor > distance) {
            blockageFactor = (int) Math.pow((2 * blockageFactor - distance), 3);
        } else {
            blockageFactor = 0;
        }

        return distance + blockageFactor;
    }


    public int countBlockedSquares(Square square1, Square square2) {
        int minRow = Math.min(square1.getRow(), square2.getRow());
        int maxRow = Math.max(square1.getRow(), square2.getRow());
        int minCol = Math.min(square1.getCol(), square2.getCol());
        int maxCol = Math.max(square1.getCol(), square2.getCol());

        int count = 0;
        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                if (maze[row][col] == 'B') {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isEndPoint(Square square) {
        return square.equals(getEnd());
    }

    public boolean isStartPoint(Square square) {
        return square.equals(getStart());
    }

    public boolean isBlockedSquare(Square square) {
        ArrayList<Square> blockedList = new ArrayList<>();
        if (maze[square.getRow()][square.getCol()] == 'B'){
                blockedList.add(square);

            }
        return blockedList.contains(square);
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

    public Square squareAdjacent(Square square, Direction direction) throws NoSuchElementException {
        int col = square.getCol();
        int row = square.getRow();
        switch (direction) {
            case UP:
                row--;
                break;
            case DOWN:
                row++;
                break;
            case LEFT:
                col--;
                break;
            case RIGHT:
                col++;
                break;
        }
        if (row >= 0 && row < getSize() && col >= 0 && col < getSize()) {
            return new Square(row, col);
        } else {
            throw new NoSuchElementException("none exist");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == startSquare.getRow() && col == startSquare.getCol()) {
                    sb.append('S');
                } else if (row == endSquare.getRow() && col == endSquare.getCol()) {
                    sb.append('E');
                } else if (maze[row][col] == 'B') {
                    sb.append('B');
                } else {
                    sb.append('_');
                }
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object mze) {
        if (this == mze) {
            return true;
        }
        if (mze instanceof Maze) {
            Maze other = (Maze) mze;
            return size == other.size && this.getBlocked().equals(other.getBlocked()) && this.startSquare.equals(other.startSquare) && this.endSquare.equals(other.endSquare);
        } else {
            return false;
        }
    }
}






