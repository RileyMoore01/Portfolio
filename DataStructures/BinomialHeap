/*
  Author: Riley Moore
  Class: CS 2413 Data Structures 501
  FileName: Problem2.c
  Purpose: Creating a binomail heap, then deleting 11 from the heap
*/
#include<stdio.h>
#include <stdlib.h>
#include<malloc.h>

//Function Prototypes
int dec_key();
struct node* extract_min();
struct node* search();
struct node* heap_union();
struct node* bin_heap();
struct node* merge_heap();
int link();


//struct to store binomial heap
struct node {
    int n;    //value
    int degree;
    struct node* parent;
    struct node* child;
    struct node* sibling;
};

int count = 1;  //Count postion on the heap
struct node *H = NULL;  //Initalize the heap


//deleting a node from the heap
int heap_delete(struct node* H, int k) {
    struct node* np;
    if (H == NULL) {
        printf("\nHEAP EMPTY");
        return 0;
    }
    dec_key(H, k, -1000);
    np = extract_min(H);
    if (np != NULL)
        printf("\nNODE DELETED SUCCESSFULLY");
}

//decreasing a a key in the heap
int dec_key(struct node* H, int i, int k) {
    int temp;
    struct node* p;
    struct node* y;
    struct node* z;
    p = search(H, i);
    if (p == NULL) {
        printf("\nINVALID CHOICE OF KEY TO BE REDUCED");
        return 0;
    }
    if (k > p->n) {
        printf("\nSORY!THE NEW KEY IS GREATER THAN CURRENT ONE");
        return 0;
    }
    p->n = k;
    y = p;
    z = p->parent;
    while (z != NULL && y->n < z->n) {
        temp = y->n;
        y->n = z->n;
        z->n = temp;
        y = z;
        z = z->parent;
    }
    printf("\nKEY REDUCED SUCCESSFULLY!");
}

//function for searching a node in the heap
struct node* search(struct node* H, int k) {
    struct node* x = H;
    struct node* p = NULL;
    if (x->n == k) {
        p = x;
        return p;
    }
    if (x->child != NULL && p == NULL) {
        p = search(x->child, k);
    }
 
    if (x->sibling != NULL && p == NULL) {
        p = search(x->sibling, k);
    }
    return p;
}

//Finding min key in the heap
struct node* extract_min(struct node* H1) {
    int min;
    struct node* t = NULL;
    struct node* x = H1;
    struct node *Hr;
    struct node* p;
    Hr = NULL;
    if (x == NULL) {
        printf("\nNOTHING TO EXTRACT");
        return x;
    }
    //    int min=x->n;
    p = x;
    while (p->sibling != NULL) {
        if ((p->sibling)->n < min) {
            min = (p->sibling)->n;
            t = p;
            x = p->sibling;
        }
        p = p->sibling;
    }
    if (t == NULL && x->sibling == NULL)
        H1 = NULL;
    else if (t == NULL)
        H1 = x->sibling;
    else if (t->sibling == NULL)
        t = NULL;
    else
        t->sibling = x->sibling;
    if (x->child != NULL) {
        //REVERT_LIST(x->child);
        (x->child)->sibling = NULL;
    }
    H = heap_union(H1, Hr);
    return x;
}

//Funciton for merging the heap
struct node* heap_union(struct node* H1, struct node* H2) {
    struct node* prev_x;
    struct node* next_x;
    struct node* x;
    struct node* H = bin_heap();
    H = merge_heap(H1, H2);
    if (H == NULL)
        return H;
    prev_x = NULL;
    x = H;
    next_x = x->sibling;
    while (next_x != NULL) {
        if ((x->degree != next_x->degree) || ((next_x->sibling != NULL)
                && (next_x->sibling)->degree == x->degree)) {
            prev_x = x;
            x = next_x;
        } else {
            if (x->n <= next_x->n) {
                x->sibling = next_x->sibling;
                link(next_x, x);
            } else {
                if (prev_x == NULL)
                    H = next_x;
                else
                    prev_x->sibling = next_x;
                link(x, next_x);
                x = next_x;
            }
        }
        next_x = x->sibling;
    }
    return H;
}

//Functino for merging two heaps 
struct node* merge_heap(struct node* H1, struct node* H2) {
    struct node* H = bin_heap();
    struct node* y;
    struct node* z;
    struct node* a;
    struct node* b;
    y = H1;
    z = H2;
    if (y != NULL) {
        if (z != NULL && y->degree <= z->degree)
            H = y;
        else if (z != NULL && y->degree > z->degree)
            /* need some modifications here;the first and the else conditions can be merged together!!!! */
            H = z;
        else
            H = y;
    } else
        H = z;
    while (y != NULL && z != NULL) {
        if (y->degree < z->degree) {
            y = y->sibling;
        } else if (y->degree == z->degree) {
            a = y->sibling;
            y->sibling = z;
            y = a;
        } else {
            b = z->sibling;
            z->sibling = y;
            z = b;
        }
    }
    return H;
}

//Linking two binomial heaps
int link(struct node* y, struct node* z) {
    y->parent = z;
    y->sibling = z->child;
    z->child = y;
    z->degree = z->degree + 1;
}

//Function to initialize the heap
struct node* bin_heap() {
    struct node* np;
    np = NULL;
    return np;
}

//Main for funtion execution
int main() 
{
    int i, n, m, l;
    struct node* p;
    struct node* np;
    char ch;
    heap_delete(H, 11);
}
