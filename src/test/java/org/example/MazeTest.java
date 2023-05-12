/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.example.Direction.UP;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    static Maze m1;

    @BeforeAll
    static void setup() {
        m1 = new Maze(4, "[[2,2],[3,4],[1,4],[3,2],[1,3]]", "[1,1]", "[2,3]");
    }

    @Test
    void testToString() {
        Maze m = new Maze(2, "[[0,0]]", "[1,0]", "[0,1]");
        assertEquals("B E \nS _ \n", m.toString());
    }

    @Test
    void parseSquareTest1() {
        String s1 = "2,3],]";
        Square exp = new Square(2, 3);
        assertEquals(exp.toString(), Maze.parseSquare(s1).toString());
    }

    @Test
    void parseSquareTest2() {
        String s1 = "1,2],";
        Square exp = new Square(1, 2);
        assertEquals(exp.toString(), Maze.parseSquare(s1).toString());
    }

    @Test
    void parseSquareTest3() {
        String s1 = "3,4]]";
        Square exp = new Square(3, 4);
        assertEquals(exp.toString(), Maze.parseSquare(s1).toString());
    }

    @Test
    void parseSquareTest4() {
        String s1 = "[3,4]";
        Square exp = new Square(3, 4);
        assertEquals(exp.toString(), Maze.parseSquare(s1).toString());
    }

    //makeMaze(4, [[2, 2],[3, 4],[1, 4],[3, 2],[1, 3]], [1, 1], [3, 3]);
    @Test
    void parseSquareListTest() {
        String sqStr = "[[2, 2],[3, 4],[1, 4],[3, 2],[1, 3]], ";
        ArrayList<Square> ans = Maze.parseSquareList(sqStr);
        assertEquals(5, ans.size());
    }

    @Test
    void parseSquareListTest_Empty() {
        String sqStr = "[], ";
        ArrayList<Square> ans = Maze.parseSquareList(sqStr);
        assertEquals(0, ans.size());
    }

    @Test
    void distanceTest() {
        Square one = new Square(2, 1);
        Square two = new Square(1, 2);
        int result = m1.distance(one, two);
        assertEquals(2, result);
    }

    @Test
    void distanceTest2() {
        int result = m1.distance(m1.getStart(), m1.getEnd());
        assertEquals(3, result);
    }

    @Test
    void evalSquare() {
        Square one = new Square(1, 2);
        int result = m1.evalSquare(one);
        assertEquals(2, result);
    }

    @Test
    void isEndPointTest() {
        Square end = new Square(2, 3);
        assertTrue(m1.isEndPoint(end));
    }

    @Test
    void isBlockedSquareTest() {
        assertTrue(m1.isBlockedSquare(new Square(1, 3)));
        assertTrue(m1.isBlockedSquare(new Square(1, 4)));
        assertFalse(m1.isBlockedSquare(new Square(1, 5)));
    }

    @Test
    void squareAdjacentTest() {
        Square one = new Square(1, 1);
        Square two = new Square(0, 1);
        assertEquals(two.toString(), m1.squareAdjacent(one, Direction.UP).toString());
    }

    @Test
    void squareAdjacentTestDown() {
        Square one = new Square(1, 1);
        Square two = new Square(2, 1);
        assertEquals(two.toString(), m1.squareAdjacent(one, Direction.DOWN).toString());
    }

    @Test
    void squareAdjacentTestLeft() {
        Square one = new Square(1, 1);
        Square two = new Square(1, 0);
        assertEquals(two.toString(), m1.squareAdjacent(one, Direction.LEFT).toString());
    }

    @Test
    void squareAdjacentTestRight() {
        Square one = new Square(1, 1);
        Square two = new Square(1, 2);
        assertEquals(two.toString(), m1.squareAdjacent(one, Direction.RIGHT).toString());
    }

    @Test
    void squareAdjacentTestBounds()  throws NoSuchElementException {
        Square one = new Square(0, 2);
        Assertions.assertThrows(NoSuchElementException.class,
                ()->m1.squareAdjacent(one, Direction.UP));
    }

    @Test
    void squareAdjacentTestBounds2() throws NoSuchElementException{
        Square one = new Square(0, 4);
        Assertions.assertThrows(NoSuchElementException.class,
                ()->m1.squareAdjacent(one, Direction.RIGHT));
    }
}

/*

    @Test
    void countBlockedSquares() {
    }

 */
