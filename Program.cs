namespace Grocery_Store_Discount_Calculator
{
    internal class Program
    {
        static void Main(string[] args)
        {
            const int maxItems = 100;
            string[] items = new string[maxItems];
            int[] quantities = new int[maxItems];
            decimal[] prices = new decimal[maxItems];

            bool finish = false;
            string choice, item;
            int quantity;
            decimal totalCost = 0.00m, discount = 0.00m, finalCost = 0.000m;    

            int itemCount = 0, j = 0, k = 0;

            do
            {
                Console.Write("Enter an item name: ");
                items[itemCount++] = Console.ReadLine();
                Console.Write("Enter quantity: ");  
                quantities[j++] = Convert.ToInt32(Console.ReadLine());
                Console.Write("Enter price: ");
                prices[k++] = Convert.ToDecimal(Console.ReadLine());
                 
                Console.Write("Do you want to enter another item? (YES/NO): ");
                choice = Console.ReadLine().ToUpper();
                Console.Clear();
            } while (choice != "NO");

            // Total Cost calculator.
            for (int i = 0; i < items.Length; i++)
            {
                totalCost += (prices[i] * quantities[i]);
            }

            // Discount calculator.
            if (totalCost > 100 && totalCost < 200) discount = totalCost * .10m;
            else if (totalCost > 200 && totalCost < 500) discount = totalCost * .15m;
            else if (totalCost > 500) discount = totalCost * .20m;
            else discount = 0.00m;

            // Final Cost calculator.
            finalCost = totalCost - discount;


            // Prints the receipt.
            Console.WriteLine("\t\t\tRECEIPT\t\t\t");
            Console.WriteLine("--------------------------------------------------------");
            Console.WriteLine("Grocery List\t\tQuanties\t\tPrice");
            for (int i2 = 0; i2 < itemCount; ++i2)
            {
                Console.WriteLine($"{items[i2]}\t\t\t{quantities[i2]}\t\t\t${prices[i2]:F2}");
            }
            Console.WriteLine("--------------------------------------------------------");
            Console.WriteLine($"Total Cost:\t\t\t\t\t ${totalCost:F2}");
            Console.WriteLine($"Discount:\t\t\t\t\t-${discount:F2}");
            Console.WriteLine($"Final Cost:\t\t\t\t\t ${finalCost:F2}");

        }
    }
}
