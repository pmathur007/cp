import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class dirtraverse
{
    private static int n;
    private static String[] names;
    private static LinkedList<Integer>[] graph;
    private static int[] numFiles;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("dirtraverse.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dirtraverse.out")));

        n = Integer.parseInt(in.readLine());
        names = new String[n];
        graph = new LinkedList[n];
        numFiles = new int[n];

        for (int i = 0; i < n; i++)
            graph[i] = new LinkedList<>();

        StringTokenizer st;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            names[i] = st.nextToken();
            int m = Integer.parseInt(st.nextToken());
            if (m > 0)
            {
                for (int j = 0; j < m; j++)
                    graph[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        long bestPathLength = preDFS(0);
        bestPathLength = getBestLength(0, bestPathLength);

        out.println(bestPathLength);

        out.close();
    }

    private static long getBestLength(int i, long len)
    {
        if (graph[i].size() == 0)
            return Long.MAX_VALUE;
        else
        {
            long bestLen = len;
            for (int c : graph[i])
            {
                long cLen = len + 3 * (numFiles[0] - numFiles[c]) - (names[c].length() + 1) * (numFiles[c]);
                bestLen = Math.min(bestLen, getBestLength(c, cLen));
            }
            return bestLen;
        }
    }

    private static long preDFS(int i)
    {
        if (graph[i].size() == 0)
        {
            numFiles[i] = 1;
            return names[i].length();
        }
        else
        {
            long totalSize = 0;
            for (int c : graph[i])
            {
                totalSize += (i == 0 ? 0 : names[i].length() + 1) + preDFS(c);
                numFiles[i] += numFiles[c];
            }
            return totalSize;
        }
    }
}