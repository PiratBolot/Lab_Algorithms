import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("smallsort.in"));
        int a = input.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = input.nextInt();
        }
        arr = selectionSort(arr);
        PrintWriter writer = new PrintWriter("smallsort.out");
        for (int i = 0; i < a; i++) {
            writer.write(arr[i] + " ");
        }
        input.close();
        writer.close();
    }
 
    public static int[] selectionSort(int[] arr){
 
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int min_i = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }
            if (i != min_i) {
                int tmp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = tmp;
            }
        }
        return arr;
    }
}
