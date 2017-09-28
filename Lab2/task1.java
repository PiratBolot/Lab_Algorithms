import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class A {
 
    private static int[] Temp;
    private static int[] Array;
 
    private static void merge(int l, int m, int r) {
        int i = l;
        int j = m;
        for (int k = l; k < r; k++) {
            if (j == r || (i < m && Array[i] <= Array[j])) {
                Temp[k] = Array[i];
                i++;
            } else {
                Temp[k] = Array[j];
                j++;
            }
        }
        System.arraycopy(Temp, l, Array, l, r - l);
    }
 
    private static void mergeSort(int l, int r) {
        if (r <= l + 1) return;
        int m = (l + r) >> 1;
        mergeSort(l, m);
        mergeSort(m, r);
        merge(l, m, r);
    }
 
    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("sort.in"));
        PrintWriter writer = new PrintWriter(new File("sort.out"));
        int n = input.nextInt();
        Array = new int[n];
        for (int i = 0; i < n; i++) {
            Array[i] = input.nextInt();
        }
        Temp = new int[n];
        mergeSort(0, n);
        for (int i = 0; i < n; i++) {
            writer.write(Integer.toString(Array[i]) + " ");
        }
        input.close();
        writer.close();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new A().run();
    }
}
