using System;
using System.Collections.Generic;
using System.Linq;

public class Point
{
    public double X { get; set; }
    public double Y { get; set; }

    public Point(double x, double y)
    {
        X = x;
        Y = y;
    }
}

class Program
{
    public static void Main(string[] args)
    {
        Point[] points = {
            new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50),
            new Point(5, 1),
            new Point(12, 10),
            new Point(3, 4)
        };

        double closestDistance = ClosestPair(points);
        Console.WriteLine($"Closest Distance: {closestDistance:F2}");
    }

    static double ClosestPair(Point[] points)
    {
        // Sort points by x-coordinate
        Array.Sort(points, (p1, p2) => p1.X.CompareTo(p2.X));
        return ClosestPairUtil(points, 0, points.Length - 1);
    }

    static double ClosestPairUtil(Point[] points, int left, int right)
    {
        // Base case: if there are 3 or fewer points, use brute force
        if (right - left <= 3)
        {
            return BruteForce(points, left, right);
        }

        // Find the mid point
        int mid = (left + right) / 2;
        double midX = points[mid].X;

        // Recursively find the smallest distance in both halves
        double d1 = ClosestPairUtil(points, left, mid);
        double d2 = ClosestPairUtil(points, mid + 1, right);
        double d = Math.Min(d1, d2);

        // Create an array to hold points within d of the midline
        List<Point> strip = new List<Point>();
        for (int i = left; i <= right; i++)
        {
            if (Math.Abs(points[i].X - midX) < d)
            {
                strip.Add(points[i]);
            }
        }

        // Sort the strip according to y-coordinate
        strip = strip.OrderBy(p => p.Y).ToList();

        // Find the closest points in the strip
        return Math.Min(d, StripClosest(strip.ToArray(), d));
    }

    static double BruteForce(Point[] points, int left, int right)
    {
        double minDist = double.MaxValue;
        for (int i = left; i <= right; i++)
        {
            for (int j = i + 1; j <= right; j++)
            {
                double dist = Distance(points[i], points[j]);
                if (dist < minDist)
                {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    static double StripClosest(Point[] strip, double d)
    {
        double minDist = d;
        int size = strip.Length;

        for (int i = 0; i < size; i++)
        {
            for (int j = i + 1; j < size && (strip[j].Y - strip[i].Y) < minDist; j++)
            {
                double dist = Distance(strip[i], strip[j]);
                if (dist < minDist)
                {
                    minDist = dist;
                }
            }
        }
        return minDist;
    }

    static double Distance(Point p1, Point p2)
    {
        return Math.Sqrt(Math.Pow(p1.X - p2.X, 2) + Math.Pow(p1.Y - p2.Y, 2));
    }
}
