public class MyBinarySearchTree<T extends Comparable<T>>{
    private Node<T> head = null;
    private int size = 0;
    private int unique = 0;
    class Node<K extends Comparable<K>> implements Comparable<Node<K>>{
        K data = null;
        Node<K> left = null;
        Node<K> right = null;
        int count = 1;
        Node(K data){
            this.data = data;
        }
        @Override
        public boolean equals(Object other){
            if (!(other instanceof Node)){
                return false;
            } else {
                @SuppressWarnings("unchecked")
                Node<K> otherNode = (Node<K>) other;
                return this.data.equals(otherNode.data);
            }
        }
        @Override
        public int compareTo(Node<K> other){
            return other.data.compareTo(this.data);
        }
        public int compareTo(K other){
            return other.compareTo(this.data);
        }
        public int numChildren(){
            int num = 0;
            if (left!=null){
                num++;
            }
            if (right!=null){
                num++;
            }
            return num;
        }
        public boolean hasLeft(){
            return left!=null;
        }
        public boolean hasRight(){
            return right!=null;
        }
    }

    public int size(){
        return size;
    }

    public int unique(){
        return unique;
    }

    public boolean contains(Object o){
        try{
            @SuppressWarnings("unchecked")
            T data = (T) o;
            Node<T> curNode = head;
            while (curNode!=null){
                int value = curNode.compareTo(data);
                if (value<0){
                    curNode = curNode.left;
                } else if (value>0){
                    curNode = curNode.right;
                } else {
                    return true;
                }
            }
            return false;
        } catch(Exception e){
            return false;
        }
    }

    public boolean remove(Object o){
        try{
            boolean foundNode = contains(o);
            @SuppressWarnings("unchecked")
            T data = (T) o;
            head = remove(head, data);
            return foundNode;
        } catch(Exception e){
            return false;
        }
    }

    public char isLeftOrRight(Node<T> ancestorNode, Node<T> curNode){
        if (ancestorNode.left.equals(curNode)){
            return 'L';
        } else if (ancestorNode.right.equals(curNode)){
            return 'R';
        } else {
            System.out.println("Error");
            return '?';
        }
    }

    //Thanks geeks for geeks for teaching me a method of BST remvoal: https://www.geeksforgeeks.org/deletion-in-binary-search-tree/
    public Node<T> remove(Node<T> curNode, T data){
        if (curNode==null){
            return curNode;
        } else {
            int value = curNode.compareTo(data);
            if (value<0){
                curNode.left = remove(curNode.left, data);
            } else if (value>0){
                curNode.right = remove(curNode.right, data);
            } else if (curNode.left==null){
                return curNode.right;
            } else if (curNode.right==null){
                return curNode.left;
            } else if (curNode.count>1){
                curNode.count--;
                size--;
            } else {
                Node<T> swapNode = findMax(curNode.left);
                curNode.data = swapNode.data;
                curNode.left = remove(curNode.left, swapNode.data);
                size--;
                unique--;
            }
            return curNode;
        }
    }

    public T findMax(){
        return findMax(head).data;
    }

    public T findMin(){
        return findMin(head).data;
    }

    public Node<T> findMax(Node<T> curNode){
        while(curNode.right!=null){
            curNode = curNode.right;
        }
        return curNode;
    }

    public Node<T> findMin(Node<T> curNode){
        while (curNode.left!=null){
            curNode = curNode.left;
        }
        return curNode;
    } 

    

    public void add(T data){
        if (head==null){
            head = new Node<T>(data);
        } else {
            add(data, head);
        }
        size++;
        unique++;
    }

    public void add(T data, Node<T> curNode){
        int compare = data.compareTo(curNode.data);
        if (compare==0){
            curNode.count++;
            unique--;
        } else if (compare<0&&curNode.left==null){
            curNode.left = new Node<T>(data);
        } else if (compare<0){
            add(data, curNode.left);
        } else if (compare>0&&curNode.right==null){
            curNode.right = new Node<T>(data);
        } else if (compare>0){
            add(data, curNode.right);
        }
    }

    public void print(){
        print(head);
    }

    public void print(Node<T> node){
        if (node==null){
            return;
        }
        print(node.left);
        System.out.print(node.data+" ");
        print(node.right);
    }

    public void printHead(){
        System.out.println(head.data);
    }

    public static void printSeperator(){
        System.out.println("------------------------------------");
    }

    public static void printSeperatorWithNewLineStart(){
        System.out.print("\n------------------------------------");
    }

    public static void main(String[] args){
        MyBinarySearchTree<Integer> testTree = new MyBinarySearchTree<>();
        int[] nums = {14,6,3,2,8,15,4,19,0,1,11,9,5,16,17,18,20,12,13,10,7};
        for(int i:nums){
            testTree.add(nums[i]);
        }
        System.out.println("Expected: 0-20 in order inclusive");
        testTree.print();
        printSeperatorWithNewLineStart();
        System.out.println("\nExpected: true and 0-20 in order without 4");
        System.out.println(testTree.remove(4));
        testTree.print();
        printSeperatorWithNewLineStart();
        System.out.println("\nEpxected true and 0-20 without 4 or 11");
        System.out.println(testTree.remove(11));
        testTree.print();
        printSeperatorWithNewLineStart();
        System.out.println("\nExpected: 20 and 0");
        System.out.println(testTree.findMax());
        System.out.println(testTree.findMin());
        printSeperator();
        testTree.remove(20);
        System.out.println("Expected: 19 and 0-19 without 4 or 11");
        System.out.println(testTree.findMax());
        testTree.print();
    }

}