import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class helpcross
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        String[] meta = in.readLine().split(" ");
        int c = Integer.parseInt(meta[0]);
        int n = Integer.parseInt(meta[1]);

        LinkedList<Time> times = new LinkedList<>();
        for (int i = 0; i < c; i++)
        {
            times.add(new Time(Integer.parseInt(in.readLine()), -1));
        }

        String[] line;
        for (int i = 0; i < n; i++)
        {
            line = in.readLine().split(" " );
            times.add(new Time(Integer.parseInt(line[0]), i));
            times.add(new Time(Integer.parseInt(line[1]), i));
        }

        Collections.sort(times);

        Set<Integer> inStack = new HashSet<>();
        LinkedList<Integer> stack = new LinkedList<>();
        int numPaired = 0;

        for (Time time : times)
        {
            if (time.cowI < 0)
            {
                if (!stack.isEmpty())
                {
                    stack.removeLast();
                    numPaired++;
                }
            }
            else
            {
                if (inStack.contains(time.cowI))
                    stack.remove((Integer) (time.cowI));
                else
                {
                    stack.add(time.cowI);
                    inStack.add(time.cowI);
                }
            }
        }

        out.println(numPaired);

        out.close();
    }

    private static class Time implements Comparable<Time>
    {
        private int t, cowI;

        public Time(int t, int cowI)
        {
            this.t = t;
            this.cowI = cowI;
        }

        @Override
        public int compareTo(Time o)
        {
            if (this.t == o.t)
                return o.cowI - this.cowI;
            return this.t - o.t;
        }
    }
}