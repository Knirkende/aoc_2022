import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class DayOne {
    public static void main(String[] args) {
        ArrayList<Integer> totals = new ArrayList<Integer>();
        int sum = 0;
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String val = reader.nextLine();
                if (val.isEmpty()) {
                    totals.add(sum);
                    sum = 0;
                    continue;
                } else {
                    sum += Integer.parseInt(val);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        Collections.sort(totals, Collections.reverseOrder());
        System.out.println(totals.get(0));
        System.out.println(totals.get(0) + totals.get(1) + totals.get(2));
    }
}