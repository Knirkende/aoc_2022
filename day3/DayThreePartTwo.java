// 2 large compartments
// first half in compartment 1, second in compartment 2
// a-z : priority 1-26
// A-Z : priority 27-52

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayThreePartTwo {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = generateScoreMap();
        int sum = 0;
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] elfBags = new String[3];
                for (int i = 0; i < 3; i++) {
                    elfBags[i] = reader.nextLine();
                }
                sum += getPriority(elfBags, scores);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static int getPriority(String[] inp, HashMap<String, Integer> scores) {
        int result = 0;
        Set<String> setOne = new HashSet<String>(Arrays.asList(inp[0].split("")));
        Set<String> setTwo = new HashSet<String>(Arrays.asList(inp[1].split("")));
        Set<String> setThree = new HashSet<String>(Arrays.asList(inp[2].split("")));
        setOne.retainAll(setTwo);
        setOne.retainAll(setThree);
        for (String s : setOne) {
            result += scores.get(s);
        }
        return result;
    }

    private static HashMap<String, Integer> generateScoreMap() {
        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        String[] lowerLetters = "abcdefghijklmnopqrstuvwxyz".split("");
        String[] upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        for (int i = 0; i < 26; i++) {
            scores.put(lowerLetters[i], i + 1);
        }
        for (int i = 0; i < 26; i++) {
            scores.put(upperLetters[i], i + 27);
        }
        return scores;
    }
}