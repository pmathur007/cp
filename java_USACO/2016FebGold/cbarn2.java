import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cbarn2
{
    private static int N, K;
    private static long[] arr, prefix;
    private static long[][][] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cbarn2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[2 * N];
        prefix = new long[2 * N];
        for (int i = 0; i < 2 * N; i++)
        {
            arr[i] = i < N ? Integer.parseInt(in.readLine()) : arr[i - N];
            prefix[i] = (i == 0 ? 0 : prefix[i - 1]) + arr[i];
        }

        dp = new long[N][K + 1][2 * N];
        for (int s = 0; s < N; s++)
        {
            for (int k = 1; k <= K; k++)
            {
                if (k == 1)
                {
                    Arrays.fill(dp[s][k], Long.MAX_VALUE);
                    dp[s][k][s] = 0;
                    for (int m = 0; m < N; m++)
                        dp[s][k][s] += m * arr[s + m];
                }
                else
                {
                    for (int n = s + k - 1; n < s + N; n++)
                    {
                        dp[s][k][n] = Long.MAX_VALUE;
                        for (int m = s + k - 2; m < n; m++)
                        {
                            dp[s][k][n] = Math.min(dp[s][k][n], dp[s][k - 1][m] - (n - m) * (prefix[s + N - 1] - prefix[n - 1]));
                        }
                    }
                }
            }
        }

        long ans = Integer.MAX_VALUE;
        for (int s = 0; s < N; s++)
        {
            for (int n = s + K - 1; n < s + N; n++)
                ans = Math.min(ans, dp[s][K][n]);
        }

        out.println(ans);
        out.close();
    }
}