using System;

class Program{
    public static void Main(string[] args){
        int[] arr = { 8, 4, 2, 1 };
        int inversionCount = CountInversion(arr);
        Console.WriteLine("Number of inversions: " + inversionCount);
    }

    static int CountInversion(int[] arr){
        int[] temp = new int[arr.Length];
        return MergeSortAndCount(arr, temp, 0, arr.Length - 1);
    }

    static int MergeAndCount(int[] arr, int[] temp, int left, int mid, int right){
        int i = left, j = mid + 1, k = left;
        int invCount = 0;

        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // Count inversions
            }
        }

        while (i <= mid){
            temp[k++] = arr[i++];
        }

        while (j <= right){
            temp[k++] = arr[j++];
        }
        
        for (i = left; i <= right; i++){
            arr[i] = temp[i];
        }

        return invCount;
    }

    static int MergeSortAndCount(int[] arr, int[] temp, int left, int right){
        int mid, invCount = 0;

        if (left < right){
            mid = left + (right - left) / 2;

            // Divide step
            invCount += MergeSortAndCount(arr, temp, left, mid);
            invCount += MergeSortAndCount(arr, temp, mid + 1, right);
            invCount += MergeAndCount(arr, temp, left, mid, right);
        }

        return invCount;
    }
}
