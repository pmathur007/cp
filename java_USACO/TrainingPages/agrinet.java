/*
ID: pranavm7
LANG: JAVA
TASK: agrinet
PROG: agrinet
*/

import java.io.*;
import java.util.Scanner;

public class agrinet
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner( new BufferedReader(new FileReader("agrinet.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

        int n = in.nextInt();
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                graph[i][j] = in.nextInt();
//                if (i == j)
//                    graph[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] dist = new int[n];
        int[] src = new int[n];
        boolean[] inTree = new boolean[n];

        int cost = 0;
        inTree[0] = true;
        for (int i = 0; i < n; i++)
        {
            dist[i] = graph[0][i];
            src[i] = 0;
        }

        for (int i = 0; i < n - 1; i++)
        {
            int minV = -1;
            for (int j = 0; j < n; j++)
            {
                if (!inTree[j] && (minV == -1 || dist[j] < dist[minV]))
                    minV = j;
            }

            cost += graph[minV][src[minV]];
            inTree[minV] = true;

            for (int j = 0; j < n; j++)
            {
                if (graph[minV][j] < dist[j])
                {
                    dist[j] = graph[minV][j];
                    src[j] = minV;
                }
            }
        }

        out.println(cost);
        out.close();
    }
}