import java.io.*;

public class blocks
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("blocks.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blocks.out")));

        int n = Integer.parseInt(in.readLine());

        int[] letterFreq = new int[26];
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            for (char c : line[0].toCharArray())
            {
                freq1[c - 97]++;
            }

            for (char c : line[1].toCharArray())
            {
                freq2[c - 97]++;
            }

            for (int j = 0; j < 26; j++)
            {
                letterFreq[j] += Math.max(freq1[j], freq2[j]);
            }

            freq1 = new int[26];
            freq2 = new int[26];
        }

        for (int i = 0; i < 26; i++)
        {
            out.println(letterFreq[i]);
        }

        out.close();
    }
}
