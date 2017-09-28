import java.io.*;
 
public class HeightTree {
 
    private  boolean[] used = {false};
    private static int heightMax = 0;
 
    private  void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("height.in"));
        PrintWriter writer = new PrintWriter(new File("height.out"));
        int n = Integer.parseInt(reader.readLine());
        int[][] information = new int[n][4];
        String[] tokens;
        for (int i = 0; i < n; i++) {
            tokens = reader.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                information[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        used = new boolean[n];
        if (n == 0) {
            writer.write(Integer.toString(0));
        } else {
            getTreeHeight(information, 0 , 0);
            writer.write(Integer.toString(heightMax));
        }
        reader.close();
        writer.close();
    }
 
    private void getTreeHeight(int[][] information, int numberTop, int localHeight) {
        if (used[numberTop]) {
            return;
        }
        localHeight++;
        if (localHeight > heightMax) {
            heightMax = localHeight;
        }
        used[numberTop] = true;
 
        if (information[numberTop][1] != 0) {
           getTreeHeight(information, information[numberTop][1] - 1, localHeight);
        }
        if (information[numberTop][2] != 0) {
            getTreeHeight(information, information[numberTop][2] - 1, localHeight);
        }
    }
 
    public static void main(String[] args) throws IOException {
        new HeightTree().run();
    }
}
