import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

class DayNineTwo {

    private static HashSet<List<Integer>> visitedLoc = new HashSet<List<Integer>>();
    private static int[][] ropeChain = new int[10][2];
    private static int[][] previousPositions = new int[10][2];

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(ropeChain[i], 0);
            Arrays.fill(previousPositions[i], 0);
        }
        try {
            File file = new File("test_inp2.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String instruction = reader.nextLine();
                performMove(instruction);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(visitedLoc.size());
    }

    public static void performMove(String instruction) {
        String direction = instruction.split(" ")[0];
        int steps = Integer.parseInt(instruction.split(" ")[1]);
        int[] change = {0, 0};
        switch (direction) {
            case "R": {
                change[1] = 1;
                break;
            } case "U": {
                change[0] = 1;
                break;
            } case "L": {
                change[1] = -1;
                break;
            } case "D": {
                change[0] = -1;
                break;
            } default: break;        
        }
        for (int i = 1; i <= steps; i++) {
            // visitedLoc.add(Arrays.asList(tailPos[0], tailPos[1]));
            // move head first
            
            for (int j = 1; j < 10; j++) {
                int knotHor = ropeChain[j][1];
                int knotVer = ropeChain[j][0];

            }           

            if (Math.abs(headPos[0] - tailPos[0]) > 1 || Math.abs(headPos[1] - tailPos[1]) > 1 ) {
                tailPos[0] = currentHeadPos[0];
                tailPos[1] = currentHeadPos[1];
            }        
        }
    }
}