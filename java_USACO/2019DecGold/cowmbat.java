import java.io.*;
import java.util.StringTokenizer;

public class cowmbat
{
    private static final int INF = 100000000;
    private static int n, m, k;
    private static String s;
    private static int[][] cost, strCost, ps, dp;
    private static int[] dpMin;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowmbat.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = in.readLine();

        cost = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++)
        {
            st = new StringTokenizer(in.readLine());
            for (int j = 1; j <= m; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int x = 1; x <= m; x++)
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= m; j++)
                    if (cost[i][j] > cost[i][x] + cost[x][j])
                        cost[i][j] = cost[i][x] + cost[x][j];

        strCost = new int[n + 1][m + 1];
        ps = new int[n + 1][m + 1];
        dpMin = new int[n + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
        {
            dpMin[i] = INF;
            for (int j = 1; j <= m; j++)
            {
                strCost[i][j] = cost[s.charAt(i - 1) - 96][j];
                ps[i][j] = ps[i - 1][j] + strCost[i][j];
                dp[i][j] = INF;
            }
        }

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= m; j++)
            {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + strCost[i][j]);
                if (i - k >= 0)
                    dp[i][j] = Math.min(dp[i][j], ps[i][j] - ps[i - k][j] + dpMin[i - k]);
                dpMin[i] = Math.min(dpMin[i], dp[i][j]);
            }
        }

        out.println(dpMin[n]);
        out.close();
    }
}

// IN CONTEST - FAILED
//    private static final int INF = 100000000;
//    private static int n, m, k;
//    private static String s;
//    private static int[] sint;
//    private static int[][] change;
//    private static int[][] changeRange;
//    private static int[][] dp;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("cowmbat.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowmbat.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        k = Integer.parseInt(st.nextToken());
//
//        s = in.readLine();
//
//        sint = new int[n];
//        change = new int[m][m];
//        changeRange = new int[n][m];
//        dp = new int[n][n];
//
//        for (int i = 0; i < m; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            for (int j = 0; j < m; j++)
//                change[i][j] = Integer.parseInt(st.nextToken());
//        }
//
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < m; j++)
//                for (int x = 0; x < m; x++)
//                    if (change[i][j] > change[i][x] + change[x][j])
//                        change[i][j] = change[i][x] + change[x][j];
//
//        for (int i = 0; i < n; i++)
//        {
//            sint[i] = s.charAt(i) - 97;
//            for (int j = 0; j < m; j++)
//                changeRange[i][j] = change[sint[i]][j] + (i == 0 ? 0 : changeRange[i - 1][j]);
//            for (int j = 0; j < n; j++)
//                dp[i][j] = -1;
//        }
//
//        int ans = solve(0, n - 1);
//        out.println(ans);
//
//        out.close();
//    }
//
//    private static int solve(int l, int r)
//    {
//        if (l > r)
//            return 0;
//        if (r - l + 1 < k)
//            return INF;
//        if (dp[l][r] != -1)
//            return dp[l][r];
//        if (r - l + 1 < 2 * k)
//        {
//            dp[l][r] = minChange(l, r);
//            return dp[l][r];
//        }
//
//        int ans = Math.min(minChange(l,l + k - 1) + minChange(l + k, r), minChange(l, r - k) + minChange(r - k + 1, r));
//        for (int i = l + k; i <= r - k + 1; i++)
//        {
//            ans = Math.min(ans, minChange(l, i) + minChange(i + 1, r));
//            for (int j = r - k; j >= i + k - 1; j--)
//                ans = Math.min(ans, minChange(l, i - 1) + solve(i, j) + minChange(j + 1, r));
//        }
//        return ans;
//    }
//
//    private static int minChange(int l, int r)
//    {
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < m; i++)
//            min = Math.min(min, changeRange[r][i] - (l == 0 ? 0 : changeRange[l - 1][i]));
//        return min;
//    }