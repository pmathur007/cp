import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class countcross
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);
        int r = Integer.parseInt(meta[2]);

        Set<Point>[][] roads = new HashSet[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                roads[i][j] = new HashSet<>();

        StringTokenizer st;
        for (int i = 0; i < r; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            roads[x1 - 1][y1 - 1].add(new Point(x2 - 1, y2 - 1));
            roads[x2 - 1][y2 - 1].add(new Point(x1 - 1, y1 - 1));
        }
        boolean[][] farm = new boolean[n][n];
        for (int i = 0; i < k; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            farm[x - 1][y - 1] = true;
        }

        int numDistant = (k * (k - 1)) / 2;
        boolean[][] visited;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (farm[i][j])
                {
                    visited = new boolean[n][n];
                    numDistant -= (numNotDistant(farm, visited, roads, i, j) - 1);
                    farm[i][j] = false;
                }
            }
        }

        out.println(numDistant);

        out.close();
    }

    private static int numNotDistant(boolean[][] farm, boolean[][] visited, Set<Point>[][] roads, int r, int c)
    {
        if (r < 0 || r >= farm.length || c < 0 || c >= farm.length || visited[r][c])
            return 0;

        visited[r][c] = true;
        int count = farm[r][c] ? 1 : 0;
        if (!roads[r][c].contains(new Point(r + 1, c)))
            count += numNotDistant(farm, visited, roads, r + 1, c);
        if (!roads[r][c].contains(new Point(r - 1, c)))
            count += numNotDistant(farm, visited, roads, r - 1, c);
        if (!roads[r][c].contains(new Point(r, c + 1)))
            count += numNotDistant(farm, visited, roads, r, c + 1);
        if (!roads[r][c].contains(new Point(r, c - 1)))
            count += numNotDistant(farm, visited, roads, r, c - 1);

        return count;
    }

    private static class Point
    {
        int r, c;

        public Point(int r, int c)
        {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r &&
                    c == point.c;
        }

        @Override
        public int hashCode()
        {
            int result = 1;
            result = 31 * result + r;
            result = 31 * result + c;
            return result;
        }
    }
}