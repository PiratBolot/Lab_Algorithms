import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("sortland.in"));
        PrintWriter writer = new PrintWriter("sortland.out");
        String ar = input.nextLine();
        int n = Integer.valueOf(ar);
        ar = input.nextLine();
        double[] arr = new double[n];
        int j = 0;
        for (String retval : ar.split(" ", n)) {
            arr[j] = Double.valueOf(retval);
            j++;
        }
        double[] arr1 = arr.clone();
        arr1 = selectionSort(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr));
        double bed = arr1[0];
        double bog = arr1[n - 1];
        double sr = arr1[n / 2];
        for (int i = 0; i < n; i++) {
            if (arr[i] == bed) writer.write((i + 1) + " ");
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == sr) writer.write((i + 1) + " ");
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] == bog) writer.write((i + 1) + " ");
        }
        input.close();
        writer.close();
    }
 
    public static double[] selectionSort(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            double min = arr[i];
            int min_i = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                double tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        return arr;
    }
}
