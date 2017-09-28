import java.io.*;
import java.util.ArrayList;
 
public class Native_Searching {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("search1.in"));
        PrintWriter writer = new PrintWriter("search1.out");
        String substring = reader.readLine();
        int matchingCount = 0;
        ArrayList<Integer> entry = new ArrayList<>();
        int subStringLength = substring.length();
        String string = reader.readLine();
        int stringLength = string.length();
        int i = 0;
        while (i < stringLength) {
            int j = 0;
            while (j < subStringLength && i + j < stringLength && substring.charAt(j) == string.charAt(i + j)) {
                j++;
            }
            if (j == subStringLength) {
                matchingCount++;
                entry.add(i + 1);
            }
            i++;
        }
        writer.println(matchingCount);
        for (int element : entry.subList(0, matchingCount)) {
            writer.write(element + " ");
        }
        writer.close();
        reader.close();
    }
 
    public static void main(String[] args) throws IOException {
        new Native_Searching().run();
 
    }
}
