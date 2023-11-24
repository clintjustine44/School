/* This program determines a student's grade.
**    Written by : Clint Justine A. Nepomuceno
**    Date : 11/23/2023
*/

#include <stdio.h>

// User-defined functions to use in the program.
void readScores(float *score1, float *score2, float *score3);
char calculateGrade(float score1, float score2, float score3);
void printResults(char grade);

int main()
{
    // Declare variables to store the values of test scores.
    float test1;
    float test2;
    float test3;
    // Declare variable to store the grade character.
    char grade;

    // This calls readScores function to ask the user for inputs.
    readScores(&test1, &test2, &test3);
    // This calls a function that determines the grade.
    grade = calculateGrade(test1, test2, test3);

    // This calls a function that print out the results.
    printResults(grade);

    return 0;
}

// Function to read test scores and return it to the main function with a pointer.
void readScores(float *score1, float *score2, float *score3)
{
    printf("Enter the first test score (between 0 and 100): ");
    scanf("%f", score1);

    printf("Enter the second test score (between 0 and 100): ");
    scanf("%f", score2);

    printf("Enter the third test score (between 0 and 100): ");
    scanf("%f", score3);
}

// Function to determine the grade w/ if and else... if statements.
char calculateGrade(float score1, float score2, float score3)
{
    float averageScore = (score1 + score2 + score3) / 3;

    if (averageScore >= 90)
    {
        return 'A';
    }
        else if (averageScore >= 70 && averageScore < 90)
        {
            if (score3 > 90)
            {
                return 'A';
            }
            else
            {
                return 'B';
            }
        }
            else if (averageScore >= 50 && averageScore < 70)
            {
                if ((score2 + score3) / 2 > 70)
                {
                    return 'C';
                }
                else
                {
                    return 'D';
                }
            }
    else
    {
        return 'F';
    }
}

// Function that prints out the result.
void printResults(char grade)
{
    printf("The student's grade is: %c\n", grade);
}