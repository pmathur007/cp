/*
ID: pranavm7
LANG: JAVA
TASK: ttwo
PROG: ttwo
*/

import java.io.*;

public class ttwo
{
    private static char[][] board;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

        board = new char[10][10];
        Point f = null, c = null;
        for (int i = 0; i < 10; i++)
        {
            String line = in.readLine();
            for (int j = 0; j < 10; j++)
            {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'F')
                    f = new Point(i, j, 0);
                else if (board[i][j] == 'C')
                    c = new Point(i, j, 0);
            }
        }

        int i;
        for (i = 1; i <= 10000; i++)
        {
            f.next();
            c.next();
            if (f.equals(c))
            {
                out.println(i);
                break;
            }
        }

        if (i > 10000)
            out.println(0);
        out.close();
    }

    private static class Point
    {
        private int x, y, d;

        private Point(int x, int y, int d)
        {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        private void next()
        {
            if (d == 0 && x - 1 >= 0 && board[x - 1][y] != '*')
                x--;
            else if (d == 1 && y + 1 < 10 && board[x][y + 1] != '*')
                y++;
            else if (d == 2 && x + 1 < 10 && board[x + 1][y] != '*')
                x++;
            else if (d == 3 && y - 1 >= 0 && board[x][y - 1] != '*')
                y--;
            else
                d = (d + 1) % 4;
        }


        private boolean equals(Point p)
        {
            return x == p.x && y == p.y;
        }
    }

//    private static void next(int direction, int i, int j)
//    {
//        boolean north = i - 1 >= 0 && board[i - 1][j] != '*';
//        boolean east = j + 1 < 10 && board[i][j + 1] != '*';
//        boolean south = i + 1 < 10 && board[i + 1][j] != '*';
//        boolean west = j - 1 >= 0 && board[i][j - 1] != '*';
//
//        int w = 0;
//        if (direction == 0)
//        {
//            w = north ? 0 : 1 + (east ? 0 : 1 + (south ? 0 : 1 + (west ? 0 : 1)));
//        }
//        else if (direction == 1)
//            w = east ? 0 : 1 + (south ? 0 : 1 + (west ? 0 : 1 + (north ? 0 : 1)));
//        else if (direction == 2)
//            w = south ? 0 : 1 + (west ? 0 : 1 + (north ? 0 : 1 + (east ? 0 : 1)));
//        else
//            w = west ? 0 : 1 + (north ? 0 : 1 + (east ? 0 : 1 + (south ? 0 : 1)));
//
//        direction = (direction + w) % 4;
//        weight[i][j] = w;
//        if (direction == 0)
//            path[i][j] = 100 * direction + 10 * (i-1) + j;
//        else if (direction == 1)
//            path[i][j] = 100 * direction + 10 * i + (j+1);
//        else if (direction == 2)
//            path[i][j] = 100 * direction + 10 * (i+1) + j;
//        else
//            path[i][j] = 100 * direction + 10 * i + (j-1);
//    }
}