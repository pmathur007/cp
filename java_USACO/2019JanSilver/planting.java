import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class planting
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));

        int n = Integer.parseInt(in.readLine());

        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[a].add(b);
            graph[b].add(a);
        }

        int maxChildren = 0;
        for (LinkedList<Integer> list : graph)
            maxChildren = Math.max(maxChildren, list.size());

        out.print(maxChildren + 1);

        out.close();
    }
}