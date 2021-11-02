import java.io.*;

public class lifeguards
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("lifeguardsS.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lifeguardsS.out")));

        int n = Integer.parseInt(in.readLine());
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++)
        {
            String[] input = in.readLine().split(" ");
            startTimes[i] = Integer.parseInt(input[0]);
            endTimes[i] = Integer.parseInt(input[1]);
        }

        int maxCovered = 0;

        for (int i = 0; i < n; i++)
        {
            int numCovered = 0;
            boolean[] covered = new boolean[1000];
            for (int j = 0; j < n; j++)
            {
                if (j == i)
                    continue;

                for (int k = startTimes[j]; k < endTimes[j]; k++)
                {
                    if (!covered[k])
                    {
                        covered[k] = true;
                        numCovered++;
                    }
                }
            }
            maxCovered = Math.max(maxCovered, numCovered);
        }

        out.println(maxCovered);
        out.close();
    }
}
