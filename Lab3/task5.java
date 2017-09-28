import java.io.*;
 
public class Pyramid {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("isheap.in"));
        PrintWriter writer = new PrintWriter(new File("isheap.out"));
        int sizeArray = Integer.parseInt(reader.readLine());
 
        String[] strings = reader.readLine().split(" ");
        int[] numbers = new int[sizeArray];
 
        for (int i = 0; i < sizeArray; i++) {
            numbers[i] = Integer.parseInt(strings[i]);
        }
        int countIter = sizeArray >> 1;
        boolean flag = true;
        for (int i = 1; i <= countIter; i++) {
            if (!(numbers[i - 1] <= numbers[2 * i - 1])) {
                flag = false;
                break;
            }
            if ((2 * i + 1 <= sizeArray)) {
                if (!(numbers[i - 1] <= numbers[2 * i])) {
                    flag = false;
                }
            }
        }
        if (flag) {
            writer.write("YES");
        } else {
            writer.write("NO");
        }
 
        reader.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new Pyramid().run();
    }
}
