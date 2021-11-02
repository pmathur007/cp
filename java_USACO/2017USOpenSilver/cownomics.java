import java.io.*;
import java.util.HashSet;

public class cownomics
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));

        String[] meta = in.readLine().split(" " );
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);

        String[] spotty = new String[n];
        String[] plain = new String[n];
        for (int i = 0; i < n; i++)
            spotty[i] = in.readLine();
        for (int i = 0; i < n; i++)
            plain[i] = in.readLine();

        int numExplain = (m * (m - 1) * (m - 2)) / 6;
        HashSet<String> genes;
        for (int i = 0; i < m - 2; i++)
        {
            for (int j = i + 1; j < m - 1; j++)
            {
                for (int k = j + 1; k < m; k++)
                {
                    genes = new HashSet<>();
                    for (int x = 0; x < n; x++)
                    {
                        genes.add("" + spotty[x].charAt(i) + spotty[x].charAt(j) + spotty[x].charAt(k));
                    }
                    for (int x = 0; x < n; x++)
                    {
                        if (genes.contains("" + plain[x].charAt(i) + plain[x].charAt(j) + plain[x].charAt(k)))
                        {
                            numExplain--;
                            break;
                        }
                    }
                }
            }
        }

        out.println(numExplain);
        out.close();
    }
}