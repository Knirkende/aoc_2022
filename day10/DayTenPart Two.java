import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayTenPartTwo {
    public static int signalStrength;
    public static int X = 1;
    public static int cycles = -1;
    public static void main(String[] args) {
        
        try {
            File file = new File("inp.txt");
            
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                if (command.equals("noop")) {
                    cycles += 1;
                    runCycle();
                } else {
                    cycles += 1;
                    runCycle();
                    cycles += 1;
                    runCycle();
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

    public static void runCycle() {
        if (cycles == X - 1 || cycles == X || cycles == X + 1) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if (cycles == 39) {
            System.out.print("\n");
            cycles = -1;
        }
    }
}