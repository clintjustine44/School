/* This program calculates the parking fare for customers who park their cars in a parking lot.
**    Written by : Clint Justine A. Nepomuceno
**    Date : 11/25/2023
*/

#include <stdio.h>
#include <math.h>
#include <string.h>

// Defined constants for values that doesn't change throughout the program.
#define CAR_FIRST_RATE 0.00
#define CAR_SECOND_RATE 1.50
#define TRUCK_FIRST_RATE 1.00
#define TRUCK_SECOND_RATE 2.30
#define BUS_FIRST_RATE 2.00
#define BUS_SECOND_RATE 3.70

// User-defined functions declarations.
void getData(char *vehicleType, int *hr_entry, int *min_entry, int *hr_exit, int *min_exit);
int calcParkingTime(int min_exit, int min_entry, int hr_exit, int hr_entry);
void calculateParkingFare(char vehicleType, int hr_entry, int min_entry, int hr_exit, int min_exit, int parkingTime);
void printResults(char vehicleType, int hr_entry, int hr_exit, int min_entry, int min_exit, int parkingTime, int parkingHours, float parkingFare);


// Main function
int main() {
    char vehicleType;
    int parkingTime;
    int hr_entry, min_entry, hr_exit, min_exit;

    //Input
    getData(&vehicleType, &hr_entry, &min_entry, &hr_exit, &min_exit);
    parkingTime = calcParkingTime(min_exit, min_entry, hr_exit, hr_entry);

    // Calculate and display parking fare
    calculateParkingFare(vehicleType, hr_entry, min_entry, hr_exit, min_exit, parkingTime);

    return 0;
}

// This function gets input from user.
void getData(char *vehicleType, int *hr_entry, int *min_entry, int *hr_exit, int *min_exit)
{
    printf("Type of vehicle? ");
    scanf("%c", vehicleType);
    printf("Hour vehicle entered lot (0 - 24)? ");
    scanf("%d", hr_entry);
    printf("Minute vehicle entered lot (0 - 60)? ");
    scanf("%d", min_entry);
    printf("Hour vehicle left lot (0 - 24)? ");
    scanf("%d", hr_exit);
    printf("Minute vehicle left lot ( 0 - 60 )? ");
    scanf("%d", min_exit);

}

// This function calculates the parking time.
int calcParkingTime(int min_exit, int min_entry, int hr_exit, int hr_entry)
{    
    int parkingTime;

    // If the exit minute is smaller than the entry minute, adjust the calculations.
    if (min_exit < min_entry)
    {
        min_exit += 60;
        hr_exit -= 1;
    }

    // Calculate parking time in minutes
    parkingTime = (hr_exit * 60 + min_exit) - (hr_entry * 60 + min_entry);

    return parkingTime;
}

// This function calculates the Parking Fare.
void calculateParkingFare(char vehicleType, int hr_entry, int min_entry, int hr_exit, int min_exit, int parkingTime)
{
    float parkingHours;  // Rounded-up parking hours
    float parkingFare;   // Parking fare

    // Calculate rounded-up parking hours
    parkingHours = ceil((float)parkingTime / 60.0);

    // Use a switch statement to determine the parking fare based on vehicle type
    switch (vehicleType) {
        case 'C':
            if (parkingHours <= 3) {
                parkingFare = CAR_FIRST_RATE;
            } else {
                parkingFare = CAR_SECOND_RATE * (parkingHours - 3);
            }
            break;
        case 'T':
            if (parkingHours <= 2) {
                parkingFare = TRUCK_FIRST_RATE * parkingHours;
            } else {
                parkingFare = TRUCK_FIRST_RATE + TRUCK_SECOND_RATE * (parkingHours - 2);
            }
            break;
        case 'B':
            if (parkingHours == 1) {
                parkingFare = BUS_FIRST_RATE * parkingHours;
            } else {
                parkingFare = BUS_FIRST_RATE + BUS_SECOND_RATE * (parkingHours - 1);
            }
            break;
        default:
            printf("Invalid vehicle type.\n");
            return;
    }


    printResults(vehicleType, hr_entry, hr_exit, min_entry, min_exit, parkingTime, parkingHours, parkingFare);
}

// This function prints out the results
void printResults(char vehicleType, int hr_entry, int hr_exit, int min_entry, int min_exit, int parkingTime, int parkingHours, float parkingFare)
{
    printf("\t\t\tPARKING LOT CHARGE\t\n");
    printf(" \n");
    printf("Type of vehicle: %c\n", vehicleType);
    printf("TIME IN\t\t\t%d  : %d\n", hr_entry, min_entry);
    printf("TIME OUT\t\t%d : %d\n", hr_exit, min_exit);
    printf("\t\t\t--------\n");
    printf("PARKING TIME\t\t  %d : %d\n", parkingTime / 60, parkingTime % 60);
    printf("ROUNDED TOTAL\t\t  %5d\n", (int)parkingHours);
    printf("\t\t\t--------\n");
    printf("TOTAL CHARGE\t\t $%-5.2f\n", parkingFare);
}