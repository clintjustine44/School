#include <stdio.h>
#include <math.h>

void triangle(double a, double b);

int main() {
    double sideA, sideB;

    printf("Enter the length of side A: ");
    scanf("%lf", &sideA);

    printf("Enter the length of side B: ");
    scanf("%lf", &sideB);

    // Call the custom function to compute perimeter and area
    triangle(sideA, sideB);

    return 0;
}

// User-defined function to compute the perimeter and area of a right triangle
void triangle(double a, double b)
{
    double perimeter;
    double area;
    double c = 0.0;
    
    c = sqrt(a * a) + (b * b);

    perimeter = a + b + c;

    // Calculate the area using the formula: 0.5 * a * b
    area = 0.5 * a * b;

    // Display the computed perimeter and area
    printf("\nPerimeter: %.2lf\n", perimeter);
    printf("Area: %.2lf\n", area);
}