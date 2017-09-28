import java.io.*;
 
public class AdjacencyList {
 
    BufferedReader reader;
    PrintWriter writer;
 
    private void run() throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new PrintWriter(new File("output.txt"));
        String[] data = reader.readLine().split(" ");
        int matrixSize = Integer.parseInt(data[0]);
        int edgesCount = Integer.parseInt(data[1]);
        Matrix sub = new Matrix(matrixSize);
        for (int i = 0; i < edgesCount; i++) {
            data = reader.readLine().split(" ");
            sub.setEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        }
        sub.showMatrix();
        reader.close();
        writer.close();
    }
 
    class Matrix {
        int[][] matrix;
        int size;
 
        public Matrix(int size) {
            matrix = new int[size][size];
            this.size = size;
        }
 
        public void setEdge(int row, int column) {
            matrix[row - 1][column - 1] = 1;
        }
 
        public void showMatrix() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(Integer.toString(matrix[i][j]) + " ");
                }
                writer.write("\n");
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        new AdjacencyList().run();
    }
}
