/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import java.util.*;

public class PSSet  {
    PartialSolution ps;
    static int count = 0;
    TreeSet<PartialSolution> PSet = new TreeSet<>(new RatingComparator());

    PSSet(PartialSolution ps) {
        PSet = new TreeSet<>(Comparator.comparingInt(PartialSolution::getRating));
        PSet.add(ps);
    }

    PSSet(Collection collection) {
        PSet.addAll(collection);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PartialSolution ps : PSet) {
            sb.append(ps.toString());
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof PSSet) {
            return this.PSet.equals(((PSSet) o).PSet);
        } else {
            return false;
        }
    }

    public int size() {
        return PSet.size();
    }

    public boolean contains(PartialSolution ps) {
        return PSet.contains(ps);
    }

    public static PSSet union(PSSet one, PSSet two) {
        TreeSet<PartialSolution> setOne = new TreeSet<>(one.PSet);
        setOne.addAll(two.PSet);
        return new PSSet(setOne);
    }


    public boolean remove(PartialSolution ps) {
        return PSet.remove(ps);
    }

    public PSIterator iterator() {
        return new PSIterator(PSet);
    }

    class PSIterator implements Iterator<PartialSolution> {
        Iterator<PartialSolution> iterator;

        PSIterator(TreeSet<PartialSolution> solutions) {
            iterator = solutions.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public PartialSolution next() {
            return iterator.next();
        }
    }

        public PartialSolution getBestPartialSolution() {
            return PSet.first();
        }
}
class RatingComparator implements Comparator<PartialSolution> {
    @Override
    public int compare(PartialSolution ps1, PartialSolution ps2) {
        return Integer.compare(ps1.getRating(), ps2.getRating());
    }
}




