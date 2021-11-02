/*
ID: pranavm7
LANG: JAVA
TASK: barn1
PROG: barn1
*/

import java.io.*;
import java.util.Arrays;

public class barn1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

        String[] meta = in.readLine().split(" ");
        int m = Integer.parseInt(meta[0]);
        int s = Integer.parseInt(meta[1]);
        int c = Integer.parseInt(meta[2]);

        int[] cows = new int[c];
        for (int i = 0; i < c; i++)
        {
            cows[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(cows);

        Interval[] gaps = new Interval[c - 1];
        for (int i = 0; i < c - 1; i++)
        {
            gaps[i] = new Interval(cows[i], cows[i + 1]);
        }

        Arrays.sort(gaps, (i1, i2) -> i2.getGapSize() - i1.getGapSize());
        for (Interval i : gaps)
            System.out.println(i.getGapSize());

        if (m >= c)
        {
            out.println(c);
        }
        else
        {
            int blocked = cows[c - 1] - cows[0] + 1;
            for (int i = 0; i < m - 1; i++)
            {
                blocked -= gaps[i].getGapSize();
            }
            out.println(blocked);
        }

        out.close();
    }
}

class Interval
{
    private int start;
    private int end;

    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public int getGapSize()
    {
        return end - start  - 1;
    }

    public int getBoardSize()
    {
        return end - start + 1;
    }
}
