#include <stdio.h>

void findMaxNum(double rates[], int *location, double *max);
void findMinNum(double rates[], int *location, double* min);

int main() {
    double rates[] = {18.24, 25.63, 5.94, 33.92, 3.71, 32.84, 35.93, 18.24, 6.9};
    int maxLoc = 0;
    int minLoc = 0;
    double maxNum = rates[0];
    double minNum = rates[0];
    
    findMaxNum(rates, &maxLoc, &maxNum);
    findMinNum(rates, &minLoc, &minNum);
    
    printf("Maximum value: %.2lf\n", maxNum);
    printf("Location: %d", maxLoc);
    printf("Minimum value: %.2lf", minNum);
    printf("Location: %d", maxLoc);

    return 0;
}

void findMaxNum(double rates[], int *location, double *max)
{
    for (int i = 0; i < 9; i++) {     
       if(rates[i] > max)    
       {
           max = rates[i];
           location = i;
       }
    }
}

void findMinNum(double rates[], int *location, double *min)
{
    for (int i = 0; i < 9; i++) {     
       if(rates[i] < low)    
       {
           min = rates[i];
           location = i;
       }
    }

}
