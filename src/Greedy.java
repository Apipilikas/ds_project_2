public class Greedy{
    public static void main (String[] args){
        GreedyImp solution = new GreedyImp(args[0]);
        solution.inpToArr();
        if (GreedyImp.readCorrectly){
            solution.input();
            solution.output();
        }
    }
}