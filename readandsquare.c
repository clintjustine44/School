#include <stdio.h>

int getNum(void)
{
    int numIn;

    printf("Enter your number: \n");
    scanf("%d", &numIn);
    return numIn;
}

int sqr(int x)
{
    return (x * x);
}

void printOne(int x)
{
    printf("The value is: %d\n", x);
    return;
}

int main()
{
    int num;
    int sqrd;

    num = getNum();
    sqrd = sqr(num);
    printOne(sqrd);

    return 0;
}