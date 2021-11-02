import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P1253D
{
    private static int[] arr, size, min, max;
    private static boolean[] visited;
    private static LinkedList<Integer>[] graph;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new LinkedList[n];
        arr = new int[n];
        size = new int[n];
        min = new int[n];
        max = new int[n];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new LinkedList<>();
            arr[i] = i;
            size[i] = 1;
            min[i] = i;
            max[i] = i;
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        int numEdges = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
        {
            if (!visited[i])
            {
                dfs(i);
                for (int j = min[i]; j <= max[i]; j++)
                {
                    if (root(i) != root(j))
                    {
                        numEdges++;
                        union(i, j);
                        if (!visited[j])
                            dfs(j);
                    }
                }
            }
        }
        System.out.println(numEdges);
    }

    private static void dfs(int v)
    {
        visited[v] = true;
        for (int u : graph[v])
        {
            if (!visited[u])
            {
                union(v, u);
                dfs(u);
            }
        }
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
        min[ra] = Math.min(min[ra], min[rb]);
        min[rb] = Math.min(min[ra], min[rb]);
        max[ra] = Math.max(max[ra], max[rb]);
        max[rb] = Math.max(max[ra], max[rb]);
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
}
