import java.io.*;
import java.util.StringTokenizer;

public class help
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("help.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("help.out")));

        final int MOD = 1000000007;
        int N = Integer.parseInt(in.readLine());
        int[] locs = new int[2 * N + 1];
        int[] powersOf2 = new int[N];

        for (int i = 0; i < N; i++)
        {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            locs[l]++;
            locs[r]--;
            powersOf2[i] = i == 0 ? 1: (2 * powersOf2[i - 1]) % MOD;
        }

        int ans = 0;
        int curOpen = 0;
        for (int i = 1; i <= 2 * N; i++)
        {
            curOpen += locs[i];
            if (locs[i] == 1)
                ans = (ans + powersOf2[N - curOpen]) % MOD;
        }

        out.println(ans);

        out.close();
    }
}