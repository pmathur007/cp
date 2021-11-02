import java.io.*;
import java.util.StringTokenizer;

public class P52C
{
    private static int[] arr;
    private static int[] segTree;
    private static int[] lazy;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        segTree = new int[(int) (2 * Math.pow(2, (int) (Math.ceil(Math.log(n) / Math.log(2)))))];
        lazy = new int[segTree.length];

        build(1, n, 1);

        int m = Integer.parseInt(in.readLine());
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int lf = Integer.parseInt(st.nextToken()) + 1;
            int rg = Integer.parseInt(st.nextToken()) + 1;
            if (st.hasMoreTokens())
            {
                int v = Integer.parseInt(st.nextToken());
                if (lf > rg)
                {
                    update(1, n, 1, rg, v, 1);
                    update(1, n, lf, n, v, 1);
                }
                else
                    update(1, n, lf, rg, v, 1);
            }
            else
            {
                if (lf > rg)
                    System.out.println(Math.min(query(1, n, 1, rg, 1), query(1, n, lf, n, 1)));
                else
                    System.out.println(query(1, n, lf, rg, 1));
            }
        }
    }

    private static void build(int start, int end, int cur)
    {
        if (start == end)
            segTree[cur] = arr[start];
        else
        {
            int mid = (start + end) / 2;
            build(start, mid, 2 * cur);
            build(mid + 1, end, 2 * cur + 1);
            segTree[cur] = Math.min(segTree[2 * cur], segTree[2 * cur + 1]);
        }
    }

    private static void update(int start, int end, int l, int r, int v, int cur)
    {
        if (lazy[cur] != 0)
        {
            segTree[cur] += lazy[cur];
            if (start != end)
            {
                lazy[2 * cur] += lazy[cur];
                lazy[2 * cur + 1] += lazy[cur];
            }
            lazy[cur] = 0;
        }
        if (start > end || start > r || end < l)
            return;
        else if (l <= start && end <= r)
        {
            segTree[cur] += v;
            if (start != end)
            {
                lazy[2 * cur] += v;
                lazy[2 * cur + 1] += v;
            }
        }
        else
        {
            int mid = (start + end) / 2;
            update(start, mid, l, r, v, 2 * cur);
            update(mid + 1, end, l, r, v, 2 * cur + 1);
            segTree[cur] = Math.min(segTree[2 * cur], segTree[2 * cur + 1]);
        }
    }

    private static int query(int start, int end, int l, int r, int cur)
    {
        if (lazy[cur] != 0)
        {
            segTree[cur] += lazy[cur];
            if (start != end)
            {
                lazy[2 * cur] += lazy[cur];
                lazy[2 * cur + 1] += lazy[cur];
            }
            lazy[cur] = 0;
        }
        if (start > end || start > r || end < l)
            return Integer.MAX_VALUE;
        if (l <= start && end <= r)
            return segTree[cur];

        int mid = (start + end) / 2;
        return Math.min(query(start, mid, l, r, 2 * cur), query(mid + 1, end, l, r, 2 * cur + 1));
    }
}