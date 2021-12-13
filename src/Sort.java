public class Sort{
    private int[] arrToSort;

    Sort(int[] arr){
        this.arrToSort = arr;
        qSort(this.arrToSort, 0, this.arrToSort.length - 1);
    }
    
    //Based on lessons' presentation slides.
    public void qSort(int[] a, int p, int r){
        if (r <= p) return;
        int i = partition(a, p, r);
        qSort(a, p, i - 1);
        qSort(a, i + 1, r);
    }

    //pivot = a[r]
    public int partition(int[] a, int p, int r){
        int i =  p - 1;
        int j = r;
        int pvt = a[r];
        for(;;){
            while (a[++i] > pvt);
            while (pvt > a[--j])
                if (j == p) break;
            if (i >= j) break;
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        int t = a[i];
        a[i] = a[r];
        a[r] = t;

        return i;
    }

    public int[] getSortedArr(){
        return this.arrToSort;
    }
}