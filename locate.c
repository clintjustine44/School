#include <stdio.h>

void findMaxNum(double rates[]);
void findMinNum(double rates[]);

int main() {
    double rates[] = {18.24, 25.63, 5.94, 33.92, 3.71, 32.84, 35.93, 18.24, 6.9};
    
    findMaxNum(rates);
    findMinNum(rates);

    return 0;
}

void findMaxNum(double rates[])
{
    int location = 0;
    double max = rates[0];

    for (int i = 0; i < 9; i++) {     
       if(rates[i] > max)    
       {
           max = rates[i];
           location = i;
       }
    }

    printf("Maximum value: %.2lf\n", max);
    printf("Location: %d\n", location);
}

void findMinNum(double rates[])
{
    int location = 0;
    double min = rates[0];

    for (int i = 0; i < 9; i++) {     
       if(rates[i] < min)    
       {
           min = rates[i];
           location = i;
       }
    }

    printf("Minimum value: %.2lf\n", min);
    printf("Location: %d", location);

}