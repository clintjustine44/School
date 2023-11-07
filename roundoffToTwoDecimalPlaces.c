#include <stdio.h>

float roundOff(float num);

int main()
{
    float input;
    float result;

    printf("Enter a floating-point number: \n");
    scanf("%f", &input);

    result = roundOff(input);
    printf("Original Number: %.6f:\n", input);
    printf("Rounded Number: %.6f\n", result);

    return 0;
}

float roundOff(float num)
{
    // Multiply by 100 to move two decimal places to the left.
    int temp = (int)(num * 100);

    // Divide by 100 to get the new value that is rounded to two decimal places.
    float roundedNumber = (float)temp / 100.0;
    
    return roundedNumber;
}