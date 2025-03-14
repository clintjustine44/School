namespace MidtermExamPt2
{
    internal class Program
    {
        static void Main(String[] args)
        {
            int N = 3;
            int M = 4;
            int[][] grid = new int[][]
            {
            new int[] { 5, 1, 7, 4 },
            new int[] { 2, 8, 3, 9 },
            new int[] { 6, 4, 5, 2 }
            };

            int result = MaxTreasure(N, M, grid);
            Console.WriteLine(result); // Output: 29
            // Path : (0,0) -> (1,0) -> (1,1) -> (1,2) -> (1,3) -> (2,3)
        }

        static int MaxTreasure(int N, int M, int[][] grid)
        {
            int[][] dp = new int[N][];
            for (int i = 0; i < N; i++)
            {
                dp[i] = new int[M];
            }

            dp[0][0] = grid[0][0];

            // Fill the first row with treasure (can only come from the left)
            for (int j = 1; j < M; j++)
            {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }

            // Fill the first column treasure (can only come from above)
            for (int i = 1; i < N; i++)
            {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }

            // Fill the rest of the dp array
            for (int i = 1; i < N; i++)
            {
                for (int j = 1; j < M; j++)
                {
                    dp[i][j] = Math.Max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            // The bottom-right corner contains the maximum treasure collected
            return dp[N - 1][M - 1];
        }
    }
}
