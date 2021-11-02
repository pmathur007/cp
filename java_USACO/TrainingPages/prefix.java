/*
ID: pranavm7
LANG: JAVA
TASK: prefix
PROG: prefix
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class prefix
{
    private static int n = 0;
    private static String[] prefixes;
    private static String word;
    private static int[] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

        String line;
        StringTokenizer st;
        prefixes = new String[200];
        while (!(line = in.readLine()).equals("."))
        {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens())
                prefixes[n++] = st.nextToken();
        }
        Arrays.sort(prefixes, 0, n);

        StringBuilder s = new StringBuilder();
        while ((line = in.readLine()) != null)
            s.append(line);
        word = s.toString();

        dp = new int[word.length() + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i] = -1;

        dp[0] = 0;
        int max = 0;
        for (int i = 1; i <= word.length(); i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (prefixes[j].length() <= i)
                {
                    if (word.substring(i - prefixes[j].length(), i).equals(prefixes[j]) && dp[i - prefixes[j].length()] != -1)
                        dp[i] = Math.max(dp[i], prefixes[j].length() + dp[i - prefixes[j].length()]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        out.println(max);
        out.close();
    }

    private static int recur(int s)
    {
        if (s >= word.length())
            return 0;
        if (dp[s] >= 0)
            return dp[s];

        int max = 0;
        for (int i = 0; i < n; i++)
        {
            if (s + prefixes[i].length() <= word.length() && word.substring(s, s + prefixes[i].length()).equals(prefixes[i]))
                max = Math.max(max, prefixes[i].length() + recur(s + prefixes[i].length()));
        }
        dp[s] = max;
        return dp[s];
    }
}