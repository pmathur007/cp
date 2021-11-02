import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        long[][] field = new long[n][n];
        long[][] dist = new long[n][n];
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++)
            {
                field[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Long.MAX_VALUE;
            }
        }

        int[] dr = new int[] {0, 0, 1, 1, -1, -1, 2, 2, -2, -2, 3, -3, 0, 0, 1, -1};
        int[] dc = new int[] {3, -3, 2, -2, 2, -2, 1, -1, 1, -1, 0, 0, 1, -1, 0, 0};

        long ans = Long.MAX_VALUE;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty())
        {
            Edge v = pq.remove();
            int r = v.d / n;
            int c = v.d % n;

            if (v.w != dist[r][c])
                continue;

            int dToEnd = Math.abs(n - 1 - r) + Math.abs(n - 1 - c);
            if (dToEnd <= 2)
                ans = Math.min(ans, v.w + dToEnd * t);
            for (int i = 0; i < dr.length; i++)
            {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && v.w + field[nr][nc] + 3 * t < dist[nr][nc])
                {
                    dist[nr][nc] = v.w + field[nr][nc] + 3 * t;
                    pq.add(new Edge(nr * n + nc, dist[nr][nc]));
                }
            }
        }

        out.println(ans);

        out.close();
    }

    private static class Edge implements Comparable<Edge>
    {
        int d;
        long w;

        public Edge(int d, long w)
        {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e)
        {
            if (this.w == e.w)
                return 0;
            else if (this.w < e.w)
                return -1;
            return 1;
        }
    }
}

//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("visitfj.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int t = Integer.parseInt(st.nextToken());
//
//        long[][] field = new long[n][n];
//        LinkedList<Edge>[][][] graph = new LinkedList[n][n][3];
//        for (int i = 0; i < n; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            for (int j = 0; j < n; j++)
//            {
//                field[i][j] = Integer.parseInt(st.nextToken());
//                graph[i][j][0] = new LinkedList<>();
//                graph[i][j][1] = new LinkedList<>();
//                graph[i][j][2] = new LinkedList<>();
//            }
//        }
//
//        for (int i = 0; i < n; i++)
//        {
//            for (int j = 0; j < n; j++)
//            {
//
//            }
//        }
//
//        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        boolean[][][] visited = new boolean[n][n][3];
//        pq.add(new Edge(0, 0, 0, 0));
//        while (!pq.isEmpty())
//        {
//            Edge v = pq.remove();
//            if (v.x == n - 1 && v.y == n - 1)
//            {
//                out.println(v.w);
//                break;
//            }
//            visited[v.x][v.y][v.m] = true;
//            if (v.x - 1 >= 0 && !visited[v.x - 1][v.y][v.m])
//                pq.add(new Edge(v.x - 1, v.y, (v.m + 1) % 3, v.w + t + (v.m == 2 ? field[v.x - 1][v.y] : 0)));
//            if (v.x + 1 < n && !visited[v.x + 1][v.y][v.m])
//                pq.add(new Edge(v.x + 1, v.y, (v.m + 1) % 3, v.w + t + (v.m == 2 ? field[v.x + 1][v.y] : 0)));
//            if (v.y - 1 >= 0 && !visited[v.x][v.y - 1][v.m])
//                pq.add(new Edge(v.x, v.y - 1, (v.m + 1) % 3, v.w + t + (v.m == 2 ? field[v.x][v.y - 1] : 0)));
//            if (v.y + 1 < n && !visited[v.x][v.y + 1][v.m])
//                pq.add(new Edge(v.x, v.y + 1, (v.m + 1) % 3, v.w + t + (v.m == 2 ? field[v.x][v.y + 1] : 0)));
//        }
//
//        out.close();
//    }
//
//private static class Edge implements Comparable<Edge>
//{
//    int x, y, m;
//    long w;
//
//    public Edge(int x, int y, int m, long w)
//    {
//        this.x = x;
//        this.y = y;
//        this.m = m;
//        this.w = w;
//    }
//
//    @Override
//    public int compareTo(Edge e)
//    {
//        if (this.w == e.w)
//            return 0;
//        else if (this.w < e.w)
//            return -1;
//        return 1;
//    }
//}