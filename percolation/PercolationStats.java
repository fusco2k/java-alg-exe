/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] results;
    private final int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N and T should be greater than zero!");
        }
        t = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            double numberOfOpenedCells = 0;
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(y, x)) {
                    percolation.open(y, x);
                    numberOfOpenedCells++;
                }
            }
            results[i] = numberOfOpenedCells / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);

    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(t);
    }

    private void info() {
        StdOut.printf("mean\t= %f\nstddev\t= %f\n95%% confidence interval\t= %f, %f\n",
                      mean(), stddev(), confidenceLo(), confidenceHi());
    }

    // test client (see below)
    public static void main(String[] args) {
        new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1])).info();
    }
}
