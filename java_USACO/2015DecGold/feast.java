import java.io.*;
import java.util.StringTokenizer;

public class feast
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("feast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int t = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean[] dp = new boolean[t + 1];
        int[] last = new int[t + 1];

        dp[0] = true;
        for (int i = 0; i <= t; i++)
        {
            if (dp[i])
            {
                last[i] = i;
                if (i + a <= t)
                    dp[i + a] = true;
                if (i + b <= t)
                    dp[i + b] = true;
            }
            else
                last[i] = last[i - 1];
        }

        int max = 0;
        for (int i = 0; i <= t; i++)
            if (dp[i])
                max = Math.max(max, Math.max(i, i / 2 + last[t - i / 2]));

        out.println(max);

        out.close();
    }
}