import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class mootube
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int q = Integer.parseInt(meta[1]);

        LinkedList<Edge>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new LinkedList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph[a - 1].add(new Edge(b - 1, r));
            graph[b - 1].add(new Edge(a - 1, r));
        }

        for (int i = 0; i < q; i++)
        {
            String[] query = in.readLine().split(" ");
            out.println(bfsForRelevance(graph, n, Integer.parseInt(query[1]) - 1, Integer.parseInt(query[0])));
        }

        out.close();
    }

    private static int bfsForRelevance(LinkedList<Edge>[] graph, int n, int s, int k)
    {
        int numVisited = 0;
        boolean[] visited = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();

            for (Edge x : graph[s])
            {
                if (!visited[x.d] && x.w >= k)
                {
                    visited[x.d] = true;
                    numVisited++;
                    queue.add(x.d);
                }
            }
        }

        return numVisited;
    }

    private static class Edge
    {
        private int d, w;

        public Edge(int d, int w)
        {
            this.d = d;
            this.w = w;
        }
    }
}