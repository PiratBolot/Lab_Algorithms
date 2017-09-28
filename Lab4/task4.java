import java.io.*;
import java.lang.*;
 
public class Search {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("binsearch.in"));
        PrintWriter writer = new PrintWriter(new File("binsearch.out"));
        int n = Integer.parseInt(reader.readLine());
        int[] array = new int[n];
        String[] numbers = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        int m = Integer.parseInt(reader.readLine());
        numbers = reader.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            int number = Integer.parseInt(numbers[i]);
            writer.write(Integer.toString(lower_bound(array, number)) + " " + Integer.toString(upper_bound(array, number)) + "\n");
        }
        reader.close();
        writer.close();
    }
 
    private int upper_bound(int[] array, int number)
    {
        int l = 0, r = array.length - 1;
        while (l < r)
        {
            int m = r - (r - l)/2;
            if (array[m] <= number)
                l = m;
            else
                r = m - 1;
        }
        return (array[l] == number) ? (l + 1) : -1;
    }
 
    private int lower_bound(int[] array, int number) {
        int l = 0, r = array.length - 1;
        while (l < r)
        {
            int m = l + (r - l) / 2;
            if (array[m] >= number)
                r = m;
            else
                l = m + 1;
        }
        return (array[l] == number) ? (l + 1) : -1;
    }
 
    public static void main(String[] args) throws IOException {
        new Search().run();
    }
 
}
