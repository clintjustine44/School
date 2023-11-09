#include <stdio.h>

int Ceiling(double origNum);
int Floor(double origNum);
double Round(double origNum);

int main()
{
    double origNum;
    int ceilingValue;
    int floorValue;
    double roundedValue;

    // Input
    printf("Enter a floating-point number: \n");
    scanf("%lf", &origNum);

    // Process
    ceilingValue = Ceiling(origNum);
    floorValue = Floor(origNum);
    roundedValue = Round(origNum);

    // Output
    printf("Original Number: %.6lf\n", origNum);
    printf("Ceiling Value: %d\n", ceilingValue);
    printf("Floor Value: %d\n", floorValue);
    printf("Rounded Value: %.6lf\n", roundedValue);

    return 0;
}

// User-defined function to calculate the ceiling value.
int Ceiling(double origNum)
{
    // Extract the integer part of the number.
    int intPart = (int)origNum;

    /* Check if there is a fractional part
    ** If there is, add 1 to the integer part, else use the integer part
    */ 
    if (origNum > intPart)
    {
        return (intPart += 1);
    }
    else
    {
        return (intPart);
    }
}

// User-defined function to calculate the floor value.
int Floor(double origNum)
{
    // Extract the integer part of the number.
    int intPart = (int)origNum;
    
    /* Checks if there is a fractional part.
    ** If there is, use the integer part, else subtract 1 from the integer part
    */
    if (origNum < intPart)
    {
        return (intPart -= 1);
    }
    else
    {
        return (intPart);
    }
}

// User-defined function to calculate the rounded value.
double Round(double origNum)
{
    // Multiply by 100 to move two decimal places to the left.
    int temp = (int)(origNum * 100 + 0.5);

    // Divide by 100 to get the new value that is rounded to two decimal places.
    float roundedNumber = (float)temp / 100.0;
    
    return roundedNumber;
}
