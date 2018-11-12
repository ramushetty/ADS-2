import java.util.Scanner;
public final class Solution {
    private Solution() {
        //empty.
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        MSD m = new MSD();
        int total = Integer.parseInt(scan.nextLine());
        String[] arr = new String[total];
        for (int i = 0 ; i < total ; i++) {
            arr[i] = scan.nextLine();
        }
        // Quick3string quick = new Quick3string();
        // quick.sort(arr);
        m.sort(arr);
        for (int i = 0; i < total; i++) {
            System.out.println(arr[i]);
        }

    }
}