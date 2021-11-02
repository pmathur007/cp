import java.io.*;

public class hpsS
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        int n = Integer.parseInt(in.readLine());

        char[] sequence = new char[n];
        int lMax = 0;
        int rMax = 0;
        int[] lFreq = new int[3];
        int[] rFreq = new int[3];
        for (int i = 0; i < n; i++)
        {
            char b = in.readLine().charAt(0);
            sequence[i] = b;
            int index = index(b);
            rFreq[index]++;
            if (rFreq[index] > rMax)
                rMax = rFreq[index];
        }

        int maxWon = rMax;
        for (int i = 0; i < n; i++)
        {
            int index = index(sequence[i]);

            lFreq[index]++;
            rFreq[index]--;

            lMax = max(lFreq);
            rMax = max(rFreq);

            if (rMax + lMax > maxWon)
                maxWon = rMax + lMax;
        }

        out.println(maxWon);

        out.close();
    }

    private static int max(int[] arr)
    {
        int max = 0;
        for (int i : arr)
            if (i > max)
                max = i;
        return max;
    }

    private static int index(char c)
    {
        if (c == 'H')
            return 0;
        if (c == 'P')
            return 1;
        return 2;
    }
}