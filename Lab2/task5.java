import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class OrderStatistic {
 
    public static int i = 0, j = 0;
 
    private int median(int[] numbers, int x, int y, int z) {
        int key = Math.max(Math.max(numbers[x], numbers[y]), numbers[z]);
        if (key == numbers[x]) {
            if (numbers[y] > numbers[z])
                return y;
            return z;
        } else if (key == numbers[y]) {
            if (numbers[x] > numbers[z])
                return x;
            return z;
        } else {
            if (numbers[x] > numbers[y])
                return x;
            return y;
        }
    }
 
    private void swap(int[] numbers, int a, int b) {
        int temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }
 
    private void partition(int[] numbers, int l, int r) {
        int median = median(numbers, l, (l + r) >> 1, r);
        swap(numbers, median, r);
        int p = l - 1, q = r; int v = numbers[r];
        i = l - 1;
        j = r;
        for (;;)
        {
            while (numbers[++i] < v) ;
            while (v < numbers[--j]) if (j == l) break;
            if (i >= j) break;
            swap(numbers, i, j);
            if (numbers[i] == v) { p++; swap(numbers, p, i); }
            if (v == numbers[j]) { q--; swap(numbers, j, q); }
        }
        swap(numbers, i, r); j = i - 1; i++;
        for (int k = l; k < p; k++, j--) swap(numbers, k, j);
        for (int k = r - 1; k > q; k--, i++) swap(numbers, i, k);
    }
 
    private int findOrderStatistic(int[] a, int l, int r, int k) {
        if (r <= l) {
            return a[l];
        }
        partition(a, l, r);
        if (j + 1 <= k && k <= i - 1) {
            return a[j + 1];
        } else if (l <= k && k <= j) {
            return findOrderStatistic(a, l, j, k);
        } else {
            return findOrderStatistic(a, i, r, k);
        }
    }
 
    private void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("kth.in"));
        PrintWriter writer = new PrintWriter(new File("kth.out"));
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int C = scanner.nextInt();
        int[] numbers = new int[n];
        if (n == 1) {
            numbers[0] = scanner.nextInt();
        } else {
            numbers[0] = scanner.nextInt();
            numbers[1] = scanner.nextInt();
            for (int i = 2; i < n; i++) {
                numbers[i] = A * numbers[i - 2] + B * numbers[i - 1] + C;
            }
        }
        int result = findOrderStatistic(numbers, 0, n - 1, k - 1);
        writer.write(Integer.toString(result));
        scanner.close();
        writer.close();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new OrderStatistic().run();
    }
}
