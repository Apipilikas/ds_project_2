import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ExperimentPrtD{

    int[] n;    //Periexei ta diaforetika N, diladi ta plithi twn fakelwn pros apothikeusi.
    Random random = new Random();

    public ExperimentPrtD(int[] n){
        this.n = n;
        createFiles();
    }

    public void createFiles(){

        String newFileName;
        
        for (int i = 0; i < n.length; i++){
            for (int j = 1; j <= 10; j++){
                newFileName = "File_" + n[i] + "_" + j + ".txt";
                writeFile(newFileName, n[i]);
            }
        }
    }

    public void writeFile(String fileName, int n){

        FileWriter writer = null;
        try{
            writer = new FileWriter(new File("data\\" + fileName));
            for (int c = 1; c <= n; c++){
                if (c == n)
                    writer.write(random.nextInt(1000000) + "");
                else
                    writer.write(random.nextInt(1000000) + "\n");
            }
            writer.close();
        }
        catch(IOException ex){
            System.err.println("Problem occured while writing Files.");
        }
    }

    public int testingGreedy(String data){
        Disk.resetDiskAmmount();
        GreedyImp solution1 = new GreedyImp(data);
        solution1.inpToArr();
        solution1.input();
        
        return Disk.totalDisks();
    }

    public int testingGreedyDecreasing(String data){
        Disk.resetDiskAmmount();
        GreedyImp solution2 = new GreedyImp(data);
        solution2.inpToArr();
        Sort sorting = new Sort(solution2.getArr());
        solution2.setArr(sorting.getSortedArr());
        solution2.input();
            
        return Disk.totalDisks();
    }

    public float[][] experiment(){
        float[][] results = new float[n.length][2];
        for (int i = 0; i < n.length; i++){
            int tGreedyDisks = 0;
            int tGreedyDecDisks = 0;    
            for (int j = 1; j <= 10; j++){
                String data = "data\\File_" + n[i] + "_" + j + ".txt";
                tGreedyDisks += testingGreedy(data);
                tGreedyDecDisks += testingGreedyDecreasing(data);
            }
            results[i][0] = tGreedyDisks / 10.0f;
            results[i][1] = tGreedyDecDisks / 10.0f; 
        }
        return results;
    }
}