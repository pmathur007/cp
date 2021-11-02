import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class closing
{
    private static int N, M;
    private static LinkedList<Integer>[] graph;
    private static int[] arr, size;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("closing.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        size = new int[N];
        graph = new LinkedList[N];
        for (int i = 0; i < N; i++)
        {
            graph[i] = new LinkedList<>();
            arr[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        Stack<Integer> rm = new Stack<>();
        for (int i = 0; i < N; i++)
            rm.push(Integer.parseInt(in.readLine()) - 1);


        boolean[] inGraph = new boolean[N];
        int[] numComponents = new int[N];
        numComponents[N - 1] = 1;

        int lastClosed = rm.pop();
        inGraph[lastClosed] = true;
        for (int i = N - 2; i >= 0; i--)
        {
            int v = rm.pop();
            inGraph[v] = true;
            numComponents[i] = numComponents[i + 1] + 1;

            for (int c : graph[v])
            {
                if (inGraph[c])
                {
                    if (root(v) != root(c))
                        numComponents[i]--;
                    union(v, c);
                }
            }
        }

        for (int i = 0; i < N; i++)
        {
            System.out.println(numComponents[i]);
            if (numComponents[i] > 1)
                out.println("NO");
            else
                out.println("YES");
        }

        out.close();
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