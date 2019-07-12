/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
public class Percolation {

    private int[][] id;
    private int size;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        id = new int[n][n];
        size = n;
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                id[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        id[row][col] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return id[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (id[i][j] != 1) {
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
                if (id[i][j] == 1) {
                    open++;
                }
            }
        }
        return open;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        System.out.println(perc.isFull(2, 2));
    }
}
