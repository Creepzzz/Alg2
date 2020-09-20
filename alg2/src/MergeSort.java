/**
 * CODE FOR ASSIGNMENT 5-6 SORTING LAB
 * Author: Matilda Qvick 001105-0606
 * Written: 16/9 - 2020
 * Last updated 20/9 - 2020
 * Purpose: The code counts the execution time for insertion sort
 *          and merge sort. This is so the data can be compared and
 *          analyzed. The user puts in wanted size of the array, then the
 *          elements in the array are randomized and put into the array.
 *          The method currentTimeInMills is used to calculate the difference
 *          in time from start of execution to end of execution. The same array
 *          is sorted with both insertion sort and merge sort and the time is
 *          displayed in stdout.
 *          For assignment 6, the user enters wanted cut-off in stdin.
 * How to use: The user enters wanted assignment number, either 5 or 6.
 *             Assignment 5: The user puts in wanted array size and a randomized
 *             array is sorted with merge sort and insertion sort. The time is then
 *             displayed in stdout.
 *             Assignment 6: Everything in assignment 5 but the user enters wanted
 *             cut-off in stdin. The time is then displayed in stdout.
 */

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
     * @param a first comparable object
     * @param b second comparable object
     * @return true if a is smaller than b
     */
    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    /**
     * Sorts the incoming array with insertion sort
     * Worst case time complexity = n^2
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
        for (int i = lo+1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    /**
     * User puts in wanted assignment, and wanted array size, (and wanted cut-off
     * for assignment 6).
     * The array is randomized and copied into another second array.
     * The arrays are then sorted and the time for execution is calculated.
     * The times are then displayed in stdout.
     * The arrays are checked with method isSorted as a test.
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
        }
        System.arraycopy(testArray,0, testArray2, 0, size);

        time = System.currentTimeMillis();
        sort(testArray, testArray, 0, size-1);
        time = System.currentTimeMillis()-time;

        time2 = System.currentTimeMillis();
        insertionSort(testArray2, 0, size-1);
        time2 = System.currentTimeMillis()-time2;

        StdOut.println("Time merge sort: " + time);
        StdOut.println("Time insertion sort: " + time2);

        assert isSorted(testArray, 0 , testArray.length-1);
        assert isSorted(testArray2, 0, testArray2.length-1);
    }
}
