import java.io.*;
 
public class QueueAlg {
 
    private  void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("queuemin.in"));
        PrintWriter writer = new PrintWriter(new File("queuemin.out"));
        int n = Integer.parseInt(reader.readLine());
        Queue queue = new Queue(n);
        for (int i = 0; i < n; i++) {
            String[] operation = reader.readLine().split(" ");
            if (operation[0].equals("+")) {
                queue.insert(Integer.parseInt(operation[1]));
            } else if (operation[0].equals("-")) {
                queue.remove();
            } else {
                writer.write(Integer.toString(queue.getMin()) + "\n");
            }
        }
        reader.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new QueueAlg().run();
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
 
        public int getMin() {
            int min = 2000000000;
            for (int i = front; i <= rear; i++) {
                if (queue[i] < min) {
                    min = queue[i];
                }
            }
            return min;
        }
 
        public void insert(int elem) {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queue[++rear] = elem;
        }
 
        public int remove() {
            int temp = queue[front++];
            if (front == maxSize) {
                front = 0;
            }
            return temp;
        }
    }
}
