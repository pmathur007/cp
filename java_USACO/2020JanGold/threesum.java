import java.io.*;
import java.util.StringTokenizer;

public class threesum
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("threesum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][n];
        int[] seen;
        for (int i = 0; i < n; i++)
        {
            seen = new int[2 * 1000000 + 1];
            for (int k = i + 1; k < n; k++)
            {
                dp[i][k] = seen[-a[i] - a[k] + 1000000];
                seen[a[k] + 1000000]++;
            }
        }

//        for (int i = )

        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(in.readLine());
            int ai = Integer.parseInt(st.nextToken()) - 1;
            int bi = Integer.parseInt(st.nextToken()) - 1;
            out.println(dp[ai][bi]);
        }

        out.close();
    }

    private static class Pair implements Comparable<Pair>
    {
        int i, v;

        public Pair(int i, int v)
        {
            this.i = i;
            this.v = v;
        }

        @Override
        public int compareTo(Pair pair)
        {
            return this.v - pair.v;
        }
    }
}
