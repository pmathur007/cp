import java.io.*;

public class div7
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("div7.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));

        int n = Integer.parseInt(in.readLine());
        long[] prefix = new long[n];
        for (int i = 0; i < n; i++)
            prefix[i] = Long.parseLong(in.readLine()) + (i > 0 ? prefix[i - 1] : 0);

        boolean flag = false;
        int max = 0;
        for (int i = n; i >= 0 && !flag; i--)
        {
            for (int j = n - 1; j - i >= -1 && !flag; j--)
            {
                long sum = prefix[j] - (j - i >= 0 ? prefix[j - i] : 0);
                if (sum % 7 == 0)
                {
                    max = i;
                    flag = true;
                }
            }
        }
        out.println(max);
        out.close();
    }
}