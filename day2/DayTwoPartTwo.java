import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayTwoPartTwo {
    public static void main(String[] args) {
        int sum = 0;
        int xScore = 1;
        int yScore = 2;
        int zScore = 3;
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] val = reader.nextLine().split("\\s");
                if (val[1].equals("X")) {
                    sum += 0;
                    if (val[0].equals("A"))
                        sum += zScore;
                    else if (val[0].equals("B"))
                        sum += xScore;
                    else if (val[0].equals("C"))
                        sum += yScore;
                } else if (val[1].equals("Y")) {
                    sum += 3;
                    if (val[0].equals("A"))
                        sum += xScore;
                    else if (val[0].equals("B"))
                        sum += yScore;
                    else if (val[0].equals("C"))
                        sum += zScore;
                } else {
                    sum += 6;
                    if (val[0].equals("A"))
                        sum += yScore;
                    else if (val[0].equals("B"))
                        sum += zScore;
                    else if (val[0].equals("C"))
                        sum += xScore;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}
