/*
ID: pranavm7
LANG: JAVA
TASK: cowtour
PROG: cowtour
*/

import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class cowtour
{
    private static final double INF = Double.MAX_VALUE;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowtour.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));

        int n = Integer.parseInt(in.readLine());

        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            points[i] = new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        int[][] adj = new int[n][n];
        double[][] paths = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j)
                    paths[i][j] = INF;

        for (int i = 0; i < n; i++)
        {
            String line = in.readLine();
            for (int j = 0; j < n; j++)
            {
                adj[i][j] = Character.getNumericValue(line.charAt(j));
                if (adj[i][j] == 1)
                    paths[i][j] = dist(points[i], points[j]);
            }
        }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (paths[i][k] + paths[k][j] < paths[i][j])
                        paths[i][j] = paths[i][k] + paths[k][j];

        int[] fields = new int[n];
        int cur = 1;
        LinkedList<LinkedList<Integer>> fieldList = new LinkedList<>();

        for (int i = 0; i < n; i++)
        {
            if (fields[i] == 0)
            {
                fieldList.add(new LinkedList<>());
                for (int j = 0; j < n; j++)
                {
                    if (paths[i][j] != Double.MAX_VALUE)
                    {
                        fields[j] = cur;
                        fieldList.getLast().add(j);
                    }
                }
                cur++;
            }
        }

        double[] diam = new double[fieldList.size()];
        for (int i = 0; i < diam.length; i++)
        {
            for (int j : fieldList.get(i))
            {
                for (int k : fieldList.get(i))
                    if (paths[j][k] > diam[i])
                        diam[i] = paths[j][k];
            }
        }

        double[] longestPaths = new double[n];
        double minPath = Double.MAX_VALUE;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (fields[i] != fields[j])
                {
                    if (longestPaths[i] == 0)
                    {
                        for (int k = 0; k < n; k++)
                            if (paths[i][k] != INF && paths[i][k] > longestPaths[i])
                                longestPaths[i] = paths[i][k];
                    }
                    if (longestPaths[j] == 0)
                    {
                        for (int k = 0; k < n; k++)
                            if (paths[j][k] != INF && paths[j][k] > longestPaths[j])
                                longestPaths[j] = paths[j][k];
                    }
                    minPath = Math.min(minPath, Math.max(diam[fields[i] - 1], Math.max(diam[fields[j] - 1], longestPaths[i] + longestPaths[j] + dist(points[i], points[j]))));
                }
            }
        }

        String formatted = new DecimalFormat("#.######").format(minPath);
        if (!formatted.contains("."))
            formatted += ".";
        int zeros = 6 - formatted.substring(formatted.indexOf(".") + 1).length();
        for (int i = 0; i < zeros; i++)
            formatted += "0";
        out.println(formatted);

        out.close();
    }

    private static double dist(Point p1, Point p2)
    {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    private static class Point
    {
        private double x, y;

        public Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
    }
}