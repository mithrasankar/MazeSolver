/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PSSetTest {

    @Test
    void unionTest() {

        // Create the first PSSet
        Maze m1 = new Maze(3, "[]", "[1,1]", "[0,1]");
        Path p1 = new Path(m1);
        ArrayList<Direction> d1 = new ArrayList<>();
        PartialSolution ps1 = new PartialSolution(d1, p1, m1);
        PSSet set1 = new PSSet(ps1);

        // Create the second PSSet
        Maze m2 = new Maze(3, "[]", "[0,1]", "[1,1]");
        Path p2 = new Path(m2);
        ArrayList<Direction> d2 = new ArrayList<>();
        PartialSolution ps2 = new PartialSolution(d2, p2, m2);
        PSSet set2 = new PSSet(ps2);

        // Create the expected union of set1 and set2
        PSSet expectedUnion = new PSSet(Arrays.asList(ps1, ps2));

        // Perform the union operation
        PSSet result = PSSet.union(set1, set2);

        // Assert that the result matches the expected union
        assertEquals(expectedUnion, result);

    }


    @Test
        void removeTest () {
            // Create the PSSet
            Maze m1 = new Maze(3, "[]", "[1,1]", "[0,1]");
            Path p1 = new Path(m1);
            ArrayList<Direction> d1 = new ArrayList<>();
            PartialSolution ps1 = new PartialSolution(d1, p1, m1);
            PSSet set = new PSSet(ps1);

            // Create the PartialSolution to remove
            Maze m2 = new Maze(3, "[]", "[0,1]", "[1,1]");
            Path p2 = new Path(m2);
            ArrayList<Direction> d2 = new ArrayList<>();
            PartialSolution ps2 = new PartialSolution(d2, p2, m2);

            // Ensure that the set contains ps2 initially
            assertTrue(set.contains(ps2));

            // Remove ps2 from the set
            boolean removed = set.remove(ps2);

            // Assert that ps2 was successfully removed from the set
            assertTrue(removed);
            assertFalse(set.contains(ps2));
        }


    @Test
    void getBestPartialSolutionTest() {
        // Create a maze and partial solutions
        Maze maze = new Maze(3, "[]", "[1,1]", "[0,1]");
        Path path1 = new Path(maze);
        ArrayList<Direction> moves1 = new ArrayList<>();
        PartialSolution ps1 = new PartialSolution(moves1, path1, maze);
        Path path2 = new Path(maze);
        ArrayList<Direction> moves2 = new ArrayList<>();
        PartialSolution ps2 = new PartialSolution(moves2, path2, maze);
        Path path3 = new Path(maze);
        ArrayList<Direction> moves3 = new ArrayList<>();
        PartialSolution ps3 = new PartialSolution(moves3, path3, maze);
        PSSet psSet = new PSSet(ps1);
        psSet.PSet.add(ps2);
        psSet.PSet.add(ps3);

        PartialSolution bestPartialSolution = psSet.getBestPartialSolution();
        assertEquals(ps1, bestPartialSolution);
    }

    @Test
    void getBestPartialSolutionTest2() {
        Maze m1 = new Maze(3,"[]", "[1,1]","[0,1]");
        Path p1 = new Path(m1);
        ArrayList<Direction> d1 = new ArrayList<>();
        PartialSolution ps1 = new PartialSolution(d1, p1, m1);
        PSSet psset = ps1.expandPartialSolution(m1);
        ArrayList<PartialSolution> arr = new ArrayList<>(psset.PSet);
        PartialSolution best = psset.getBestPartialSolution();
        assertEquals(arr.get(0), best);
    }




//        d1.add(Direction.LEFT);
//        p1.push(new Square(1,0));
//        PartialSolution ps2 = new PartialSolution(d1, p1, m1);
    /*@Test
    void removeTest() {
        Maze m1 = new Maze(3, "[]", "[1,1]", "[0,1]");
        Path p1 = new Path(m1);
        ArrayList<Direction> d1 = new ArrayList<>();
        PartialSolution ps1 = new PartialSolution(d1, p1, m1);

        PSSet psset = ps1.expandPartialSolution(m1);
        System.out.println(psset);
        assertTrue(psset.contains(ps1));
        psset.remove(ps1);
        assertFalse(psset.contains(ps1));
    }


     */
    }





