import java.io.*;
import java.util.StringTokenizer;

public class talent
{
    private static final long NEGINF = -1000000000000000L;
    private static int n, w;
    private static long[] wt, t;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("talent.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("talent.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        wt = new long[n];
        t = new long[n];

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            wt[i] = Integer.parseInt(st.nextToken());
            t[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 1000 * 1000 * 250;
        while (l <= r)
        {
            int mid = (l + r) / 2;
            if (dp(mid))
                l = mid + 1;
            else
                r = mid - 1;
        }

        out.println(l - 1);
        out.close();
    }

    private static boolean dp(int y)
    {
        long[] dp = new long[w + 1];
        for (int i = 1; i <= w; i++)
            dp[i] = NEGINF;
        for (int i = 0; i < n; i++)
        {
            long value = 1000 * t[i] - y * wt[i];
            for (int j = w; j >= 0; j--)
            {
                int addIdx = Math.min(w, (int) (j + wt[i]));
                if (dp[j] != NEGINF)
                    if (dp[addIdx] < dp[j] + value)
                        dp[addIdx] = dp[j] + value;
            }
        }
        return dp[w] >= 0;
    }
}

// SOLUTION 1, FAILED

//    StringTokenizer st =  new StringTokenizer(in.readLine());
//
//    int n = Integer.parseInt(st.nextToken());
//    int w = Integer.parseInt(st.nextToken());
//    int[] wt = new int[n];
//    int[] ta = new int[n];
//    int sumTa = 0;
//
//        for (int i = 0; i < n; i++)
//        {
//        st = new StringTokenizer(in.readLine());
//        wt[i] = Integer.parseInt(st.nextToken());
//        ta[i] = Integer.parseInt(st.nextToken());
//        sumTa += ta[i];
//        }
//
//        int[][] dp = new int[n + 1][sumTa + 1];
//        for (int i = 0; i <= sumTa; i++)
//        dp[0][i] = Integer.MAX_VALUE;
//        for (int i = 1; i <= n; i++)
//        dp[i][0] = 0;
//
//        double r = 0;
//        for (int i = 1; i <= n; i++)
//        {
//        for (int j = 1; j <= sumTa; j++)
//        {
//        if (ta[i - 1] > j)
//        dp[i][j] = dp[i - 1][j];
//        else
//        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - ta[i - 1]] + wt[i - 1]);
//
//        if (dp[i][j] != Integer.MAX_VALUE)
//        r = r > ((double) i) / dp[i][j] ? r : ((double) i) / dp[i][j];
//        }
//        }
//
//        out.println((int) Math.floor(1000 * r));