#include <stdio.h>

// Defined constants for labor cost and tax rate.
#define LABOR 0.35
#define TAX_RATE 0.085

// User-defined functions.
void readData(int *a, int *b, int *c, double *d);
void calcInstalledPrice(int length, int width, double pricePerSqrFt, double *installedPrice, double *area, double *carpetCost, double *laborCost);
void calcTotalPrice(double subtotal, double *tax, double *priceTotal);
void calcSubtotal(double installedPrice, int discount, double *subtotal, double *discountTotal);
void printMeasure(int length, int width, int area);
void printCharges(double area, double carpetCost, double laborCost, double installedPrice, int discount, double discountTotal, double subtotal, double tax, double priceTotal);

int main()
{
    // Variables declarations to hold various data.
    int length; 
    int width;
    int discount;
    double area;
    double pricePerSqrFt;
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
void readData(int *a, int *b, int *c, double *d)
{
    printf("Length of room (feet)? \n");
    scanf("%d", a);
    printf("Width of the room (feet)? \n");
    scanf("%d", b);
    printf("Customer discount (percent)? \n");
    scanf("%d", c);
    printf("Cost per square foot (xxx.xx)? \n");
    scanf("%lf", d);
    return;
}

// This function calculates the area, carpetCost, laborCost, and installedPrice.
void calcInstalledPrice(int length, int width, double pricePerSqrFt, double *installedPrice, double *area, double *carpetCost, double *laborCost)
{
    *area = length * width;
    *carpetCost = *area * pricePerSqrFt;
    *laborCost = *area * LABOR;
    *installedPrice = *carpetCost + *laborCost;
    return;
}

// This function calculates the total price and tax.
void calcTotalPrice(double subtotal, double *tax, double *priceTotal)
{
    *tax = TAX_RATE * subtotal;
    *priceTotal = subtotal + *tax;
    return;
}

// This function calculates the discount and subtotal.
void calcSubtotal(double installedPrice, int discount, double *subtotal, double *discountTotal)
{
    *discountTotal = ((double)discount / 100) * installedPrice;
    *subtotal = installedPrice - *discountTotal;
    return;
}

// This function prints the measurements.
void printMeasure(int length, int width, int area)
{
    printf("\t\t\tMEASUREMENT\n\n");
    printf("Length\t\t\t\t\t\t%.0d ft\n", length);
    printf("Width\t\t\t\t\t\t%.0d ft\n", width);
    printf("Area\t\t\t\t\t\t%.0d square ft\n", area);
    return;
}

// This function prints the charges.
void printCharges(double area, double carpetCost, double laborCost, double installedPrice, int discount, double discountTotal, double subtotal, double tax, double priceTotal)
{
    printf("\t\t\tCHARGES\n\n");
    printf("DESCRIPTION\t\tCOST/SQ.FT.\t\tCHARGE\n");
    printf("-----------\t\t-----------\t\t----------\n");
    printf("Carpet\t\t\t%.2lf\t\t\t$%.2lf\n", area, carpetCost);
    printf("Labor\t\t\t%.2lf\t\t\t%.2lf\n", LABOR, laborCost);
    printf("\t\t\t\t\t\t----------\n");
    printf("INSTALLED PRICE\t\t\t\t\t$%.2lf\n", installedPrice);
    printf("Discount\t\t%.2d%%\t\t\t%.2lf\n", discount, discountTotal);
    printf("\t\t\t\t\t\t----------\n");
    printf("SUBTOTAL\t\t\t\t\t$%.2lf\n", subtotal);
    printf("Tax\t\t\t\t\t\t%.2lf\n", tax);
    printf("TOTAL\t\t\t\t\t\t$%.2lf", priceTotal);
    return;
}
