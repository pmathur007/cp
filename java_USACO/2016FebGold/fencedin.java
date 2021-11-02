import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fencedin
{
    private static int A, B, N, M;
    private static int[] a, b, arr, size;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N];
        b = new int[M];
        for (int i = 0; i < N; i++)
            a[i] = Integer.parseInt(in.readLine());
        for (int i = 0; i < M; i++)
            b[i] = Integer.parseInt(in.readLine());
        Arrays.sort(a);
        Arrays.sort(b);

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int lastA = 0;
        for (int i = 0; i <= N; i++)
        {
            int ai = i == N ? A : a[i];
            for (int j = 0; j < M; j++)
            {
                pq.add(new Edge(j * (N + 1) + i, (j + 1) * (N + 1) + i,ai - lastA));
            }
            lastA = ai;
        }

        int lastB = 0;
        for (int i = 0; i <= M; i++)
        {
            int bi = i == M ? B : b[i];
            for (int j = 0; j < N; j++)
            {
                pq.add(new Edge(i * (N + 1) + j, i * (N + 1) + j + 1, bi - lastB));
            }
            lastB = bi;
        }

        arr = new int[(N + 1) * (M + 1)];
        size = new int[(N + 1) * (M + 1)];
        for (int i = 0; i < (N + 1) * (M + 1); i++)
        {
            arr[i] = i;
            size[i] = 1;
        }

        long cost = 0;
        int edgesAdded = 0;
        while (!pq.isEmpty() && edgesAdded < N * M + N + M)
        {
            Edge e = pq.remove();
            if (root(e.v) != root(e.u))
            {
                cost += e.w;
                edgesAdded++;
                union(e.v, e.u);
            }
        }

        out.println(cost);

        out.close();
    }

    private static void union(int a, int b)
    {
        int ra = root(a);
        int rb = root(b);
        if (size[ra] < size[rb])
        {
            arr[ra] = arr[rb];
            size[rb] += size[ra];
        }
        else
        {
            arr[rb] = arr[ra];
            size[ra] += size[rb];
        }
    }

    private static int root(int i)
    {
        while (arr[i] != i)
        {
            arr[i] = arr[arr[i]];
            i = arr[i];
        }
        return i;
    }

    private static class Edge implements Comparable<Edge>
    {
        private int v, u, w;

        public Edge(int v, int u, int w)
        {
            this.v = v;
            this.u = u;
            this.w = w;
        }

        @Override
        public int compareTo(Edge edge)
        {
            return this.w - edge.w;
        }
    }
}