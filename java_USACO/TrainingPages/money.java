/*
ID: pranavm7
LANG: JAVA
TASK: money
PROG: money
*/

import java.io.*;
import java.util.Scanner;

public class money
{
    private static int v, n;
    private static int[] values;
    private static long[] dp;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("money.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

        v = in.nextInt();
        n = in.nextInt();

        dp = new long[n + 1];
        values = new int[v];
        for (int i = 0; i < v; i++)
            values[i] = in.nextInt();

        dp[0] = 1;

        for (int i = 0; i < v; i++)
        {
            for (int j = 0; j < n + 1; j++)
            {
                dp[j] = dp[j] + (values[i] <= j ? dp[j - values[i]] : 0);
            }
        }

        out.println(dp[n]);
        out.close();
    }
}