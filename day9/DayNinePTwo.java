import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

class DayNinePTwo {

    private static HashSet<List<Integer>> visitedLoc = new HashSet<List<Integer>>();
    private static int[][] ropeChain = new int[10][2];
    private static int[][] previousPositions = new int[10][2];

    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            Arrays.fill(ropeChain[i], 0);
            Arrays.fill(previousPositions[i], 0);
        }

        try {
            File file = new File("test_inp3.txt");
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
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(ropeChain[i]));
        }
    }

    public static void performMove(String instruction) {
        String direction = instruction.split(" ")[0];
        int steps = Integer.parseInt(instruction.split(" ")[1]);

        for (int i = 1; i <= steps; i++) {
            visitedLoc.add(Arrays.asList(ropeChain[9][0], ropeChain[9][1]));
            //int[] currentHeadPos = {headPos[0], headPos[1]};
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
            // after a change: check relative pos of each link to the next link
                // if distance is greater than 2
                    // if X and Y are different: move X and Y towards head
                    // if directly up or down: change X by an amount
                    // if horizontal: change Y by an amount
            for (int cur = 1; cur < 10; cur++) {
                //System.out.println("CURRENT LINK EXAMINED: " + cur);
                int prev = cur - 1;
                if (Math.abs(ropeChain[prev][0] - ropeChain[cur][0]) > 1 || Math.abs(ropeChain[prev][1] - ropeChain[cur][1]) > 1 ) { // 2 away in at least one dim
                    
                    //System.out.println("PREV: " + ropeChain[prev][0] + " " + ropeChain[prev][1]);
                    //System.out.println("THIS: " + ropeChain[cur][0] + " " + ropeChain[cur][1]);
                    //System.out.println("TAIL POS:");
                    //System.out.println(Arrays.toString(ropeChain[8]));
                    
                    if (ropeChain[prev][0] != ropeChain[cur][0] && ropeChain[prev][1] != ropeChain[cur][1]) { //the change is diagonal; increment X and Y tow prev
                        int x_change = previousPositions[prev][0] - ropeChain[cur][0];
                        int y_change = previousPositions[prev][1] - ropeChain[cur][1];
                        //when this happens for one knot, all knots should perform this move, unless they are covered by the previous knot
                        for (int j = cur; j < 10; j++) {
                            //System.out.println("DIAGONAL LOOP START, ITERATION " + j);
                            if (Math.abs(ropeChain[j - 1][0] - ropeChain[j][0]) > 1 || Math.abs(ropeChain[j - 1][1] - ropeChain[j][1]) > 1 ) {
                                if (previousPositions[j - 1][0] == ropeChain[j][0] && previousPositions[j - 1][1] == ropeChain[j][1]) {
                                    //System.out.println("****************************************");
                                    continue; // current knot is under the previous; do not move it
                                } else {
                                    //System.out.println("PREV: " + ropeChain[j - 1][0] + " " + ropeChain[j - 1][1]);
                                    //System.out.println("THIS: " + ropeChain[j][0] + " " + ropeChain[j][1]);
                                    ropeChain[j][0] += x_change;
                                    ropeChain[j][1] += y_change;
                                    //System.out.println("THIS CHANGED TO: " + ropeChain[j][0] + " " + ropeChain[j][1]);
    
                                }
                            }
                        }
                    } else { // the change is not diagonal; perform the change as before for only a single knot and continue to next knot
                        ropeChain[cur][0] = previousPositions[prev][0];
                        ropeChain[cur][1] = previousPositions[prev][1];
                    }
                    //System.out.println("THIS CHANGED TO: " + ropeChain[cur][0] + " " + ropeChain[cur][1]);
                } // remember to change previousPositions after each change
                previousPositions[prev][0] = ropeChain[prev][0];
                previousPositions[prev][1] = ropeChain[prev][1];
                visitedLoc.add(Arrays.asList(ropeChain[9][0], ropeChain[9][1]));
            }
        
        }
    }
}