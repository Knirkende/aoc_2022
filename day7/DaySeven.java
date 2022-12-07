import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class DaySix {

    private static ElfDirectory root = new ElfDirectory("/", 0);
    private static ElfDirectory cwd = root;

    public static void main(String[] args) {
        try {
            File file = new File("inp.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String commandLine = reader.nextLine();
                parseCommand(commandLine);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        // part one
        long a = root.sumPartOne();
        System.out.println((a));

        // part two
        long freeSpace = 70000000 - root.getTotalSize();
        long neededSpace = 30000000 - freeSpace;
        ArrayList<Long> dirList = root.sumPartTwo(new ArrayList<Long>(), neededSpace);
        Collections.sort(dirList);
        System.out.println(dirList.get(0));
    }

    private static void parseCommand(String line) {
        if (line.startsWith("$ cd")) {
            String dirName = line.split(" ")[2];
            if (dirName.equals("..")) {
                cwd = cwd.getParent();
                return;
            } else if (dirName.equals("/")) {
                cwd = root;
                return;
            } else {
                ElfDirectory newDirObj = new ElfDirectory(dirName, 0);
                cwd.addChild(newDirObj);
                cwd = newDirObj;
                return;
            }
        } else if (line.startsWith("$ ls") || line.startsWith("dir")) {
            return;
        } else {
            long size = Long.parseLong(line.split(" ")[0]);
            cwd.addFile(size);
        }
    }
}