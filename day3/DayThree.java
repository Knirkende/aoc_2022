// 2 large compartments
// first half in compartment 1, second in compartment 2
// a-z : priority 1-26
// A-Z : priority 27-52

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayThree {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = generateScoreMap();
        int sum = 0;
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                sum += getPriority(reader.nextLine(), scores);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static int getPriority(String inp, HashMap<String, Integer> scores) {
        int result = 0;
        int half = inp.length() / 2;
        Set<String> setOne = new HashSet<String>(Arrays.asList(inp.substring(0, half).split("")));
        Set<String> setTwo = new HashSet<String>(Arrays.asList(inp.substring(half).split("")));
        setOne.retainAll(setTwo);
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