import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * {Declaring an object of weighted quick union}.
     */
    private Graph uf;
    /**
     * {variable n}.
     */
    private int n;
    /**
     * {Variables size, first, last, count}.
     */
    private int size, first, last, count;
    /**
     * {Declaring an integer array of type boolean}.
     */
    private boolean[] connected;
    /**
     * Constructs the object.
     *
     * @param      new1    The new1
     */
    Percolation(final int new1) {
        this.n = new1;
        this.size = new1 * new1;
        this.first = size;
        this.last = size + 1;
        this.count = 0;
        connected = new boolean[size];
        uf = new Graph(size + 2);
        for (int i = 0; i < n; i++) {
            uf.addEdge(first, i);
            uf.addEdge(last, size - i - 1);
        }
    }
    /**
     * Searches first match.
     *Time Complexity is O(1)
     * @param      i     {row}
     * @param      j     {column}
     *
     * @return     {index value}
     */
    private int indexOf(final int i, final int j) {
        return (n * (i - 1)) + (j - 1);
    }
    /**
     * Links open sites.
     *Time Complexity is O(1)
     * @param      row   The row
     * @param      col   The col
     */
    private void linkOpenSites(final int row, final int col) {
        if (connected[col] && !uf.hasEdge(row, col)) {
            uf.addEdge(row, col);
        }
    }
    /**
     * {Method to open site}.
     *Time Complexity is O(1)
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        int index = indexOf(row, col);
        connected[index] = true;
        count++;
        int top = index - n;
        int bottom = index + n;
        if (n == 1) {
            uf.addEdge(first, index);
            uf.addEdge(first, index);
        }
        if (bottom < size) {
            linkOpenSites(index, bottom);
        }
        if (top >= 0) {
            linkOpenSites(index, top);
        }
        if (col == 1) {
            if (col != n) {
                linkOpenSites(index, index + 1);
            }
            return;
        }
        if (col == n) {
            linkOpenSites(index, index - 1);
            return;
        }
        linkOpenSites(index, index + 1);
        linkOpenSites(index, index - 1);
    }
    /**
     * Determines if the site is open.
     *Time Complexity is O(1)
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return connected[indexOf(row, col)];
    }
    /**
     * {Method to determine the num of open sites}.
     *Time Complexity is O(1)
     * @return     {num of open sites}
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * {Method to determine does the system percolate}.
     *Time Complexity is O(1)
     * @return     {Boolean value}
     */
    public boolean percolates() {
        CC cc = new CC(uf);
        return cc.connected(first, last);
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // empty Constructor.
    }
    /**
     * {main function}.
     *Time complexity is O(N)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        Percolation per = new Percolation(num);
        while (scan.hasNextLine()) {
            String[] tokens = scan.nextLine().split(" ");
            per.open(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }
        System.out.println(per.percolates() && per.numberOfOpenSites() != 0);
    }
}
