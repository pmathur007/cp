/*
ID: pranavm7
LANG: JAVA
TASK: inflate
PROG: inflate
*/

import java.io.*;
import java.util.Scanner;

public class inflate
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("inflate.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

        int m = in.nextInt();
        int n = in.nextInt();

        int[] pts = new int[n];
        int[] time = new int[n];
        for (int i = 0; i < n; i++)
        {
            pts[i] = in.nextInt();
            time[i] = in.nextInt();
        }

        int[] dp = new int[m + 1];
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (time[j] <= i)
                    dp[i] = Math.max(dp[i], dp[i - time[j]] + pts[j]);
            }
        }

        out.println(dp[m]);

        out.close();
    }
}