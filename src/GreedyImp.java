import java.io.*;
import java.text.ParseException;

public class GreedyImp{

    public MaxPQ pq;
    String path;
    Disk newDisk;
    float totalSize = 0;
    static boolean readCorrectly = true;
    int numOfLines = 0; //N
    private static int[] sizesArr;

    public GreedyImp(String path){
        this.pq = new MaxPQ();
        this.path = path;
    }

    //Iterrates through previously created 1D array and calls chooseDiskAndAddFolder() method
    public void input(){
        for (int i = 0; i < numOfLines; i++){          
            chooseDiskAndAddFolder(sizesArr[i], pq);
            totalSize += sizesArr[i];
        }
    }
    
    /*
      Scans txt file to check if input is valid and counts the number of 
      given sizes. Then creates a 1D Array using the previously counted number
      and copies .txt file's contents into the Array.
    */
    public void inpToArr(){
        String line;
        int intLine;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(new File(path)));
            line = reader.readLine();

            while (line != null && readCorrectly){
                intLine = Integer.parseInt(line);
                if (intLine >= 0 && intLine <= 1000000){
                    numOfLines++;
                    line = reader.readLine();
                }
                else{
                    readCorrectly = false;
                    throw new IOException();
                }
            }
            reader.close();

            sizesArr = new int[numOfLines];
            reader = new BufferedReader(new FileReader(new File(path)));
            for (int i = 0; i < numOfLines; i++){
                line = reader.readLine();
                intLine = Integer.parseInt(line);
                sizesArr[i] = intLine;
            }
        }
        catch(IOException ex){
            readCorrectly = false;
            System.err.println(">!< Error reading the file. >!<");
        }
        catch(NumberFormatException ex){
            readCorrectly = false;
            System.err.println(">!< Invalid input. >!<");
        }
    }

    /*Checks if the passed folder size (intLine) can be added
      in Priority Queue's root item (Disk) (which has the MOST free space).
      If there's enough space in it then the folder gets added and the root Disk
      gets sunk.
      If that's not possible then we cannot add it to none of the rest Disks
      so we create a new Disk, we add the passed folder size to it and we call insert() which will
      put the disk in the correct position in the Priority Queue. 
    */
    public void chooseDiskAndAddFolder(int intLine, MaxPQ pq){
        if (pq.isEmpty()){
            Disk newDisk = new Disk();
            newDisk.addAFolder(intLine);
            pq.insert(newDisk);
        }
        else if (pq.peek().getFreeSpace() >= intLine){
            pq.peek().addAFolder(intLine);
            pq.sink(1);
        }
        else{
            Disk newDisk = new Disk();
            newDisk.addAFolder(intLine);
            pq.insert(newDisk);
        }
    }

    public int[] getArr(){
        return GreedyImp.sizesArr;
    }

    public static void setArr(int[] a){
        GreedyImp.sizesArr = a;
    }
      
    public void printArr(){
        for (int i = 0 ; i < sizesArr.length; i++)
            System.out.println(sizesArr[i]);
    }
    
    public void output(){
        System.out.println("% java Greedy read file: " + path);
        System.out.println("Sum of all folders: " + totalSize/1000000 + " TB");
        System.out.println("Total number of disks used: " + Disk.totalDisks());
        if (numOfLines <= 100){
            System.out.println("Disks: ");
            while ( !pq.isEmpty() ){
                pq.getMax().printDisk();
                System.out.println("");
            }
        }
    }
}