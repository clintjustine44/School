#include <stdio.h>

#define LABOR 0.35
#define TAX_RATE 0.085

double area (double num1, double num2);

int main()
{
    double length; 
    double width;
    double compute;
    double costPerSqrFt;
    double discount;
    double carpetCost;
    double laborCost;
    double priceTotal;
    double discountTotal; 
    double subtotal;
    double tax; 
    double total;

    printf("Length of room (feet)? \n");
    scanf("%lf", &length);
    printf("Width of the room (feet)? \n");
    scanf("%lf", &width);
    printf("Customer discount (percent)? \n");
    scanf("%lf", &discount);
    printf("Cost per square foot (xxx.xx)? \n");
    scanf("%lf", &costPerSqrFt);

    compute = area(length, width);
    carpetCost = compute * costPerSqrFt;
    laborCost = LABOR * compute;
    priceTotal = carpetCost + laborCost;
    discountTotal = (discount/100) * priceTotal;
    subtotal = priceTotal - discountTotal;
    tax = TAX_RATE * subtotal;
    total = tax + subtotal;


    printf("\t\t\tMEASUREMENT\n\n");
    printf("Lenth\t\t\t\t\t\t%.0lf ft\n", length);
    printf("Width\t\t\t\t\t\t%.0lf ft\n", width);
    printf("Area\t\t\t\t\t\t%0.lf square ft\n", compute);
    printf("\t\t\tCHARGES\n\n");
    printf("DESCRIPTION\t\tCOST/SQ.FT.\t\tCHARGE\n");
    printf("-----------\t\t-----------\t\t----------\n");
    printf("Carpet\t\t\t%.2lf\t\t\t$%.2lf\n", compute, carpetCost);
    printf("Labor\t\t\t%.2lf\t\t\t%.2lf\n", LABOR, laborCost);
    printf("\t\t\t\t\t\t----------\n");
    printf("INSTALLED PRICE\t\t\t\t\t%.2lf\n", priceTotal);
    printf("Discount\t\t%.2lf%%\t\t\t%.2lf\n", discount, discountTotal);
    printf("\t\t\t\t\t\t----------\n");
    printf("SUBTOTAL\t\t\t\t\t$%.2lf\n", subtotal);
    printf("Tax\t\t\t\t\t\t%.2lf\n", tax);
    printf("TOTAL\t\t\t\t\t\t$%.2lf", total);

    return 0;
}

    double area (double num1, double num2)
{
    double result;
    result = num1 * num2;
    return result;
}
