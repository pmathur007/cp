import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lifeguardsS
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));

        int n = Integer.parseInt(in.readLine());
        State[] states = new State[2 * n];

        StringTokenizer st;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            states[2 * i] = new State(Integer.parseInt(st.nextToken()), i);
            states[2 * i + 1] = new State(Integer.parseInt(st.nextToken()), i);
        }
        Arrays.sort(states, (s1, s2) -> s1.time - s2.time);

        TreeSet<Integer> working = new TreeSet<>();
        int totalTime = 0;
        int[] loss = new int[n];
        int last = 0;

        for (State s : states)
        {
            if (working.size() == 1)
                loss[working.first()] = s.time - last;
            if (!working.isEmpty())
                totalTime += s.time - last;
            if (working.contains(s.index))
                working.remove(s.index);
            else
                working.add(s.index);
            last = s.time;
        }

        int minLoss = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
        {
            if (loss[i] < minLoss)
                minLoss = loss[i];
        }

        out.println(totalTime - minLoss);
        out.close();
    }

    static class State
    {
        private int time, index;

        public State(int time, int index)
        {
            this.time = time;
            this.index = index;
        }
    }
}