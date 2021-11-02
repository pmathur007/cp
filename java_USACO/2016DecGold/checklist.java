import java.io.*;
import java.util.StringTokenizer;

public class checklist
{
    private static final int INF = 1000000000;
    private static int h, g;
    private static Point[] hp, gp;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        h = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        hp = new Point[h + 1];
        gp = new Point[g + 1];
        dp = new int[h + 1][g + 1][2];

        for (int i = 1; i <= h; i++)
        {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            hp[i] = new Point(x, y);
        }
        hp[0] = hp[1];

        for (int j = 1; j <= g; j++)
        {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            gp[j] = new Point(x, y);
        }
        gp[0] = gp[1];

        for (int i = 0; i <= h; i++)
        {
            for (int j = 0; j <= g; j++)
            {
                dp[i][j][0] = dp[i][j][1] = INF;
                if (i == 0 && j == 0)
                    dp[i][j][0] = dp[i][j][1] = 0;
                else if (j == 0)
                    dp[i][j][0] = dp[i - 1][j][0] + dist(hp[i], hp[i - 1]);
            }
        }

        for (int i = 1; i <= h; i++)
        {
            for (int j = 1; j <= g; j++)
            {
                dp[i][j][0] = Math.min(dp[i - 1][j][0] + dist(hp[i], hp[i - 1]), dp[i - 1][j][1] + dist(hp[i], gp[j]));
                dp[i][j][1] = Math.min(dp[i][j - 1][0] + dist(gp[j], hp[i]), dp[i][j - 1][1] + dist(gp[j], gp[j - 1]));
            }
        }

        out.println(dp[h][g][0]);
        out.close();
    }

    private static int dist(Point p1, Point p2)
    {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
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

// SOLUTION 1, FAILED

//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("checklist.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        int h = Integer.parseInt(st.nextToken());
//        int g = Integer.parseInt(st.nextToken());
//
//        Point[] cows = new Point[h + g];
//        for (int i = 0; i < h + g; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            cows[i] = new Point(x, y);
//        }
//
//        Point[] last = new Point[h - 1];
//        int dist = 0;
//        for (int i = 0; i < h - 1; i++)
//        {
//            dist += dist(cows[i], cows[i + 1]);
//            last[i] = cows[i];
//        }
//
//        for (int i = h; i < h + g; i++)
//        {
//            int minChange = Integer.MAX_VALUE;
//            int lastToChange = -1;
//            for (int j = 0; j < h - 1; j++)
//            {
//                int potChange = dist(last[j], cows[i]) + dist(cows[i], cows[j + 1]) - dist(last[j], cows[j + 1]);
//                if (potChange < minChange)
//                {
//                    minChange = potChange;
//                    lastToChange = j;
//                }
//            }
//            dist += minChange;
//            last[lastToChange] = cows[i];
//        }
//
//        out.println(dist);
//
//        out.close();
//    }