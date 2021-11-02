/*
ID: pranavm7
LANG: JAVA
TASK: subset
PROG: subset
*/

import java.io.*;

public class subset
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("subset.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

        int n = Integer.parseInt(in.readLine());
        int sum = (n * (n + 1)) / 2;
        if (sum % 2 == 1)
            out.println(0);
        else
        {
            sum = sum / 2;
            int[][] mem = new int[n + 1][sum + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= sum; j++)
                    mem[i][j] = -1;

            out.println(search(mem,n - 1, sum - n));
        }
        out.close();
    }

    private static int search(int[][] mem, int n, int s)
    {
        if (s == 1 || s == 0)
            return 1;
        if (((n * (n + 1)) / 2) < s || s < 0)
            return 0;

        if (n > s)
            n = s;

        if (mem[n][s] == -1)
            mem[n][s] = search(mem,n - 1, s) + search(mem,n - 1, s - n);
        return mem[n][s];
    }
}