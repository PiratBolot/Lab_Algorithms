import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 

public class D {
 
    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("antiqs.in"));
        PrintWriter writer = new PrintWriter(new File("antiqs.out"));
        int n = input.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < n; i++) {
            int temp = array[i];
            array[i] = array[i / 2];
            array[i / 2] = temp;
        }
        for (int i = 0; i < n; i++) {
            writer.write(Integer.toString(array[i]) + " ");
        }
        input.close();
        writer.close();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new D().run();
    }
}
