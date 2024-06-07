public class MyLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;
    private final static String seperator = "------------------------------------";

    class Node<E> {
        public Node<E> prev = null;
        public Node<E> next = null;
        public E data = null;
        Node(E data){
            this.data = data;
        }
        public boolean hasNext(){
            return next!=null;
        }
        public boolean hasPrev(){
            return prev!=null;
        }
    }

    public void print(){
        if (size==0){
            return;
        }
        String msg = head.data.toString();
        Node<T> curNode = head;
        while(curNode.hasNext()){
            curNode = curNode.next;
            msg = msg +","+curNode.data.toString();
        }
        System.out.println(msg);
    }

    public T remove(int index){
        if (index>=size){
            return null;
        }
        Node<T> curNode = null;
        if (index>=size/2){
            curNode = tail;
            for (int i=size-1; i>index; i--){
                curNode = curNode.prev;
            }
        } else if (index<size/2){
            curNode = head;
            for(int i=0; i<index; i++){
                curNode = curNode.next;
            }
        }
        if (curNode.hasPrev()){
            curNode.prev.next = curNode.next;
        }
        if (curNode.hasNext()){
            curNode.next.prev = curNode.prev;
        }
        size--;
        return curNode.data;
    }

    public boolean remove(Object obj){
        Node<T> curNode = head;
        boolean found = false;
        while(curNode.next!=null){
            if (curNode.data.equals(obj)){
                found = true;
                System.out.println("found");
                break;
            }
            curNode = curNode.next;
        }
        if (curNode.data.equals(obj)){
            found = true;
        }
        if (found){
            if (curNode.hasPrev()){
                curNode.prev.next = curNode.next;
            }
            if (curNode.hasNext()){
                curNode.next.prev = curNode.prev;
            }
        }
        size--;
        return found;
    }

    public int size(){
        return size;
    }

    public boolean contains(Object obj){
        return getIndexOf(obj)!=-1;
    }

    public int getIndexOf(Object obj){
        int index = 0;
        Node<T> currentNode = head;
        do{
            if (currentNode.data.equals(obj)){
                return index;
            } else {
                index++;
                currentNode = currentNode.next;
            }
        } while (currentNode.next != null);
        if (currentNode.data.equals(obj)){
            return index;
        }
        return -1;
    }

    public T get(int index){
        Node<T> curNode = null;
        if (index==0){
            return head.data;
        } else if (index>=size/2){
            curNode = tail;
            for (int i=size-1; i>index; i--){
                curNode = curNode.prev;
            }
        } else if (index<size/2){
            curNode = head;
            for(int i=0; i<index; i++){
                curNode = curNode.next;
            }
        }
        return curNode.data;
    }

    public void add(int index, T data){
        if (index>=size){
            System.out.println("Exception java.lang.IndexOutOfBoundsException: "+index+", Size: "+size+" at MyLinkedList.add");
            return;
        } else if (index==0){
            addFirst(data);
        } else if (index==size-1){
            add(data);
        } else {
            Node<T> curNode = head;
            for(int i=1; i<index; i++){
                curNode = curNode.next;
            }
            Node<T> insertNode = new Node<T>(data);
            curNode.next.prev = insertNode;
            insertNode.next = curNode.next;
            insertNode.prev = curNode;
            curNode.next = insertNode;
        }
        size++;
    }

    public void addFirst(T data){
        head.prev = new Node<T>(data);
        head.prev.next = head;
        head = head.prev;
        size++;
    }

    public boolean add(T data){
        if (head==null){
            head = new Node<T>(data);
            tail = head;
        } else {
            Node<T> tempNode = new Node<T>(data);
            tail.next = tempNode;
            tempNode.prev = tail;
            tail = tempNode;
        }
        size++;
        return true;
    }
    
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        for(int i=0; i<10; i++){
            testList.add(i);
        }
        System.out.println("Expected: 0,1,2,3,4,5,6,7,8,9");
        testList.print();
        System.out.println(seperator);
        System.out.println("Expected: 2");
        System.out.println(testList.remove(2));
        System.out.println(seperator);
        System.out.println("Expected: 0,1,3,4,5,6,7,8,9");
        testList.print();
        System.out.println(seperator);
        System.out.println("Expected: true");
        System.out.println(testList.remove(Integer.valueOf(9)));
        System.out.println(seperator);
        System.out.println("Expected: 0,1,3,4,5,6,7,8");
        testList.print();
        System.out.println(seperator);
        System.out.println("Expected: 8");
        System.out.println(testList.size());
        System.out.println(seperator);
        System.out.println("Expected: true");
        System.out.println(testList.contains(1));
        System.out.println(seperator);
        System.out.println("Expected: false");
        System.out.println(testList.contains(9));
        System.out.println(seperator);
        testList.addFirst(-1);
        System.out.println("Expected: -1,0,1,3,4,5,6,7,8");
        testList.print();
        System.out.println(seperator);
        System.out.println("Expected: -1,0,2,1,3,4,5,6,7,8");
        testList.add(2,2);
        testList.print();
    }
}
