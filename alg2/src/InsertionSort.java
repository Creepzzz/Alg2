import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class InsertionSort {
    private static int size;
    private static long numberOfSwaps;
    private static int numberOfInversions;
    private static long time;

    /**
     * Method implements insertion sort
     * Worst case = n^2
     *
     * @param unsorted is the unsorted array
     */
    public static void sort(int[] unsorted) {
        time = System.currentTimeMillis();
        for (int i = 1; i < size; i++) {
            for (int j = i; (j > 0) && (unsorted[j] < unsorted[j - 1]); j--) {
                swapper(unsorted, j, j - 1);
                printArray(unsorted, unsorted.length);
            }
        }
        time = System.currentTimeMillis() - time;
    }

    /**
     * Swaps the position of two the current element and the element before it
     *
     * @param a array in which the elements exists in
     * @param i element that is to be swapped to the left
     * @param j element that is to be swapped to the right
     */
    private static void swapper(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
        numberOfSwaps++;
    }

    /**
     * Counts the number of inversions of two elements
     *
     * @param a the array we want to know the number of inversions of
     */
    private static void inversionCount(int[] a) {
        ;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (a[i] > a[j]) {
                    StdOut.println("Pos, element: [" + (i) +","+ a[i] + "] <--> [" + (j) +"," + a[j] + "]");
                    numberOfInversions++;
                }
            }
        }
    }

    /**
     * Prints the array in it's current state
     *
     * @param a array to be printed
     */
    public static void printArray(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println("\t");
    }

    /**
     * @param a array that supposedly is sorted
     * @return true if the whole array is sorted
     */
    private static boolean isSorted(int[] a) {
        for (int i = 0; i <= size; i++)
            if ((a[i] < a[i - 1])) return false;
        return true;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        StdOut.println("Enter assignment nr from 1-3: ");
        int nr = StdIn.readInt();

        StdOut.println("Enter size of array: ");
        size = StdIn.readInt();

        int testArray[] = new int[size];
        StdOut.println("Enter one element at the time: ");
        for (int i = 0; i < size; i++) {
            testArray[i] = StdIn.readInt();
        }
        sort(testArray);
        assert isSorted(testArray);
        StdOut.println("\nSorted array: " + "\t");
        printArray(testArray, size);
        if (nr == 2) {
            StdOut.println("Number of swaps: " + numberOfSwaps);
        }
        if (nr == 3) {
            inversionCount(testArray);
            StdOut.println("Number of inversions: " + numberOfInversions);
        }
    }
}