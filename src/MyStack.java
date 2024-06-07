public class MyStack<T> {
    private final static String seperator = "------------------------------------";
    private MyLinkedList<T> list = new MyLinkedList<>();

    public boolean empty(){
        return list.size()==0;
    }

    public T push(T data){
        list.addFirst(data);
        return data;
    }

    public T peek(){
        return list.get(0);
    }

    public T pop(){
        return list.remove(0);
    }

    public int serach(Object obj){
        return list.getIndexOf(obj);
    }

    public void print(){
        list.print();
    }

    public static void main(String[] args){
        MyStack<Integer> stack = new MyStack<>();
        for(int i=0; i<10; i++){
            stack.push(i);
        }
        System.out.println("Expected: 9,8,7,6,5,4,3,2,1,0");
        stack.print();
        System.out.println(seperator);
        System.out.println("Expected: false");
        System.out.println(stack.empty());
        System.out.println(seperator);
        System.out.println("Expected: 9");
        System.out.println(stack.peek());
        System.out.println(seperator);
        System.out.println("Expected 9");
        System.out.println(stack.pop());
        System.out.println(seperator);
        for(int i=0; i<8; i++){
            stack.pop();
        }
        System.out.println("Expected true");
        System.out.println(stack.empty());
        

    }
}
