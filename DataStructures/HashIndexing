/*
Riley Moore
File_name: Hash-Index.c
Specification: program for implementing a linked list in a hashed index 
For: CS 2413 Data structures Section 501
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define HASHTABLE_SIZE 100    //deefining the  hash table size

typedef struct {
    char* key; // in this example, key is some long id
    char* value; // value is a string
} KeyValue;

//Function to print the hash table
void printHashtable(KeyValue* table[])
{
// things correctly!
    int i;
    for (i=0;i<HASHTABLE_SIZE;i++) {
        if (table[i] == NULL) {
            printf("%2d: -\n",i);
        } else {
            printf("%2d: <%s,%s>\n",i,table[i]->key,table[i]->value);
        }
    }
}

//function to preform the hash function
//Returns: percentage of the sum
int hashFunc(char str[])
{
   int sum = 0; 
   for(int i=0;i<str[i]!='\0';i++)
   {
        sum = sum + (int) str[i];
   }
   return sum%100;      
}

//Function to insert a value into the hash table
void insert(KeyValue* table[], char key[], char value[]) {
// insert a key and value
    printf("inserting %s,%s to the table...\n",key, value);
    int hash = hashFunc(key);
    int index = hash;
   if (table[index] != NULL) {
        //if collison then occupy next free index
         printf("collision!%d\n",index);
        while(table[index]!=NULL)
        {
                index++;
        }
        KeyValue* newEntry = (KeyValue*) malloc(sizeof(KeyValue));
        newEntry->key =key;
        newEntry->value =value;
        //insert at new index
        table[index] =newEntry;
        //printf("%d\t%s",table[index]->key,table[index]->value)
    }else {
// add the new key-value pair to the correct position
        printf("Entry inserted at position %d\n",index);
        KeyValue* newEntry = (KeyValue*) malloc(sizeof(KeyValue));
        newEntry->key = key;
        newEntry->value = value;
        table[index] = newEntry;
    }
}

//Function to search for a value in the hash table
char* search(KeyValue *table[], char key[]) {
// look up a key and return the value
    int hash = hashFunc(key);
    int index = hash;
        //record present
    if (table[index] != NULL) {
        if (table[index]->key == key)
        {
          return table[index]->value;
        }       
       //collison
      else{
        index++;
        if(table[index]!=NULL && table[index]->key == key){
          return table[index]->value;
          } else { 
            return NULL;
          }
      }
  } 
 //record not present
  else {
    return NULL;
  }
}

//Driver Code
int main(){
  KeyValue* hashtable[HASHTABLE_SIZE] = {NULL};
//inserting restaurants details into hash table
  insert(hashtable, "StarBucks","No:3,VS roads");
  insert(hashtable, "McDonalds","No:43,Higih roads");
  insert(hashtable,"PizzaHut","No:11,MK woods");
  insert(hashtable, "CopperKitchen","No:111,kaka roads");
  insert(hashtable, "Dominos","No:3,Donok roads");
  insert(hashtable, "RoofTop","No:3,VS roads");

  printHashtable(hashtable);
//search for restaurant address with their name
  printf("Lookup %s - result: %s\n", "Dominos",search(hashtable,"Dominos"));
  printf("Lookup %s - result: %s\n", "StarBucks",search(hashtable,"StarBucks"));


    return 0;

}
