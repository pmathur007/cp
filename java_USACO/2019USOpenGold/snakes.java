import java.io.*;
import java.util.StringTokenizer;

public class snakes
{
    private static int n, k;
    private static int[] line, pre, seg;
    private static int[][] dp;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        dp = new int[n + 1][k + 1];
        line = new int[n + 1];
        int high = 0, sum = 0;
        for (int i = 1; i <= n; i++)
        {
            line[i] = Integer.parseInt(st.nextToken());
            sum += line[i];
            high = Math.max(high, line[i]);
            dp[i][0] = i * high;
            for (int j = 1; j <= k; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;
                int max = line[i];
                for (int b = i - 1; b >= 0; b--)
                {
                    dp[i][j] = Math.min(dp[i][j], dp[b][j - 1] + (i - b) * max);
                    max = Math.max(max, line[b]);
                }
            }
        }

        out.println(dp[n][k] - sum);
//        line = new int[n];
//        pre = new int[n];
//        for (int i = 0; i < n; i++)
//        {
//            line[i] = Integer.parseInt(st.nextToken());
//            pre[i] = line[i] + (i == 0 ? 0 : pre[i - 1]);
//        }
//        seg = new int[4 * n];
//        buildSeg(0, n - 1, 0);
//
//        dp = new int[k + 1][n];
//        for (int i = 0; i < k + 1; i++)
//        {
//            for (int j = 0; j < n; j++)
//            {
//                if (i == 0)
//                    dp[i][j] = wasted(querySeg(0, j), 0, j);
//                else
//                    dp[i][j] = -1;
//            }
//        }
//
//        out.println(recur(k, n));

        out.close();
    }

    private static int recur(int numLeft, int last)
    {
        if (last <= 0)
            return 0;
        if (dp[numLeft][last - 1] != -1)
            return dp[numLeft][last - 1];

        int minAns = Integer.MAX_VALUE;
        for (int i = last - 1; i >= 0; i--)
        {
            int wasted = wasted(querySeg(i, last - 1), i, last - 1);
            minAns = Math.min(minAns, wasted + recur(numLeft - 1, i));
        }
        dp[numLeft][last - 1] = minAns;
        return minAns;
    }

    private static int wasted(int net, int l, int r)
    {
        return (r - l + 1) * net - (pre[r] - (l == 0 ? 0 : pre[l - 1]));
    }

    private static void buildSeg(int l, int r, int cur)
    {
        if (l == r)
            seg[cur] = line[l];
        else
        {
            int mid = (l + r) / 2;
            buildSeg(l, mid, 2*cur+1);
            buildSeg(mid + 1, r, 2*cur+2);
            seg[cur] = Math.max(seg[2*cur+1], seg[2*cur+2]);
        }
    }

    private static int querySeg(int ql, int qr)
    {
        return querySeg(ql, qr, 0, n - 1, 0);
    }

    private static int querySeg(int ql, int qr, int l, int r, int cur)
    {
        if (qr < l || r < ql)
            return 0;
        if (ql <= l && r <= qr)
            return seg[cur];
        int mid = (l + r) / 2;
        return Math.max(querySeg(ql, qr, l, mid, 2*cur+1), querySeg(ql, qr, mid+1, r, 2*cur+2));
    }
}

//    int[] netSize = new int[n];
//    int[] nextPointer = new int[n];
//
//        netSize[0] = querySeg(0, n - 1);
//                nextPointer[0] = n;
//                int minWasted = n * netSize[0] - pre[n - 1];
//
//                for (int i = 0; i < k; i++)
//        {
//        int lastNet = 0;
//        int nextNet = nextPointer[lastNet];
//
//        int minLast = 0;
//        int minCur = 0;
//        int minNext = 0;
//        int minLastSize = 0;
//        int minCurSize = 0;
//        int newMinWasted = minWasted;
//
//        for (int j = 1; j < n; j++)
//        {
//        if (j == nextNet)
//        {
//        lastNet = j;
//        nextNet = nextPointer[lastNet];
//        }
//        else
//        {
//        int potLastSize = querySeg(lastNet, j-1);
//        int potCurSize = querySeg(j, nextNet - 1);
//        int oldWasted = wasted(netSize[lastNet], lastNet, nextNet - 1);
//        int newWasted = wasted(potLastSize, lastNet, j-1) + wasted(potCurSize, j, nextNet - 1);
//        if (minWasted - oldWasted + newWasted <= newMinWasted)
//        {
//        minLast = lastNet;
//        minCur = j;
//        minNext = nextNet;
//        minLastSize = potLastSize;
//        minCurSize = potCurSize;
//        newMinWasted = minWasted - oldWasted + newWasted;
//        }
//        }
//        }
//
//        netSize[minLast] = minLastSize;
//        netSize[minCur] = minCurSize;
//        nextPointer[minLast] = minCur;
//        nextPointer[minCur] = minNext;
//        minWasted = newMinWasted;
//        }
//
//        out.println(minWasted);