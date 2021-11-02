import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class atlarge
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("atlarge.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;

        Set<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++)
            graph[i] = new HashSet<>();
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        LinkedList<Integer> q = new LinkedList<>();
        parent[k] = -1;
        build(graph, parent, depth, q, -1, k);

        int[] farmers = new int[n];
        boolean[] added = new boolean[n];
        while (!q.isEmpty())
        {
            int cur = q.remove();
            if (parent[cur] != -1)
            {
                farmers[parent[cur]] = farmers[parent[cur]] == 0 ? farmers[cur] + 1 : Math.min(farmers[parent[cur]], farmers[cur] + 1);
                if (!added[parent[cur]])
                {
                    q.add(parent[cur]);
                    added[parent[cur]] = true;
                }
            }
        }

        out.println(dfsAns(graph, depth, farmers, k));

        out.close();
    }

    private static int dfsAns(Set<Integer>[] graph, int[] depth, int[] farmers, int cur)
    {
        if (graph[cur].size() == 0 || farmers[cur] <= depth[cur])
            return 1;
        int ans = 0;
        for (int i : graph[cur])
            ans += dfsAns(graph, depth, farmers, i);
        return ans;
    }

    private static void build(Set<Integer>[] graph, int[] parent, int[] depth, LinkedList<Integer> q, int prev, int cur)
    {
        parent[cur] = prev;
        graph[cur].remove(prev);
        if (prev != -1)
            depth[cur] = depth[prev] + 1;
        if (graph[cur].size() == 0)
            q.add(cur);
        for (int i : graph[cur])
            build(graph, parent, depth, q, cur, i);
    }
}