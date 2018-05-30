package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;

public class PercolationStats {
    private double threshhold [];
    private double mean;
    private double std;
    private double conf1;
    private double conf2;



    public PercolationStats(int N, int T, PercolationFactory pf){
        if((N < 1) || (T < 1)){
            throw new IllegalArgumentException("Value must be greater than zero!");
        }
        if(T == 1){
            throw new IllegalArgumentException("");
        }
        threshhold = new double[T];
        double tol = 0;
        double tol2 = 0;
        for(int i = 0; i < T; i += 1){
            int[] samp = StdRandom.permutation(N*N);
            Percolation perc = pf.make(N);
            while(!perc.percolates()) {
                int cell = samp[perc.numberOfOpenSites()];
                perc.open(cell / N, cell % N);
            }
            threshhold[i] = ((double)perc.numberOfOpenSites())/((double)(N*N));
            tol += threshhold[i];
            tol2 += threshhold[i]*threshhold[i];
        }

        mean = tol/((double)T);
        double second = tol2/((double)(T));
        if(T>1) {std = Math.sqrt((second - mean*mean)*((double)T)/((double)(T-1)));}
        else {std = 0;}
        /**mean = StdStats.mean(threshhold);
        std = StdStats.stddev(threshhold);
         */
        conf1 = mean - 1.96*std/(Math.sqrt(T));
        conf2 = mean + 1.96*std/(Math.sqrt(T));

    }   // perform T independent experiments on an N-by-N grid

    public double mean(){ return mean; }// sample mean of percolation threshold
    public double stddev(){ return std; }                                         // sample standard deviation of percolation threshold
    public double confidenceLow(){ return conf1; }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh(){ return conf2; }         // high endpoint of 95% confidence interval

/**
    public static void main(String[] args){
        PercolationStats p = new PercolationStats(9, 100, new PercolationFactory());
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println(p.confidenceHigh());
        System.out.println(p.confidenceLow());
    }
*/
}
