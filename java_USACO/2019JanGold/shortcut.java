import java.io.*;
import java.util.*;

public class shortcut
{
    private static final long INF = 1000000000000000L;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        long[] c = new long[n];
        LinkedList<Edge>[] graph = new LinkedList[n];
        LinkedList<Integer>[] dfsGraph = new LinkedList[n];
        long[] dist = new long[n];
        int[] p = new int[n];
        long[] numThru = new long[n];

        for (int i = 0; i < n; i++)
        {
            c[i] = Integer.parseInt(st.nextToken());
            graph[i] = new LinkedList<>();
            dfsGraph[i] = new LinkedList<>();
            dist[i] = i == 0 ? 0 : INF;
            p[i] = -1;
            numThru[i] = c[i];
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();

        pq.add(new Edge(0, 0));
        while (visited.size() != n)
        {
            int v = pq.remove().d;
            visited.add(v);
            for (Edge e : graph[v])
            {
                if (!visited.contains(e.d))
                {
                    if (dist[v] + e.w == dist[e.d])
                        p[e.d] = Math.min(p[e.d], v);
                    else if (dist[v] + e.w < dist[e.d])
                    {
                        dist[e.d] = dist[v] + e.w;
                        p[e.d] = v;
                    }
                    pq.add(new Edge(e.d, dist[e.d]));
                }
            }
        }

        for (int i = 0; i < n; i++)
            if (i != 0)
                dfsGraph[p[i]].add(i);
        dfs(dfsGraph, c, numThru, 0);

        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            max = Math.max(max, numThru[i] * (dist[i] - t));
        out.println(max);

        out.close();
    }

    private static void dfs(LinkedList<Integer>[] dfsGraph, long[] c, long[] numThru, int v)
    {
        for (int u : dfsGraph[v])
        {
            dfs(dfsGraph, c, numThru, u);
            numThru[v] += numThru[u];
        }
    }

    private static class Edge implements Comparable<Edge>
    {
        int d;
        long w;

        public Edge(int d, long w)
        {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e)
        {
            if (this.w == e.w)
                return 0;
            else if (this.w < e.w)
                return -1;
            return 1;
        }
    }
}

// SUBMISSION 2 - FAILED

//    private static final long INF = 100000000000000000L;
//    private static int n, m;
//    private static long t;
//    private static long[] numCows;
//    private static LinkedList<Edge>[] graph;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        t = Long.parseLong(st.nextToken());
//
//        numCows = new long[n];
//        graph = new LinkedList[n];
//        st = new StringTokenizer(in.readLine());
//        for (int i = 0; i < n; i++)
//        {
//            numCows[i] = Long.parseLong(st.nextToken());
//            graph[i] = new LinkedList<>();
//        }
//
//        for (int i = 0; i < m; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int a = Integer.parseInt(st.nextToken()) - 1;
//            int b = Integer.parseInt(st.nextToken()) - 1;
//            long w = Long.parseLong(st.nextToken());
//            graph[a].add(new Edge(b, w));
//            graph[b].add(new Edge(a, w));
//        }
//
//        long[] dist = new long[n];
//        int[] parent = new int[n];
//        HashSet<Integer> visited = new HashSet<>();
//        PriorityQueue<Edge> pq = new PriorityQueue<>();
//
//        for (int i = 0; i < n; i++)
//        {
//            dist[i] = i == 0 ? 0 : INF;
//            parent[i] = -1;
//        }
//        pq.add(new Edge(0, 0));
//
//        while (visited.size() != n)
//        {
//            int cur = pq.remove().d;
//            visited.add(cur);
//            for (Edge e : graph[cur])
//            {
//                if (!visited.contains(e.d))
//                {
//                    if (dist[cur] + e.w < dist[e.d] || (dist[cur] + e.w == dist[e.d] && cur < parent[e.d]))
//                    {
//                        dist[e.d] = dist[cur] + e.w;
//                        parent[e.d] = cur;
//                    }
//                    pq.add(new Edge(e.d, dist[e.d]));
//                }
//            }
//        }
//
//        long[] numThru = new long[n];
//        for (int i = 0; i < n; i++)
//        {
//            int cur = i;
//            while (cur != -1)
//            {
//                numThru[cur] += numCows[i];
//                cur = parent[cur];
//            }
//        }
//
//        long max = 0;
//        for (int i = 0; i < n; i++)
//            max = Math.max(max, numCows[i] * (dist[i] - t));
//        out.println(max);
//
//        out.close();
//    }
//
//private static class Edge implements Comparable<Edge>
//{
//    int d;
//    long w;
//
//    public Edge(int d, long w)
//    {
//        this.d = d;
//        this.w = w;
//    }
//
//    @Override
//    public int compareTo(Edge e)
//    {
//        if (this.w < e.w)
//            return -1;
//        else if (this.w > e.w)
//            return 1;
//        return 0;
//    }
//}

