/*
ID: pranavm7
LANG: JAVA
TASK: concom
PROG: concom
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class concom
{
    private static LinkedList<Edge>[] graph = new LinkedList[100];

    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("concom.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

        int n = in.nextInt();

        for (int i = 0; i < 100; i++)
            graph[i] = new LinkedList<>();

        for (int i = 0; i < n; i++)
        {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int w = in.nextInt();

            graph[a].add(new Edge(b, w));
        }

        Queue<Integer> q;
        int[] amtOwned;
        boolean[] visited;

        for (int i = 0; i < 100; i++)
        {
            q = new LinkedList<>();
            amtOwned = new int[100];
            visited = new boolean[100];

            q.add(i);
            amtOwned[i] = 100;

            while (!q.isEmpty())
            {
                int cur = q.remove();
                visited[cur] = true;
                for (Edge e : graph[cur])
                {
                    amtOwned[e.d] += e.w;
                    if (amtOwned[e.d] > 50 && !visited[e.d])
                        q.add(e.d);
                }
            }

            for (int j = 0; j < amtOwned.length; j++)
            {
                if (i != j && amtOwned[j] > 50)
                    out.println((i + 1) + " " + (j + 1));
            }
        }

        out.close();
    }

    private static class Edge
    {
        private int d, w;

        public Edge(int d, int w)
        {
            this.d = d;
            this.w = w;
        }
    }
}