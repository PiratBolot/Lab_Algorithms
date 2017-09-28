import java.io.*;
 
public class PriorityQueue {
 
    int[] pusha = new int[1000000];
    int position = 0;
 
    private  void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("priorityqueue.in"));
        PrintWriter writer = new PrintWriter(new File("priorityqueue.out"));
        Queue queue = new Queue(1000000);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] operation = line.split(" ");
            if (operation[0].equals("push")) {
                queue.insert(Integer.parseInt(operation[1]));
                pusha[position] = queue.rear;
            } else if (operation[0].equals("extract-min")) {
                if (queue.size <= 0) {
                    writer.write("*\n");
                } else {
                    writer.write(Integer.toString(queue.getMin()) + "\n");
                }
            } else {
                queue.decrease_key(pusha[Integer.parseInt(operation[1]) - 1], Integer.parseInt(operation[2]));
                pusha[position] = pusha[Integer.parseInt(operation[1])];
            }
            position++;
        }
        reader.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new PriorityQueue().run();
    }
 
    static class Queue {
 
        private int[] queue;
        private int maxSize;
        private int front;
        private int rear;
        private  int size;
 
        public Queue(int maxSize) {
            this.maxSize = maxSize;
            queue = new int[maxSize];
            rear = -1;
            front = 0;
            size = 0;
        }
 
        public int getMin() {
            int min = 2000000000;
            int pos = 0;
            for (int i = front; i <= rear; i++) {
                if (queue[i] < min) {
                    min = queue[i];
                    pos = i;
                }
            }
            queue[pos] = 2000000001;
            size--;
            return min;
        }
 
        public void insert(int elem) {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            size++;
            queue[++rear] = elem;
        }
 
        public void decrease_key(int i, int value) {
            queue[i] = value;
        }
    }
}
