import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

class DayNinePartTwo {

    private static HashSet<List<Integer>> visitedLoc = new HashSet<List<Integer>>();
    private static int[][] ropeChain = new int[10][2];

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Arrays.fill(ropeChain[i], 0);
        }

        try {
            File file = new File("inp.txt");
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
            switch (direction) {
                case "R": {
                    ropeChain[0][1] += 1;
                    break;
                } case "U": {
                    ropeChain[0][0] += 1;
                    break;
                } case "L": {
                    ropeChain[0][1] -= 1;
                    break;
                } case "D": {
                    ropeChain[0][0] -= 1;
                    break;
                } default: break;
            
            }
            for (int cur = 1; cur < 10; cur++) {
                int diff_x = ropeChain[cur - 1][0] - ropeChain[cur][0];
                int diff_y = ropeChain[cur - 1][1] - ropeChain[cur][1];
                if (Math.abs(diff_x) > 1 || Math.abs(diff_y) > 1 ) {
                    if (diff_x > 0) {
                        ropeChain[cur][0] += 1;
                    } else if (diff_x < 0) {
                        ropeChain[cur][0] -= 1;
                    }
                    if (diff_y > 0) {
                        ropeChain[cur][1] += 1;
                    } else if (diff_y < 0) {
                        ropeChain[cur][1] -= 1;
                    }
            }
            }
            visitedLoc.add(Arrays.asList(ropeChain[9][0], ropeChain[9][1]));        
        }
    }
}