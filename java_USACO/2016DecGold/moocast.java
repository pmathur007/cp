import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class moocast
{
    private static int[] uf, size;

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("moocast.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

        int n = in.nextInt();
        int[] x = new int[n], y = new int[n];
        for (int i = 0; i < n; i++)
        {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                pq.add(new Edge(i, j, (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])));

        uf = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++)
        {
            uf[i] = i;
            size[i] = 1;
        }

        int maxEdge = 0;
        while(!pq.isEmpty())
        {
            Edge e = pq.remove();
            if (root(e.s) != root(e.d))
            {
                maxEdge = Math.max(maxEdge, e.w);
                union(e.s, e.d);
            }
        }
        out.println(maxEdge);

        out.close();
    }

    private static void union(int a, int b)
    {
        int rootA = root(a);
        int rootB = root(b);
        if (size[rootA] < size[rootB])
        {
            uf[rootA] = rootB;
            size[rootB] += size[rootA];
        }
        else
        {
            uf[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    private static int root(int i)
    {
        while (uf[i] != i)
        {
            uf[i] = uf[uf[i]];
            i = uf[i];
        }
        return i;
    }

    private static class Edge implements Comparable<Edge>
    {
        private int s, d, w;

        public Edge(int s, int d, int w)
        {
            this.s = s;
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o)
        {
            return this.w - o.w;
        }
    }
}