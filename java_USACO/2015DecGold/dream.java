import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dream
{
    private static int[] dr = {0, -1, 0, 1};
    private static int[] dc = {1, 0, -1, 0};
    private static int n, m;
    private static int[][] board;
    private static boolean[][][] visited;
    private static PriorityQueue<State> pq;

    public static void main(String[]args)throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("dream.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
             for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        pq = new PriorityQueue<>();
        pq.add(new State(0, 0, 0, 0));

        int ans = -1;
        while (!pq.isEmpty())
        {
            State s = pq.remove();

            if (s.r == n - 1 && s.c == m - 1)
            {
                ans = s.d;
                break;
            }

            if (visited[s.r][s.c][s.o]) continue;
            visited[s.r][s.c][s.o] = true;

            for (int i = 0; i < 4; i++)
            {
                int nr = s.r + dr[i], nc = s.c + dc[i], no = s.o, nd = s.d + 1;
                if (canTravel(nr, nc, no))
                {
                    if (board[nr][nc] == 4)
                    {
                        while (canTravel(nr + dr[i], nc + dc[i], no) && board[nr][nc] == 4)
                        {
                            nr += dr[i]; nc += dc[i]; nd++;
                            no = 0;
                        }
                    }
                    if (board[nr][nc] == 2)
                        no = 1;
                    if (!visited[nr][nc][no])
                        pq.add(new State(nr, nc, no, nd));
                }
            }
        }

        out.println(ans);

        out.close();
    }

    private static boolean canTravel(int r, int c, int o)
    {
        if (r < 0 || r >= n || c < 0 || c >= m) return false;
        if (board[r][c] == 0 || (board[r][c] == 3 && o == 0)) return false;
        return true;
    }

    private static class State implements Comparable<State>
    {
        private int r, c, o, d;

        public State(int r, int c, int o, int d)
        {
            this.r = r;
            this.c = c;
            this.d = d;
            this.o = o;
        }

        @Override
        public int compareTo(State state)
        {
            return this.d - state.d;
        }
    }
}