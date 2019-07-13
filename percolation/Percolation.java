/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {

    private int[][] grid;
    private int[] id;
    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // initialize the grid array
        grid = new int[n][n];
        // initialize the id array
        id = new int[n * n + 2];
        size = n;
        // fill the grid with closed spots (0)
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
        // fill the id with respective number
        for (int i = 0; i < n * n + 2; i++) {
            id[i] = i;
        }
        // connect top and bottom
        for (int i = 1; i <= size; i++) {
            union(0, i, 0, 0, true, false);
            union(size, i, 0, 0, false, true);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = 1;
            if (row - 1 > 0) {
                if (isOpen(row - 1, col)) {
                    union(row, col, row - 1, col, false, false);
                }
            }
            if (row != size) {
                if (isOpen(row + 1, col)) {
                    union(row, col, row + 1, col, false, false);
                }
            }
            if (col - 1 > 0) {
                if (isOpen(row, col - 1)) {
                    union(row, col, row, col - 1, false, false);
                }
            }
            if (col != size) {
                if (isOpen(row, col + 1)) {
                    union(row, col, row, col + 1, false, false);
                }
            }
        }
    }

    // is the site (row, col) open? normalized
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int open = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == 1) {
                    open++;
                }
            }
        }
        return open;
    }

    // does the system percolate? normalized
    public boolean percolates() {
        return connected(id[0], id[size + 1]);
    }

    // normalized
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int row, int col, int rol2, int col2, boolean top, boolean bottom) {
        if (top) {
            int pid = id[col];
            int qid = id[0];
            for (int i = 0; i < id.length; i++)
                if (id[i] == pid) id[i] = qid;
        }
        if (bottom) {
            int pid = id[col];
            int qid = id[id.length - 1];
            for (int i = 0; i < id.length; i++)
                if (id[i] == pid) id[i] = qid;
        }
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        for (int i = 0; i < perc.size * perc.size + 2; i++) {
            System.out.println(perc.id[i]);
        }
    }
}
