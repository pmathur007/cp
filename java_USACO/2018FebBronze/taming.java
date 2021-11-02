import java.io.*;
import java.util.StringTokenizer;

public class taming
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("taming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));

        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int[] logs = new int[n];

        for (int i = 0; i < n; i++)
        {
            logs[i] = Integer.parseInt(st.nextToken()) * (i == 0 ? 0 : 1);

            if (logs[i] > 0)
            {
                for (int j = 1; j <= logs[i]; j++)
                {
                    if (logs[i - j] != -1 && logs[i - j] != logs[i] - j)
                    {
                        out.println(-1);
                        out.close();
                        return;
                    }
                    logs[i - j] = logs[i] - j;
                }
            }
        }

        int numKnown = 0;
        int numUnknown = 0;

        for (int i = 0; i < n; i++)
        {
            if (logs[i] == 0)
                numKnown++;
            else if (logs[i] == -1)
                numUnknown++;
        }

        out.println(numKnown + " " + (numKnown + numUnknown));
        out.close();
    }
}
