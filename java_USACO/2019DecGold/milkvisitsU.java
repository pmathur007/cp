import java.io.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class milkvisitsU
{
    private static int n, m;
    private static int[] c;
    private static Pair[] anc;
    private static LinkedList<Integer>[] graph;
    private static int[] path;
    private static Stack<Pair>[] ancColors;
    private static LinkedList<Pair>[] queries;
    private static int[] qc;
    private static boolean[] ans;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        c = new int[n];
        graph = new LinkedList[n];
        path = new int[n];
        ancColors = new Stack[n];
        queries = new LinkedList[n];

        for (int i = 0; i < n; i++)
        {
            c[i] = Integer.parseInt(st.nextToken()) - 1;
            graph[i] = new LinkedList<>();
            ancColors[i] = new Stack<>();
            queries[i] = new LinkedList<>();
        }

        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        anc = new Pair[n];
        ancdfs(0, -1);

        qc = new int[m];
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            queries[a].add(new Pair(i, b));
            queries[b].add(new Pair(i, a));
            qc[i] = c;
        }


        ans = new boolean[m];
        qdfs(0, -1, 0);

        StringBuilder s = new StringBuilder();
        for (boolean b : ans)
            s.append(b ? "1" : "0");
        out.println(s.toString());

        out.close();
    }

    private static int posQ = 0;
    private static void qdfs(int v, int p, int d)
    {
        path[posQ++] = v;
        ancColors[c[v]].push(new Pair(v, d));
        for (Pair q : queries[v])
        {
            if (!ancColors[qc[q.f]].empty() && !ans[q.f])
            {
                Pair y = ancColors[qc[q.f]].peek();
                if (y.f == v)
                    ans[q.f] = true;
                else if (isAnc(y.f, q.s))
                    ans[q.f] = !(isAnc(path[y.s + 1], v) && isAnc(path[y.s + 1], q.s));
                else
                    ans[q.f] = true;
            }
        }
        for (int c : graph[v])
            if (c != p)
                qdfs(c, v, d + 1);
        posQ--;
        ancColors[c[v]].pop();
    }

    private static int posA = 0;
    private static void ancdfs(int v, int p)
    {
        int f = posA++;
        for (int c : graph[v])
            if (c != p)
                ancdfs(c, v);
        anc[v] = new Pair(f, posA++);
    }

    private static boolean isAnc(int a, int b)
    {
        return anc[a].f <= anc[b].f && anc[b].s <= anc[a].s;
    }

    private static class Pair
    {
        private int f, s;

        public Pair(int f, int s)
        {
            this.f = f;
            this.s = s;
        }
    }
}