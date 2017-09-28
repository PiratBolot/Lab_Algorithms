import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class C {
 
    private long count = 0;
    private int[] temp;
    private int[] a;
 
    private void merge(int l, int m, int r) {
        int i = l;
        int j = m;
        for (int k = l; k < r; k++) {
            if (j == r || i < m && a[i] <= a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                count += m - i;
                temp[k] = a[j];
                j++;
            }
        }
        System.arraycopy(temp, l, a, l, r - l);
    }
 
    private void mergeSort(int l, int r) {
        if (r <= l + 1) return;
        int m = (l + r) >> 1;
        mergeSort(l, m);
        mergeSort(m, r);
        merge(l, m, r);
    }
 
    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("inversions.in"));
        PrintWriter writer = new PrintWriter(new File("inversions.out"));
        int n = input.nextInt();
        a = new int[n];
        temp = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        mergeSort(0, n);
        writer.write(Long.toString(count));
        input.close();
        writer.close();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new C().run();
    }
}
