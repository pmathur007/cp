/*
ID: pranavm7
LANG: JAVA
TASK: numtri
PROG: numtri
*/

import java.io.*;
import java.util.StringTokenizer;

public class numtri
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int n = Integer.parseInt(in.readLine());
        int[] triangle = new int[(n * (n + 1)) / 2];
        int counter = 0;
        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j <= i; j++)
            {
                triangle[counter] = Integer.parseInt(st.nextToken());
                counter++;
            }
        }

        int[] maxSums = new int[(n * (n + 1)) / 2];
        for (int i = 0; i < n; i++)
        {
            maxSums[maxSums.length - i - 1] = triangle[maxSums.length - i - 1];
        }

        for (int i = n - 2; i >= 0; i--)
        {
            int offset = (i * (i + 1)) / 2;
            for (int j = 0; j <= i; j++)
            {
                maxSums[offset + j] = triangle[offset + j] + Math.max(maxSums[offset + j + i + 1], maxSums[offset + j + i + 2]);
            }
        }

        out.println(maxSums[0]);

        out.close();
    }
}
