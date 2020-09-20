import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {
    public static long time;
    public static long time2;
    public static int size;
    public static int CUTOFF = 0;

    /**
     * Method that takes in parts from the original array recursively
     * from the smallest parts, then bigger and bigger and sort the parts
     * coming in, merging the two smaller arrays coming in, sorting
     * the elements of the two and sends back the sorted array. The sorting
     * and merging happens recursively, thus the incoming arrays gets bigger
     * and bigger util the whole array is sorted.
     * @param src the source of the array
     * @param dst the destination in which merged array ends up in
     * @param lo  the lowest index of the array
     * @param mid the middle index of the array
     * @param hi  the highest index of the array
     */
    private static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];   // to ensure stability
            else                           dst[k] = src[i++];
        }
        assert isSorted(dst, lo, hi);
    }

    /**
     * Method that divides the array into smaller and smaller pieces
     * the sends the parts recursively to the merge function which sorts the
     * parts.
     * If the parted array is small enough we use insertion sort.
     * Worst case = n log n
     * @param src array source
     * @param dst array destination
     * @param lo low index of array
     * @param hi index of array
     */
    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        merge(src, dst, lo, mid, hi);
    }

    /**
     *
     * @param a first comparable object
     * @param b second comparable object
     * @return true if a is smaller than b
     */
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    /**
     * Sorts the incoming array with insertion sort
     * Worst case = n^2
     * @param a is comparable array to be sorted
     * @param lo is lowest index of array
     * @param hi is highest index of array
     */
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                swapper(a, j, j-1);
    }

    /**
     * Swaps tho elements in the array
     * @param a array
     * @param i first index
     * @param j second index
     */
    private static void swapper(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Checks if the array is sorted by checking if each value n+1
     * is bigger than n.
     * @param a array to check
     * @param lo low index number of array
     * @param hi high index number of array
     * @return true if the array is sorted
     */
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     *
     * @param args
     */
    public static void main(String[]args){
        StdOut.println("Enter assignment nr from 5-6: ");
        int nr = StdIn.readInt();
        if(nr == 6){
            StdOut.println("Enter size of cut-off: ");
            CUTOFF = StdIn.readInt();
        }
        StdOut.println("Enter size of array: ");
        size = StdIn.readInt();
        Comparable[] testArray = new Comparable[size];
        Comparable[] testArray2 = new Comparable[size];

        for (int i = 0; i < size; i++) {
            testArray[i] = StdRandom.uniform(100);
            testArray2[i] = StdRandom.uniform(100);
        }

        time = System.currentTimeMillis();
        sort(testArray, testArray, 0, size-1);
        time = System.currentTimeMillis()-time;

        //time2 = System.currentTimeMillis();
        //insertionSort(testArray2, 0, size-1);
        //time2 = System.currentTimeMillis()-time2;

        StdOut.println("Time merge sort: " + time);
       // StdOut.println("Time insertion sort: " + time2);
    }
}
