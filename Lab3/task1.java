import java.io.*;
import java.util.Scanner;
 
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
 
class Stack {
    private int top;
    private int[] stackArray;
 
    public Stack(int size) {
        stackArray = new int[size];
        top = -1;
    }
 
    public void push(int number) {
        stackArray[++top] = number;
    }
 
    public int pop() {
        return stackArray[top--];
    }
}
 
public class MainStack {
 
    static void run() throws IOException {
        FastScanner scanner = new FastScanner("stack.in");
        PrintWriter writer = new PrintWriter(new File("stack.out"));
        int countOperations = scanner.nextInt();
        Stack stack = new Stack(countOperations);
        for (int i = 0; i < countOperations; i++) {
            char operation = scanner.nextChar();
            if (operation == '+') {
                int number = scanner.nextInt();
                stack.push(number);
            } else {
                writer.write(Integer.toString(stack.pop()) + "\n");
            }
        }
        scanner.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new MainStack().run();
    }
}
