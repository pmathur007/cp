import java.io.*;
import java.util.StringTokenizer;

public class shuffleS
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(in.readLine());
        int[] shuffle = new int[n];
        int[] numIn = new int[n];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            shuffle[i] = Integer.parseInt(st.nextToken());
            numIn[shuffle[i] - 1] = numIn[shuffle[i] - 1] + 1;
        }

        boolean[] zerosProcessed = new boolean[n];
        int unprocessedZeros = 0;
        for (int i = 0; i < n; i++)
        {
            if (numIn[i] == 0)
                unprocessedZeros++;
        }

        while (unprocessedZeros > 0)
        {
            for (int i = 0; i < n; i++)
            {
                if (numIn[i] == 0 && !zerosProcessed[i])
                {
                    zerosProcessed[i] = true;
                    unprocessedZeros--;
                    numIn[shuffle[i] - 1] = numIn[shuffle[i] - 1] - 1;
                    if (numIn[shuffle[i] - 1] == 0)
                        unprocessedZeros++;
                }
            }
        }

        int numStable = 0;
        for (int i = 0; i < n; i++)
        {
            if (numIn[i] > 0)
                numStable++;
        }

        out.println(numStable);

        out.close();
    }
}
