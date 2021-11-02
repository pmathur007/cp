/*
ID: pranavm7
LANG: JAVA
TASK: stamps
PROG: stamps
*/

import java.io.*;
import java.util.Scanner;

public class stamps
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("stamps.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

        int k = in.nextInt();
        int n = in.nextInt();

        int[] stamps = new int[n];
        for (int i = 0; i < n; i++)
            stamps[i] = in.nextInt();

        int[] dp = new int[2000001];
        for (int i = 1; i < dp.length; i++)
        {
            dp[i] = -1;
            for (int j = 0; j < n; j++)
            {
                if (stamps[j] <= i && dp[i - stamps[j]] < k)
                    dp[i] = dp[i] == -1 ? dp[i - stamps[j]] + 1 : Math.min(dp[i], dp[i - stamps[j]] + 1);
            }
            if (dp[i] == -1)
            {
                out.println(i - 1);
                break;
            }
        }

        out.close();
    }
}