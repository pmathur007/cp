import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class mootubeG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> edges = new PriorityQueue<>();
        PriorityQueue<Query> queries = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(in.readLine());
            queries.add(new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
        }

        int[] tree = new int[n];
        int[] size = new int[n];
        int[] ans = new int[q];

        for (int i = 0; i < n; i++)
        {
            tree[i] = i;
            size[i] = 1;
        }

        while (!queries.isEmpty())
        {
            Query qu = queries.remove();
            Edge e;
            while (!edges.isEmpty() && edges.peek().w >= qu.k)
            {
                e = edges.remove();
                union(tree, size, e.a, e.b);
            }
            ans[qu.x] = size[root(tree, qu.s)] - 1;
        }

        for (int i = 0; i < q; i++)
            out.println(ans[i]);

        out.close();
    }

    private static void union(int[] tree, int[] size, int a, int b)
    {
        int ra = root(tree, a);
        int rb = root(tree, b);

        if (size[ra] < size[rb])
        {
            tree[ra] = tree[rb];
            size[rb] += size[ra];
        }
        else
        {
            tree[rb] = tree[ra];
            size[ra] += size[rb];
        }
    }

    private static int root(int[] tree, int i)
    {
        while (tree[i] != i)
        {
            i = tree[i];
        }
        return i;
    }

    private static class Query implements Comparable<Query>
    {
        private int x, k, s;
        public Query(int x, int k, int s)
        {
            this.x = x;
            this.k = k;
            this.s = s;
        }

        @Override
        public int compareTo(Query o)
        {
            return o.k - this.k;
        }
    }

    private static class Edge implements Comparable<Edge>
    {
        private int a, b, w;
        public Edge(int a, int b, int w)
        {
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o)
        {
            return o.w - this.w;
        }
    }
}