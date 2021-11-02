import java.io.*;

public class circlecross
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("circlecross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("circlecross.out")));

        String road = in.readLine();
        int[] starts = new int[26];
        int[] ends = new int[26];
        for (int i = 0; i < 26; i++)
        {
            starts[i] = -1;
            ends[i] = -1;
        }

        for (int i = 0; i < 52; i++)
        {
            if (starts[road.charAt(i) - 65] == -1)
                starts[road.charAt(i) - 65] = i;
            else
                ends[road.charAt(i) - 65] = i;
        }

        int numCrossingPairs = 0;
        for (int i = 0; i < 25; i++)
        {
            for (int j = i + 1; j < 26; j++)
            {
                numCrossingPairs += crossingPair(starts[i], ends[i], starts[j], ends[j]);
            }
        }

        out.println(numCrossingPairs);
        out.close();
    }

    private static int crossingPair(int aStart, int aEnd, int bStart, int bEnd)
    {
        if (aStart < bStart && aEnd > bStart && bEnd > aEnd)
            return 1;
        else if (bStart < aStart && bEnd > aStart && aEnd > bEnd)
            return 1;
        return 0;
    }
}
