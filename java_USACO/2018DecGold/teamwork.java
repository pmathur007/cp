import java.io.*;

public class teamwork
{
    private static int n;
    private static int k;
    private static int[] cows;
    private static int[] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));

        String[] meta = in.readLine().split(" ");
        n = Integer.parseInt(meta[0]);
        k = Integer.parseInt(meta[1]);

        cows = new int[n];
        for (int i = 0; i < n; i++)
            cows[i] = Integer.parseInt(in.readLine());
        dp = new int[n];

        out.println(search(0));

        out.close();
    }

    private static int search(int i)
    {
        if (i < n && dp[i] != 0)
            return dp[i];

        int max = 0;
        int r = 0;
        for (int size = 1; size <= k && i + size <= n; size++)
        {
            max = Math.max(max, cows[i + size - 1]);
            r = Math.max(r, size * max + search(i + size));
        }
        if (i < n)
            dp[i] = r;
        return r;
    }
}