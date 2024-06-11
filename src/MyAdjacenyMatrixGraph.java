import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
class MyAdjacenyMatrixGraph<T>{
    private HashMap<T, Integer> integerPairings = new HashMap<T, Integer>();
    private int[][] graph = new int[0][0];

    public int depthFirstSearch(T source, T destination){
        Integer sourceInteger = integerPairings.get(source);
        Integer destinationInteger = integerPairings.get(destination);
        if (sourceInteger==null||destinationInteger==null){
            return -1;
        }
        boolean visited[] = new boolean[graph.length];
        int distance = depthFirstSearch(sourceInteger, visited, destinationInteger, 0);
        if (distance==Integer.MAX_VALUE){
            return -1;
        } else {
            return distance;
        }
    }

    public int depthFirstSearch(int currentNode, boolean[] found, int targetNode, int currentDistance){
        found[currentNode] = true;
        if (currentNode==targetNode){
            found[currentNode] = false;
            return currentDistance;
        }
        //System.out.println(currentDistance+"|"+currentNode);
        int distance = Integer.MAX_VALUE;
        for(int i=0; i<graph.length; i++){
            if (graph[currentNode][i]>0&&!found[i]){
                //System.out.println("?");
                int tempDistance = depthFirstSearch(i, found, targetNode, currentDistance+1);
                if (tempDistance<distance){
                    distance = tempDistance;
                }
            }
        }
        found[currentNode] = false;
        return distance;
    }

    public int breadthFirstSearch(T source, T destination){
        Integer sourceInteger = integerPairings.get(source);
        Integer destinationInteger = integerPairings.get(destination);
        if (sourceInteger==null||destinationInteger==null){
            return -1;
        }
        boolean visited[] = new boolean[graph.length];
        Queue<Integer> nodes = new LinkedList<>();
        for(int i=0; i<graph.length; i++){
            if (graph[sourceInteger][i]>0){
                nodes.add(i);
                visited[i] = true;
            }
        }
        Queue<Integer> tempNodes = new LinkedList<>();
        int distance = 1;
        while(nodes.size()>0){
            int node = nodes.poll();
            if (node==destinationInteger){
                return distance;
            }
            for(int i=0; i<graph.length; i++){
                if (graph[node][i]>0&&!visited[i]){
                    tempNodes.add(i);
                }
            }
            if (nodes.size()==0){
                distance++;
                nodes = tempNodes;
                tempNodes = new LinkedList<>();
            }
        }
        return -1;
    }

    public boolean removeEdge(T vert1, T vert2){
        Integer key1 = integerPairings.get(vert1);
        Integer key2 = integerPairings.get(vert2);
        if (key1!=null&&key2!=null){
            graph[key1][key2] = 0;
            graph[key2][key1] = 0;
            return true;
        } else {
            return false;
        }
    }

    public boolean addEdge(T vert1, T vert2){
        Integer key1 = integerPairings.get(vert1);
        Integer key2 = integerPairings.get(vert2);
        if (key1!=null&&key2!=null){
            graph[key1][key2]++;
            graph[key2][key1]++;
            return true;
        } else {
            return false;
        }
    }

    public int setEdge(T vert1, T vert2, int weight){
        Integer key1 = integerPairings.get(vert1);
        Integer key2 = integerPairings.get(vert2);
        if (key1!=null&&key2!=null){
            int previousSet = graph[key1][key2];
            graph[key1][key2] = weight;
            graph[key2][key1] = weight;
            return previousSet;
        } else {
            return -1;
        }
    }

    public boolean addVertex(T data){
        if (integerPairings.containsKey(data)){
            return false;
        } else {
            integerPairings.put(data, graph.length);
            int[][] tempGraph = new int[graph.length+1][graph.length+1];
            for(int row=0; row<graph.length; row++){
                for(int col=0; col<graph[0].length; col++){
                    tempGraph[row][col] = graph[row][col];
                }
            }
            graph = tempGraph;
            return true;
        }
    }

    public boolean removeVertex(T data){
        Integer index = integerPairings.remove(data);
        if (index==null){
            return false;
        } else {
            int[][] tempGraph = new int[graph.length-1][graph.length-1];
            for(int row=0; row<graph.length; row++){
                for(int col=0; col<graph.length; col++){
                    if (col!=index&&row!=index){
                        int newRow = row;
                        int newCol = col;
                        if (col>index){
                            newCol--;
                        }
                        if (row>index){
                            newRow--;
                        }
                        tempGraph[newRow][newCol] = graph[row][col];
                    }
                }
            }
            graph = tempGraph;
            for (Map.Entry<T,Integer>entry:integerPairings.entrySet()){
                if (entry.getValue()>index){
                    integerPairings.replace(entry.getKey(), entry.getValue()-1);
                }
            }
            return true;
        }
    }

    public void print(){
        for(int row=0; row<graph.length; row++){
            for(int col=0; col<graph.length; col++){
                System.out.print(graph[row][col]+",");
            }
            System.out.println("");
        }
    }

    public void printMappings(){
        for(Map.Entry<T,Integer> entry : integerPairings.entrySet()){
            System.out.println(entry.getKey()+"|"+entry.getValue());
        }
    }

    public static void printSeperator(){
        System.out.println("------------------------------------");
    }

    public static void main(String[] args){
        MyAdjacenyMatrixGraph<Integer> graph = new MyAdjacenyMatrixGraph<>();
        for(int i=0; i<7; i++){
            graph.addVertex(i);
        }
        //graph.printMappings();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        System.out.println("Expected: see https://visualgo.net/en/graphds");
        graph.print();
        printSeperator();
        System.out.println("Expected: 4");
        System.out.println(graph.breadthFirstSearch(0, 6));
        printSeperator();
        System.out.println("Expected: 2");
        System.out.println(graph.breadthFirstSearch(0, 3));
        printSeperator();
        System.out.println("Expected: 4");
        System.out.println(graph.depthFirstSearch(0, 6));
        printSeperator();
        System.out.println("Expected: 2");
        System.out.println(graph.depthFirstSearch(0, 3));
    }
} 