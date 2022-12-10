import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

class DayNinePartTwo {

    private static HashSet<List<Integer>> visitedLoc = new HashSet<List<Integer>>();
    private static int[][] ropeChain = new int[9][2];
    private static int[][] previousPositions = new int[9][2];

    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            Arrays.fill(ropeChain[i], 0);
            Arrays.fill(previousPositions[i], 0);
        }
        

        try {
            File file = new File("test_inp.txt");
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
        System.out.println(visitedLoc);
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
            for (int link = 1; link < 9; link++) {
                //System.out.println("POSITION: " + link);
                if (Math.abs(ropeChain[link - 1][0] - ropeChain[link][0]) > 1 || Math.abs(ropeChain[link - 1][1] - ropeChain[link][1]) > 1 ) {
                    if (link == 1 && ropeChain[link - 1][0] != ropeChain[link][0] && ropeChain[link - 1][1] != ropeChain[link][1]) {
                        System.out.println("DIAGONAL");
                        // a diagonal move; check which knots follow (the ones in line with knot at pos 1?)
                        ropeChain[1][0] = previousPositions[0][0];
                        ropeChain[1][1] = previousPositions[0][1];
                        int up = ropeChain[1][0];
                        int over = ropeChain[1][1];
                        for (int linkInner = 2; linkInner < 9; linkInner++) {
                            System.out.println("INDEX: " + linkInner);
                            System.out.println(ropeChain[linkInner][0] + " AND " + ropeChain[linkInner][1]);
                            if (ropeChain[linkInner][0] == previousPositions[1][0]) {
                                System.out.println("SOMETHING WAS IN LINE");
                                System.out.println(up);
                                ropeChain[linkInner][0] = ropeChain[1][0];
                                //previousPositions[linkInner ][0] = ropeChain[linkInner][0];
                                
                            } else if (ropeChain[linkInner][1] == previousPositions[link][1]) {
                                ropeChain[linkInner][1] = over;
                                //previousPositions[linkInner][1] = ropeChain[linkInner][1];
                            }
                            else {
                                ropeChain[linkInner][0] = previousPositions[linkInner - 1][0];
                                ropeChain[linkInner][1] = previousPositions[linkInner - 1][1];
                            }
                            previousPositions[linkInner][0] = ropeChain[linkInner][0];
                            previousPositions[linkInner][1] = ropeChain[linkInner][1];
                        }
                        
                    } else {
                        ropeChain[link][0] = previousPositions[link - 1][0];
                        ropeChain[link][1] = previousPositions[link - 1][1];
                    }

                    visitedLoc.add(Arrays.asList(ropeChain[8][0], ropeChain[8][1]));
                    //System.out.println("PREV: " + ropeChain[link - 1][0] + " " + ropeChain[link - 1][1]);
                    //System.out.println("THIS: " + ropeChain[link][0] + " " + ropeChain[link][1]);
                    //System.out.println(Arrays.toString(ropeChain[8]));
                }
                previousPositions[link - 1][0] = ropeChain[link - 1][0];
                previousPositions[link - 1][1] = ropeChain[link - 1][1];
                
            }
            System.out.println("MOVE " + i);
            for (int j = 0; j < 9; j++) {
                System.out.println(Arrays.toString(ropeChain[j]));
            } 
            //System.out.println("HEAD POS: " + Arrays.toString(ropeChain[0]));       
        }
        //visitedLoc.add(Arrays.asList(ropeChain[8][0], ropeChain[8][1]));
    }
}