import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class DayEight {

    private static List<List<Integer>> verticalTrees = new ArrayList<List<Integer>>();
    private static List<List<Integer>> visibleTrees = new ArrayList<List<Integer>>(); // [(X, Y), (X, Y)]
    private static List<List<Integer>> invertedVisibleTrees = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        int lineNumber = 0;
        try {
            File file = new File("test_inp.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String treeLine = reader.nextLine();
                scanTreeLine(treeLine, lineNumber);
                lineNumber += 1;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        for (int i = 0; i < visibleTrees.size(); i++) {
            invertedVisibleTrees.add(new ArrayList<Integer>());
            for (int j = 0; j < visibleTrees.get(i).size(); j++) {
                int val = visibleTrees.get(j).get(i);
                invertedVisibleTrees.get(i).add(val);
            }
        }
        for (int i = 0; i < verticalTrees.size(); i++) {
            scanTreeLine(verticalTrees.get(i), i);
        }
        int sum = 0;
        for (int i = 0; i < invertedVisibleTrees.size(); i++) {
            sum += invertedVisibleTrees.get(i).stream().mapToInt(Integer::intValue).sum();
        }
        System.out.println(sum);

    }

    public static void scanTreeLine(String treeLine, int lineNumber) {
        int maxLeft = -1;
        int maxRight = -1;
        visibleTrees.add(new ArrayList<Integer>());
        List<Integer> trees = Stream.of(treeLine.split(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        // check left-to-right
        for (int i = 0; i < (trees.size()); i++) {
            int currentTree = trees.get(i);
            if (verticalTrees.size() <= i) {
                verticalTrees.add(new ArrayList<Integer>());
            }
            verticalTrees.get(i).add(currentTree);
            if (currentTree > maxLeft) {
                maxLeft = currentTree;
                visibleTrees.get(lineNumber).add(1);
            } else {
                visibleTrees.get(lineNumber).add(0);
            }
        }
        // check right-to-left
        Collections.reverse(trees);
        for (int i = 0; i < (trees.size()); i++) {
            int currentTree = trees.get(i);
            if (currentTree > maxRight) {
                maxRight = currentTree;
                visibleTrees.get(lineNumber).set(trees.size() - 1 - i, 1);
            }
        }
    }

    public static void scanTreeLine(List<Integer> treeLine, int lineNumber) {
        int maxLeft = -1;
        int maxRight = -1;
        // check left-to-right
        for (int i = 0; i < (treeLine.size()); i++) {
            int currentTree = treeLine.get(i);
            if (currentTree > maxLeft) {
                maxLeft = currentTree;
                invertedVisibleTrees.get(lineNumber).set(i, 1);
            }
        }
        // check right-to-left
        Collections.reverse(treeLine);
        for (int i = 0; i < (treeLine.size()); i++) {
            int currentTree = treeLine.get(i);
            if (currentTree > maxRight) {
                maxRight = currentTree;
                invertedVisibleTrees.get(lineNumber).set(treeLine.size() - 1 - i, 1);
            }
        }
    }

}