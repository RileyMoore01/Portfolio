/*
Riley Moore
File_name: Quadratic_probing.c
Specification: Using quadratic probing to insert the followin keys into the hash tabele
For: CS 2413 Data structures Section 501
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

//Function to print the array
void print_array(int arr[], int n){
  for(int i = 0; i < n; i++){
    printf("%d ", arr[i]);
  }
}

//Function to implement Quadratic Probing
void hasing(int table[], int tsize, int arr[], int N){
  //Iterating through the array
  for(int i = 0; i < N; i++){
    //Computing the hash value
    int hv = arr[i] % tsize;

    //Insert in the table if their is no collision
    if(table[hv] == -1)
      table[hv] = arr[i];
    else{
      //If there is a collision iterating through all possible quadratic values
      for(int j = 0; j < tsize; j++){
        //Computing the new hash value
        int t = (hv + j * j) % tsize;
        if(table[t] == -1){
          //Break the loop after insertin the value in teh table
          table[t] = arr[i];
          break;
        }
      }
    }
  }
  print_array(table, N);
}

void search(int key, int L, int table[]){
  int index, i, flag = 0, hkey;
  hkey = key % L;
  for(i = 0;i < L; i++){
    index = (hkey + i*i) % L;
    if(table[index] == key){
      printf("Value is found at index %d",index);
      break;
    }
  }
  //error message if element not found
  if(i == L)
   printf("\n Value is not found\n");
}


//Driver Code
int main(void) {
  int arr[] = {50, 700, 76, 85, 92, 73, 101};   //Keys for hasing
  int N = 7;

  int L = 10;     //Size of the hash table
  int hash_table[10];

  //Initalizing the hash table
  for(int i = 0; i < L; i++){
    hash_table[i] = -1;
  }

  //Quadratic Probing
  hasing(hash_table, L, arr, N);
  return 0;
}

