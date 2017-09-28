import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
/**
 * Created by Пользователь on 08.02.2017.
 */
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("aplusbb.in"));
        long a = input.nextLong();
        long b = input.nextLong();
        PrintWriter writer = new PrintWriter("aplusbb.out");
        long c = a + b * b;
        writer.write(Long.toString(c));
        input.close();
        writer.close();
    }
}
