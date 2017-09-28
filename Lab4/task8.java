import java.io.*;
import java.util.HashMap;
import java.util.Map;
 
public class Quack {
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("quack.in"));
        PrintWriter writer = new PrintWriter(new File("quack.out"));
 
        Map<String, Integer> markers = new HashMap<>();
        String[] source = new String[100000];
        Queue queue = new Queue(100000);
        int[] registers = new int[26];
 
        String line;
        int count = -1;
        while ((line = reader.readLine()) != null) {
            count++;
            source[count] = line;
            if (source[count].charAt(0) == ':') {
                markers.put(line, count);
            }
        }
 
        int i = -1;
        while (i < count){
            i++;
            if (source[i].compareTo("+") == 0) {
                queue.insert((queue.remove() + queue.remove()) & 65535);
            } else if (source[i].compareTo("-") == 0) {
                queue.insert((queue.remove() - queue.remove()) & 65535);
            } else if (source[i].compareTo("*") == 0) {
                queue.insert((queue.remove() * queue.remove()) & 65535);
            } else if (source[i].compareTo("/") == 0) {
                int temp = queue.remove();
                int temp1 = queue.remove();
                if (temp1 == 0 && temp == 0) {
                    queue.insert(0);
                } else {
                    queue.insert((temp / temp1) & 65535);
                }
            } else if (source[i].compareTo("P") == 0) {
                writer.write(Integer.toString(queue.remove()) + "\n");
            } else if (source[i].compareTo("%") == 0) {
                int temp = queue.remove();
                int temp1 = queue.remove();
                if (temp1 == 0 && temp == 0) {
                    queue.insert(0);
                } else {
                    queue.insert((temp % temp1) & 65535);
                }
            } else if (source[i].compareTo("C") == 0) {
                writer.write((char) (queue.remove() % 255));
            } else if (source[i].compareTo("Q") == 0) {
                break;
            } else if (source[i].charAt(0) == '>') {
                registers[source[i].charAt(1) - 97] = queue.remove();
            } else if (source[i].charAt(0) == '<') {
                queue.insert(registers[source[i].charAt(1) - 97]);
            } else if (source[i].charAt(0) == 'P') {
                writer.write(registers[source[i].charAt(1) - 97] + "\n");
            } else if (source[i].charAt(0) == 'C') {
                writer.write((char) registers[source[i].charAt(1) - 97]);
            } else if (source[i].charAt(0) == 'J') {
                i = markers.get(":" + source[i].substring(1));
            } else if (source[i].charAt(0) == 'Z') {
                if (registers[source[i].charAt(1) - 97] == 0) {
                    i = markers.get(":" + source[i].substring(2));
                }
            } else if (source[i].charAt(0) == 'E') {
                if (registers[source[i].charAt(1) - 97] == registers[source[i].charAt(2) - 97]) {
                    i = markers.get(":" + source[i].substring(3));
                }
            } else if (source[i].charAt(0) == 'G') {
                if (registers[source[i].charAt(1) - 97] > registers[source[i].charAt(2) - 97]) {
                    i = markers.get(":" + source[i].substring(3));
                }
 
            } else if (source[i].charAt(0) != ':'){
                queue.insert(Integer.parseInt(source[i]));
            }
        }
        writer.close();
        reader.close();
    }
 
    public static void main(String[] args) throws IOException {
        new Quack().run();
    }
 
    static class Queue {
 
        private int[] queue;
        private int maxSize;
        private int front;
        private int rear;
 
 
        public Queue(int maxSize) {
            this.maxSize = maxSize;
            queue = new int[maxSize];
            rear = -1;
            front = 0;
        }
 
        private void insert(int elem) {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queue[++rear] = elem;
        }
 
        private int remove() {
            int temp = queue[front++];
            return temp;
        }
    }
}
