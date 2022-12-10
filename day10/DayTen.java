import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayTen {
    public static int signalStrength;
    public static int X = 1;
    public static void main(String[] args) {
        
        try {
            File file = new File("inp.txt");
            
            int cycles = 0;
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                if (command.equals("noop")) {
                    cycles += 1;
                    runCycle(cycles);
                } else {
                    cycles += 1;
                    runCycle(cycles);
                    cycles += 1;
                    runCycle(cycles);
                    X += runCommand(command);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(signalStrength);
    }

    public static int runCommand(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    public static void runCycle(int cycles) {
        if (cycles == 20 || cycles == 60 || cycles == 100 || cycles == 140 || cycles == 180 || cycles == 220) {
            signalStrength += (cycles * X);
        }
        // pixie is at X-1, X and X+1 coords
    }
}

// ***
//  X
// width: 40 (0-39)
// height : 6
// draw 1 pixel per cycle