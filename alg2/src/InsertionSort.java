//Input size of array
//Input elements
//Insertion sort with output on each move
//Unit tests in main
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class InsertionSort {
    private static int size;
    private static int array[] = new int[size];

    /**
     * Method implements insertion sort
     * @param unsorted is the unsorted array
     */
    public static void sort(int [] unsorted){
        for (int i = 1; i < size; i++){
            for (int j = i; j >0 &&(unsorted[j]<unsorted[j-1]); j--){
                swapper(unsorted, j, j-1);
                printArray(unsorted);
            }
        }
    }

    /**
     * Swaps the position of two the current element and the element before it
     * @param a array in which the elements exists in
     * @param i element that is to be swapped to the left
     * @param j element that is to be swapped to the right
     */
    private static void swapper(int[] a, int i, int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Prints the array in it's current state
     * @param a array to be printed
     */
    public static void printArray(int[] a){
        for (int i = 0; i<size; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println("\t");
    }


    /**
     * 
     * @param args
     */
    public static void main(String [] args){
        StdOut.println("Enter size of array: ");
        size = StdIn.readInt();
        StdOut.println("Enter one element at the time: ");

        int testArray[] = new int[size];
        for (int i = 0; i< size; i++){
            int k = StdIn.readInt();
            testArray[i] = k;
        }
        printArray(testArray);
        sort(testArray);
        StdOut.println("\nSorted array: " + "\t");
        printArray(testArray);
    }
}
