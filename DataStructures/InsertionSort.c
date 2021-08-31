/*
Riley Moore
File_name: Insertion_Sort.c
Specification: Using isnertion sort to sort an array
For: CS 2413 Data structures Section 501
*/
#include <stdio.h>
#include <math.h>

//Functino to sort the array using Insertion Sort
void insertion_sort(int arr[], int n){
  int i, key, j;
  for(i = 1; i < n; i++){
    key = arr[i];
    j = i - 1;

    while(j >= 0 && arr[j] > key){
        arr[j + 1] = arr[j];
        j = j - 1;
    }
    arr[j + 1] = key;
  }
}

//Function to print the array
void print_array(int arr[], int n){
  for(int i = 0; i < n; i++){
    printf("%d ", arr[i]);
  }
  printf("\n");
}

int main(void) {
  int arr[] = {4, 12, 7, 8, 2, 5, 15};
  int n = sizeof(arr) / sizeof(arr[0]);

  printf("Time complexity(s): \n");
  printf("\tBest Case: O(n)\n");
  printf("\tWorst Case: O(n^2)\n\n\n");
  
  printf("The current arr is: \n");
  print_array(arr, n);
  printf("The new sorted array is: \n");
  insertion_sort(arr, n);
  print_array(arr, n);

  return 0;
}
