import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C610
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(in.readLine());

        StringTokenizer st;
        for (int x = 0; x < m; x++)
        {
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int na = 0, nb = 0;

            int[] dif = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++)
            {
                dif[i] = Integer.parseInt(st.nextToken());
                if (dif[i] == 0) na++;
                else nb++;
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>();
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++)
            {
                int t = Integer.parseInt(st.nextToken());
                pq.add(new Pair(t, dif[i]));
            }


            int manda = 0;
            int mandb = 0;
            int max = 0;
            for (int i = 0; i < n; i++)
            {
                Pair p = pq.remove();
                if (p.f - 1 >= a * manda + b * mandb)
                    max = Math.max(max, manda + mandb + maxCanSolve(p.f - a * manda - b * mandb - 1, a, b, na - manda, nb - mandb));
                if (p.s == 0) manda++;
                else mandb++;
            }
            if (T >= a * manda + b * mandb)
                max = Math.max(max, manda + mandb + maxCanSolve(T - a * manda - b * mandb, a, b, na - manda, nb - mandb));

            System.out.println(max);
        }
    }

    private static int maxCanSolve(int i, int a, int b, int na, int nb)
    {
        if (i <= 0)
            return 0;
        if (na * a <= i)
            return na + Math.min((i - na * a) / b, nb);
        return i / a;
    }

    private static class Pair implements Comparable<Pair>
    {
        int f, s;

        public Pair(int f, int s)
        {
            this.f = f;
            this.s = s;
        }

        @Override
        public int compareTo(Pair pair)
        {
            return this.f - pair.f;
        }
    }
}