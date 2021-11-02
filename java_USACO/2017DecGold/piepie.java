import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class  piepie
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("piepie.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));

        int n = in.nextInt();
        int d = in.nextInt();

        long[] dist = new long[2 * n];

        Pie[] bps = new Pie[n];
        Pie[] eps = new Pie[n];
        Queue<Pie> q = new LinkedList<>();
        boolean[] visited = new boolean[2 * n];

        for (int i = 0; i < 2*n; i++)
        {
            int b = in.nextInt();
            int e = in.nextInt();
            if (i < n)
            {
                bps[i] = new Pie(i, b, e, true);
                if (e == 0)
                {
                    dist[i] = 1;
                    q.add(bps[i]);
                    visited[i] = true;
                }
                else
                    dist[i] = -1;
            }
            else
            {
                eps[i - n] = new Pie(i, b, e, false);
                if (b == 0)
                {
                    dist[i] = 1;
                    q.add(eps[i - n]);
                    visited[i] = true;
                }
                else
                    dist[i] = -1;
            }
        }

        Arrays.sort(bps);
        Arrays.sort(eps);

        while (!q.isEmpty())
        {
            Pie p = q.remove();
            visited[p.i] = true;
            if (p.i < n)
            {
                int index = binSearch(eps, p.b - d);
                while (index < n && eps[index].b >= p.b - d && eps[index].b <= p.b)
                {
                    dist[eps[index].i] = dist[p.i] + 1;
                    if (!visited[eps[index].i])
                        q.add(eps[index]);
                    index++;
                }
            }
            else
            {
                int index = binSearch(bps, p.e - d);
                while (index < n && bps[index].e >= p.e - d && bps[index].e <= p.e)
                {
                    dist[bps[index].i] = dist[p.i] + 1;
                    if (!visited[bps[index].i])
                        q.add(bps[index]);
                    index++;
                }
            }
        }

        for (int i = 0; i < n; i++)
            out.println(dist[i]);

        out.close();
    }

    private static int binSearch(Pie[] arr, int lb)
    {
        int l = 0, r = arr.length - 1, mid;

        while (l < r)
        {
            mid = (l + r) / 2;
            if (arr[mid].getReceiver() <= lb)
                l = mid + 1;
            else
                r = mid - 1;
        }

        return l;
    }

    private static class Pie implements Comparable<Pie>
    {
        private int i, b, e;
        private boolean bessies;

        public Pie(int i, int b, int e, boolean bessies)
        {
            this.i = i;
            this.b = b;
            this.e = e;
            this.bessies = bessies;
        }

        public int getOwner()
        {
            return bessies ? b : e;
        }

        public int getReceiver()
        {
            return bessies ? e : b;
        }

        @Override
        public int compareTo(Pie o)
        {
            return bessies ? this.e - o.e : this.b - o.b;
        }
    }
}