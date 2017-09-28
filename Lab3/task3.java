import java.io.*;
 
class Stack {
    private int top;
    private char[] stackArray;
 
    public Stack(int size) {
        stackArray = new char[size];
        top = -1;
    }
 
    public void push(char symbol) {
        stackArray[++top] = symbol;
    }
 
    public char pop() {
        return stackArray[top--];
    }
 
    public char peek() {
        if (top != -1) {
            return stackArray[top];
        }
        return '+';
    }
 
    public boolean empty() {
        return top == -1;
    }
}
 
 
public class BracketSequence {
 
    final int SIZE = 10000;
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("brackets.in"));
        PrintWriter writer = new PrintWriter(new File("brackets.out"));
        String line;
        int condition;
 
        while ((line = reader.readLine()) != null) {
            Stack stack = new Stack(SIZE);
            condition = 1;
            for (char ch : line.toCharArray()) {
                if (ch == '(' || ch == '[') {
                    stack.push(ch);
                } else if (ch == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                        continue;
                    } else {
                        condition = 0;
                        break;
                    }
                } else {
                    if (stack.peek() == '[') {
                        stack.pop();
                        continue;
                    } else {
                        condition = 0;
                        break;
                    }
                }
            }
            if (!stack.empty() || condition == 0) {
                writer.write("NO\n");
            } else {
                writer.write("YES\n");
            }
        }
        reader.close();
        writer.close();
    }
 
    public static void main(String[] args) throws IOException {
        new BracketSequence().run();
    }
}
