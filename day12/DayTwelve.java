// current node, elevation a
// goal node, elevation z
// can only step 1 elevation level up, but any down

// A*
// keep a frontier (priority queue) with number of steps so far
// (int) c
//no, actually do bfs w path costs

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayTwelve {

    private static List<int[]> map = new ArrayList<int[]>();
    private static List<List<Node>> graph = new ArrayList<List<Node>>();
    private static int[] startNode;
    private static int[] goalNode;

    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                addRowToMap(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        for (int i = 0; i < map.size(); i++) {
            System.out.println(Arrays.toString(map.get(i)));
        }
        System.out.println(Arrays.toString(startNode));
        findPath(startNode[0], startNode[1]);
        System.out.println(graph.get(goalNode[0]).get(goalNode[1]).cost);

    }

    public static void addRowToMap(String line) {
        String[] arr = line.split("");
        int[] intArr = new int[arr.length];
        List<Node> nodeArr = new ArrayList<Node>();
        int currentRow = map.size();

        for (int i = 0; i < arr.length; i++) {
            Node currentNode = new Node();
            if (arr[i].equals("S")) {
                startNode = new int[] { currentRow, i };
                currentNode.cost = 0;
                arr[i] = "a";
            } else if (arr[i].equals("E")) {
                goalNode = new int[] { currentRow, i };
                arr[i] = "z";
            }
            intArr[i] = (int) arr[i].charAt(0);
            nodeArr.add(currentNode);
        }
        map.add(intArr);
        graph.add(nodeArr);
    }

    public static void findPath(int currentRow, int currentCol) {

        int currentElevation = map.get(currentRow)[currentCol];
        Node currentNode = graph.get(currentRow).get(currentCol);
        Node nextNode = null;

        if (currentRow > 0) { // look above
            if (map.get(currentRow - 1)[currentCol] <= currentElevation + 1) { // it is possible to get there
                nextNode = graph.get(currentRow - 1).get(currentCol);
                if (nextNode.cost > currentNode.cost + 1) { // it is cheaper to get there through current path
                    nextNode.cost = currentNode.cost + 1;
                    nextNode.previous = currentNode;
                    findPath(currentRow - 1, currentCol);
                }
            }
        }
        if (currentRow < map.size() - 1) { // look below
            if (map.get(currentRow + 1)[currentCol] <= currentElevation + 1) { // it is possible to get there
                nextNode = graph.get(currentRow + 1).get(currentCol);
                if (nextNode.cost > currentNode.cost + 1) { // it is cheaper to get there through current path
                    nextNode.cost = currentNode.cost + 1;
                    nextNode.previous = currentNode;
                    findPath(currentRow + 1, currentCol);
                }
            }
        }
        if (currentCol > 0) { // look left
            if (map.get(currentRow)[currentCol - 1] <= currentElevation + 1) { // it is possible to get there
                nextNode = graph.get(currentRow).get(currentCol - 1);
                if (nextNode.cost > currentNode.cost + 1) { // it is cheaper to get there through current path
                    nextNode.cost = currentNode.cost + 1;
                    nextNode.previous = currentNode;
                    findPath(currentRow, currentCol - 1);
                }
            }
        }
        if (currentCol < map.get(0).length - 1) { // look right
            if (map.get(currentRow)[currentCol + 1] <= currentElevation + 1) { // it is possible to get there
                nextNode = graph.get(currentRow).get(currentCol + 1);
                if (nextNode.cost > currentNode.cost + 1) { // it is cheaper to get there through current path
                    nextNode.cost = currentNode.cost + 1;
                    nextNode.previous = currentNode;
                    findPath(currentRow, currentCol + 1);
                }
            }
        }
    }
}

class Node {
    public int cost;
    public Node previous;

    public Node() {
        this.cost = 9999;
        this.previous = null;
    }
}