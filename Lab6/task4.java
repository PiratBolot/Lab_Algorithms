import java.io.*;
 
public class Connected_component {
 
    boolean[] used;
    int componentCount = 0;
    int[] vertexAccessory;
    ArrayList<Integer> comp = new ArrayList<>();
    ArrayList<Integer>[] lists;
 
    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("components.in"));
        PrintWriter writer = new PrintWriter(new File("components.out"));
 
        String[] data = reader.readLine().split(" ");
        int vertexCount = Integer.parseInt(data[0]);
        int edgeCount = Integer.parseInt(data[1]);
        vertexAccessory = new int[vertexCount];
        used = new boolean[vertexCount];
        lists = new ArrayList[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            lists[i] = new ArrayList();
        }
        for (int i = 0; i < edgeCount; i++) {
            data = reader.readLine().split(" ");
            lists[Integer.parseInt(data[0]) - 1].add(Integer.parseInt(data[1]) - 1);
            lists[Integer.parseInt(data[1]) - 1].add(Integer.parseInt(data[0]) - 1);
        }
        find_comps(vertexCount);
        writer.write(Integer.toString(componentCount) + "\n");
        for (int i = 0; i < vertexCount; i++) {
            writer.write(Integer.toString(vertexAccessory[i]) + " ");
        }
        reader.close();
        writer.close();
    }
 
    void dfs (int v) {
        used[v] = true;
        comp.add(v);
        for (int i = 0; i < lists[v].size(); i++) {
            int to = lists[v].get(i);
            if (!used[to]) {
                dfs(to);
            }
        }
    }
 
    void find_comps(int vertexCount) {
        for (int i = 0; i < vertexCount; i++) {
            if (!used[i]) {
                componentCount++;
                comp.clear();
                dfs (i);
                for (int j = 0; j < comp.size(); j++) {
                    vertexAccessory[comp.get(j)] = componentCount;
                }
            }
        }
    }
 
    class ArrayList<E> {
        int length;
        int sizeOf;
        E[] array;
 
        public ArrayList() {
            array = (E[]) new Object[10];
            length = 0;
            sizeOf = 10;
        }
 
        public int size() {
            return length;
        }
 
        public E get(int index) {
            return array[index];
        }
 
        public boolean add(E e) {
            ensureCapacity();
            array[length++] = e;
            return false;
        }
 
        public void clear() {
            for (int i = 0; i < length; i++) {
                array[i] = null;
            }
            length = 0;
        }
 
        public void ensureCapacity() {
            if (length + 1 == sizeOf) {
                Object[] oldData = array;
                sizeOf = (sizeOf * 3) / 2 + 1;
                array = (E[]) new Object[sizeOf];
                System.arraycopy(oldData, 0, array, 0, length);
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        new Connected_component().run();
    }
}
