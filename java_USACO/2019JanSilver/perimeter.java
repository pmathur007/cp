import java.io.*;

public class perimeter
{
    private static int maxArea = 0;
    private static int minPer = 0;
    private static int area = 0;
    private static int per = 0;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        int n = Integer.parseInt(in.readLine());
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            grid[i] = in.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (!visited[i][j] && grid[i][j] == '#')
                {
                    dfsAP(grid, visited, i, j);
                    if (area > maxArea)
                    {
                        maxArea = area;
                        minPer = per;
                    }
                    else if (area == maxArea)
                        minPer = Math.min(minPer, per);

                    area = 0;
                    per = 0;
                }
            }
        }

        out.println(maxArea + " " + minPer);

        out.close();
    }

    private static void dfsAP(char[][] grid, boolean[][] visited, int r, int c)
    {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid.length || visited[r][c] || grid[r][c] == '.')
            return;

        area++;
        visited[r][c] = true;
        int tempPer = 4;

        if (r + 1 < grid.length && grid[r + 1][c] == '#')
            tempPer--;
        if (r - 1 >= 0 && grid[r - 1][c] == '#')
            tempPer--;
        if (c + 1 < grid.length && grid[r][c + 1] == '#')
            tempPer--;
        if (c - 1 >= 0 && grid[r][c - 1] == '#')
            tempPer--;

        dfsAP(grid, visited, r + 1, c);
        dfsAP(grid, visited, r - 1, c);
        dfsAP(grid, visited, r, c + 1);
        dfsAP(grid, visited, r, c - 1);

        per += tempPer;
    }
}