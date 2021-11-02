/*
ID: pranavm7
LANG: JAVA
TASK: humble
PROG: humble
*/

import java.io.*;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class humble
{
    private static int[] primes, humble, next;
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("humble.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

        int k = in.nextInt();
        int n = in.nextInt();

        primes = new int[k];
        for (int i = 0; i < k; i++)
            primes[i] = in.nextInt();

        humble = new int[n + 1];
        next = new int[k];
        HashSet<Integer> humbleSet = new HashSet<>();
        PriorityQueue<Prime> pq = new PriorityQueue<>();
        humble[0] = 1;

        for (int i = 0; i < k; i++)
            pq.add(new Prime(primes[i], 0));

        int i = 1;
        while (i <= n)
        {
            Prime p = pq.remove();
            if (!humbleSet.contains(p.p * humble[p.m]))
            {
                humble[i] = p.p * humble[p.m];
                humbleSet.add(humble[i]);
                i++;
            }
            while (humbleSet.contains(p.p * humble[p.m]))
                p.m = p.m + 1;
            pq.add(p);
        }

        out.println(humble[n]);
        out.close();
    }

    private static class Prime implements Comparable<Prime>
    {
        private int p, m;

        public Prime(int p, int m)
        {
            this.p = p;
            this.m = m;
        }

        @Override
        public int compareTo(Prime o)
        {
            return p * humble[m] - o.p * humble[o.m];
        }
    }
}