// SUBMISSION 1 - FAILED

//    private static final long INF = 10000000000000L;
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("shortcut.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        int n = Long.parseLong(st.nextToken());
//        int m = Long.parseLong(st.nextToken());
//        int t = Long.parseLong(st.nextToken());
//
//        st = new StringTokenizer(in.readLine(), " ");
//        int[] numCows = new int[n];
//        for (int i = 0; i < n; i++)
//        {
//            numCows[i] = Long.parseLong(st.nextToken());
//        }
//
//        List<Edge>[] graph = new LinkedList[n];
//        for (int i = 0; i < n; i++)
//            graph[i] = new LinkedList<>();
//
//        for (int i = 0; i < m; i++)
//        {
//            st = new StringTokenizer(in.readLine(), " ");
//            int a = Long.parseLong(st.nextToken()) - 1;
//            int b = Long.parseLong(st.nextToken()) - 1;
//            int w = Long.parseLong(st.nextToken());
//
//            graph[a].add(new Edge(b, w));
//            graph[b].add(new Edge(a, w));
//        }
//
//        long[] dist = new long[n];
//        int[] last = new int[n];
//        for (int i = 0; i < n; i++)
//        {
//            if (i != 0)
//                dist[i] = INF;
//            last[i] = -1;
//        }
//        boolean[] processed = new boolean[n];
//        PriorityQueue<Edge> q = new PriorityQueue<>(n);
//
//        djikstra(graph, q, dist, last, processed,0);
//
//        List<Integer>[] tree = new LinkedList[n];
//        for (int i = 0; i < n; i++)
//            tree[i] = new LinkedList<>();
//        for (int i = 0; i < n; i++)
//        {
//            if (last[i] != -1)
//                tree[last[i]].add(i);
//        }
//        long[] passThru = new long[n];
//        dfs(tree, numCows, passThru, 0);
//
//        long maxReduce = 0;
//        for (int i = 0; i < n; i++)
//        {
//            long potReduce = passThru[i] * (dist[i] - t);
//            if (potReduce > maxReduce)
//                maxReduce = potReduce;
//        }
//
//        out.print(maxReduce);
//
//        out.close();
//    }
//
//    private static void djikstra(List<Edge>[] graph, PriorityQueue<Edge> q, long[] dist, int[] last, boolean[] processed, int src)
//    {
//        q.add(new Edge(src, 0));
//
//        int numProcesed = 0;
//        while (numProcesed != graph.length)
//        {
//            int cur = q.remove().d;
//            processed[cur] = true;
//            numProcesed++;
//
//            long potDist;
//
//            for (Edge e : graph[cur])
//            {
//                if (!processed[e.d])
//                {
//                    potDist = dist[cur] + e.w;
//                    if (potDist < dist[e.d] || (potDist == dist[e.d] && cur < last[e.d]))
//                    {
//                        dist[e.d] = potDist;
//                        last[e.d] = cur;
//                    }
//                    q.add(new Edge(e.d, potDist));
//                }
//            }
//        }
//    }
//
//    private static int dfs(List<Integer>[] tree, int[] numCows, long[] passThru, int cur)
//    {
//        int numCowsPassing = numCows[cur];
//        for (Integer i : tree[cur])
//        {
//            numCowsPassing += dfs(tree, numCows, passThru, i);
//        }
//        passThru[cur] = numCowsPassing;
//        return numCowsPassing;
//    }
//
//    private static class Edge implements Comparable<Edge>
//    {
//        int d;
//        long w;
//
//        public Edge(int d, long w)
//        {
//            this.d = d;
//            this.w = w;
//        }
//
//        @Override
//        public int compareTo(Edge o)
//        {
//            if (this.w > o.w)
//                return 1;
//            if (this.w < o.w)
//                return -1;
//            return 0;
//        }
//    }