import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {

    private boolean[][] grid;
    private final int[] id;
    private final int size;
    private final WeightedQuickUnionUF wquf;
    private int count = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        // initialize the grid array
        grid = new boolean[n][n];
        // initialize the id array
        id = new int[n * n + 2];
        wquf = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        // fill the grid with closed spots (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        // fill the id with respective number
        for (int i = 0; i < n * n + 2; i++) {
            id[i] = i;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > size || col > size || row <= 0 || col <= 0) {
            throw new IllegalArgumentException("n > size");
        }
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            count++;
            if (row - 1 != 0) {
                if (isOpen(row - 1, col)) {
                    union(row, col, row - 1, col);
                }
            }
            else {
                unionBottom(row, col);
            }
            if (row != size) {
                if (isOpen(row + 1, col)) {
                    union(row, col, row + 1, col);
                }
            }
            else {
                unionTop(row, col);
            }
            if (col - 1 != 0) {
                if (isOpen(row, col - 1)) {
                    union(row, col, row, col - 1);
                }
            }
            if (col != size) {
                if (isOpen(row, col + 1)) {
                    union(row, col, row, col + 1);
                }
            }
        }
    }

    // is the site (row, col) open? normalized
    public boolean isOpen(int row, int col) {
        if (row > size || col > size || row <= 0 || col <= 0) {
            throw new IllegalArgumentException("n > size");
        }
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > size || col > size || row <= 0 || col <= 0) {
            throw new IllegalArgumentException("n > size");
        }
        int pid = id[(row - 1) * size + col];
        int f = wquf.find(pid);
        return wquf.connected(f, 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate? normalized
    public boolean percolates() {
        return wquf.connected(id[0], id[size * size + 1]);
    }

    private void union(int row, int col, int row2, int col2) {
        int pid = id[(row - 1) * size + col];
        int qid = id[(row2 - 1) * size + col2];
        // for (int i = 0; i < id.length; i++)
        //     if (id[i] == pid) id[i] = qid;
        wquf.union(pid, qid);
    }

    private void unionBottom(int row, int col) {
        int pid = id[(row - 1) * size + col];
        int qid = id[0];
        // for (int i = 0; i < id.length; i++)
        //     if (id[i] == pid) id[i] = qid;
        wquf.union(pid, qid);
    }

    private void unionTop(int row, int col) {
        int pid = id[(row - 1) * size + col];
        int qid = id[size * size + 1];
        // for (int i = 0; i < id.length; i++)
        //     if (id[i] == pid) id[i] = qid;
        wquf.union(pid, qid);
    }

    public static void main(String[] args) {
        // Percolation percolation = new Percolation(99);
    }
}
