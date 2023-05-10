package org.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PSSet {
    PartialSolution ps;
    ArrayList<Direction> d1 = new ArrayList<>();
    Set<PartialSolution> PSet = new HashSet<PartialSolution>();
    PSSet(PartialSolution ps){
        PSet.add(ps);
    }
    PSSet(Collection collection){
        PSet.addAll(collection);

    }

    public int size(){
        return PSet.size();
    }

    public PSSet union(PSSet one, PSSet two){
        Set<PSSet> setOne = new HashSet<>();
        setOne.add(one);
        Set<PSSet> setTwo = new HashSet<>();
        setTwo.add(two);
        setOne.addAll(setTwo);
        PSSet together = new PSSet(setOne);
        return together;
    }
    public void remove(PartialSolution ps){
        this.remove(ps);
    }

    PartialSolution getBestPartialSolution(PSSet newpsset){
        return partial;
    }


}




