import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class cownav
{
    private static int n;
    private static char[][] board;
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownav.out")));

        n = Integer.parseInt(in.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++)
            board[i] = in.readLine().toCharArray();

        int[][][][][][] dp = new int[n][n][4][n][n][4];
        Queue<State> q = new LinkedList<>();
        q.add(new State(n - 1, 0, 0, n - 1, 0, 1));
        while (!q.isEmpty())
        {
            State s = q.remove();
            if (s.r1 == 0 && s.c1 == n - 1 && s.r2 == 0 && s.c2 == n - 1)
            {
                out.println(dp[s.r1][s.c1][s.d1][s.r2][s.c2][s.d2]);
                break;
            }

            for (State c : s.getChildren())
            {
                if (dp[c.r1][c.c1][c.d1][c.r2][c.c2][c.d2] == 0)
                {
                    dp[c.r1][c.c1][c.d1][c.r2][c.c2][c.d2] = dp[s.r1][s.c1][s.d1][s.r2][s.c2][s.d2] + 1;
                    q.add(c);
                }
            }
        }

        out.close();
    }

    private static class State
    {
        private int r1, c1, d1, r2, c2, d2;

        public State(int r1, int c1, int d1, int r2, int c2, int d2)
        {
            this.r1 = r1;
            this.c1 = c1;
            this.d1 = d1;
            this.r2 = r2;
            this.c2 = c2;
            this.d2 = d2;
        }

        private State[] getChildren()
        {
            return new State[] {turnLeft(), turnRight(), forward()};
        }

        private State turnLeft()
        {
            return new State(r1, c1, (d1 + 1) % 4, r2, c2, (d2 + 1) % 4);
        }

        private State turnRight()
        {
            return new State(r1, c1, (d1 + 3) % 4, r2, c2, (d2 + 3) % 4);
        }

        private State forward()
        {
            int nr1 = r1, nc1 = c1;
            if (nr1 != 0 || nc1 != n - 1)
            {
                if (nr1 + dy[d1] >= 0 && nr1 + dy[d1] < n)
                    nr1 += dy[d1];
                if (nc1 + dx[d1] >= 0 && nc1 + dx[d1] < n)
                    nc1 += dx[d1];
                if (board[nr1][nc1] == 'H')
                {
                    nr1 = r1;
                    nc1 = c1;
                }
            }

            int nr2 = r2, nc2 = c2;
            if (nr2 != 0 || nc2 != n - 1)
            {
                if (nr2 + dy[d2] >= 0 && nr2 + dy[d2] < n)
                    nr2 += dy[d2];
                if (nc2 + dx[d2] >= 0 && nc2 + dx[d2] < n)
                    nc2 += dx[d2];
                if (board[nr2][nc2] == 'H')
                {
                    nr2 = r2;
                    nc2 = c2;
                }
            }

            return new State(nr1, nc1, d1, nr2, nc2, d2);
        }
    }
}
