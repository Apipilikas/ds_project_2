import java.lang.Comparable;
public class MaxPQ{

    private Disk[] heap;    //The heap to store data (Disk's) in
    private int size;       //Current size of priority queue
    
    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SZ = 4;

    public MaxPQ(){
        this.heap = new Disk[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    //Copies all contents of the previous Array to a new one which is bigger by 4 cells.
    public void mergeAndGrow(){
        //Creating the bigger Array
        Disk[] newHeap = new Disk[heap.length + AUTOGROW_SZ];

        //Copying previous array/heap to newHeap
        for (int i = 1; i <= size; i++)
            newHeap[i] = heap[i];

        heap = newHeap;
    }

    public void swap(int i, int j){
        Disk t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    public void sink(int i){
        int left = 2*i;             //left i's child (position)
        int right = left + 1;       //right i's child (position)

        //Checks if left, which is smaller than right, is already a leaf and if so returns
        if (left > size)
            return;
        else if (left == size){
            if (heap[i].compareTo(heap[left]) < 0)
                swap(i, left);
        }

        //If left isn't the only leaf then sinking process
        while (left <= size){
            int max = left;
            if (right <= size){     //checks if there is a right child
                if (heap[left].compareTo(heap[right]) < 0)
                    max = right;
            }

            if (heap[i].compareTo(heap[max]) >= 0)
                return;
            else{
                swap(i, max);
                i = max;            //i = left or right, depending on previous if-statement result.
                left = 2*i;
                right = left + 1;
            }//returns to while condition
        }//end while
    }

    //Inserts a new Disk into the correct position in the Priority Queue 
    public void insert(Disk item){
        if (size == heap.length - 1)
            mergeAndGrow();
        heap[++size] = item;
        swim(size);
    }

    public void swim(int i){
        //checking if i is root
        if (i == 1)
            return;
        //Checks if i's parent has less free space and if so swaps
        //Does the same continuously until the Disk becomes the root or its parent has more free space.
        int parent = i/2;
        while (i != 1 && heap[i].compareTo(heap[parent]) > 0){
            swap(i, parent);
            i = parent;
            parent = i/2;
        }
    }

    //Assigns the root Disk that needs to be returned to "root" Disk reference and sets as heap's root the last Disk in Priority Queue.
    //Then sinks the new root until it is in its correct position in the PQ.
    //Lastly returns the "root" Disk reference
    public Disk getMax(){
        if (size == 0){
            System.out.println("MaxPQ is empty.");
            return null;
        }

        Disk root = heap[1];

        //Replacing root with "last" (bottom and rightmost) leaf
        heap[1] = heap[size];
        size--;
        sink(1);
        return root;
    }

    public Disk peek(){
        return heap[1];
    }

    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }
}