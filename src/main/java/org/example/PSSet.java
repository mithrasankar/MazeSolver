/**
 * <h1>Lab11</h1>
 * <h2>CISC181-080L Spring 2023</h2>
 * <h3>University of Delaware</h3>
 * @author Mithra Sankar
 */
package org.example;
import java.util.*;

public class PSSet{
    PartialSolution ps;
    ArrayList<PartialSolution> psolutions = new ArrayList<>();
    HashSet<PartialSolution> PSet = new HashSet<>();
    PSSet(PartialSolution ps){
        PSet.add(ps);
    }
    PSSet(Collection collection){
        PSet.addAll(collection);
    }

    public String toString(){
        String string = "";
        Iterator iterator = iterator();
        while (iterator.hasNext()){
            string = string.concat(iterator.next().toString());
        }
        return string;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof PSSet){
          return this.PSet.equals(((PSSet)o).PSet);
      }
      else{
          return false;
      }
    }

    public int size(){
        return this.PSet.size();
    }

    public boolean contains(PartialSolution ps){
        return PSet.contains(ps);
    }

    public static PSSet union(PSSet one, PSSet two){
        Set<PartialSolution> setOne = one.PSet;
        Set<PartialSolution> setTwo = two.PSet;
        setOne.addAll(setTwo);
        return new PSSet(setOne);

    }

    public boolean remove(PartialSolution ps){
        return PSet.remove(ps);

    }

    public PSIterator iterator(){
        return new PSIterator(PSet);
    }

    class PSIterator implements Iterator<PartialSolution> {
        int index;
        HashSet<PartialSolution> solutions;

        PSIterator(HashSet<PartialSolution> solutions){
            this.solutions = solutions;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < solutions.size();
        }

        @Override
        public PartialSolution next() {
            ArrayList<PartialSolution> ps = new ArrayList<>(solutions);
            return ps.get(index++);
        }

    }
    public ArrayList<PartialSolution> getPsolutions(){
        return psolutions;
    }

    public void setPsolutions(ArrayList<PartialSolution> pSolutions){
        this.psolutions = pSolutions;
    }

    public PartialSolution getBestPartialSolution() {
        int rating = PSet.iterator().next().getRating();
        PartialSolution best = PSet.iterator().next();
        for (PartialSolution partialSolution : PSet) {
            if (partialSolution.getRating() < rating) {
                rating = partialSolution.getRating();
                best = partialSolution;
            }
        }
        return best;
    }



}




