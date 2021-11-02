import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class C628
{
    private static int N;
    private static LinkedList<Integer>[] graph;
    private static int[] w;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        graph = new LinkedList[N];
        w = new int[N - 1];

        for (int i = 0; i < N; i++)
        {
            graph[i] = new LinkedList<>();
            if (i < N - 1)
                w[i] = -1;
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(i);
            graph[b].add(i);
        }

        boolean marked = false;
        for (int i = 0; i < N; i++)
        {
            if (!marked && graph[i].size() >= 3)
            {
                marked = true;
                int cur = 0;
                for (int edge : graph[i])
                    w[edge] = cur++;
                if (cur > 2)
                    break;
            }
        }

        int cur = marked ? 3 : 0;
        for (int i = 0; i < N - 1; i++)
        {
            if (w[i] == -1)
                w[i] = cur++;
            System.out.println(w[i]);
        }
    }
}