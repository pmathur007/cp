import java.io.*;

public class circlecrossG
{
    private static int n;
    private static int[] fwt = new int[100001];
    private static int[] entry = new int[50001];

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));

        n = Integer.parseInt(in.readLine());
        int ans = 0;
        for (int i = 1; i <= 2 * n; i++)
        {
            int c = Integer.parseInt(in.readLine());
            if (entry[c] == 0)
            {
                entry[c] = i;
                update(i, 1);
            }
            else
            {
                update(entry[c], -1);
                ans += query(entry[c], i);
            }
        }

        out.println(ans);
        out.close();
    }

    private static int query(int a, int b)
    {
        return query(b) - (a == 0 ? 0 : query(a - 1));
    }

    private static int query(int i)
    {
        int sum = 0;
        for (; i > 0; i -= (i & -i))
            sum += fwt[i];
        return sum;
    }

    private static void update(int i, int v)
    {
        for (; i <= 2 * n; i += (i & -i))
            fwt[i] += v;
    }
}