/*
ID: pranavm7
LANG: JAVA
TASK: maze1
PROG: maze1
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class maze1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

        String[] meta = in.readLine().split(" ");
        int w = Integer.parseInt(meta[0]);
        int h = Integer.parseInt(meta[1]);

        char[][] board = new char[2*h+1][2*w+1];
        int e1x = 0, e1y = 0, e2x = 0, e2y = 0;

        for (int i = 0; i < board.length; i++)
        {
            String line = in.readLine();
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = j >= line.length() ? ' ' : line.charAt(j);
                if (isExit(board.length, board[0].length, i, j, board[i][j]))
                {
                    if (e1x == 0 && e1y == 0)
                    {
                        e1x = i;
                        e1y = j;
                        if (i == 0) e1x++;
                        else if (i == board.length - 1) e1x--;
                        else if (j == 0) e1y++;
                        else e1y--;
                    }
                    else
                    {
                        e2x = i;
                        e2y = j;
                        if (i == 0) e2x++;
                        else if (i == board.length - 1) e2x--;
                        else if (j == 0) e2y++;
                        else e2y--;
                    }
                }
            }
        }

        int[][] dist1 = new int[2*h+1][2*w+1];
        int[][] dist2 = new int[2*h+1][2*w+1];
        flood(board, dist1, e1x, e1y);
        flood(board, dist2, e2x, e2y);

        int max = 0;
        for (int i = 1; i < board.length; i += 2)
            for (int j = 1; j < board[0].length; j += 2)
                max = Math.max(max, Math.min(dist1[i][j], dist2[i][j]));

        out.println(max);
        out.close();
    }

    private static void flood(char[][] board, int[][] dist, int i, int j)
    {
        Queue<Loc> q = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        q.add(new Loc(i, j, 1));
        visited[i][j] = true;

        while (!q.isEmpty())
        {
            Loc l = q.remove();
            dist[l.i][l.j] = l.d;

            if (isWithin(board, l.i + 2, l.j) && board[l.i + 1][l.j] != '-' && !visited[l.i + 2][l.j])
            {
                q.add(new Loc(l.i + 2, l.j, l.d + 1));
                visited[l.i + 2][l.j] = true;
            }
            if (isWithin(board, l.i - 2, l.j) && board[l.i - 1][l.j] != '-' && !visited[l.i - 2][l.j])
            {
                q.add(new Loc(l.i - 2, l.j, l.d + 1));
                visited[l.i - 2][l.j] = true;
            }
            if (isWithin(board, l.i, l.j + 2) && board[l.i][l.j + 1] != '|' && !visited[l.i][l.j + 2])
            {
                q.add(new Loc(l.i, l.j + 2, l.d + 1));
                visited[l.i][l.j + 2] = true;
            }
            if (isWithin(board, l.i, l.j - 2) && board[l.i][l.j - 1] != '|' && !visited[l.i][l.j - 2])
            {
                q.add(new Loc(l.i, l.j - 2, l.d + 1));
                visited[l.i][l.j - 2] = true;
            }
        }

    }

    private static boolean isWithin(char[][] board, int i, int j)
    {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }

    private static boolean isExit(int h, int w, int i, int j, char c)
    {
        if ((i == 0 || i == h - 1) && j % 2 == 1 && c != '-' && c != '+')
            return true;
        return (j == 0 || j == w - 1) && i % 2 == 1 && c != '|' && c != '+';
    }

    private static class Loc
    {
        private int i, j, d;
        public Loc(int i, int j, int d)
        {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }
}