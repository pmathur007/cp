import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class split
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("split.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("split.out")));

        int n = Integer.parseInt(in.readLine());
        Point[] xpts = new Point[n];
        Point[] ypts = new Point[n];

        StringTokenizer st;
        long bminx = 1000000001, bmaxx = 0, bminy = 1000000001, bmaxy = 0;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xpts[i] = new Point(x, y);
            ypts[i] = new Point(y, x);
            bminx = Math.min(bminx, x);
            bmaxx = Math.max(bmaxx, x);
            bminy = Math.min(bminy, y);
            bmaxy = Math.max(bmaxy, y);
        }

        long bigArea = (bmaxx - bminx) * (bmaxy - bminy);
        Arrays.sort(xpts, split::compareX);
        Arrays.sort(ypts, split::compareX);

        out.println(bigArea - Math.min(minBestArea(n, bigArea, xpts), minBestArea(n, bigArea, ypts)));

        out.close();
    }
    
    private static long minBestArea(int n, long bigArea, Point[] points)
    {
        long minNewArea = bigArea;

        long[] yminstart = new long[n];
        long[] ymaxstart = new long[n];
        long[] yminend = new long[n];
        long[] ymaxend = new long[n];

        for (int i = 0; i < n; i++)
        {
            if (i == 0)
            {
                yminstart[i] = ymaxstart[i] = points[i].y;
                yminend[n - i - 1] = ymaxend[n - i - 1] = points[n - i - 1].y;
            } else
            {
                yminstart[i] = Math.min(yminstart[i - 1], points[i].y);
                ymaxstart[i] = Math.max(ymaxstart[i - 1], points[i].y);
                yminend[n - i - 1] = Math.min(yminend[n - i], points[n - i - 1].y);
                ymaxend[n - i - 1] = Math.max(ymaxend[n - i], points[n - i - 1].y);
            }
        }

        for (int i = 0; i < n - 1; i++)
        {
            long leftArea = (points[i].x - points[0].x) * (ymaxstart[i] - yminstart[i]);
            long rightArea = (points[n - 1].x - points[i + 1].x) * (ymaxend[i + 1] - yminend[i + 1]);
            minNewArea = Math.min(minNewArea, leftArea + rightArea);
        }

        return minNewArea;
    }
    
    private static int compareX(Point p1, Point p2)
    {
        if (p1.x == p2.x)
            return compareY(p1, p2);
        else if (p1.x < p2.x)
            return -1;
        return 1;
    }

    private static int compareY(Point p1, Point p2)
    {
        if (p1.y == p2.y)
            return compareX(p1, p2);
        else if (p1.y < p2.y)
            return -1;
        return 1;
    }

    private static class Point
    {
        private long x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}