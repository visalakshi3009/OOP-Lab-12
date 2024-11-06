#include<stdio.h>
#include<stdlib.h>
typedef struct t
{
    int data;
    struct t* lchild;
    struct t* rchild;
}tree;
void insert(tree** t, int ele)
{
    int choice;
    if(*t == NULL){
        tree* temp = malloc(sizeof(tree));
        temp -> data = ele;
        temp -> lchild = NULL;
        temp -> rchild = NULL;
        *t = temp;
        return;
    }
    printf("Left child or right child?\n");
    scanf("%d", &choice);
    if(choice == 1){
        if((*t) -> lchild == NULL){
            tree* temp = malloc(sizeof(tree));
            temp -> data = ele;
            temp -> lchild = NULL;
            temp -> rchild = NULL;
            (*t) -> lchild = temp;
            return;
        }
        insert(&((*t) -> lchild), ele);
    }
    else{
        if((*t) -> rchild == NULL){
            tree* temp = malloc(sizeof(tree));
            temp -> data = ele;
            temp -> lchild = NULL;
            temp -> rchild = NULL;
            (*t) -> rchild = temp;
            return;
        }
        insert(&((*t) -> rchild), ele);
    }
}
int find(tree* head, int ele)
{
    if(head == NULL)
        return 0;
    if(head -> data == ele)
        return 1;
    if(find(head -> lchild, ele) || find(head -> rchild, ele))
    {
        printf("%d\t", head -> data);
        return 1;
    }
    return 0;
}
int main(void)
{
    tree* head = NULL;
    int n, ele, i, x;
    printf("Enter the number of elements in the binary tree\t");
    scanf("%d", &n);
    printf("Enter the binary tree elements\n");
    for(i = 0; i<n; i++){
        scanf("%d", &ele);
        insert(&head, ele);
    }
    printf("Enter an element\n");
    scanf("%d", &ele);
    printf("The ancestors of %d are\n", ele);
    find(head, ele);
    printf("\n");
    return 0;
}