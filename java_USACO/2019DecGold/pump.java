import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class pump
{
    private static int n, m;
    private static int[][] cost, flow;
    private static LinkedList<Integer>[] graph;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("pump.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        cost = new int[n][n];
        flow = new int[n][n];
        PriorityQueue<Integer> flows = new PriorityQueue<>();
        for(int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
            cost[a][b] = cost[b][a] = c;
            flow[a][b] = flow[b][a] = f;
            flows.add(f);
        }

        double best = 0;
        while (!flows.isEmpty())
        {
            int f = flows.remove();
            while (!flows.isEmpty() && flows.peek() == f)
                flows.remove();
            int c = djikstra(f);
            if (c == -1)
                break;
            else
                best = Math.max(best, ((double) f) / ((double) c));
        }

        out.println((int) (1000000 * best));

        out.close();
    }

    private static int djikstra(int f)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();

        pq.add(new Pair(0, 0));
        while (!pq.isEmpty())
        {
            Pair p = pq.remove();
            visited.add(p.s);
            if (p.s == n - 1)
                return p.f;
            for (int c : graph[p.s])
            {
                if (flow[p.s][c] >= f && !visited.contains(c))
                    pq.add(new Pair(p.f + cost[p.s][c], c));
            }
        }
        return -1;
    }

    private static class Pair implements Comparable<Pair>
    {
        private int f, s;

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
// SOLUTION 1 - DFS, GETS 5 TEST CASES
//private static int n, m;
//    private static LinkedList<Edge>[] graph;
//    private static LinkedList<Integer> cList = new LinkedList<>();
//    private static LinkedList<Integer> fList = new LinkedList<>();
//    private static boolean[] visited;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("pump.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//
//        graph = new LinkedList[n];
//        visited = new boolean[n];
//        for (int i = 0; i < n; i++)
//            graph[i] = new LinkedList<>();
//        for (int i = 0; i < m; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int a = Integer.parseInt(st.nextToken()) - 1;
//            int b = Integer.parseInt(st.nextToken()) - 1;
//            int c = Integer.parseInt(st.nextToken());
//            int f = Integer.parseInt(st.nextToken());
//            graph[a].add(new Edge(b, c, f));
//            graph[b].add(new Edge(a, c ,f));
//        }
//
//        dfs(0, 0, Integer.MAX_VALUE);
//
//        int best = 0;
//        while (!cList.isEmpty())
//        {
//            int c = cList.remove();
//            int f = fList.remove();
//            best = Math.max(best, (int) (1000000 * (((double) f)/((double) c))));
//        }
//
//        out.println(best);
//
//        out.close();
//    }
//
//    private static void dfs(int v, int c, int f)
//    {
//        if (v == n - 1)
//        {
//            cList.add(c);
//            fList.add(f);
//        }
//        else
//        {
//            visited[v] = true;
//            for (Edge e : graph[v])
//                if (!visited[e.d])
//                    dfs(e.d, c + e.c, Math.min(f, e.f));
//            visited[v] = false;
//        }
//    }
//
//private static class Edge
//{
//    private int d, c, f;
//
//    public Edge(int d, int c, int f)
//    {
//        this.d = d;
//        this.c = c;
//        this.f = f;
//    }
//}