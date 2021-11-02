import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class time
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("time.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] mooney = new int[n];
        LinkedList<Integer>[] src = new LinkedList[n];
        long[][] dp = new long[1000][n];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
        {
            mooney[i] = Integer.parseInt(st.nextToken());
            src[i] = new LinkedList<>();
            for (int j = 0; j < 1000; j++)
                dp[j][i] = Long.MIN_VALUE;
        }

        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            src[b].add(a);
        }

        dp[0][0] = 0;
        long ans = 0;
        for (int i = 1; i < 1000; i++)
        {
            for (int v = 0; v < n; v++)
            {
                for (int u : src[v])
                {
                    if (dp[i - 1][u] != Long.MIN_VALUE)
                        dp[i][v] = Math.max(dp[i][v], dp[i - 1][u] + mooney[v] - c * (2 * i - 1));
                }
            }
            ans = Math.max(ans, dp[i][0]);
        }

        out.println(ans);

        out.close();
    }
}

//    private static int n, m, c;
//    private static int[] mooney;
//    private static LinkedList<Integer>[] graph;
//    private static boolean[] visited;
//    private static LinkedList<Pair> cycles;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("time.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
//        StringTokenizer st = new StringTokenizer(in.readLine());
//
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        c = Integer.parseInt(st.nextToken());
//
//        mooney = new int[n];
//        graph = new LinkedList[n];
//        st = new StringTokenizer(in.readLine());
//        for (int i = 0; i < n; i++)
//        {
//            mooney[i] = Integer.parseInt(st.nextToken());
//            graph[i] = new LinkedList<>();
//        }
//
//        for (int i = 0; i < m; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int a = Integer.parseInt(st.nextToken()) - 1;
//            int b = Integer.parseInt(st.nextToken()) - 1;
//            graph[a].add(b);
//        }
//
//        visited = new boolean[n];
//        visited[0] = true;
//        cycles = new LinkedList<>();
//
//        dfs(0, 0, 0);
//
//        long ans = 0;
//        int t = 0;
//        while (true)
//        {
//            long best_choice = 0;
//            int bestT = 0;
//            for (Pair cycle : cycles)
//            {
//                long choice = cycle.w - c * (2 * t * cycle.l + cycle.l * cycle.l);
//                if (choice > best_choice)
//                {
//                    best_choice = choice;
//                    bestT = cycle.l;
//                }
//            }
//            if (best_choice <= 0)
//                break;
//            ans += best_choice;
//            t += bestT;
//        }
//
//        out.println(ans);
//        out.close();
//    }
//
//    private static void dfs(int v, int w, int d)
//    {
//        visited[v] = true;
//        for (int child : graph[v])
//        {
//            if (child == 0)
//                cycles.add(new Pair(d + 1, w));
//            if (!visited[child])
//                dfs(child, w + mooney[child], d + 1);
//        }
//    }
//
//private static class Pair
//{
//    private int l, w;
//
//    public Pair(int l, int w)
//    {
//        this.l = l;
//        this.w = w;
//    }
//}