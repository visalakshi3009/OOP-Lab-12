#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int preValue = 0;
typedef struct t
{
    char data;
    struct t* lchild;
    struct t* rchild;
}tree;
void inorder(tree* head)
{
    if(head == NULL)
        return;
    inorder(head -> lchild);
    printf("%c\t", head -> data);
    inorder(head -> rchild);
}
tree* create_tree(char* pre, char* post, int postStart, int postEnd)
{
    if(preValue >= strlen(pre) || postStart > postEnd)
        return NULL;
    tree* head = malloc(sizeof(tree));
    head -> data = pre[preValue++];
    if(postStart == postEnd || preValue >= strlen(pre))
        return head;
    int postValue;
    for(int i = 0; i<= strlen(post); i++)
    {
        if(post[i] == pre[preValue])
            postValue = i;
    }
    head -> lchild = create_tree(pre, post, postStart, postValue);
    head -> rchild = create_tree(pre, post, postValue + 1, postEnd - 1);
    return head;
}
int main(void)
{
    tree* head = NULL;
    char* pre = malloc(100 * sizeof(char));
    char* post = malloc(100 * sizeof(char));
    printf("Enter the preorder traversal\n");
    scanf("%s", pre);
    printf("Enter the postorder traversal\n");
    scanf("%s", post);
    head = create_tree(pre, post, 0, strlen(post) - 1);
    printf("The tree is\n");
    inorder(head);
    printf("\n");
    return 0;
}