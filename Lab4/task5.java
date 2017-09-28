import java.io.*;
 
public class Garland {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("garland.in"));
        PrintWriter writer = new PrintWriter(new File("garland.out"));
        String[] arg = reader.readLine().split(" ");
        int number = Integer.parseInt(arg[0]);
        double height = Double.parseDouble(arg[1]);
        double floor = 0;
        double ceil = height;
 
        while (true) {
            double mid = (floor + ceil) / 2;
            if (mid == floor || mid == ceil) {
                break;
            }
            if (isAboveGround(number, height, mid)) {
                ceil = mid;
            } else {
                floor = mid;
            }
        }
        for (int i = 3; i<= number; i++) {
            double temp = ceil;
            ceil = 2 * (ceil + 1) - height;
            height = temp;
        }
        writer.printf("%.2f", ceil);
        reader.close();
        writer.close();
    }
 
    private boolean isAboveGround(int number, double penultimate, double previous) {
        for (int i = 3; i <= number; i++) {
            double temp = previous;
            previous = 2 * (previous + 1) - penultimate;
            penultimate = temp;
            if (previous < 0) {
                return false;
            }
        }
        return true;
    }
 
    public static void main(String[] args) throws IOException {
        new Garland().run();
    }
}
