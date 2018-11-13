import java.util.Scanner;
/**
 * { class Solution}.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * { main function }.
     * time complexity is O(N)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        // MSD m = new MSD();
        int total = Integer.parseInt(scan.nextLine());
        String[] arr = new String[total];
        for (int i = 0; i < total; i++) {
            arr[i] = scan.nextLine();
        }
        Quick3string quick = new Quick3string();
        quick.sort(arr);
        // m.sort(arr);
        System.out.print("[");
        for (int i = 0; i < total - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[total - 1] + "]");

    }
}
