import java.util.*;

public class ElfDirectory {

    private long size;
    private ArrayList<ElfDirectory> next = new ArrayList<ElfDirectory>();
    private ElfDirectory parent = null;

    public ElfDirectory(String name, long size) {
        this.size = size;
    }

    public long getTotalSize() {
        long total = this.size;
        for(int i = 0; i < this.next.size(); i++) {
            total += this.next.get(i).getTotalSize();
        }
        return total;      
    }

    public ElfDirectory getParent() {
        return this.parent;
    }

    public void addChild(ElfDirectory child) {
        child.parent = this;
        this.next.add(child);
    }
    
    public void addFile(long size) {
        this.size += size;
    }

    public long sumPartOne() {
        long thisSize = this.getTotalSize();
        thisSize = thisSize <= 100000 ? thisSize : 0;
        for (int i = 0; i < this.next.size(); i++) {
            thisSize += this.next.get(i).sumPartOne();
        }
        return thisSize;        
    }

    public ArrayList<Long> sumPartTwo(ArrayList<Long> dirList, long spaceNeeded) {
        long thisSize = this.getTotalSize();
        if (thisSize >= spaceNeeded) {
            dirList.add(thisSize);
        }
        for (int i = 0; i < this.next.size(); i++) {
            this.next.get(i).sumPartTwo(dirList, spaceNeeded);
        }
        return dirList;        
    }

}
