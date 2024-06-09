public class MyBinarySearchTree<T extends Comparable<T>>{
    private Node<T> head = null;
    private int size = 0;
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

    /*public int size(){
        
    }*/

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
            } else {
                Node<T> swapNode = findMax(curNode.left);
                curNode.data = swapNode.data;
                curNode.left = remove(curNode.left, swapNode.data);
            }
            return curNode;
        }
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
    }

    public void add(T data, Node<T> curNode){
        int compare = data.compareTo(curNode.data);
        if (compare==0){
            curNode.count++;
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

    public static void main(String[] args){
        System.out.println("Sucessfully complied");
    }

}