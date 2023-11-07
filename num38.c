#include <stdio.h>

int Ceiling(float origNum);
int Floor(float origNum);
float Round(float origNum);

int main()
{
    float origNum;
    int ceilingValue;
    int floorValue;
    float roundedValue;


    printf("Enter a floating-point number: \n");
    scanf("%f", &origNum);

    ceilingValue = Ceiling(origNum);
    floorValue = Floor(origNum);
    roundedValue = Round(origNum);

    printf("Original Number: %.6f\n", origNum);
    printf("Ceiling Value: %d\n", ceilingValue);
    printf("Floor Value: %d\n", floorValue);
    printf("Rounded Value: %.6f\n", roundedValue);

    return 0;
}

int Ceiling(float origNum)
{
    int intPart = (int)origNum;
    if (origNum > intPart)
    {
        return (intPart += 1);
    }
    else
    {
        return (intPart);
    }
}

int Floor(float origNum)
{
    int intPart = (int)origNum;
    if (origNum < intPart)
    {
        return (intPart -= 1);
    }
    else
    {
        return (intPart);
    }
}

float Round(float origNum)
{
    int intPart = (int)origNum;
    float decimalPart = origNum - intPart;
    if (decimalPart >= 0.5)
    {
        return (intPart += 1);
    }
    else
    {
        return (intPart);
    }

}