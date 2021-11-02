import java.io.*;
import java.util.*;

public class lasers
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Integer, HashSet<Integer>> xMap = new HashMap<>();
        Map<Integer, HashSet<Integer>> yMap = new HashMap<>();
        Point[] points = new Point[n + 2];

        int lx = Integer.parseInt(st.nextToken());
        int ly = Integer.parseInt(st.nextToken());
        points[0] = new Point(lx, ly);
        xMap.put(lx, new HashSet<>());
        xMap.get(lx).add(0);
        yMap.put(ly, new HashSet<>());
        yMap.get(ly).add(0);

        int bx = Integer.parseInt(st.nextToken());
        int by = Integer.parseInt(st.nextToken());
        points[1] = new Point(bx, by);
        if (!xMap.containsKey(bx))
            xMap.put(bx, new HashSet<>());
        xMap.get(bx).add(1);
        if (!yMap.containsKey(by))
            yMap.put(by, new HashSet<>());
        yMap.get(by).add(1);

        for (int i = 2; i < n + 2; i++)
        {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
            if (!xMap.containsKey(x))
                xMap.put(x, new HashSet<>());
            xMap.get(x).add(i);
            if (!yMap.containsKey(y))
                yMap.put(y, new HashSet<>());
            yMap.get(y).add(i);
        }

        int[] parent = new int[n + 2];
        for (int i = 0; i < n + 2; i++)
            parent[i] = -1;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty())
        {
            int p = q.remove();
            if (p == 1)
                break;

            Iterator<Integer> xCoords = xMap.get(points[p].x).iterator();
            while (xCoords.hasNext())
            {
                int i = xCoords.next();
                if (parent[i] == -1 && i != 0)
                {
                    parent[i] = p;
                    q.add(i);
                }
                xCoords.remove();
            }

            Iterator<Integer> yCoords = yMap.get(points[p].y).iterator();
            while (yCoords.hasNext())
            {
                int i = yCoords.next();
                if (parent[i] == -1 && i != 0)
                {
                    parent[i] = p;
                    q.add(i);
                }
                yCoords.remove();
            }
        }

        if (parent[1] == -1)
            out.println(-1);
        else
        {
            int depth = 0;
            int cur = 1;
            while (parent[cur] != -1)
            {
                depth++;
                cur = parent[cur];
            }
            out.println(depth - 1);
        }

        out.close();
    }

    private static class Point
    {
        private int x, y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}