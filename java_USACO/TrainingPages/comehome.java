/*
ID: pranavm7
LANG: JAVA
TASK: comehome
PROG: comehome
*/

import java.io.*;

public class comehome
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));

        int n = Integer.parseInt(in.readLine());
        int[][] graph = new int[52][52];

        for (int i = 0; i < n; i++)
        {
            String line = in.readLine();
            int a = line.charAt(0) <= 90 ? line.charAt(0) - 65 : line.charAt(0) - 97 + 26;
            int b = line.charAt(2) <= 90 ? line.charAt(2) - 65 : line.charAt(2) - 97 + 26;
            if (a != b)
            {
                graph[a][b] = graph[a][b] == 0 ? Integer.parseInt(line.substring(4)) : Math.min(graph[a][b], Integer.parseInt(line.substring(4)));
                graph[b][a] = graph[a][b];
            }
        }

        int[] dist = new int[52];
        boolean[] visited = new boolean[52];
        for (int i = 0; i < 52; i++)
            dist[i] = Integer.MAX_VALUE;

        dist[25] = 0;
        int numVisited = 0;
        while (numVisited < 52)
        {
            int cur = 0;
            for (int i = 0; i < 52; i++)
                if (!visited[i] && dist[i] != Integer.MAX_VALUE && dist[i] < dist[cur])
                    cur = i;

            visited[cur] = true;
            numVisited++;

            for (int i = 0; i < 52; i++)
                if (graph[cur][i] != 0 && dist[cur] + graph[cur][i] < dist[i])
                    dist[i] = dist[cur] + graph[cur][i];
        }

        char minCow = 'A';
        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < 25; i++)
        {
            if (dist[i] < minPath)
            {
                minCow = (char) (i + 65);
                minPath = dist[i];
            }
        }

        out.println(minCow + " " + minPath);
        out.close();
    }
}