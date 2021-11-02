import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class haircut
{
    private static int N;
    private static int[] A;
    private static long[] fwt;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("haircut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));

        N = Integer.parseInt(in.readLine());
        A = new int[N + 1];
        fwt = new long[N + 1];

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 1; i <= N; i++)
        {
            A[i] = Integer.parseInt(st.nextToken());
            pq.add(new Pair(A[i], i));
        }

        long ans = 0;
        out.println(ans);
        for (int j = 1; j < N; j++)
        {
            while (!pq.isEmpty() && pq.peek().v < j)
            {
                Pair p = pq.remove();
                ans += p.i - 1 - query(p.i);
                update(p.i);
            }
            out.println(ans);
        }

        out.close();
    }

    private static long query(int i)
    {
        long ans = 0;
        for (; i > 0; i -= (i & -i))
            ans += fwt[i];
        return ans;
    }

    private static void update(int i)
    {
        for (; i <= N; i += (i & -i))
            fwt[i] += 1;
    }

    private static class Pair implements Comparable<Pair>
    {
        private int v, i;

        public Pair(int v, int i)
        {
            this.v = v;
            this.i = i;
        }

        @Override
        public int compareTo(Pair pair)
        {
            if (this.v == pair.v)
                return this.i - pair.i;
            return this.v - pair.v;
        }
    }
}