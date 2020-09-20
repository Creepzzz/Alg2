/*
CODE FOR ASSIGNMENT 4 ON SORTING LAB
Author: Matilda Qvick 001105-0606
Written: 15/9 - 2020
Last updated: 16/9 - 2020
Purpose: Takes input of array size and elements of array from stdin
         then sorts the array so that all negative integers are put before
		 the positive. The algortime is in place, meaning it uses no extra 
		 memory. The algortim for sorting has a time complexity between
		 n and n^2
How to use: The user puts wanted size of array and wanted elements into input
            then the array is sorted accoring to previous statement.
*/

#include<stdio.h>
#include<conio.h>

/*
Prints the array
*/
void printArray(int* p, int size) {
	for (int i = 0; i < size; i++) {
		printf("%d " , p[i]);
	}
}

/*
Takes unsorted array and the array size as parameters. 
Goes through the whole array and find the first positive number, another
pointer looks for the first negativa number after the positive and switches the two.
*/
void sort(int*p, int size) {
	int t = 0;
	int i = 0;
	while(t<size) {
		while (p[i] < 0) {
			i++;
		}
		t = i;
		while ((p[t] > 0)) {
			if (t + 1 < size) {
				t++;
			}
			else {
				return;
			}
		}
		int a = p[i];
	    p[i] = p[t];
	    p[t] = a;
	}
}


/*
User enters wanted size of array and then 
the wanted elements. The array is then sorted and
printed out. 
*/
int main(void) {
	printf("Enter size of array: ");
	printf("\n");
	int size;
	scanf("%d", &size);
	
	int p[10];
	printf("Enter positive and negative integers one by one: ");
	
	for (int i = 0; i < size; i++) {
		scanf("%d", &p[i]);
	}

	sort(p, size);
	printArray(p, size);
}