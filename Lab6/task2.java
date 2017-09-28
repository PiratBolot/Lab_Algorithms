import java.io.*;
 
public class Check_Of_Undirected_Graph {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new File("output.txt"));
        int matrixSize = Integer.parseInt(reader.readLine());
        String[][] matrix = new String[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            matrix[i] = reader.readLine().split(" ");
        }
        if (checkMatrix(matrix, matrixSize)) {
            writer.write("YES");
        } else {
            writer.write("NO");
        }
        reader.close();
        writer.close();
    }
 
    public boolean checkMatrix(String[][] matrix, int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (i == j && matrix[i][i].compareTo("1") == 0) {
                    return false;
                } else if (i != j && matrix[i][j].compareTo(matrix[j][i]) != 0) {
                    return  false;
                }
            }
        }
        return true;
    }
 
    public static void main(String[] args) throws IOException {
        new Check_Of_Undirected_Graph().run();
    }
}
