import java.io.*;
import java.util.Scanner;

public class spainting
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("spainting.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));

        long n = in.nextInt();
        long m = in.nextInt();
        long k = in.nextInt();
        final long MOD = 1000000007;

        long[] s = new long[((int) n) + 1];
        s[0] = 0;
        for (int i = 1; i < k; i++)
            s[i] = (m * s[i - 1] + m) % MOD;
        for (int i = (int) k; i <= n; i++)
            s[i] = (m * s[i - 1] + MOD - ((m - 1) * (s[i - ((int) k)])) % MOD) % MOD;

        long ans = 1;
        for (int i = 1; i <= n; i++)
            ans = (m * ans) % MOD;

        out.println((ans + MOD - s[(int) n] + s[(int) (n - 1)]) % MOD);

        out.close();
    }
}