import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
 
public class B {
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("race.in"));
        PrintWriter writer = new PrintWriter(new File("race.out"));
        int n = input.nextInt();
        String stage = new String();
        String surname = new String();
        Map<String, ArrayList<String>> sort = new TreeMap<String, ArrayList<String>>();
        for (int i = 0; i < n; i++) {
            stage = input.next();
            surname= input.next();
            if (sort.containsKey(stage)) {
                sort.get(stage).add(surname);
            } else {
                sort.put(stage, new ArrayList<String>());
                sort.get(stage).add(surname);
            }
        }
 
        for (Map.Entry<String, ArrayList<String>> entry : sort.entrySet()) {
            writer.write("=== " + entry.getKey() + " ===\n");
            for (String str : entry.getValue()) {
                writer.write(str + "\n");
            }
        }
        input.close();
        writer.close();
    }
}
