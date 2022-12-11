// starting items: worry level
// no items; end turn
// operation : change worry level, then divide it by 3
// test
// each monkey goes through all items
// throw: add to end of other monkey's list

// each monkey has 1 turn = 1 round

// 20 rounds
// count number of times each monkey inspects an item
// multiply together top 2

// (a mod n) mod n = a mod n.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.math.BigInteger;

class DayEleven {
    public static List<Monkey> monkeyList = new ArrayList<Monkey>();
    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);
            generateMonkeys(reader);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        //solvePartOne();
        solvePartTwo();
    }

    public static void solvePartOne() {
        startMonkeyBusiness(false, 20, -1);
        int[] scores = new int[monkeyList.size()];
        for (int i = 0; i < monkeyList.size(); i++) {
            scores[i] = monkeyList.get(i).getInspectionNumber();
        }
        Arrays.sort(scores);
        System.out.println(Arrays.toString(scores));
    }

    public static void solvePartTwo() {
        int mod = 1;
        for (int i = 0; i < monkeyList.size(); i++) {
            mod *= monkeyList.get(i).testParam;
        }
        startMonkeyBusiness(true, 10000, mod);
        int[] scores = new int[monkeyList.size()];
        for (int i = 0; i < monkeyList.size(); i++) {
            scores[i] = monkeyList.get(i).getInspectionNumber();
        }
        Arrays.sort(scores);
        System.out.println(Arrays.toString(scores));
    }

    public static void startMonkeyBusiness(boolean partTwo, int iterations, int mod) {
        for (int i = 0; i < iterations; i++) {
            for (int m = 0; m < monkeyList.size(); m++) {
                Monkey currentMonkey = monkeyList.get(m);
                while (currentMonkey.items.size() > 0) {
                    BigInteger[] business = currentMonkey.popLastItem(partTwo, mod);
                    monkeyList.get(business[0].intValue()).addItem(business[1]);
                }
            }
            if (i % 1000 == 0) {
                System.out.println("ROUND NUMBER " + i);
                for (int j = 0; j < monkeyList.size(); j++) {
                    System.out.println("MONKEY: " + monkeyList.get(j).getMonkeyID());
                    System.out.println(monkeyList.get(j).getInspectionNumber());
                }
            }
        }
    }

    public static void generateMonkeys(Scanner reader) {
        int monkeys = 8; // TODO change to 4 for test set, 8 for input set

        for (int i = 0; i < monkeys; i++) {
            int inspectVal = -1;
            reader.nextLine();
            List<BigInteger> itemList = new ArrayList<BigInteger>();
            String[] items = reader.nextLine().split(": ")[1].split(", ");
            for (int j = 0; j < items.length; j++) {
                itemList.add(BigInteger.valueOf(Integer.parseInt(items[j])));
            }
            String opCommand = reader.nextLine();
            String inspectOperator = opCommand.split("old ")[1].split(" ")[0];
            String inspectTarget = opCommand.split("old ")[1].split(" ")[1];
            if (inspectTarget.equals("old")) {
                inspectOperator = "squared";
            } else {
                inspectVal = Integer.parseInt(inspectTarget);
            }
            int testParam = Integer.parseInt(reader.nextLine().split("by ")[1]);
            int trueTarget = Integer.parseInt(reader.nextLine().split("monkey ")[1]);
            int falseTarget = Integer.parseInt(reader.nextLine().split("monkey ")[1]);
            Monkey newMonkey = new Monkey(itemList, i, trueTarget, falseTarget, inspectOperator, inspectVal, testParam);
            monkeyList.add(newMonkey);
            if (reader.hasNextLine()) {
                reader.nextLine();
            }
        }
    }

}

class Monkey {

    public List<BigInteger> items;
    private int hasInspected;
    private int monkeyID;
    private int trueTarget;
    private int falseTarget;
    private String inspectOperator;
    private int inspectVal;
    public int testParam;
  
    public Monkey(List<BigInteger> items, int monkeyID, int trueTarget, int falseTarget,
            String inspectOperator, int inspectVal, int testParam) {
        this.items = items;
        this.monkeyID = monkeyID;
        this.trueTarget = trueTarget;
        this.falseTarget = falseTarget;
        this.inspectOperator = inspectOperator;
        this.inspectVal = inspectVal;
        this.testParam = testParam;
    }

    public void addItem(BigInteger item) {
        this.items.add(item);
    }

    private BigInteger inspectItem(BigInteger item, boolean partTwo, int mod) {
        BigInteger newVal = item;
        if (this.inspectOperator.equals("*")) {
            newVal = newVal.multiply(BigInteger.valueOf(this.inspectVal));
        } else if (this.inspectOperator.equals("squared")) {
            newVal = newVal.multiply(newVal);
        } else {
            newVal = newVal.add(BigInteger.valueOf(this.inspectVal));
        }
        if (partTwo) {
            newVal = newVal.mod(BigInteger.valueOf(mod));
            return newVal;
        }
        newVal = newVal.divide(BigInteger.valueOf(3));
        return newVal;
    }

    private int getTargetMonkey(BigInteger item) {
        if (item.mod(BigInteger.valueOf(testParam)) == BigInteger.valueOf(0)) {
            return trueTarget;
        } else {
            return falseTarget;
        }
    }

    public BigInteger[] popLastItem(boolean partTwo, int mod) {
        BigInteger currentItem = items.remove(items.size() - 1);
        currentItem = inspectItem(currentItem, partTwo, mod);
        int targetMonkey = getTargetMonkey(currentItem);
        this.hasInspected += 1;
        BigInteger[] result = {BigInteger.valueOf(targetMonkey), currentItem};
        return result;
    }

    public int getInspectionNumber() {
        return this.hasInspected;
    }

    public int getMonkeyID() {
        return this.monkeyID;
    }

}