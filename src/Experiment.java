public class Experiment{
    public static void main(String[] args){
        int[] n ={100, 500, 1000};
        ExperimentPrtD exper = new ExperimentPrtD(n);
        float[][] results;
        results = exper.experiment();
        System.out.println("* * * * * * * * * * * Results * * * * * * * * * * * ");
        for (int i = 0; i < n.length; i++){
            System.out.println("Testing algorithms with " + n[i] + " folders");
            System.out.println("Average Greedy Disks: " + results[i][0]);
            System.out.println("Average Greedy-Decreasing Disks: " + results[i][1]);
            System.out.println("-------------------------------------------------");
        }
    }
}