import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

class DayFour {
    public static void main(String[] args) {
        int counter = 0;
        int overlapCounter = 0;
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] pair = reader.nextLine().split(",");
                int firstStart = Integer.parseInt(pair[0].split("-")[0]);
                int firstEnd = Integer.parseInt(pair[0].split("-")[1]);
                int secStart = Integer.parseInt(pair[1].split("-")[0]);
                int secEnd = Integer.parseInt(pair[1].split("-")[1]);
                if ((firstStart >= secStart && firstEnd <= secEnd) || (secStart >= firstStart && secEnd <= firstEnd)) {
                    counter += 1;
                }
                // part two
                if ((firstStart >= secStart && firstStart <= secEnd)
                        || (secStart >= firstStart && secStart <= firstEnd)
                        || (firstEnd <= secEnd && firstEnd >= secStart)
                        || (secEnd <= firstEnd && secEnd >= firstStart)) {
                    overlapCounter += 1;
                    System.out.println(Arrays.toString(pair));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(overlapCounter);
    }
}