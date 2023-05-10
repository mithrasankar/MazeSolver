package org.example;
public class Square {
    int row;
    int col;

     Square(int row,int col){
         this.row = row;
         this.col = col;
    }

    public String toString(){
         return "(" + row + ", " + col + ")";
     }

    public boolean equals(Object other) {
        if (other instanceof Square) {
            return this.row == ((Square) other).row && this.col == ((Square) other).col;
        } return false;
    }
    public int getRow(){
         return row;
    }
    public int getCol(){
        return col;
    }

}


