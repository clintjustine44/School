#include <stdio.h>

// Defined constants for labor cost and tax rate.
#define LABOR 0.35
#define TAX_RATE 0.085

// User-defined functions.
void readData(double *a, double *b, double *c, double *d);
void calcArea(int length, int width, double *area);
void calcCarpetCost(double area, double pricePerSqrFt, double *carpetCost);
void calcLaborCost(double area, double *laborCost);
void calcInstalledPrice(int length, int width, double pricePerSqrFt, double *installedPrice, double *area, double *carpetCost, double *laborCost);
void calcTotalPrice(double subtotal, double *tax, double *priceTotal);
void calcSubtotal(double installedPrice, double discount, double *subtotal, double *discountTotal);
void printMeasure(double length, double width, double area);
void printCharges(double area, double carpetCost, double laborCost, double installedPrice, double discount, double discountTotal, double subtotal, double tax, double priceTotal);

int main()
{
    // Variables declarations to hold various data.
    double length; 
    double width;
    double area;
    double pricePerSqrFt;
    double discount;
    double carpetCost;
    double laborCost;
    double installedPrice;
    double discountTotal; 
    double subtotal;
    double tax; 
    double priceTotal;

    // Calls a function that reads data from the user.
    readData(&length, &width, &discount, &pricePerSqrFt);

    // Calls functions that calculates various data.
    calcInstalledPrice(length, width, pricePerSqrFt, &installedPrice, &area, &carpetCost, &laborCost);
    calcSubtotal(installedPrice, discount, &subtotal, &discountTotal);
    calcTotalPrice(subtotal, &tax, &priceTotal);

    // Calls function that prints out the results.
    printMeasure(length, width, area);
    printCharges(area, carpetCost, laborCost, installedPrice, discount, discountTotal, subtotal, tax, priceTotal);

    return 0;
}

// Function to read data from the user.
void readData(double *a, double *b, double *c, double *d)
{
    printf("Length of room (feet)? \n");
    scanf("%lf", a);
    printf("Width of the room (feet)? \n");
    scanf("%lf", b);
    printf("Customer discount (percent)? \n");
    scanf("%lf", c);
    printf("Cost per square foot (xxx.xx)? \n");
    scanf("%lf", d);
}

// Function to calculate the area.
void calcArea(int length, int width, double *area)
{
    *area = length * width;
}

// This function to calculate the carpet cost
void calcCarpetCost(double area, double pricePerSqrFt, double *carpetCost)
{
    *carpetCost = area * pricePerSqrFt;
}

// This function to calculate the labor cost
void calcLaborCost(double area, double *laborCost)
{
    *laborCost = area * LABOR;
}

// This function calls three subfunctions then calculates the installed price.
void calcInstalledPrice(int length, int width, double pricePerSqrFt, double *installedPrice, double *area, double *carpetCost, double *laborCost)
{
    calcArea(length, width, area);
    calcCarpetCost(*area, pricePerSqrFt, carpetCost);
    calcLaborCost(*area, laborCost);
    *installedPrice = *carpetCost + *laborCost;
}

// This function calculates the tax and the total price.
void calcTotalPrice(double subtotal, double *tax, double *priceTotal)
{
    *tax = TAX_RATE * subtotal;
    *priceTotal = subtotal + *tax;
}

// This function to calculates the discount and subtotal.
void calcSubtotal(double installedPrice, double discount, double *subtotal, double *discountTotal)
{
    *discountTotal = (discount / 100) * installedPrice;
    *subtotal = installedPrice - *discountTotal;
}

// This function prints the measurements.
void printMeasure(double length, double width, double area)
{
    printf("\t\t\tMEASUREMENT\n\n");
    printf("Length\t\t\t\t\t\t%.0lf ft\n", length);
    printf("Width\t\t\t\t\t\t%.0lf ft\n", width);
    printf("Area\t\t\t\t\t\t%.0lf square ft\n", area);
}

// This function prints the charges.
void printCharges(double area, double carpetCost, double laborCost, double installedPrice, double discount, double discountTotal, double subtotal, double tax, double priceTotal)
{
    printf("\t\t\tCHARGES\n\n");
    printf("DESCRIPTION\t\tCOST/SQ.FT.\t\tCHARGE\n");
    printf("-----------\t\t-----------\t\t----------\n");
    printf("Carpet\t\t\t%.2lf\t\t\t$%.2lf\n", area, carpetCost);
    printf("Labor\t\t\t%.2lf\t\t\t%.2lf\n", LABOR, laborCost);
    printf("\t\t\t\t\t\t----------\n");
    printf("INSTALLED PRICE\t\t\t\t\t$%.2lf\n", installedPrice);
    printf("Discount\t\t%.2lf%%\t\t\t%.2lf\n", discount, discountTotal);
    printf("\t\t\t\t\t\t----------\n");
    printf("SUBTOTAL\t\t\t\t\t$%.2lf\n", subtotal);
    printf("Tax\t\t\t\t\t\t%.2lf\n", tax);
    printf("TOTAL\t\t\t\t\t\t$%.2lf", priceTotal);
}
