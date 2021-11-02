import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class barnpainting
{
    private static final int MOD = 1000000007;
    private static int n, k;
    private static LinkedList<Integer>[] graph;
    private static int[] color, parent;
    private static long[][] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("barnpainting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new LinkedList[n];
        color = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new LinkedList<>();
            color[i] = -1;
            parent[i] = -1;
        }

        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 0; i < k; i++)
        {
            st = new StringTokenizer(in.readLine());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            color[b] = c;
        }

        parents(0);

        dp = new long[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                dp[i][j] = -1;

        if (color[0] >= 0)
            out.println(count(0, color[0]));
        else
        {
            long total = 0;
            total = (total + count(0, 0)) % MOD;
            total = (total + count(0, 1)) % MOD;
            total = (total + count(0, 2)) % MOD;
            out.println(total);
        }

        out.close();
    }

    private static long count(int b, int c)
    {
        if (dp[b][c] >= 0)
            return dp[b][c];

        long totalPos = 1;
        for (int d : graph[b])
        {
            if (d == parent[b])
                continue;
            if (color[d] >= 0)
            {
                if (color[d] == c)
                {
                    totalPos = 0;
                    break;
                }
                else
                {
                    totalPos = (totalPos * count(d, color[d])) % MOD;
                }
            }
            else
            {
                long childPos = 0;
                for (int i = 0; i < 3; i++)
                {
                    if (i != c)
                        childPos += count(d, i);
                }
                totalPos = (totalPos * childPos) % MOD;
            }
        }
        dp[b][c] = totalPos;
        return totalPos;
    }

    private static void parents(int b)
    {
        for (int d : graph[b])
        {
            if (parent[b] != d)
            {
                parent[d] = b;
                parents(d);
            }
        }
    }
}