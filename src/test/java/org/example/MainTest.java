/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.example.Direction.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
class MainTest {

    static Maze m1, m2, m3;
    static ArrayList<Maze> mazes = new ArrayList<>();

    @BeforeAll
    static void setup(){
        mazes.add( new Maze(2,"[[0,0]]", "[1,0]","[0,1]"));
        mazes.add(new Maze(4, "[[1,1],[2,3],[0,3],[2,1],[0,2]]", "[0,0]", "[1,3]"));
        mazes.add(new Maze(10, //Maze written by Alex Herman
                "[[0,1], [0,3], [1,1], [1,6], [1,7], [2,1], [2,3], [2,4], [2,5], [2,9], [3,3], [3,6], [3,7], [3,8], [4,1], [5,1], [5,2], [5,3], [5,4], [5,6], [5,7], [5,8], [6,4], [6,9], [7,0], [7,1], [7,2], [7,4], [7,6], [7,7], [8,4], [8,8], [9,4], [9,6], [9,7]]"
                ,"[0,0]","[2,6]"));
        mazes.add(new Maze(4, "[[0, 1], [1, 1], [1, 2]", "[0, 0]", "[0, 2]"));
        mazes.add(new Maze(6, "[[1,0], [1,1], [1,2], [1,3], [1,4], [1,4], [2,4], [3,4], [4,4]]", "[2,0]", "[0,0]"));

        String s1 = "[[8,8],[8,7],[8,6],[8,5],[8,4],[8,3],[8,2],[8,1],[7,1],[6,1],[5,1],[4,1],[3,1],[2,1],[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7],[2,7],[3,7],[4,7],[5,7],[6,7],[6,6],[6,5],[6,4],[6,3],[5,3],[4,3],[3,3],[3,4],[3,5],[4,5]]";
        String s2 = "[[9,0],[9,1],[9,2],[9,3],[9,4],[9,5],[0,1],[0,2],[0,3],[2,1],[2,2],[2,3],[2,4],[2,5],[2,6],[1,5],[1,6],[3,5],[4,5],[4,1],[5,1],[6,1],[7,1],[5,2],[7,2],[5,3],[6,3],[7,3],[5,4],[5,5],[5,6],[5,7],[5,8],[4,8],[3,8],[2,8],[1,8],[8,9],[7,4],[7,5],[7,6],[7,7],[8,7]]";
        mazes.add( new Maze(9, s1,"[8,0]","[4,4]"));
        mazes.add( new Maze(10, s2,"[0,0]","[9,9]"));

        m1 = new Maze(2,"[[0,0]]", "[1,0]","[0,1]");
        m2 = new Maze(4, "[[1,1],[2,3],[0,3],[2,1],[0,2]]", "[0,0]", "[1,3]");
        m3 = new Maze(6, "[[1,0], [1,1], [1,3], [1,4], [2,4], [3,2], [4,2], [3,4], [4,4]]", "[2,0]", "[0,0]");
    }

    @Test
    void solveMazeTest1() {
        List<Direction> ans = Main.solveMaze(m1, 100);
        ArrayList<Direction> correct = new ArrayList<>(2);
        correct.add(RIGHT);
        correct.add(UP);
        assertEquals(correct, ans);
    }

    @Test
    void solveMazeTest2() {
        List<Direction> ans = Main.solveMaze(m2, 100);
        Direction[] steps = {DOWN, DOWN, DOWN, RIGHT, RIGHT, UP, UP, RIGHT};
        ArrayList<Direction> correct = new ArrayList<>(List.of(steps));
        assertEquals(correct, ans);
    }


    @Test
    void solveMazeTest3() {
        Maze maze = mazes.get(0);
        List<Direction> ans = Main.solveMaze(maze, 100);
        List<Direction> correct = Arrays.asList(RIGHT, UP);
        assertEquals(correct, ans);
    }

    @Test
    void solveMazeTest4() {
        Maze maze = mazes.get(1);
        List<Direction> ans = Main.solveMaze(maze, 100);
        List<Direction> correct = Arrays.asList(DOWN, DOWN, DOWN, RIGHT, RIGHT, UP, UP, RIGHT);
        System.out.println(ans);
        assertEquals(correct, ans);
    }

    @Test
    void solveMazeTest5() {
        for (Maze m:mazes){
            Main.solveMaze(m, 100);

        }
}
}
