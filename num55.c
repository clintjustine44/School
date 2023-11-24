/* This program determines the quadrant, given a user-input angle.
**    Written by : Clint Justine A. Nepomuceno
**    Date : 11/23/2023
*/

#include <stdio.h>

// User-defined functions to use in this program.
void readAngle(int *a);
void findQuadrant(int a);

int main()
{
    // Declaring a variable to store value.
    int angle;

    // Calls the readAngle function to read data from user.
    readAngle(&angle);

    // Calls the function that determines the quadrant.
    findQuadrant(angle);

    return 0;
}

// This function reads the user-input angle and returns it to the main function.
void readAngle(int *a)
{
    printf("Enter an angle: ");
    scanf("%d", a);
    
}       

// This function determines the quadrant and prints the result.
void findQuadrant(int a)
{
    if (a == 0) 
    {
        printf("The angle lies on the positive X-axis.\n");
    } 
        else if (a == 90) 
        {
            printf("The angle lies on the positive Y-axis.\n");
        } 
            else if (a == 180) 
            {
                printf("The angle lies on the negative X-axis.\n");
            } 
                else if (a == 270) 
                {
                    printf("The angle lies on the negative Y-axis.\n");
                } 
    else 
    {
        // Determine the quadrant based on the angle
        if (a > 0 && a < 90)
        {
            printf("The angle is in Quadrant 1.\n");
        }
            else if (a > 90 && a < 180) 
            {
                printf("The angle is in Quadrant 2.\n");
            } 
                else if (a > 180 && a < 270)
                {
                    printf("The angle is in Quadrant 3.\n");
                } 
        else 
        {
            printf("The angle is in Quadrant 4.\n");
        }
    }
}