import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DayTwo {
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
                    sum += xScore;
                    if (val[0].equals("A"))
                        sum += 3;
                    else if (val[0].equals("B"))
                        sum += 0;
                    else if (val[0].equals("C"))
                        sum += 6;
                } else if (val[1].equals("Y")) {
                    sum += yScore;
                    if (val[0].equals("A"))
                        sum += 6;
                    else if (val[0].equals("B"))
                        sum += 3;
                    else if (val[0].equals("C"))
                        sum += 0;
                } else {
                    sum += zScore;
                    if (val[0].equals("A"))
                        sum += 0;
                    else if (val[0].equals("B"))
                        sum += 6;
                    else if (val[0].equals("C"))
                        sum += 3;
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