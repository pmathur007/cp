import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P339D
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);

        int[] values = new int[(int) (Math.pow(2, n))];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < values.length; i++)
            values[i] = Integer.parseInt(st.nextToken());

        int[] segTree = new int[(int) (Math.pow(2, n + 1))];
        build(segTree, values, 1, values.length, 1, n % 2 == 1);

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int p = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            update(segTree, values, 1, values.length, p, b, 1, n % 2 == 1);
            System.out.println(segTree[1]);
        }
    }

    private static void build(int[] segTree, int[] values, int start, int end, int cur, boolean or)
    {
        if (start == end)
            segTree[cur] = values[start - 1];
        else
        {
            int mid  = (start + end) / 2;
            build(segTree, values, start, mid, 2 * cur, !or);
            build(segTree, values, mid + 1, end, 2 * cur + 1, !or);
            segTree[cur] = or ? segTree[2 * cur] | segTree[2 * cur + 1] : segTree[2 * cur] ^ segTree[2 * cur + 1];
        }
    }

    private static void update(int[] segTree, int[] values, int start, int end, int i, int dif, int cur, boolean or)
    {
        if (start == end)
        {
            values[i - 1] = dif;
            segTree[cur] = dif;
        }
        else
        {
            int mid = (start + end) / 2;
            if (start <= i && i <= mid)
                update(segTree, values, start, mid, i, dif, 2 * cur, !or);
            else if (mid < i && i <= end)
                update(segTree, values, mid + 1, end, i, dif,2 * cur + 1, !or);
            segTree[cur] = or ? segTree[2 * cur] | segTree[2 * cur + 1] : segTree[2 * cur] ^ segTree[2 * cur + 1];
        }
    }
}