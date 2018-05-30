package hw4.puzzle;

import  java.util.Queue;
import java.util.LinkedList;
import java.lang.Math;

public class Board implements WorldState{
    private int[][] tileset;
    private static int BLANK = 0;

    public Board(int[][] tiles){
        tileset = new int[tiles.length][tiles.length];
        for(int i = 0; i < tiles.length; i += 1){
            for(int j = 0; j < tiles.length; j += 1){
                int val = tiles[i][j];
                tileset[i][j] = val;
            }
        }
    }

    public int tileAt(int i, int j){
        if(i < 0 || i > size() - 1 || j <0 || j > size() - 1 ){
            throw new IndexOutOfBoundsException();
        }
        int val = tileset[i][j];
        return val;
    }

    public int size(){return tileset.length;}


    public int hamming(){
        int tol = 0;
        for(int i = 0; i < size(); i+=1){
            for(int j = 0; j < size(); j += 1){
                if(tileset[i][j] - 1 != j + i*size() && tileset[i][j] != BLANK){tol += 1; }
            }
        }
        return tol;
    }

    public int manhattan(){
        int tol = 0;
        for(int i = 0; i < size(); i+=1){
            for(int j = 0; j < size(); j += 1){
                if(tileset[i][j] != BLANK){
                    int curr = tileset[i][j] - 1;
                    tol += Math.abs(curr / size() - i) + Math.abs(curr % size() - j);
                }
            }
        }
        return tol;
    }

    public int estimatedDistanceToGoal(){
        return manhattan();
    }

    public boolean equals(Board y){
        if(y==null){return false;}
        for(int i = 0; i < size(); i+=1){
            for(int j = 0; j < size(); j += 1){
                if(y.tileset[i][j] != tileset[i][j]){return false;}
            }
        }
        return true;
    }


    /** Returns the string representation of the board.
      * Uncomment this method. */

   public String toString() {
=======

public class Board {

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    /*public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


    /** neighbors method taken from default implementation given as part of assignment 4
     * http://joshh.ug/neighbors.html*/

    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new LinkedList<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.add(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    }


}
