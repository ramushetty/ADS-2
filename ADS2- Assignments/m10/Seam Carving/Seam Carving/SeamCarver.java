
public class SeamCarver {
	// create a seam carver object based on the given picture
	Picture pic;
	int wid;
	int hei;
	public SeamCarver(Picture picture) {
		pic =  picture;
		wid = picture.width();
		hei  = picture.height();
	}
	// current picture
	public Picture picture() {
		return null;
	}
	// width of current picture
	public int width() {
		return wid;
	}

	// height of current picture
	public int height() {
		return hei;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || y == 0) {
			return 1000;
		}
		else {
			double rgb = Math.sqrt(pic.getRGB(x, y));
			System.out.println(rgb);
		}
		return 0;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}