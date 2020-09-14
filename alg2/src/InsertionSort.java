//Input size of array
//Input elements
//Insertion sort with output on each move
//Unit tests in main
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class InsertionSort {
    private static int size;
    private static int array[] = new int[size];

    public static void sort(int [] unsorted){
        for (int i = 1; i < size; i++){
            for (int j = i; j >0 &&(unsorted[j]<unsorted[j-1]); j--){
                swapper(unsorted, j, j-1);
                printArray(unsorted);
            }
        }
    }

    private static void swapper(int[] a, int i, int j){
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void printArray(int[] a){
        for (int i = 0; i<size; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println("\t");
    }


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
        StdOut.println("Sorted array: " + "\t");
        printArray(testArray);
    }
}
