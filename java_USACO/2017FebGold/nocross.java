import java.io.*;
import java.util.TreeSet;

public class nocross
{
    private static int n;
    private static int[][] dp = new int[1000][1000];
    private static int[] line = new int[1000];
    private static int[] index = new int[1000];
    private static TreeSet<Integer>[] adj = new TreeSet[1000];

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));

        n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++)
        {
            line[i] = Integer.parseInt(in.readLine()) - 1;
            index[line[i]] = i;
            adj[i] = new TreeSet<>();
            for (int j = 0; j < n; j++)
                dp[i][j] = -1;
        }

        for (int i = 0; i < n; i++)
        {
            int field = Integer.parseInt(in.readLine()) - 1;
            for (int j = Math.max(0, field - 4); j <= Math.min(n - 1, field + 4); j++)
                adj[index[j]].add(i);
        }

        out.println(recur(0, 0));

        out.close();
    }

    private static int recur(int i, int j)
    {
        if (j >= n || i >= n)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int f = f(i, j);
        if (f < n - 1)
            dp[i][j] = Math.max(dp[i][j], 1 + recur(i + 1, f(i, j) + 1));
        dp[i][j] = Math.max(dp[i][j], recur(i + 1, j));
        return dp[i][j];
    }

    private static int f(int i, int j)
    {
        for (int c : adj[i])
        {
            if (c >= j)
                return c;
        }
        return n;
    }
}