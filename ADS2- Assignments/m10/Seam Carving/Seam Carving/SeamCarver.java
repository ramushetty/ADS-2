import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**
     * {Border Energy Value}.
     */
    private static final double BORDER_VALUE = 1000;
    /**
     * {Picture Object}.
     */
    private Picture pict;

    /**
     * Constructs the object.
     *
     * @param      picture  The picture
     */
    SeamCarver(final Picture picture) {
        if (picture == null) {
            throw new java.lang.IllegalArgumentException("picture is null");
        }
        this.pict = picture;
    }


    /**
     * {current picture}.
     * Time complexity  O(1)
     * @return     {Picture}
     */
    public Picture picture() {
        return this.pict;
    }

    /**
     * {width}.
     * Time complexity O(1)
     * @return     {Integer}
     */
    public int width() {
        return this.pict.width();
    }

    /**
     * {height}.
     * Time complexity is O(1)
     * @return     {Integer}
     */
    public int height() {
        return this.pict.height();
    }


    /**
     * {energy of pixel}.
     * Time complexity is O(1)
     * @param      d     {Column}
     * @param      p     {Row}
     *
     * @return     {Double}
     */
    public  double energy(final int d, final int p) {
        int wi = width() - 1, hei = height() - 1;
        if (d < 0 || d > wi || p < 0 || p > hei) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        if (d == 0 || d == wi ||  p == 0 || p == hei) {
            return BORDER_VALUE;
        }
        return internalEnergy(d, p);
    }


    /**
     * {energy of pixel at column d and row p not on border}.
     * Time complexity is O(1)
     * @param      d     {Column}
     * @param      p     {Row}
     *
     * @return     {Double}
     */
    private double internalEnergy(final int d, final int p) {
        Color left = this.pict.get(d - 1, p);
        Color right = this.pict.get(d + 1, p);
        Color up = this.pict.get(d, p - 1);
        Color down = this.pict.get(d, p + 1);
        return Math.sqrt(gradient(left, right) + gradient(up, down));
    }

    /**
     * {Method to find the gradient}.
     * Time complexity is O(1)
     * @param      one   One
     * @param      two   Two
     *
     * @return     {Double}
     */
    private double gradient(final Color one, final Color two) {
        double red = one.getRed() - two.getRed();
        double green = one.getGreen() - two.getGreen();
        double blue = one.getBlue() - two.getBlue();
        return red * red + green * green + blue * blue;
    }

    /**
     * Gets the energy matrix.
     * Time complexity is height * width
     * @return     The energy matrix.
     */
    private double[][] getEnergyMatrix() {
        double[][] energi = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energi[i][j] = energy(j, i);
            }
        }
        return energi;
    }


    /**
     * {}.
     * Time complexity height * width
     * @param      array  The array
     */
    private void topologicalSort(final double[][] array) {
        int hei = array.length;
        int wi = array[0].length;
        for (int row = 1; row < hei; row++) {
            for (int col = 0; col < wi; col++) {
                double temp = array[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, array[row - 1][col - 1]);
                }

                if (col != (wi - 1)) {
                    min = Math.min(min, array[row - 1][col + 1]);
                } else {
                    min = min;
                }
                array[row][col] += min;
            }
        }

    }
    /**
     * {Method to transpose the grid}.
     * Time complexity is height * width
     * @param      array  The array
     *
     * @return     {2-D Double Array}
     */
    private double[][] transposeGrid(final double[][] array) {
        int hei = array.length;
        int wi = array[0].length;
        double[][] tempArray = new double[wi][hei];
        for (int row = 0; row < hei; row++) {
            for (int col = 0; col < wi; col++) {
                tempArray[col][row] = array[row][col];
            }
        }
        return tempArray;
    }

    /**
     * {Method to find the minimum vertical path}.
     *
     * @param      array  The array
     *
     * @return     {1-D Integer array}
     */
    private int[] minVerticalPath(final double[][] array) {
        int hei = array.length, wi = array[0].length;
        int[] path = new int[hei];

        topologicalSort(array);

        path[hei - 1] = 0;
        for (int i = 0; i < wi; i++) {
            if (array[hei - 1][i] < array[hei - 1][path[hei - 1]]) {
                path[hei - 1] = i;
            }
        }
        for (int row = hei - 2; row >= 0; row--) {
            int col = path[row + 1];
            path[row] = col;
            if (col > 0 && array[row][col - 1] < array[row][path[row]]) {
                path[row] = col - 1;
            }
            if (col < (wi - 2) && array[row][col + 1] < array[row][path[row]]) {
                path[row] = col + 1;
            }
        }
        return path;
    }

    /**
     * {sequence of indices for horizontal seam}.
     * Time complexity is O(1)
     * @return     {1-D Integer array}
     */
    public int[] findHorizontalSeam() {
        double[][] transposeEnergies = transposeGrid(getEnergyMatrix());
        return minVerticalPath(transposeEnergies);
    }

    /**
     * {sequence of indices for vertical seam}.
     * Time complexity is O(1)
     * @return     {1-D Integer array}
     */
    public int[] findVerticalSeam() {
        double[][] normalEnergies = getEnergyMatrix();
        return minVerticalPath(normalEnergies);
    }

    /**
     * Removes a horizontal seam.
     * Time complexity is O(N ^ 2)
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        if (height() <= 1 || !isValid(seam, width(), height() - 1)) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        Picture picture1 = new Picture(width(), height() - 1);
        for (int wi = 0; wi < width(); wi++) {
            for (int hei = 0; hei < seam[wi]; hei++) {
                picture1.set(wi, hei, this.pict.get(wi, hei));
            }

            for (int hei = seam[wi] + 1; hei < height(); hei++) {
                picture1.set(wi, hei - 1, this.pict.get(wi, hei));
            }
        }
        this.pict = picture1;
    }


    /**
     * Removes a vertical seam.
     * Time complexity is O(N ^ 2)
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {
        if (width() <= 1 || !isValid(seam, height(), width())) {
            throw new java.lang.IllegalArgumentException(
                "IllegalArgumentException");
        }
        Picture picture2 = new Picture(width() - 1, height());
        for (int hei = 0; hei < height(); hei++) {
            for (int wi = 0; wi < seam[hei]; wi++) {
                picture2.set(wi, hei, this.pict.get(wi, hei));
            }
            for (int wi = seam[hei] + 1; wi < width(); wi++) {
                picture2.set(wi - 1, hei, this.pict.get(wi, hei));
            }
        }
        this.pict = picture2;
    }

    /**
     * Determines if valid.
     *
     * @param      a      {Integer array}
     * @param      len    The length
     * @param      range  The range
     * Time complexity is O(1)
     * @return     True if valid, False otherwise.
     */
    private boolean isValid(final int[] a,
                            final int len, final int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1)
                    || a[i] > Math.min(range, a[i - 1] + 1)) {
                return false;
            }
        }
        return true;
    }
}
