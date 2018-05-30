package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private int[][] grid;
    private int size;
    private int opencount;
    private WeightedQuickUnionUF set;
    private int entry;
    private int exit;

    public Percolation(int N) {
        if(N < 1){
            throw new IllegalArgumentException("Value must be greater than zero!");
        }
        set = new WeightedQuickUnionUF(N*N + 1 + N);
        size = N;
        opencount = 0;
        grid = new int[N][N];
        for(int i = 0, len = grid.length; i < len; i++){Arrays.fill(grid[i], 1);}
        exit = N*N;
        entry = N*N + N;
    }              // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col){
        if((row > size - 1) || (col > size - 1) || (0 > row) || (0 > col)){
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        if(!isOpen(row, col)) {
            opencount += 1;
            grid[row][col] = 0;
        }

        if(row != 0){
            if(isOpen(row - 1, col)) {
                set.union(row * size + col, (row - 1) * size + col);
            }
        } else{
            set.union(row*size + col, entry);
        }
        if(row != size - 1){
            if(isOpen(row + 1, col)){
                set.union(row*size + col, (row + 1)*size + col);
            }
        } else{
            set.union(row*size + col, (row + 1)*size + col);
        }
        if((col != 0) && isOpen(row, col - 1)){
            set.union(row*size + col, row*size + col - 1);
        }
        if((col != size - 1) && isOpen(row, col + 1)){
            set.union(row*size + col, row*size + col + 1);
        }

    }       // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col){
        if((row > size - 1) || (col > size - 1) || (0 > row) || (0 > col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        return (grid[row][col] == 0);
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col){
        if((row > size - 1) || (col > size - 1) || (0 > row) || (0 > col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        return (set.connected(row*size + col, entry));
    }  // is the site (row, col) full?

    public int numberOfOpenSites(){
        return opencount;
    }           // number of open sites

    public boolean percolates(){
        for(int ex = exit; ex < exit + size;ex += 1) {
            if (set.connected(entry, ex)) {
                return true;
            }
        }
        return false;
    }
    // does the system percolate?


    public static void main(String[] args){

        /**
        Percolation perc = new Percolation(3);
        if (!perc.isFull(0,0)){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        perc.open(0,0);
        if (perc.isFull(0,0)){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        perc.open(0,1);
        perc.open(1,1);
        perc.open(1,2);
        perc.open(2,2);
        if (perc.numberOfOpenSites()== 5){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        if (perc.percolates()){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        Percolation perc1 = new Percolation(3);
        perc1.open(0,0);
        perc1.open(0,1);
        perc1.open(0,1);
        perc1.open(0,1);
        perc1.open(1,2);
        perc1.open(2,2);
        if (perc1.numberOfOpenSites()== 4){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        if (!perc1.percolates()){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        if (perc1.isOpen(0,0) && !perc1.isOpen(0,2) && perc1.isFull(0,0) && !perc1.isFull(0,2)){System.out.print("SUCCESS!");} else{System.out.print("FAIL!");}
        System.out.print(StdRandom.permutation(5));
        */

    }   // use for unit testing (not required)

}
