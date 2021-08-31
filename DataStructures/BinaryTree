/*
Author: Riley Moore
File_name: Problem1.c
Specification: Creating a menu for binary tree use
For: CS 2413 Data structures Section 501
*/
#include <stdio.h>
#include <stdlib.h>

//struct for BST
struct BST{
int data;
struct BST *left;
struct BST *right;
};

typedef struct BST NODE;
NODE *node;

/*
Name: createtree
Input_param: node, data
Output_param: temp
Purpose: creating nodes in bst
*/
NODE* createtree(NODE *node, int data){
  if (node == NULL){
    NODE *temp;
    temp= (NODE*)malloc(sizeof(NODE));
    temp->data = data;
    temp->left = temp->right = NULL;
    return temp;
  }
  if (data < (node->data)){
    node->left = createtree(node->left, data);
  }
  else if (data > node->data){
    node -> right = createtree(node->right, data);
  }
  return node;
  }

/*
Name: search
Input_param: node, data
Output_param: node
Purpose: search the tree for an eleement
*/
NODE* search(NODE *node, int data){
  if(node == NULL)
    printf("\nElement not found");
  else if(data < node->data){
    node->left=search(node->left, data);
  }
  else if(data > node->data){
    node->right=search(node->right, data);
  }
  else
    printf("\nElement found is: %d", node->data);

  return node;
}

/*
Name: inorder
Input_param: node
Output_param:none
Purpose: inorder traversal of the tree
*/
void inorder(NODE *node){
  if(node != NULL){
    inorder(node->left);
    printf("%d\t", node->data);
    inorder(node->right);
  }
}

/*
Name: preorder
Input_param: node
Output_param:none
Purpose: preordering the tree
*/
void preorder(NODE *node){
  if(node != NULL){
    printf("%d\t", node->data);
    preorder(node->left);
    preorder(node->right);
  }
}

/*
Name: postorder
Input_param: node
Output_param:noen
Purpose: sorting order of the tree
*/
void postorder(NODE *node){
  if(node != NULL){
    postorder(node->left);
    postorder(node->right);
    printf("%d\t", node->data);
  }
}

/*
Name: findMin
Input_param: node
Output_param: none
Purpose: finding the min element in bst
*/
NODE* findMin(NODE *node){
  if(node==NULL)
    return NULL;

  if(node->left)
    return findMin(node->left);

  else
    return node;
}

/*
Name: del
Input_param: node, data
Output_param:node
Purpose: deleting a node
*/
NODE* del(NODE *node, int data){
  NODE *temp;
  if(node == NULL){
  printf("\nElement not found");
  }
  else if(data < node->data){
    node->left = del(node->left, data);
  }
  else if(data > node->data){
    node->right = del(node->right, data);
  }
  else{ /* Now We can delete this node and replace with either minimum element in the right sub tree or maximum element in the left subtree */
    if(node->right && node->left){ /* Here we will replace with minimum element in the right sub tree */
      temp = findMin(node->right);
      node -> data = temp->data;
      node -> right = del(node->right,temp->data);
    }
    else{
      temp = node;
      if(node->left == NULL)
        node = node->right;
      else if(node->right == NULL)
        node = node->left;
        free(temp); /* temp is longer required */
    }
  }
return node;
}

/*Show printing path*/
void print_paths_recur(NODE *node, int path[], int path_len);
void print_array(int ints[], int len);

/*Function to store all the paths from the root node to all leaf nodes in a array*/
void print_paths(NODE *node){
int path[1000];
print_paths_recur(node, path, 0);
}

/*Function which helps the print_path to recursively print all the nodes*/
void print_paths_recur(NODE *node, int path[], int path_len){
if (node == NULL)
return;

path[path_len] = node->data;
path_len++;

if (node->left == NULL && node->right == NULL){
print_array(path, path_len);
}
else{
print_paths_recur(node->left, path, path_len); //recursively calls the left node of the tree
print_paths_recur(node->right, path, path_len); //recursively calls the right node of the tree
}
}

/*Function to print all the paths */
void print_array(int ints[], int len){
int i;
for (i = 0; i < len; i++){
printf("%d -> ", ints[i]);
}
printf("\n");
}

/*to create the format display for program*/
int main(){
int data, ch, i, n;
NODE *root=NULL;
while (1){
printf("\n1.Create an integer Binary Tree");
printf("\n2.Display Tree in console ");
printf("\n3.Remove a specific node in the tree");
printf("\n4.Search an item in Binary Tree \n5.Exit");
printf("\nEnter your choice: ");
scanf("%d", &ch);

switch (ch){
  /*To insert an item*/
  case 1: printf("\n How many items you want to enter?: " );
    scanf("%d", &n);
    printf("\nEnter the values to create BST separated by space\n");
    for(i=0; i<n; i++){
    scanf("%d", &data);
    root=createtree(root, data);
    }
    break;

  /*To do inorder traversal*/
  case 2: printf("\nInorder Traversal: \n");
    inorder(root);
    printf("\nPreorder Traversal: \n");
    preorder(root);
    printf("\nPostorder Traversal: \n");
    postorder(root);
    break;

  /*Enter the element to delete*/
  case 3: printf("\nEnter the element to delete: ");
    scanf("%d", &data);
    root=del(root, data);
    break;
  /*Search for an element*/
  case 4: printf("\nEnter the element to search: ");
    scanf("%d", &data);
    root=search(root, data);
    printf("\n Visiting trace path \n");
    print_paths(root);
    break;

  /*Exit the prorgam*/
  case 5: exit(0);
    default:printf("\nWrong option");
    break;
}
}
}
