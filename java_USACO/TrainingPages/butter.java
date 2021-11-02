/*
ID: pranavm7
LANG: JAVA
TASK: butter
PROG: butter
*/

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class butter
{
    private static int N, P, C;
    private static LinkedList<Edge>[] graph;
    private static int[] cows, cost;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("butter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cows = new int[P];
        for (int i = 0; i < N; i++)
        {
            int p = Integer.parseInt(in.readLine()) - 1;
            cows[p]++;
        }

        graph = new LinkedList[P];
        for (int i = 0; i < P; i++)
            graph[i] = new LinkedList<>();

        for (int i = 0; i < C; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
        }

        cost = new int[P];
        for (int p = 0; p < P; p++)
        {
            if (cows[p] > 0)
            {
                int[] dist = new int[P];
                for (int i = 0; i < P; i++)
                    if (i != p)
                        dist[i] = 1000000000;

                PriorityQueue<Edge> pq = new PriorityQueue<>();
                HashSet<Integer> closed = new HashSet<>();
                pq.add(new Edge(p, 0));

                while (!pq.isEmpty() && closed.size() < P)
                {
                    Edge cur = pq.remove();
                    if (closed.contains(cur.v)) continue;
                    closed.add(cur.v);

                    for (Edge e : graph[cur.v])
                    {
                        if (dist[cur.v] + e.w < dist[e.v])
                        {
                            dist[e.v] = dist[cur.v] + e.w;
                            pq.add(new Edge(e.v, dist[e.v]));
                        }
                    }
                }

                for (int i = 0; i < P; i++)
                    cost[i] += cows[p] * dist[i];
            }
        }

        int min = 1000000000;
        for (int i = 0; i < P; i++)
            min = Math.min(min, cost[i]);
        out.println(min);

        out.close();
    }

    private static class Edge implements Comparable<Edge>
    {
        private int v, w;
        public Edge(int v, int w)
        {
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge edge)
        {
            return this.w - edge.w;
        }
    }
}