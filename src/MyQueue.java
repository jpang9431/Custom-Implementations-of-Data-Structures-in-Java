public class MyQueue<T> {
    private final static String seperator = "------------------------------------";
    private MyLinkedList<T> list = new MyLinkedList<T>();

    public boolean add(T data) {
        return list.add(data);
    }

    public T element() {
        return list.get(0);
    }

    public boolean offer(T data) {
        return list.add(data);
    }

    public T peek() {
        return list.get(0);
    }

    public T poll() {
        return list.remove(0);
    }

    public T remove() {
        return list.remove(0);
    }

    public int size() {
        return list.size();
    }

    public void print() {
        list.print();
    }

    public static void main(String[] args) {
        MyQueue<Integer> testQueue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            testQueue.offer(i);
        }
        System.out.println("Expected: 0,1,2,3,4,5,6,7,8,9");
        testQueue.print();
        System.out.println(seperator);
        System.out.println("Expected: 10");
        System.out.println(testQueue.size());
        System.out.println(seperator);
        System.out.println("Expected: 0");
        System.out.println(testQueue.peek());
        System.out.println(seperator);
        System.out.println("Expected: 0");
        System.out.println(testQueue.poll());
        System.out.println(seperator);
        for (int i = 0; i < 9; i++) {
            testQueue.poll();
        }
        System.out.println("Expected: 0");
        System.out.println(testQueue.size());
        System.out.println(seperator);
        System.out.println("Expected: null");
        System.out.println(testQueue.poll());

    }
}
