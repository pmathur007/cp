import java.io.*;

public class blist
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("blist.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));

        int n = Integer.parseInt(in.readLine());
        int[] times = new int[1000];

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            int buckets = Integer.parseInt(line[2]);

            for (int j = start; j < end; j++)
            {
                times[j] = times[j] + buckets;
            }
        }

        int maxBuckets = 0;
        for (int i = 0; i < 1000; i++)
        {
            if (times[i] > maxBuckets)
                maxBuckets = times[i];
        }

        out.println(maxBuckets);

        out.close();
    }
}