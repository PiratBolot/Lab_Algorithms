import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
/**
 * Created by Пользователь on 08.02.2017.
 */
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("aplusb.in"));
        int a = input.nextInt();
        int b = input.nextInt();
        PrintWriter writer = new PrintWriter("aplusb.out");
        int c = a + b;
        writer.write(Integer.toString(c));
        input.close();
        writer.close();
    }
}
