import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B610
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        StringTokenizer st;
        for (int x = 0; x < t; x++)
        {
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] goods = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++)
                goods[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(goods);

            long[] dp = new long[n + 1];
            dp[1] = goods[0];
            for (int i = 2; i <= n; i++)
            {
                dp[i] = goods[i - 1] + dp[i - 1];
                if (i >= k)
                    dp[i] = Math.min(dp[i], goods[i - 1] + dp[i - k]);
            }

            int max = 0;
            for (int i = 1; i <= n; i++)
                if (dp[i] <= p)
                    max = i;
            System.out.println(max);
        }
    }
}