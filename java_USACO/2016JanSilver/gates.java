import java.io.*;
import java.util.HashSet;

public class gates
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("gates.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));

        int n = Integer.parseInt(in.readLine());
        String path = in.readLine();

        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++)
        {
            if (path.charAt(i) == 'N')
            {
                y++;
                yMax = y > yMax ? y : yMax;
            }
            else if (path.charAt(i) == 'E')
            {
                x++;
                xMax = x > xMax ? x : xMax;
            }
            else if (path.charAt(i) == 'S')
            {
                y--;
                yMin = y < yMin ? y : yMin;
            }
            else if (path.charAt(i) == 'W')
            {
                x--;
                xMin = x < xMin ? x : xMin;
            }
        }

        x = 0 - (xMin - 1);
        y = 0 - (yMin - 1);
        xMax += 0 - (xMin - 2);
        yMax += 0 - (yMin - 2);
        xMin = 0;
        yMin = 0;

        HashSet<Point>[][] points = new HashSet[xMax][yMax];
        for (int i = 0; i < xMax; i++)
            for (int j = 0; j < yMax; j++)
                points[i][j] = new HashSet<>();

        for (int i = 0; i < n; i++)
        {
            if (path.charAt(i) == 'N')
            {
                points[x][y].add(new Point(x - 1, y));
                points[x - 1][y].add(new Point(x, y));
                y++;
            } else if (path.charAt(i) == 'E')
            {
                points[x][y].add(new Point(x, y - 1));
                points[x][y - 1].add(new Point(x, y));
                x++;
            } else if (path.charAt(i) == 'S')
            {
                y--;
                points[x][y].add(new Point(x - 1, y));
                points[x - 1][y].add(new Point(x, y));
            } else if (path.charAt(i) == 'W')
            {
                x--;
                points[x][y].add(new Point(x, y - 1));
                points[x][y - 1].add(new Point(x, y));
            }
        }

        boolean[][] visited = new boolean[xMax][yMax];
        int numApart = -1;
        for (int i = 0; i < xMax; i++)
        {
            for (int j = 0; j < yMax; j++)
            {
                if (!visited[i][j])
                {
                    numApart++;
                    dfs(points, visited, i, j);
                }
            }
        }

        out.println(numApart);

        out.close();
    }

    private static void dfs(HashSet[][] points, boolean[][] visited, int x, int y)
    {
        if (x < 0 || x >= points.length || y < 0 || y >= points[0].length || visited[x][y])
            return;

        visited[x][y] = true;
        if (!points[x][y].contains(new Point(x + 1, y)))
            dfs(points, visited, x + 1, y);
        if (!points[x][y].contains(new Point(x - 1, y)))
            dfs(points, visited, x - 1, y);
        if (!points[x][y].contains(new Point(x, y + 1)))
            dfs(points, visited, x, y + 1);
        if (!points[x][y].contains(new Point(x, y - 1)))
            dfs(points, visited, x, y - 1);
    }

    private static class Point
    {
        int x, y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode()
        {
            return x * x * x + y * y * y - x - y;
        }
    }
}