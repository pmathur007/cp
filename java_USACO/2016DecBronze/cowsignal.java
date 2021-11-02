import java.io.*;

public class cowsignal
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowsignal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowsignal.out")));

        String[] meta = in.readLine().split(" ");
        int m = Integer.parseInt(meta[0]);
        int n = Integer.parseInt(meta[1]);
        int k = Integer.parseInt(meta[2]);

        for (int i = 0; i < m; i++)
        {
            char[] line = in.readLine().toCharArray();
            for (int ki = 0; ki < k; ki++)
            {
                for (int j = 0; j < n; j++)
                {
                    for (int kj = 0; kj < k; kj++)
                    {
                        out.print(line[j]);
                    }
                }
                out.println();
            }
        }

        out.close();
    }
}
