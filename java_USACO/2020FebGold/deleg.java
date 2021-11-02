import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class deleg
{
    private static int n;
    private static LinkedList<Integer>[] graph;
    private static int[] size;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("deleg.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));

        n = Integer.parseInt(in.readLine());
        graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        size = new int[n];
        dfsChildren(-1, 0);



        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++)
        {
            if ((n - 1) % i != 0)
                sb.append(0);
        }



        out.close();
    }

    private static void dfsChildren(int p, int v)
    {
        size[v] = 1;
        for (int c : graph[v])
        {
            if (c != p)
            {
                dfsChildren(v, c);
                size[v] += size[c];
            }
        }
    }
}