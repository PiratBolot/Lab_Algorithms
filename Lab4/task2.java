import java.io.*;
 
public class CorrectSearchTree {
 
    private  boolean[] used = {false};
    boolean result = true;
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("check.in"));
        PrintWriter writer = new PrintWriter(new File("check.out"));
        int n = Integer.parseInt(reader.readLine());
        int[][] information = new int[n][3];
        String[] tokens;
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                information[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        used = new boolean[n];
        if (n == 0) {
            writer.write("YES");
        } else {
            getTreeHeight(information, 0, -2000000000, 2000000000, 0);
            if (result) {
                writer.write("YES");
            } else {
                writer.write("NO");
            }
        }
        reader.close();
        writer.close();
    }
 
    private void getTreeHeight(int[][] information, int numberTop, int min, int max, int relationShip) {
        if (used[numberTop]) {
            return;
        }
        used[numberTop] = true;
        if (!(information[numberTop][0] > min && information[numberTop][0] < max)) {
            result = false;
        }
        if (information[numberTop][1] != 0) {
            getTreeHeight(information, information[numberTop][1] - 1, min, information[numberTop][0], 0);
        }
        if (information[numberTop][2] != 0) {
            getTreeHeight(information, information[numberTop][2] - 1, information[numberTop][0], max, 1);
        }
    }
 
    public static void main(String[] args) throws IOException {
       new CorrectSearchTree().run();
    }
}
