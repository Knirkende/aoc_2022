//sequence of 4 different characters
//num chars from beginning to end of first seq of 4 different chars

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

class DaySix {
    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            String[] sequence = reader.nextLine().split("");
            for (int i = 13; i < sequence.length; i++) {
                String[] window = IntStream.range(i-13, i + 1).mapToObj(x -> sequence[x]).toArray(String[]::new);
                HashSet<String> windowSet = new HashSet<String>(Arrays.asList(window));
                if (windowSet.size() == 14 ) {
                    System.out.println(i +1);
                    System.out.println(windowSet);
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}