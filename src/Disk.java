import java.lang.*;
public class Disk implements Comparable<Disk>{
    
    private int id;
    private List<Integer> folders;
    private int space;
    private static int disksCreated = 0;
	
    //Constructor
    Disk(){
        this.id = disksCreated;
        disksCreated++;
        this.folders = new List<Integer>(); 
        this.space = 1000000;
    }

    //Returns a Disk's free space in MB
    public int getFreeSpace(){
        return space;
    }

    //Inserts at the back of the list that contains a Disk's folders' sizes a new folder's size.
    public void addAFolder(int i){
        this.folders.insertAtBack(i);
        this.space -= i;
    }

    //Override
    public int compareTo(Disk D){
        if (getFreeSpace() > D.getFreeSpace()){
            return 1;
        }
        else if (getFreeSpace() < D.getFreeSpace()){
            return -1;
        }
        else 
            return 0;
    }

    public static int totalDisks(){
        return disksCreated;
    }

    //Resets disksCreated variable to 0
    public static void resetDiskAmmount(){
        disksCreated = 0;
    }

    //Prints a Disk's information following the task's given example
    public void printDisk(){
        System.out.print("id " + this.id + " " + getFreeSpace() + " : ");
        folders.printList();
    }
}