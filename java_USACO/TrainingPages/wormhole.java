/*
ID: pranavm7
LANG: JAVA
TASK: wormhole
PROG: wormhole
*/

import java.io.*;

public class wormhole
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        int n = Integer.parseInt(in.readLine());

        int[] x = new int[n + 1];
        int[] y = new int[n + 1];
        int[] pairs = new int[n + 1];
        int[] nextOnRight = new int[n + 1];

        for (int i = 1; i <= n; i++)
        {
            String[] line = in.readLine().split(" ");
            x[i] = Integer.parseInt(line[0]);
            y[i] = Integer.parseInt(line[1]);
        }

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (y[j] == y[i] && x[j] > x[i])
                {
                    if (nextOnRight[i] == 0 || (x[j] - x[i]) < (x[nextOnRight[i]] - x[i]))
                    {
                        nextOnRight[i] = j;
                    }
                }
            }
        }

        int count = solve(pairs, nextOnRight, n);
        out.println(count);
        out.close();
    }

    private static int solve(int[] pairs, int[] nextOnRight, int n)
    {
        int solutions = 0;

        int i = 0;

        for (i = 1; i <= n; i++)
        {
            if (pairs[i] == 0)
                break;
        }

        if (i > n)
        {
            return isCycle(pairs, nextOnRight, n) ? 1 : 0;
        }

        for (int j = i + 1; j <= n; j++)
        {
            if (pairs[j] == 0)
            {
                pairs[i] = j;
                pairs[j] = i;
                solutions += solve(pairs, nextOnRight, n);
                pairs[i] = 0;
                pairs[j] = 0;
            }
        }

        return solutions;
    }

    private static boolean isCycle(int[] pairs, int[] nextOnRight, int n)
    {
        for (int i = 1; i <= n; i++)
        {
            int current = i;
            for (int j = 0; j < n; j++)
            {
                current = nextOnRight[pairs[current]];
            }
            if (current != 0)
                return true;
        }
        return false;
    }
}

