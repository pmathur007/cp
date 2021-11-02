import java.io.*;
import java.util.*;

public class milkvisits
{
    private static int n, m;
    private static LinkedList<Integer>[] graph;
    private static int[] type, parent, depth, heavy, head, pos, segTreeArr, segTree;
    private static HashMap<Integer, LinkedList<Integer>> loc;
    private static int curPos = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        type = new int[n];
        graph = new LinkedList[n];
        parent = new int[n];
        parent[0] = -1;
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        segTreeArr = new int[n];
        segTree = new int[4 * n];
        loc = new HashMap<>();

        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
        {
            type[i] = Integer.parseInt(st.nextToken());
            if (!loc.containsKey(type[i]))
                loc.put(type[i], new LinkedList<>());
            loc.get(type[i]).add(i);

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

        PriorityQueue<Query> queries = new PriorityQueue<>();
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            queries.add(new Query(i, a, b, c));
        }

        int[] ans = new int[m];
        int last = -1;

        while (!queries.isEmpty())
        {
            Query query = queries.remove();
            if (!loc.containsKey(query.c))
                ans[query.i] = 0;
            else
            {
                if (query.c != last)
                {
                    for (int loc : loc.get(query.c))
                        update(0, n - 1, pos[loc], query.c, 0);
                    last = query.c;
                }
                ans[query.i] = queryTree(query.a, query.b) == query.c ? 1 : 0;
            }
        }

        for (int i : ans)
            out.print(i);
        out.println();

        out.close();
    }

    private static class Query implements Comparable<Query>
    {
        int i, a, b, c;

        public Query(int i, int a, int b, int c)
        {
            this.i = i;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Query query)
        {
            return this.c - query.c;
        }
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
        segTreeArr[curPos] = type[v];
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
            res = Math.max(res, querySegTree(pos[head[b]], pos[b], 0, n - 1, 0));
        }
        if (depth[a] > depth[b])
        {
            int temp = a;
            a = b;
            b = temp;
        }
        res = Math.max(res, querySegTree(pos[a], pos[b], 0, n - 1, 0));
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
            segTree[cur] = Math.max(segTree[2 * cur + 1], segTree[2 * cur + 2]);
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
            segTree[cur] = Math.max(segTree[2 * cur + 1], segTree[2 * cur + 2]);
        }
    }

    private static int querySegTree(int ql, int qr, int l, int r, int cur)
    {
        if (qr < l || r < ql)
            return 0;
        if (ql <= l && r <= qr)
            return segTree[cur];
        int mid = (l + r) / 2;
        return Math.max(querySegTree(ql, qr, l, mid, 2 * cur + 1), querySegTree(ql, qr, mid + 1, r, 2 * cur + 2));
    }
}