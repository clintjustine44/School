#include <stdio.h>

// Defined constants for labor cost and tax rate.
#define LABOR 0.35
#define TAX_RATE 0.085

// Function prototypes
void readData(double *a, double *b, double *c, double *d);
double calcArea(int length, int width);
double calcCarpetCost(double area, double pricePerSqrFt);
double calcLaborCost(double area);
double calcInstalledPrice(int length, int width, double pricePerSqrFt, double *area, double *carpetCost, double *laborCost);
double calcTotalPrice(double subtotal, double *tax);
double calcSubtotal(double installedPrice, double discount, double *discountTotal);
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

    // Calls functions that calculate various data.
    area = calcArea(length, width);
    carpetCost = calcCarpetCost(area, pricePerSqrFt);
    laborCost = calcLaborCost(area);
    installedPrice = calcInstalledPrice(length, width, pricePerSqrFt, &area, &carpetCost, &laborCost);
    discountTotal = calcSubtotal(installedPrice, discount, &discountTotal);
    tax = calcTotalPrice(discountTotal, &tax);

    // Calls function that prints out the results.
    printMeasure(length, width, area);
    printCharges(area, carpetCost, laborCost, installedPrice, discount, discountTotal, subtotal, tax, priceTotal);

    return 0;
}

// Function to read data from the user and return discount.
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
double calcArea(int length, int width)
{
    return (double)(length * width);
}

// This function calculates the carpet cost.
double calcCarpetCost(double area, double pricePerSqrFt)
{
    return area * pricePerSqrFt;
}

// This function calculates the labor cost.
double calcLaborCost(double area)
{
    return area * LABOR;
}

// This function calls three subfunctions and then calculates the installed price.
double calcInstalledPrice(int length, int width, double pricePerSqrFt, double *area, double *carpetCost, double *laborCost)
{
    *area = calcArea(length, width);
    *carpetCost = calcCarpetCost(*area, pricePerSqrFt);
    *laborCost = calcLaborCost(*area);
    return *carpetCost + *laborCost;
}

// This function calculates the tax and the total price.
double calcTotalPrice(double subtotal, double *tax)
{
    *tax = TAX_RATE * subtotal;
    return subtotal + *tax;
}

// This function calculates the discount and subtotal.
double calcSubtotal(double installedPrice, double discount, double *discountTotal)
{
    *discountTotal = (discount / 100) * installedPrice;
    return installedPrice - *discountTotal;
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
    printf("Discount\t\t%.2lf%%\t\t\t$%.2lf\n", discount, discountTotal);
    printf("\t\t\t\t\t\t----------\n");
    printf("SUBTOTAL\t\t\t\t\t$%.2lf\n", subtotal);
    printf("Tax\t\t\t\t\t\t%.2lf\n", tax);
    printf("TOTAL\t\t\t\t\t\t$%.2lf", priceTotal);
}
