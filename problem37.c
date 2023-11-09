#include <stdio.h>

float roundOff(float num);

int main()
{
    float input;
    float result;

    // Input
    printf("Enter a floating-point number: \n");
    scanf("%f", &input);

    // Process
    result = roundOff(input);

    // Output
    printf("Original Number: %.6f\n", input);
    printf("Rounded Number: %.6f\n", result);

    return 0;
}

float roundOff(float num)
{
    // Multiply by 100 to move two decimal places to the left.
    int temp = (int)(num * 100 + 0.5);

    // Divide by 100 to get the new value that is rounded to two decimal places.
    float roundedNumber = (float)temp / 100.0;
    
    return roundedNumber;
}