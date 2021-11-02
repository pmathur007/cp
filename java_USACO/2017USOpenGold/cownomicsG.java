import java.io.*;
import java.util.HashSet;

public class cownomicsG
{
    private static int n, m;
    private static String[] spotty, plain;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));

        String[] line = in.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        spotty = new String[n];
        plain = new String[n];

        for (int i = 0; i < n; i++)
            spotty[i] = in.readLine();
        for (int i = 0; i < n; i++)
            plain[i] = in.readLine();

        int l = 1, r = m;
        while (l <= r)
        {
            int mid = (l + r) / 2;
            if (explains(mid))
                r = mid - 1;
            else
                l = mid + 1;
        }

        out.println(l);
        out.close();
    }

    private static boolean explains(int k)
    {
        for (int i = 0; i <= m - k; i++)
        {
            HashSet<String> sbstr = new HashSet<>();
            for (int j = 0; j < n; j++)
                sbstr.add(spotty[j].substring(i, i + k));

            boolean explains = true;
            for (int j = 0; j < n; j++)
            {
                if (sbstr.contains(plain[j].substring(i, i + k)))
                {
                    explains = false;
                    break;
                }
            }
            if (explains) return true;
        }
        return false;
    }
}