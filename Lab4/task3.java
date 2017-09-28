import java.io.*;
 
public class BinarySearchTree {
 
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("bstsimple.in"));
        PrintWriter writer = new PrintWriter(new File("bstsimple.out"));
        Tree<Integer> tree = new Tree<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] operation = line.split(" ");
            if (operation[0].equals("insert")) {
                if (!tree.exist(Integer.parseInt(operation[1]))) {
                    tree.add(Integer.parseInt(operation[1]));
                }
            } else if (operation[0].equals("delete")) {
                tree.delete(Integer.parseInt(operation[1]));
            } else if (operation[0].equals("exists")) {
                writer.println(Boolean.toString(tree.exist(Integer.parseInt(operation[1]))));
            } else if (operation[0].equals("next")) {
                Integer integer = tree.next(Integer.parseInt(operation[1]));
                if (integer.compareTo(Integer.parseInt(operation[1])) == 0) {
                    writer.println("none");
                } else {
                    writer.println(Integer.toString(integer));
                }
            } else {
                Integer integer = tree.prev(Integer.parseInt(operation[1]));
                if (integer.compareTo(Integer.parseInt(operation[1])) == 0) {
                    writer.println("none");
                } else {
                    writer.println(Integer.toString(integer));
                }
            }
        }
        reader.close();
        writer.close();
    }
}
 
class Tree<T extends Comparable<T>> {
    private Node<T> root = null;
 
    void add(T k) {
        Node<T> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.getValue());
            if (cmp == 0) {
                return;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        Node<T> newNode = new Node<T>(null, null, k);
        if (y == null) {
            root = newNode;
        } else {
            if (k.compareTo(y.getValue()) < 0) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
        }
    }
 
    public boolean exist(T k) {
 
        Node<T> x = root;
        while (x != null) {
            int cmp = k.compareTo(x.getValue());
            if (cmp == 0) {
                return true;
            }
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return false;
    }
 
    public T next(T value) {
        Node<T> result = new Node<T>(null, null, value);
        return next(root, value, result);
    }
 
    private T next(Node<T> root, T value, Node<T> result) {
        if (root == null) {
            return result.getValue();
        }
        if (root.getValue().compareTo(value) <= 0) {
            next(root.getRight(), value, result);
        } else {
            result.changeValue(root.getValue());
            next(root.getLeft(), value, result);
        }
        return result.getValue();
    }
 
    public T prev(T value) {
        Node<T> result = new Node<>(null, null, value);
        return prev(root, value, result);
    }
 
    private T prev(Node<T> root, T value, Node<T> result) {
        if (root == null) {
            return result.getValue();
        }
        if (root.getValue().compareTo(value) >= 0) {
            prev(root.getLeft(), value, result);
        } else {
            result.changeValue(root.getValue());
            prev(root.getRight(), value, result);
        }
        return result.getValue();
    }
 
    public void delete(T k) {
        Node<T> x = root, y = null;
        while (x != null) {
            int cmp = k.compareTo(x.getValue());
            if (cmp == 0) {
                break;
            } else {
                y = x;
                if (cmp < 0) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else {
                if (x == y.left) {
                    y.left = x.left;
                } else {
                    y.right = x.left;
                }
            }
        } else {
            Node<T> leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.changeValue(leftMost.getValue());
            x.changeValue(leftMost.value);
        }
    }
 
    private static class Node<T extends Comparable<T>> implements Comparable<T> {
        private Node<T> left;
        private Node<T> right;
        private T value;
 
        public Node(Node<T> left, Node<T> right, T value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
 
        public Node<T> getLeft() {
            return left;
        }
 
        public Node<T> getRight() {
            return right;
        }
 
        public T getValue() {
            return value;
        }
 
        public void changeValue(T value) {
            this.value = value;
        }
 
        public int compareTo(T o) {
            return o.compareTo(value);
        }
    }
}
