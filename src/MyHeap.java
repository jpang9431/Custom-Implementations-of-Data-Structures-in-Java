import java.util.ArrayList;

public class MyHeap<T extends Comparable<T>> {
    private ArrayList<T> heap = new ArrayList<T>();
    private boolean isMaxHeap = true;

    MyHeap() {
    };

    MyHeap(boolean setHeapType) {
        isMaxHeap = setHeapType;
    }

    public boolean swap(T parent, T child) {
        int compare = parent.compareTo(child);
        boolean swap = compare <= 0;
        if (!isMaxHeap) {
            swap = !swap;
        }
        return swap;
    }

    public int getPrevIndex(int index) {
        if (index % 2 == 0) {
            return (index / 2) - 1;
        } else {
            return (index / 2);
        }
    }

    public boolean add(T data) {
        heap.add(data);
        int dataIndex = heap.size() - 1;
        int parentIndex = getPrevIndex(dataIndex);
        if (parentIndex < 0) {
            return true;
        }
        while (swap(heap.get(parentIndex), heap.get(dataIndex))) {
            T holder = heap.get(dataIndex);
            heap.set(dataIndex, heap.get(parentIndex));
            heap.set(parentIndex, holder);
            dataIndex = parentIndex;
            parentIndex = getPrevIndex(dataIndex);
            if (parentIndex < 0) {
                break;
            }
        }
        return true;
    }

    public int compareIndexes(int index1, int index2) {
        if (swap(heap.get(index1), heap.get(index2))) {
            return index2;
        } else {
            return index1;
        }
    }

    public int getLeftChild(int index) {
        return index + 1;
    }

    public int getRightChild(int index) {
        return index + 2;
    }

    public void heapify() {
        int currentIndex = 0;
        int leftChild = 1;
        int rightChild = 2;
        boolean didSwap = true;
        while (didSwap) {
            didSwap = false;
            int swapIndex = -1;
            if (rightChild >= heap.size() && leftChild >= heap.size()) {
                break;
            } else if (rightChild >= heap.size()) {
                swapIndex = leftChild;
            } else {
                swapIndex = compareIndexes(leftChild, rightChild);
            }
            if (swap(heap.get(currentIndex), heap.get(swapIndex))) {
                T data = heap.get(currentIndex);
                didSwap = true;
                heap.set(currentIndex, heap.get(swapIndex));
                heap.set(swapIndex, data);
                currentIndex = swapIndex;
                leftChild = getLeftChild(currentIndex);
                rightChild = getRightChild(currentIndex);
            }
        }
    }

    public boolean contains(Object o) {
        return heap.indexOf(o) != -1;
    }

    public T peek() {
        return heap.get(0);
    }

    public T poll() {
        T data = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify();
        return data;
    }

    public void print() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.get(i) + ",");
        }
    }

    public static void printSeperator() {
        System.out.println("------------------------------------");
    }

    public static void printSeperatorWithNewLineStart() {
        System.out.print("\n------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("Sucessfully complied");
        MyHeap<Integer> testHeap = new MyHeap<>();
        int[] nums = { 5, 19, 12, 2, 14, 18, 1, 10, 20, 7, 0, 11, 8, 16, 13, 4, 6, 3, 17, 9, 15 };
        for (int i = 0; i < nums.length; i++) {
            testHeap.add(nums[i]);
        }
        System.out.println("Expected: 0-20 in an order such that 20 is at index 0:");
        testHeap.print();
        printSeperatorWithNewLineStart();
        System.out.println("\nExpected: 20 and 20");
        System.out.println(testHeap.peek());
        System.out.println(testHeap.poll());
        System.out.println("Expected: 0-19 in an order such that 19 is at index 0:");
        testHeap.print();
        testHeap.add(19);
        printSeperatorWithNewLineStart();
        System.out.println("\nExpected: 19 and 19:");
        System.out.println(testHeap.poll() + " and " + testHeap.poll());
    }

}
