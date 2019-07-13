/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int[] opened;
    int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        t = trials;
        opened = new int[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            for (int j = 0; j <= 100000; j++) {
                if (perc.percolates()) {
                    opened[i] = perc.numberOfOpenSites();
                    break;
                }
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                perc.open(x, y);
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(opened);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(opened);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / t;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / t;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(80, 500);
        System.out.println("mean: " + stats.mean());
        System.out.println("stddev: " + stats.stddev());
        System.out.println("95%: [" + stats.confidenceLo() + " , " + stats.confidenceHi() + "]");
    }
}
