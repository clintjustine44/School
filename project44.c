#include <stdio.h>

// Defined constants for labor cost and tax rate.
#define LABOR 0.35
#define TAX_RATE 0.085

// Types of functions declaration to get input.
int getLength (void);
int getWidth (void);
int getDiscount (void);
double getCostPerSqrFt(void);

// Types of function delcaration that are function processes.
int calcArea (int lengthValue, int widthValue);
double carpetCost (double costPerSquareFoot, double calcArea);
double laborCost (double squareFoot);
double installedPrice (double carpetCost, double laborCost);
double discountPrice (double installedPrice, double discount);
double subTotalPrice (double installed_price, double discountPrice);
double taxPrice (double subTotalPrice);
double totalPriceCost (double subTotalPrice, double taxPrice);

// Types of function that prints the output.
void printMeasure(int length, int width, int area);
void printCharges(double costPerSqrFt, double carpetCost, double laborCost, double installedPrice, int discount, double discountTotal, double subtotal, double tax, double priceTotal);


int main()
{

    // Variables declarations to hold various data.
    int length;
    int width;
    int discount;
    double costPerSqrFt;
    int area;
    double carpet;
    double labor;
    double installed_price;
    double discountTotal;
    double subtotal;
    double tax;
    double priceTotal;

    // Initiziale and calling function that gets the input.
    length = getLength();
    width = getWidth();
    discount = getDiscount();
    costPerSqrFt = getCostPerSqrFt();

    // Initiziale and calls the functions that calculates various data.
    area = calcArea(length, width);
    carpet = carpetCost(costPerSqrFt, area);
    labor = laborCost(area);
    installed_price = installedPrice(carpet, labor);
    discountTotal = discountPrice(installed_price, discount);
    subtotal = subTotalPrice(installed_price, discountTotal);
    tax = taxPrice(subtotal);
    priceTotal = totalPriceCost(subtotal, tax);

    // Caliing the function that prints the output)
    printMeasure(length, width, area);
    printCharges(costPerSqrFt, carpet, labor, installed_price, discount, discountTotal, subtotal, tax, priceTotal); 


    return 0;

}


// Function definitions where it gets the input and prompts the user
int getLength (void)
{
    int length;

    printf("Length of room (feet)?\t\t");
    scanf("%d", &length);

    return length;

}
int getWidth (void)
{
    int width;

    printf("Width of room (feet)?\t\t");
    scanf("%d", &width);

    return width;
}

int getDiscount (void)
{
   int discount;
    
    printf("Customer discount (percent)?\t");
    scanf("%d", &discount);

    return discount;
}


// Function Definition that processed all the calculations that is needed.
double getCostPerSqrFt(void)
{
    double costPerSqrFt;
    printf("Cost per square foot (xxx.xx)?\t");
    scanf("%lf", &costPerSqrFt);

    return costPerSqrFt;
}

int calcArea (int lengthValue, int widthValue)
{
    return  lengthValue * widthValue;
}
double carpetCost (double costPerSquareFoot, double calcArea)
{
    return costPerSquareFoot * calcArea;
}
double laborCost (double squareFoot)
{
    return squareFoot * LABOR;
}
double installedPrice (double carpetCost, double laborCost)
{
    return carpetCost + laborCost;
}
double discountPrice (double installedPrice, double discount)
{
    return installedPrice * (discount / 100);
}
double subTotalPrice (double installed_price, double discountPrice)
{
    return installed_price - discountPrice;
}
double taxPrice (double subTotalPrice)
{
    return subTotalPrice * TAX_RATE;
}
double totalPriceCost (double subTotalPrice, double taxPrice)
{
    return subTotalPrice + taxPrice;
}


// These functions prints the output that was being inputted by the user and also the calculations.
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
void printCharges(double costPerSqrFt, double carpetCost, double laborCost, double installedPrice, int discount, double discountTotal, double subtotal, double tax, double priceTotal)
{
    printf("\t\t\tCHARGES\n\n");
    printf("DESCRIPTION\t\tCOST/SQ.FT.\t\tCHARGE\n");
    printf("-----------\t\t-----------\t\t----------\n");
    printf("Carpet\t\t\t%.2lf\t\t\t$%.2lf\n", costPerSqrFt, carpetCost);
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
