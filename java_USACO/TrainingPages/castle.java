/*
ID: pranavm7
LANG: JAVA
TASK: castle
PROG: castle
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class castle
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

        String[] meta = in.readLine().split(" ");
        int cols = Integer.parseInt(meta[0]);
        int rows = Integer.parseInt(meta[1]);

        int[][] castle = new int[rows][cols];
        int[][] components = new int[rows][cols];
        ArrayList<Integer> componentSizes = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++)
        {
            String[] line = in.readLine().split(" ");
            for (int c = 0; c < cols; c++)
            {
                castle[r][c] = Integer.parseInt(line[c]);
            }
        }

        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                if (!visited[r][c])
                    componentSizes.add(floodFill(castle, components, visited, componentSizes.size(), r, c));
            }
        }

        out.println(componentSizes.size());
        out.println(max(componentSizes));

        int maxRow = 0;
        int maxCol = 0;
        char maxDirection = 'N';
        int maxSize = 0;

        for (int c = 0; c < cols; c++)
        {
            for (int r = rows - 1; r >= 0; r--)
            {
                int rmNorth = removeNorth(components, componentSizes, r, c);
                int rmEast = removeEast(components, componentSizes, r, c);
                if (rmNorth > maxSize)
                {
                    maxSize = rmNorth;
                    maxRow = r;
                    maxCol = c;
                    maxDirection = 'N';
                }
                if (rmEast > maxSize)
                {
                    maxSize = rmEast;
                    maxRow = r;
                    maxCol = c;
                    maxDirection = 'E';
                }
            }
        }

        out.println(maxSize);
        out.println((maxRow + 1) + " " + (maxCol + 1) + " " + maxDirection);

        out.close();
    }

    private static int removeNorth(int[][] components, ArrayList<Integer> componentSizes, int r, int c)
    {
        if (r - 1 < 0)
            return -1;

        if (components[r][c] == components[r - 1][c])
            return 0;

        return componentSizes.get(components[r][c]) + componentSizes.get(components[r - 1][c]);
    }

    private static int removeEast(int[][] components, ArrayList<Integer> componentSizes, int r, int c)
    {
        if (c + 1 >= components[0].length)
            return -1;

        if (components[r][c] == components[r][c + 1])
            return 0;

        return componentSizes.get(components[r][c]) + componentSizes.get(components[r][c + 1]);
    }

    private static int floodFill(int[][] castle, int[][] components, boolean[][] visited, int currentComponent, int r, int c)
    {
        if (r < 0 || r >= castle.length || c < 0 || c >= castle[0].length || visited[r][c])
            return 0;

        visited[r][c] = true;
        components[r][c] = currentComponent;

        int count = 1;
        int currentLoc = castle[r][c];

        if (currentLoc < 8)
            count += floodFill(castle, components, visited, currentComponent, r + 1, c);
        else
            currentLoc -= 8;

        if (currentLoc < 4)
            count += floodFill(castle, components, visited, currentComponent, r, c + 1);
        else
            currentLoc -= 4;

        if (currentLoc < 2)
            count += floodFill(castle, components, visited, currentComponent, r - 1, c);
        else
            currentLoc -= 2;

        if (currentLoc < 1)
            count += floodFill(castle, components, visited, currentComponent, r, c - 1);

        return count;
    }

    private static int max(ArrayList<Integer> list)
    {
        int max = 0;
        Iterator<Integer> itr = list.listIterator();
        while (itr.hasNext())
        {
            max = Math.max(itr.next(), max);
        }
        return max;
    }
}
