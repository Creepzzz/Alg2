/**
 * CODE FOR ASSIGNMENT 1-3 SORTING LAB
 * Author: Matilda Qvick 001105-0606
 * Written: 15/9 - 2020
 * Last updated: 20/9 - 2020
 * Purpose: The user puts in wanted size of array, then
 *          wanted elements. The array is then sorted with insertion
 *          sort, displaying all swaps of two elements in stdout.
 *          For assignment 2 there is an implementation of a swap counter
 *          and the number of swaps are displayed in stdout.
 *          For assignment 3 there is a method implemented that counts the
 *          number of inversions in the given array. The inversions are then
 *          displayed in the format "[i,a[i]], [j, a[j]]" where i and j are
 *          the indexes of the elements and the a[i], b[j] the elements themselves.
 * How to use: The user is asked to enter an assignment number into stdin. The user
 *             will then be asked to enter wanted size of array and then the wanted
 *             elements.
 *             Assignment 1: The stdout will display every step of a switch in the
 *             insertion sort and then the sorted array.
 *             Assignment 2: Everything in assignment 1 but the number of swaps are
 *             displayed.
 *             Assignment 3: Everything in assignment 1 but the number of inversions
 *             in the unsorted array are displayed.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class InsertionSort {
    private static int size;
    private static long numberOfSwaps;
    private static int numberOfInversions;
    private static long time;

    /**
     * Method implements insertion sort
     * Worst case time complexity = n^2
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
     * Counts the number of inversions of two elements in unsorted array
     * Worst case time complexity = n^2
     * @param a the array we want to know the number of inversions of
     */
    private static void inversionCount(int[] a) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (a[i] > a[j]) {
                    StdOut.println("Pos, element: [" + (i) +","+ a[i] + "], [" + (j) +"," + a[j] + "]");
                    numberOfInversions++;
                }
            }
        }
    }

    /**
     * Prints the array in it's current state
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
        for (int i = 1; i <= size-1; i++)
            if ((a[i] < a[i - 1])) return false;
        return true;
    }


    /**
     * User enters number of assignment, then size of array and elements
     * The array sorting is then printed out in each step.
     * The sorted array is printed out as well as (number of swaps for
     * assignment 2 and number of inversions as well as
     * inversions of unsorted array for assignment 3)
     * The array is checked with method isSorted as a test.
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
        if (nr == 3) {
            inversionCount(testArray);
            StdOut.println("Number of inversions: " + numberOfInversions);
        }
        printArray(testArray, size);
        sort(testArray);
        assert isSorted(testArray);
        StdOut.println("\nSorted array: " + "\t");
        printArray(testArray, size);
        if (nr == 2) {
            StdOut.println("Number of swaps: " + numberOfSwaps);
        }
    }
}