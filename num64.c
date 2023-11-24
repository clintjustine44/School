/* This program computes the real roots of a quadratic equation.
**    Written by : Clint Justine A. Nepomuceno
**    Date : 11/25/2023
*/

#include <stdio.h>
#include <math.h>

// User-defined functions
void getData(int *a, int *b, int *c);
void calcRootandPrint(int a, int b, int c);

int main()
{
    // Input coefficients
    int a, b, c;

    // Calls a user-defined function to get values.
    getData(&a, &b, &c);

    calcRootandPrint(a, b, c);

    return 0;
}

// This function prompts the user to enter coefficients.
void getData(int *a, int *b, int *c)
{
    printf("Enter coefficient a: ");
    scanf("%lf", &a);

    printf("Enter coefficient b: ");
    scanf("%lf", &b);

    printf("Enter coefficient c: ");
    scanf("%lf", &c);
}

// This function calculates the roots and prints it according to the rules.
void calcRootandPrint(int a, int b, int c)
{
    double discriminant;
    double root;
    double root1;
    double root2;

    // Check for special cases
    if (a == 0 && b == 0)
    {
        printf("No solution. Both a and b are zero.\n");
    }
        else if (a == 0)
        {
        // If a is zero, there is only one root (-c / b)
        root = -c / b;
        printf("One root: %lf\n", root);
        }
    else
    {
        // Calculate discriminant
        discriminant = b * b - 4 * a * c;

        if (discriminant < 0)
        {
            // If the discriminant is negative, there are no real roots
            printf("No real roots. Discriminant is negative.\n");
        }
        else
        {
            // Calculate real roots using the quadratic formula
            double root1 = (-b + sqrt(discriminant)) / (2 * a);
            double root2 = (-b - sqrt(discriminant)) / (2 * a);

            // Display roots
            printf("Root 1: %.2lf\n", root1);
            printf("Root 2: %.2lf\n", root2);
        }
    }
}