import java.io.*;
import java.util.StringTokenizer;

public class hayfeast
{
    private static int n;
    private static long m;
    private static long[] flavor, spice, spiceST;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        flavor = new long[n];
        spice = new long[n];

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            flavor[i] = (i != 0 ? flavor[i-1] : 0) + Integer.parseInt(st.nextToken());
            spice[i] = Integer.parseInt(st.nextToken());
        }

        spiceST = new long[4*n];
        build(0, n-1, 0);

        long minSpice = 1000000001L;
        int r = 0;
        int l = 0;

        for (; r < n; r++)
        {
            while (flavor[r] - (l == 0 ? 0 : flavor[l - 1]) >= m && l < r)
            {
                minSpice = Math.min(minSpice, query(l, r, 0, n-1, 0));
                if (l < r) l++;
            }
        }

        out.println(minSpice);
        out.close();
    }

    private static void build(int l, int r, int cur)
    {
        if (l == r)
            spiceST[cur] = spice[l];
        else
        {
            int mid = (l + r) / 2;
            build(l, mid, 2*cur+1);
            build(mid + 1, r, 2*cur+2);
            spiceST[cur] = Math.max(spiceST[2*cur+1], spiceST[2*cur+2]);
        }
    }

    private static long query(int ql, int qr, int l, int r, int cur)
    {
        if (qr < l || r < ql)
            return 0;
        if (ql <= l && r  <= qr)
            return spiceST[cur];
        int mid = (l + r) / 2;
        return Math.max(query(ql, qr, l, mid, 2*cur+1), query(ql, qr, mid+1, r, 2*cur+2));
    }
}