import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

class DayNine {

    private static HashSet<List<Integer>> visitedLoc = new HashSet<List<Integer>>();
    private static int[] headPos = {0, 0};
    private static int[] tailPos = {0, 0};

    public static void main(String[] args) {
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

        for (int i = 1; i <= steps; i++) {
            visitedLoc.add(Arrays.asList(tailPos[0], tailPos[1]));
            int[] currentHeadPos = {headPos[0], headPos[1]};
            switch (direction) {
                case "R": {
                    headPos[1] += 1;
                    break;
                } case "U": {
                    headPos[0] += 1;
                    break;
                } case "L": {
                    headPos[1] -= 1;
                    break;
                } case "D": {
                    headPos[0] -= 1;
                    break;
                } default: break;
            
            }
            if (Math.abs(headPos[0] - tailPos[0]) > 1 || Math.abs(headPos[1] - tailPos[1]) > 1 ) {
                tailPos[0] = currentHeadPos[0];
                tailPos[1] = currentHeadPos[1];
            }        
        }
    }
}