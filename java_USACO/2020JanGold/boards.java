import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boards
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("boards.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        Board[] xb = new Board[p];
        Board[] yb = new Board[p];

        for (int i = 0; i < p; i++)
        {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            xb[i] = yb[i] = new Board(i, x1, y1, x2, y2);
        }

        Arrays.sort(xb, (b1, b2) -> b1.x1 - b2.x1);
        Arrays.sort(yb, (b1, b2) -> b1.y1 - b2.y1);

        PriorityQueue<State> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[p];

        pq.add(new State(-1, 0, 0, 0));
        while (!pq.isEmpty())
        {
            State s = pq.remove();
            if (s.x == n && s.y == n)
            {
                out.println(s.d);
                out.close();
                return;
            }

            if (s.i != -1)
            {
                if (visited[s.i]) continue;
                visited[s.i] = true;
            }

            pq.add(new State(-2, n, n, s.d + n - s.x + n - s.y));

            int xBound = lowerBound(xb, s.x, true);
            int yBound = lowerBound(yb, s.y, false);
            if (xBound >= yBound)
            {
                for (int k = xBound; k < p; k++)
                {
//                    int idx = xb[k].i;
                    if (!visited[k] && xb[k].y1 >= s.y)
                        pq.add(new State(k, xb[k].x2, xb[k].y2, s.d + (xb[k].x1 - s.x) + (xb[k].y1 - s.y)));
                }
            }
            else
            {
                for (int k = yBound; k < p; k++)
                {
//                    int idx = yb[k].i;
                    if (!visited[yb[k].i] && yb[k].x1 >= s.x)
                        pq.add(new State(k, yb[k].x2, yb[k].y2, s.d + (yb[k].x1 - s.x) + (yb[k].y1 - s.y)));
                }
            }
        }

        out.println(-1);

        out.close();
    }

    private static int lowerBound(Board[] arr, int v, boolean x)
    {
        int l = 0, r = arr.length;
        while (l < r)
        {
            int mid = (l + r) / 2;
            if (v <= (x ? arr[mid].x1 : arr[mid].y1))
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private static class State implements Comparable<State>
    {
        int i, x, y, d;

        public State(int i, int x, int y, int d)
        {
            this.i = i;
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(State state)
        {
            return this.d - state.d;
        }
    }

    private static class Board
    {
        private int i, x1, y1, x2, y2;

        public Board(int i, int x1, int y1, int x2, int y2)
        {
            this.i = i;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}