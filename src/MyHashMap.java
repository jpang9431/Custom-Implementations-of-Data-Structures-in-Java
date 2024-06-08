public class MyHashMap<T,V> {
    private int size = 0;
    private int maxSize = 16;
    private double loadFactor = .75;
    @SuppressWarnings("unchecked")
    private T[] keys = (T[]) new Object[maxSize];
    @SuppressWarnings("unchecked")
    private V[] values = (V[]) new Object[maxSize];
    private boolean[] isSet = new boolean[maxSize];
    private final static String seperator = "------------------------------------";

    public void print(){
        for(int i=0; i<keys.length; i++){
            if (keys[i]!=null){
                System.out.println(i+".{"+keys[i]+","+values[i]+"}");
            } 
        }
    }

    public void reHashCheck(){
        if (size>=maxSize*loadFactor){
            int actualSize = 0;
            maxSize = maxSize*2;
            T[] tempKeys = keys.clone();
            V[] tempValues = values.clone();
            @SuppressWarnings("unchecked")
            T[] altKeys = (T[]) new Object[maxSize];
            @SuppressWarnings("unchecked")
            V[] altValues = (V[]) new Object[maxSize];
            keys = altKeys.clone();
            values = altValues.clone();
            size = 0;
            isSet = new boolean[maxSize];
            for(int i=0; i<tempKeys.length; i++){
                if (tempKeys[i]!=null){
                    put(tempKeys[i], tempValues[i]);
                } 
            }
            size = actualSize;
        }
    }

    public int size(){
        return size;
    }

    public V get(Object key){
        int index = indexOf(key);
        if (index==-1){
            return null;
        } else {
            return values[index];
        }
    }

    public int indexOf(Object key){
        int index = key.hashCode()%maxSize;
        while((keys[index]!=null||isSet[index])&&!key.equals(keys[index])){
            index = getNextIndex(index);
        }
        if (keys[index]!=null&&keys[index].equals(key)){
            return index;
        } else {
            return -1;
        }
    }

    public V remove(T key){
        int index = getIndex(key);
        if (!keys[index].equals(key)){
            return null;
        } else {
            keys[index] = null;
            return values[index];
        }
    }

    public V put(T key, V value){
        int index = getIndex(key);
        V prevValue = values[index];
        keys[index] = key;
        values[index] = value;
        isSet[index] = true;
        reHashCheck();
        size++;
        return prevValue;
    }

    public int getIndex(T key){
        int index = key.hashCode()%maxSize;
        while((keys[index]!=null||isSet[index])&&!keys[index].equals(key)){
            index = getNextIndex(index);
        }
        return index;
    }

    public int getNextIndex(int index){
        return (index^2)%maxSize;
    }

    public static void main(String[] args){
        MyHashMap<String, Integer> testHashMap = new MyHashMap<>();
        for(int i=0; i<16; i++){
            testHashMap.put("Key"+i, i);
        }
        System.out.println("Expected result: {Key(num), (num)} with num being 1-16 with the two nums being equal to eachother");
        testHashMap.print();
        System.out.println(seperator);
        System.out.println("Expected result: 0");
        System.out.println(testHashMap.get("Key0"));
        System.out.println(seperator);
        testHashMap.put("Key0", -1);
        System.out.println("Expected result: -1");
        System.out.println(testHashMap.get("Key0"));
        System.out.println(seperator);
        testHashMap.remove("Key1");
        System.out.println("Expeceted result: null");
        System.out.println(testHashMap.get("Key1"));
        System.out.println(seperator);

    }
}