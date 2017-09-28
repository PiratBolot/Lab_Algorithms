import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
 
public class Lab6 {
 
    int bitsbyte = 8;
    int bytesword;
    int R = 1 << bitsbyte;
    String[] strings;
 
    void radixLSD(int countStages, int countNumbers)
    {
        String[] aux = new String[countNumbers];
        for (int d = bytesword - 1; countStages > 0; d--, countStages--)
        {
            int[] count = new int[R + 1];
            for (int j = 0; j < R; j++) {
                count[j] = 0;
            }
            for (int i = 0; i < countNumbers ; i++) {
                count[digit(i, d) + 1]++;
            }
            for (int j = 1; j < R; j++) {
                count[j] += count[j - 1];
            }
            for (int i = 0; i < countNumbers; i++) {
                aux[count[digit(i, d)]++] = strings[i];
            }
            for (int i = 0; i < countNumbers; i++) {
                strings[i] = aux[i];
            }
        }
    }
 
    private int digit(int i, int B) {
        return strings[i].charAt(B);
    }
 
    private void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("radixsort.in"));
        PrintWriter writer = new PrintWriter(new File("radixsort.out"));
        int countNumbers = scanner.nextInt();
        int lengthWord = scanner.nextInt();
        int countStages = scanner.nextInt();
        strings = new String[countNumbers];
        scanner.nextLine();
        for (int i = 0; i < countNumbers; i++) {
            strings[i] = scanner.nextLine();
        }
        bytesword = lengthWord;
        radixLSD(countStages, countNumbers);
        for (int i = 0; i < countNumbers; i++) {
            writer.write(strings[i] + "\n");
        }
        scanner.close();
        writer.close();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new Lab6().run();
    } 
}
