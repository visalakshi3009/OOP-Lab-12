#include<stdio.h>
#include<stdlib.h>
typedef struct t
{
    int data;
    struct t* lchild;
    struct t* rchild;
}tree;
typedef struct s
{
    tree** arr;
    int top;
}stack;
void push(stack* s, tree* ele)
{
    s -> arr[++s -> top] = ele;
}
tree* pop(stack* s)
{
    if(s -> top == -1){
        printf("Stack is empty\n");
        return NULL;
    }
    return s -> arr[s -> top--];
}
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
void delete(tree** head, int ele)
{
    tree* t = *head;
    tree* temp = NULL;
    tree* parent = NULL;
    int right = 0;
    while(t != NULL){
        if(ele < t -> data)
            t = t -> lchild;
        else if(ele > t -> data)
            t = t -> rchild;
        else{
            if(t -> lchild == NULL && t -> rchild == NULL){
                free(t);
                t = NULL;
                return;
            }
            else{
                parent = t;
                temp = t -> lchild;
                while(temp -> rchild != NULL){
                    parent = temp;
                    temp = temp -> rchild;
                    right = 1;
                }
                t -> data = temp -> data;
                if(temp -> lchild == NULL && temp -> rchild == NULL){
                    free(temp);
                    if(right == 1)
                        parent -> rchild = NULL;
                    else
                        parent -> lchild = NULL;
                    return;
                }
                else{
                    t -> lchild = temp -> lchild;
                    free(temp);
                    return;
                }
            }
        }
    }
}
void inorder(tree* head)
{
    int done = 0;
    tree* t = head;
    stack s;
    s.arr = (tree**)malloc(100 * sizeof(tree*));
    for(int i = 0; i<100; i++)
        s.arr[i] = (tree*)malloc(sizeof(tree));
    s.top = -1;
    while(done == 0){
        while(t != NULL){
            push(&s, t);
            t = t -> lchild;
        }
        if(s.top != -1){
            t = pop(&s);
            printf("%d\t", t -> data);
            t = t -> rchild;
        }
        else
            done = 1;
    }
    printf("\n");
}
int main(void)
{
    tree* head = NULL;
    int choice, flag, ele;
    printf("1.Insert element\n2.Delete element\n3.Display tree\n");
    do
    {
        printf("Enter your choice\n");
        scanf("%d", &choice);
        switch(choice)
        {
            case 1:
                printf("Enter an element\t");
                scanf("%d", &ele);
                insert(&head, ele);
                break;
            case 2:
                printf("Enter the element to be deleted\t");
                scanf("%d", &ele);
                delete(&head, ele);
                break;
            case 3:
                printf("The tree is\n");
                inorder(head);
                break;
            default:
                printf("Enter a valid choice\n");
        }
        printf("Enter 1 to continue, 0 to stop\n");
        scanf("%d", &flag);
    } while (flag == 1);
    return 0;
}