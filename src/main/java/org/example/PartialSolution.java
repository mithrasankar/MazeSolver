package org.example;
import java.util.ArrayList;
import java.util.List;

public class PartialSolution {
        int rating;
        Path path;
        Maze maze;
        ArrayList<Direction> moves;
        PartialSolution(Maze maze){
            this.maze = maze;
            path = new Path(maze);
        }
        PartialSolution(ArrayList<Direction> moves,Path path,Maze maze){
            this.moves = moves;
            this.path = path;
            this.maze = maze;
        }

        public ArrayList<Direction> getMoves(){
            return moves;
        }

       public Path getPath(){
            return path;
        }

        public int getRating() {
            return rating;
        }
        public boolean isSolution(Maze maze){
             return getPath().isSolutionPath(maze);
        }

        public PSSet expandPartialSolution(Maze maze){
            Path path1 = new Path(maze);
            Path path2 = new Path(maze);
            Path path3 = new Path(maze);
            Path path4 = new Path(maze);

            for (Direction d: maze){

            }

        }


        public String toString(){
            return path.getPathString();
        }



    }


    List<Direction> solveMaze(Maze maze,int runtime){
        PSSet psset1 = new PSSet(new PartialSolution()(maze));
        for (int i = 0; i< psset1.length; i++) {
            best = psset1.getBestPartialSolution();
            if (best.isSolution(maze) && runtime == max) {
                return psset1.getMoves();
            } else if (runtime < max) {
                PSSet psset2 = expandPartialSolution(maze);
            }
        }
        psset2.remove(psset1);
        PSSet psset3 = union(psset1, psset2);
        return psset3.getMoves();
    }


}

 */






