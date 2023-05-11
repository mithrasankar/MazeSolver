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
        psolutions.addAll(collection);
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
       /* if (this.PSet.contains(ps)) {
            this.remove(ps);
            return true;
        }
        return false;
        */

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

    public PartialSolution getBestPartialSolution(){
        int rating = getPsolutions().get(0).getRating();
        PartialSolution best = getPsolutions().get(0);
        for(int i = 0; i<getPsolutions().size(); i++){
            if(getPsolutions().get(i).getRating() <rating){
                rating = getPsolutions().get(i).getRating();
                best = getPsolutions().get(i);
            }
        }
        return best;
    }


}




