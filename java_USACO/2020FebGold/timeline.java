import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class timeline
{
    private static int n, m, c;
    private static int[] times;
    private static LinkedList<Edge>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("timeline.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        times = new int[n];
        graph = new LinkedList[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
        {
            times[i] = Integer.parseInt(st.nextToken());
            graph[i] = new LinkedList<>();
        }

        boolean[] outof = new boolean[n];
        for (int i = 0; i < c; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken());
            graph[b].add(new Edge(a, x));
            outof[a] = true;
        }

        visited = new boolean[n];
        for (int i = 0; i < n; i++)
        {
            if (!outof[i])
                dfs(i);
        }

        for (int i = 0; i < n; i++)
            out.println(times[i]);

        out.close();
    }

    private static void dfs(int i)
    {
        if (visited[i])
            return;
        visited[i] = true;
        for (Edge e : graph[i])
        {
            dfs(e.a);
            times[i] = Math.max(times[i], times[e.a] + e.x);
        }
    }

    private static class Edge
    {
        int a, x;

        public Edge(int a, int x)
        {
            this.a = a;
            this.x = x;
        }
    }
}