import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class DayEightPartTwo {

    private static List<List<Integer>> horizontalTrees = new ArrayList<List<Integer>>();
    private static List<List<Integer>> verticalTrees = new ArrayList<List<Integer>>();

    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String treeLine = reader.nextLine();
                List<Integer> trees = Stream.of(treeLine.split(""))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
                horizontalTrees.add(trees);
                // flip matrix
                for (int i = 0; i < (trees.size()); i++) {
                    int currentTree = trees.get(i);
                    if (verticalTrees.size() <= i) {
                        verticalTrees.add(new ArrayList<Integer>());
                    }
                    verticalTrees.get(i).add(currentTree);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        // generate the scoreboard
        int[][] scoreBoard = new int[horizontalTrees.size()][horizontalTrees.get(1).size()];
        for (int i = 0; i < scoreBoard.length; i++) {
            Arrays.fill(scoreBoard[i], 1);
        }

        // for each index, scan all directions
        for (int i = 0; i < horizontalTrees.size(); i++) {
            for (int j = 0; j < horizontalTrees.get(i).size(); j++) {
                int currentTree = horizontalTrees.get(i).get(j);
                int score = generateScore(horizontalTrees.get(i), currentTree, j);
                scoreBoard[i][j] *= score;
                score = generateScore(verticalTrees.get(j), currentTree, i);
                scoreBoard[i][j] *= score;
            }
        }

        // find max score
        int maxScore = 0;
        for (int i = 0; i < scoreBoard.length; i++) {
            for (int j = 0; j < scoreBoard[i].length; j++) {
                int s = scoreBoard[i][j];
                maxScore = s > maxScore ? s : maxScore;
            }
        }
        System.out.println(maxScore);        

    }

    public static int generateScore(List<Integer> treeLine, int currentTree, int startIndex) {
        int rightCounter = 0;
        // check left-to-right
        for (int i = startIndex + 1; i < (treeLine.size()); i++) {
            if (currentTree <= treeLine.get(i)) {
                rightCounter += 1;
                break;
            } else {
                rightCounter += 1;
            }
        }
        // check right-to-left
        int leftCounter = 0;
        for (int i = startIndex - 1; i >= 0; i--) {
            if (currentTree <= treeLine.get(i)) {
                leftCounter += 1;
                break;
            } else {
                leftCounter += 1;
            }
        }
        return leftCounter * rightCounter;        
    }

}