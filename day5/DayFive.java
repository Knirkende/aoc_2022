import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DayFive {
    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            ArrayList<ArrayList<String>> cargoArray = buildInitialStack(reader);
            cargoArray = crateMover9001(reader, cargoArray);
            for (ArrayList<String> stack : cargoArray) {
                System.out.printf(stack.get(stack.size() - 1));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private static ArrayList<ArrayList<String>> crateMover9000(Scanner reader,
            ArrayList<ArrayList<String>> cargoArray) {
        while (reader.hasNextLine()) {
            int[] move = getNextMove(reader.nextLine());
            for (int i = 0; i < move[0]; i++) {
                int len = cargoArray.get(move[1] - 1).size();
                ArrayList<String> fromStack = cargoArray.get(move[1] - 1);
                ArrayList<String> toStack = cargoArray.get(move[2] - 1);
                String fromBox = fromStack.remove(len - 1);
                toStack.add(fromBox);
                cargoArray.set(move[1] - 1, fromStack);
                cargoArray.set(move[2] - 1, toStack);
            }
        }
        return cargoArray;
    }

    private static ArrayList<ArrayList<String>> crateMover9001(Scanner reader,
            ArrayList<ArrayList<String>> cargoArray) {
        while (reader.hasNextLine()) {
            ArrayList<String> moveStack = new ArrayList<String>();
            int[] move = getNextMove(reader.nextLine());
            if (cargoArray.get(move[1] - 1).size() <= move[0]) {
                moveStack = new ArrayList<>(cargoArray.get(move[1] - 1));
            } else {
                List<String> moveTemp = cargoArray.get(move[1] - 1)
                        .subList(cargoArray.get(move[1] - 1).size() - (move[0]), cargoArray.get(move[1] - 1).size());
                moveStack = new ArrayList<>(moveTemp);
            }
            ArrayList<String> fromStack = cargoArray.get(move[1] - 1);
            ArrayList<String> toStack = cargoArray.get(move[2] - 1);
            for (int i = 0; i < moveStack.size(); i++) {
                System.out.println(cargoArray);
                System.out.println(moveStack);
                fromStack.remove(fromStack.size() - 1);
            }
            toStack.addAll(moveStack);
            cargoArray.set(move[1] - 1, fromStack);
            cargoArray.set(move[2] - 1, toStack);
        }
        return cargoArray;
    }

    private static int[] getNextMove(String line) {
        int numCrates = Integer.parseInt(line.split("from")[0].replaceAll("[\\D]", ""));
        int fromIdx = Integer.parseInt(line.split("from")[1].split("to")[0].replaceAll("[\\D]", ""));
        int toIdx = Integer.parseInt(line.split("from")[1].split("to")[1].replaceAll("[\\D]", ""));
        int[] currentMove = { numCrates, fromIdx, toIdx };
        return currentMove;
    }

    private static ArrayList<ArrayList<String>> buildInitialStack(Scanner reader) {
        ArrayList<ArrayList<String>> cargoArray = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 9; i++) {
            cargoArray.add(new ArrayList<String>());
        }
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.isEmpty()) {
                break;
            }
            for (int i = 0; i < 9; i++) {
                String[] currentRow = line.split("");
                if (currentRow[1].trim().equals("1")) {
                    break;
                }
                if (!currentRow[i * 4 + 1].trim().isEmpty()) {
                    cargoArray.get(i).add(0, currentRow[i * 4 + 1].trim());
                }
            }
        }

        return cargoArray;
    }
}