import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class P17B
{
    private static int[] qual;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        qual = new int[n];
        int maxQual = 0;
        for (int i = 0; i < n; i++)
        {
            qual[i] = in.nextInt();
            if (qual[i] > qual[maxQual])
                maxQual = i;
        }

        int m = in.nextInt();
        LinkedList<Edge> edges = new LinkedList<>();
        for (int i = 0; i < m; i++)
        {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();

            edges.add(new Edge(a, b, c));
        }
        Collections.sort(edges);

        int[] minVals = new int[n];
        for (int i = 0; i < n; i++)
            minVals[i] = i == maxQual ? 0 : -1;

        for (Edge e : edges)
        {
            if (minVals[e.d] == -1 || e.c < minVals[e.d])
                minVals[e.d] = e.c;
        }

        boolean exists = true;
        int sum = 0;
        for (int i = 0; i < n && exists; i++)
        {
            if (minVals[i] == -1)
            {
                sum = -1;
                exists = false;
            }
            else
                sum += minVals[i];
        }

        System.out.println(sum);
    }

    private static class Edge implements Comparable<Edge>
    {
        int s, d, c;

        public Edge(int s, int d, int c)
        {
            this.s = s;
            this.d = d;
            this.c = c;
        }

        @Override
        public int compareTo(Edge e)
        {
            if (qual[this.d] == qual[e.d])
                return this.c - e.c;
            return qual[e.d] - qual[this.d];

        }
    }
}
