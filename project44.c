#include <stdio.h>

// Defined constants for labor cost and tax rate.
#define LABOR 0.35
#define TAX_RATE 0.085

// User-defined functions.
void readData(double *a, double *b, double *c, double *d);
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

    // Calls the function that reads data from the user.
    readData(&length, &width, &discount, &pricePerSqrFt);

    // Calls three functions that calculates various data/values.
    calcInstalledPrice(length, width, pricePerSqrFt, &installedPrice, &area, &carpetCost, &laborCost);
    calcSubtotal(installedPrice, discount, &subtotal, &discountTotal);
    calcTotalPrice(subtotal, &tax, &priceTotal);

    // Calls the functions that prints out the results.
    printMeasure(length, width, area);
    printCharges(area, carpetCost, laborCost, installedPrice, discount, discountTotal, subtotal, tax, priceTotal);

    return 0;
}

// This function reads data from the user.
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

// This function calculates the area, carpetCost, laborCost, and installedPrice.
void calcInstalledPrice(int length, int width, double pricePerSqrFt, double *installedPrice, double *area, double *carpetCost, double *laborCost)
{
    *area = length * width;
    *carpetCost = *area * pricePerSqrFt;
    *laborCost = *area * LABOR;
    *installedPrice = *carpetCost + *laborCost;
}

// This function calculates the total price and tax.
void calcTotalPrice(double subtotal, double *tax, double *priceTotal)
{
    *tax = TAX_RATE * subtotal;
    *priceTotal = subtotal + *tax;
}

// This function calculates the discount and subtotal.
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
