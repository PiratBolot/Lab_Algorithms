import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
 
public class Solution {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("sort.in"));
        PrintWriter writer = new PrintWriter(new File("sort.out"));
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        HeapUtils.sort(array);
        for (int i = 0; i < n; i++) {
            writer.write(Integer.toString(array[i]) + " ");
        }
        scanner.close();
        writer.close();
    } 
}
 
class HeapUtils {
 
    public static void siftDown(int[] array, int size, int pos) {
        while (2 * pos + 1 < size) {
            int t = 2 * pos + 1;
            if (2 * pos + 2 < size && array[2 * pos + 1] < array[2 * pos + 2]) {
                t = 2 * pos + 2;
            }
            if (array[pos] < array[t]) {
                swap(array, pos, t);
                pos = t;
            } else {
                break;
            }
        }
    }
 
    public static int[] heapMake(int[] array) {
        int n = array.length;
        for (int i = n - 1; i >= 0; i--) {
            siftDown(array, n, i);
        }
        return array;
    }
 
    public static void sort(int[] array) {
        int n = array.length;
        heapMake(array);
        while (n > 0) {
            swap(array, 0, n - 1);
            n--;
            siftDown(array, n, 0);
        }
    }
 
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
