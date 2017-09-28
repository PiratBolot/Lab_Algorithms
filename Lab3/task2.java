import java.io.*;
 
class FastScanner implements Closeable {
    private BufferedReader reader;
 
    private int currTokenPos;
    private String[] currTokens;
    private String currLine;
 
    String splitPattern = " ";
 
    public FastScanner(String path, String splitPattern) throws FileNotFoundException {
        this(path);
        this.splitPattern = splitPattern;
    }
 
    public FastScanner(String path) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(path));
    }
 
    public int nextInt() throws IOException {
        checkCurrLine();
        return Integer.parseInt(currTokens[currTokenPos++]);
    }
 
    public char nextChar() throws IOException {
        checkCurrLine();
        return currTokens[currTokenPos++].charAt(0);
    }
 
    public String nextLine() throws IOException {
        return reader.readLine();
    }
 
    public String[] getTokens() {
        return currTokens;
    }
 
    public void checkCurrLine() throws IOException {
        if (currLine == null || currTokens.length == currTokenPos) {
            currLine = nextLine();
            currTokens = currLine.split(splitPattern);
            currTokenPos = 0;
        }
    }
 
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
 
class Queue {
 
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
 
public class QueueAlgorithm {
 
    private  void run() throws IOException {
        FastScanner scanner = new FastScanner("queue.in");
        PrintWriter writer = new PrintWriter(new File("queue.out"));
        int countOperations = scanner.nextInt();
        Queue queue = new Queue(countOperations);
        for (int i = 0; i < countOperations; i++) {
            char symbol = scanner.nextChar();
            if (symbol == '+') {
                int number = scanner.nextInt();
                queue.insert(number);
            } else {
                writer.write(Integer.toString(queue.remove()) + "\n");
            }
        }
        scanner.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new QueueAlgorithm().run();
    }
}
