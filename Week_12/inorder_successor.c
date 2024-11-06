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
    tree* loc;
    tree* temp = malloc(sizeof(tree));
    temp -> data = ele;
    temp -> lchild = NULL;
    temp -> rchild = NULL;
    if(*t == NULL){
        *t = temp;
        return;
    }
    tree* i = *t;
    while(i != NULL)
    {
        if(ele < i -> data){
            loc = i;
            i = i -> lchild;
        }
        else if(ele > i -> data){
            loc = i;
            i = i -> rchild;
        }
    }
    if(ele < loc -> data)
        loc -> lchild = temp;
    else if(ele > loc -> data)
        loc -> rchild = temp;
}
void inorder(tree* head, int arr[])
{
    static int i = 0;
    if(head == NULL)
        return;
    inorder(head -> lchild, arr);
    arr[i] = head -> data;
    i++;
    inorder(head -> rchild, arr);
}
int find(int arr[], int ele, int n) {
    for(int i = 0; i< n - 1; i++){
        if(arr[i] == ele)
            return arr[i+1];
    }
    printf("%d has no successor\n", ele);
    return -263;
}
int main(void)
{
    tree* head = NULL;
    int arr[100];
    int ele, n, i, succ;
    printf("Enter the number of elements in the BST\t");
    scanf("%d", &n);
    printf("Enter the BST elements\n");
    for(i = 0; i<n; i++){
        scanf("%d", &ele);
        insert(&head, ele);
    }
    inorder(head, arr);
    printf("Enter an element\n");
    scanf("%d", &ele);
    succ = find(arr, ele, n);
    if(succ != -263)
        printf("The inorder successor of %d is %d\n", ele, succ);
    return 0;
}