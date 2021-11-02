/*
ID: pranavm7
LANG: JAVA
TASK: skidesign
PROG: skidesign
*/

import java.io.*;

public class skidesign
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        int n = Integer.parseInt(in.readLine());
        int[] hills = new int[n];

        for (int i = 0; i < n; i++) // read hills into array
        {
            hills[i] = Integer.parseInt(in.readLine());
        }

        int[] changedHills = new int[n];
        int cost = 1000000000;

        for (int i = 0; i <= 83; i++)
        {
            int min = i;
            int max = i + 17;

            for (int j = 0; j < n; j++)
            {
                if (hills[j] < min)
                    changedHills[j] = min;
                else if (hills[j] > max)
                    changedHills[j] = max;
                else
                    changedHills[j] = hills[j];
            }

            cost = Math.min(cost, computeCost(hills, changedHills));
        }

        out.println(cost);
        out.close();
    }

    private static int computeCost(int[] hills, int[] changedHills)
    {
        int cost = 0;
        for (int i = 0; i < hills.length; i++)
            cost += (hills[i] - changedHills[i]) * (hills[i] - changedHills[i]);
        return cost;
    }
}
