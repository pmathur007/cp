import java.io.*;
import java.util.PriorityQueue;

public class bphoto
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));

        int n = Integer.parseInt(in.readLine());
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++)
        {
            int h = Integer.parseInt(in.readLine());
            q.add(new Node(0, h, 0, i));
            q.add(new Node(2 * i, h, 0, i - 1));
            q.add(new Node(2 * i + 1, h, i + 1, n));
        }

        int[] fwt = new int[n + 1];
        int[] ans = new int[2 * n + 2];

        while (!q.isEmpty())
        {
            Node cur = q.remove();
            if (cur.pos != 0)
                ans[cur.pos] = query(fwt, cur.r) - query(fwt, cur.l - 1);
            else
                update(fwt, cur.r);
        }

        int num = 0;
        for (int i = 1; i <= n; i++)
        {
            if (ans[2*i] > 2*ans[2*i+1] || ans[2*i+1] > 2*ans[2*i])
                num++;
        }
        out.println(num);
        out.close();
    }

    private static int query(int[] fwt, int i)
    {
        int sum = 0;
        for (; i > 0; i -= (i & -i))
            sum += fwt[i];
        return sum;
    }

    private static void update(int[] fwt, int i)
    {
        for (; i <= fwt.length - 1; i += (i & -i))
            fwt[i] += 1;
    }

    private static class Node implements Comparable<Node>
    {
        private int pos, val, l, r;

        public Node(int pos, int val, int l, int r)
        {
            this.pos = pos;
            this.val = val;
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Node n)
        {
            if (this.val == n.val)
                return n.pos - this.pos;
            return n.val - this.val;
        }
    }
}