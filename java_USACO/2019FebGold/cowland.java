import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class cowland
{
    private static int n, q;
    private static LinkedList<Integer>[] graph;
    private static int[] enjoy, parent, depth, heavy, head, pos, segTreeArr, segTree;
    private static int curPos = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowland.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));


        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        enjoy = new int[n];
        graph = new LinkedList[n];
        parent = new int[n];
        parent[0] = -1;
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        segTreeArr = new int[n];
        segTree = new int[4 * n];

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
        {
            enjoy[i] = Integer.parseInt(st.nextToken());
            graph[i] = new LinkedList<>();
            heavy[i] = -1;
        }

        for (int i = 0; i < n - 1; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(0);
        decompose(0, 0);
        buildSegTree(0, n - 1, 0);

        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (t == 1)
                update(0, n - 1, pos[a - 1], b, 0);
            else
                out.println(queryTree(a - 1, b - 1));
        }

        out.close();
    }

    private static int dfs(int v)
    {
        int size = 1;
        int maxChildSize = 0;
        for (int c : graph[v])
        {
            if (c != parent[v])
            {
                parent[c] = v;
                depth[c] = depth[v] + 1;
                int childSize = dfs(c);
                size += childSize;
                if (childSize > maxChildSize)
                {
                    maxChildSize = childSize;
                    heavy[v] = c;
                }
            }
        }
        return size;
    }

    private static void decompose(int v, int h)
    {
        head[v] = h;
        segTreeArr[curPos] = enjoy[v];
        pos[v] = curPos++;
        if (heavy[v] != -1)
            decompose(heavy[v], h);
        for (int c : graph[v])
            if (c != parent[v] && c != heavy[v])
                decompose(c, c);
    }

    private static int queryTree(int a, int b)
    {
        int res = 0;
        for (; head[a] != head[b]; b = parent[head[b]])
        {
            if (depth[head[a]] > depth[head[b]])
            {
                int temp = a;
                a = b;
                b = temp;
            }
            res = res ^ querySegTree(pos[head[b]], pos[b], 0, n - 1, 0);
        }
        if (depth[a] > depth[b])
        {
            int temp = a;
            a = b;
            b = temp;
        }
        res = res ^ querySegTree(pos[a], pos[b], 0, n - 1, 0);
        return res;
    }

    private static void buildSegTree(int l, int r, int cur)
    {
        if (l == r)
            segTree[cur] = segTreeArr[l];
        else
        {
            int mid = (l + r) / 2;
            buildSegTree(l, mid, 2 * cur + 1);
            buildSegTree(mid + 1, r, 2 * cur + 2);
            segTree[cur] = segTree[2 * cur + 1] ^ segTree[2 * cur + 2];
        }
    }

    private static void update(int l, int r, int pos, int newVal, int cur)
    {
        if (l == r)
        {
            segTreeArr[l] = newVal;
            segTree[cur] = newVal;
        }
        else
        {
            int mid = (l + r) / 2;
            if (pos <= mid)
                update(l, mid, pos, newVal, 2 * cur + 1);
            else
                update(mid + 1, r, pos, newVal,2 * cur + 2);
            segTree[cur] = segTree[2 * cur + 1] ^ segTree[2 * cur + 2];
        }
    }

    private static int querySegTree(int ql, int qr, int l, int r, int cur)
    {
        if (qr < l || r < ql)
            return 0;
        if (ql <= l && r <= qr)
            return segTree[cur];
        int mid = (l + r) / 2;
        return querySegTree(ql, qr, l, mid, 2 * cur + 1) ^ querySegTree(ql, qr, mid + 1, r, 2 * cur + 2);
    }
}

// IN CONTEST - FAILED

//    private static int n;
//    private static int q;
//    private static int[] enjoy;
//    private static Set<Integer>[] graph;
//    private static int[] parent;
//    private static int[] depth;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("cowland.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowland.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        n = Integer.parseInt(st.nextToken());
//        q = Integer.parseInt(st.nextToken());
//
//        st = new StringTokenizer(in.readLine());
//        enjoy = new int[n];
//        for (int i = 0; i < n; i++)
//            enjoy[i] = Integer.parseInt(st.nextToken());
//
//        graph = new HashSet[n];
//        for (int i = 0; i < n; i++)
//            graph[i] = new HashSet<>();
//        for (int i = 0; i < n - 1; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int a = Integer.parseInt(st.nextToken()) - 1;
//            int b = Integer.parseInt(st.nextToken()) - 1;
//            graph[a].add(b);
//            graph[b].add(a);
//        }
//
//        parent = new int[n];
//        depth = new int[n];
//        parent[0] = 0;
//        buildPark(0, 0);
//
//        int[][] jumpTable = new int[n][((int) (Math.log(n) / Math.log(2))) + 1];
//        for (int i = 0; i < n; i++)
//            jumpTable[i][0] = parent[i];
//        for (int j = 1; j < jumpTable[0].length; j++)
//            for (int i = 0; i < n; i++)
//                jumpTable[i][j] = jumpTable[jumpTable[i][j - 1]][j - 1];
//
//        for (int i = 0; i < q; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int type = Integer.parseInt(st.nextToken());
//            if (type == 1)
//            {
//                int index = Integer.parseInt(st.nextToken()) - 1;
//                enjoy[index] = Integer.parseInt(st.nextToken());
//            }
//            else
//            {
//                int u = Integer.parseInt(st.nextToken()) - 1;
//                int v = Integer.parseInt(st.nextToken()) - 1;
//                int lca = lca(jumpTable, u, v);
//                int uPath = 0;
//                int vPath = 0;
//                while (u != lca)
//                {
//                    uPath = uPath ^ enjoy[u];
//                    u = parent[u];
//                }
//                while (v != lca)
//                {
//                    vPath = vPath ^ enjoy[v];
//                    v = parent[v];
//                }
//                out.println(uPath ^ vPath ^ enjoy[lca]);
//            }
//        }
//        out.close();
//    }
//
//    private static int lca(int[][] jumpTable, int u, int v)
//    {
//        int d = Math.abs(depth[u] - depth[v]);
//        if (depth[u] > depth[v])
//            u = jump(jumpTable, u, d);
//        else
//            v = jump(jumpTable, v, d);
//
//        if (u == v)
//            return u;
//
//        for (int i = jumpTable[0].length - 1; i >= 0; i--)
//        {
//            if (jumpTable[u][i] != jumpTable[v][i])
//            {
//                u = jumpTable[u][i];
//                v = jumpTable[v][i];
//            }
//        }
//        return parent[u];
//    }
//
//    private static int jump(int[][] jumpTable, int u, int d)
//    {
//        int j = 0;
//        while (d > 0)
//        {
//            if ((d & 1) == 1)
//                u = jumpTable[u][j];
//            d = d >> 1;
//            j++;
//        }
//        return u;
//    }
//
//    private static void buildPark(int prev, int cur)
//    {
//        parent[cur] = prev;
//        graph[cur].remove(prev);
//        if (cur != 0)
//            depth[cur] = depth[prev] + 1;
//        for (int i : graph[cur])
//            buildPark(cur, i);
//    }