/*
ID: pranavm7
LANG: JAVA
TASK: ariprog
PROG: ariprog
*/

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class ariprog
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        int n = Integer.parseInt(in.readLine());
        int m = Integer.parseInt(in.readLine());

        int numBS = 0;
        boolean[] bisquares = new boolean[m * m + m * m + 1];

        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                if (!bisquares[i * i + j * j])
                {
                    numBS++;
                }
                bisquares[i * i + j * j] = true;
            }
        }

        int[] orderedBS = new int[numBS];
        int index = 0;
        for (int i = 0; i < bisquares.length; i++)
        {
            if (bisquares[i])
            {
                orderedBS[index] = i;
                index++;
            }
        }

        LinkedList<Sequence> sequences = new LinkedList<>();

        for (int i = 0; i < numBS; i++)
        {
            int maxDif = (orderedBS[numBS - 1] - orderedBS[i]) / (n - 1);
            for (int j = i; j < numBS && orderedBS[j] - orderedBS[i] <= maxDif; j++)
            {
                int dif = orderedBS[j] - orderedBS[i];
                if (dif == 0)
                    continue;

                boolean valid = true;
                for (int k = 0; k < n; k++)
                {
                    if (!bisquares[orderedBS[i] + k * dif])
                    {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    sequences.add(new Sequence(orderedBS[i], dif));
            }
        }

        Collections.sort(sequences);

        if (sequences.size() == 0)
            out.println("NONE");
        else
        {
            for (Sequence s : sequences)
                out.println(s);
        }

        out.close();
    }
}

class Sequence implements Comparable<Sequence>
{
    private int start;
    private int dif;

    public Sequence(int start, int dif)
    {
        this.start = start;
        this.dif = dif;
    }

    public int getStart()
    {
        return start;
    }

    public int getDif()
    {
        return dif;
    }

    @Override
    public int compareTo(Sequence o)
    {
        if (this.dif != o.dif)
            return this.dif - o.dif;
        return this.start - o.start;
    }

    @Override
    public String toString()
    {
        return this.start + " " + this.dif;
    }
}
