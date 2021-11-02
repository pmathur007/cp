import java.io.*;
import java.util.PriorityQueue;
import java.util.Stack;

public class art2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("art2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));

        int n = Integer.parseInt(in.readLine());

        PriorityQueue<State> pq = new PriorityQueue<>();
        int[] first = new int[n + 1];
        int[] last = new int[n + 1];

        for (int i = 1; i <= n; i++)
        {
            int x = Integer.parseInt(in.readLine());
            if (x == 0)
                pq.add(new State(i, x, true));
            if (first[x] == 0)
                first[x] = i;
            last[x] = i;
        }

        for (int i = 1; i <= n; i++)
        {
            if (first[i] != 0)
            {
                pq.add(new State(first[i], i, true));
                pq.add(new State(last[i], i, false));
            }
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] closed = new boolean[n + 1];
        int max = 0;
        boolean invalid = false;

        while (!pq.isEmpty())
        {
            State s = pq.remove();
            if (s.n == 0)
            {
                while (!stack.empty())
                    closed[stack.pop()] = true;
            }
            else if (s.start)
            {
                if (closed[s.n])
                {
                    out.println(-1);
                    invalid = true;
                    break;
                }
                stack.add(s.n);
            }
            else
            {
                if (closed[s.n])
                {
                    out.println(-1);
                    invalid = true;
                    break;
                }
                while (stack.peek() != s.n)
                    closed[stack.pop()] = true;
                closed[stack.pop()] = true;
            }

            max = Math.max(max, stack.size());
        }

        if (!invalid)
            out.println(max);

        out.close();
    }

    private static class State implements Comparable<State>
    {
        private int i, n;
        private boolean start;

        public State(int i, int n, boolean start)
        {
            this.i = i;
            this.n = n;
            this.start = start;
        }

        @Override
        public int compareTo(State state)
        {
            if (this.i == state.i)
                return this.start ? -1 : 1;
            return this.i - state.i;
        }
    }
}