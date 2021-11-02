import java.io.*;

public class speeding
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("speeding.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);

        int[] speedLimits = new int[100];
        int[] bessieSpeeds = new int[100];

        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            int d = Integer.parseInt(line[0]);
            int s = Integer.parseInt(line[1]);

            int start = counter;
            for (int j = start; j < start + d; j++)
            {
                speedLimits[counter] = s;
                counter++;
            }
        }

        counter = 0;
        for (int i = 0; i < m; i++)
        {
            String[] line = in.readLine().split(" ");
            int d = Integer.parseInt(line[0]);
            int s = Integer.parseInt(line[1]);

            int start = counter;
            for (int j = start; j < start + d; j++)
            {
                bessieSpeeds[counter] = s;
                counter++;
            }
        }

        int maxDif = 0;
        for (int i = 0; i < 100; i++)
        {
            maxDif = Math.max(maxDif, bessieSpeeds[i] - speedLimits[i]);
        }

        out.println(maxDif);

        out.close();
    }
}