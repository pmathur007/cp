/*
ID: pranavm7
LANG: JAVA
TASK: nocows
PROG: nocows
*/

import java.io.*;
import java.util.Scanner;

public class nocows
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("nocows.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

        int n = in.nextInt();
        int k = in.nextInt();

        if (n % 2 == 0)
        {
            out.println(0);
            out.close();
            return;
        }

        n = (n - 1) / 2;
        k--;

        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= k; i++)
        {
            for (int j = 1; j <= (i < 8 ? Math.min(Math.pow(2, i) - 1, n) : n); j++)
            {
                for (int x = 0; x < j; x++)
                {
                    dp[i][j] = (dp[i][j] + ((dp[i - 1][x] * dp[i - 1][j - x - 1]) % 9901)) % 9901;
                }
            }
        }

        if (dp[k][n] - dp[k - 1][n] < 0)
            out.println(dp[k][n] - dp[k - 1][n] + 9901);
        else
            out.println(dp[k][n] - dp[k - 1][n]);
        out.close();
    }
}

// BIG INTEGER - FAILED
//    private static int n, k;
//    private static final BigInteger MOD = new BigInteger("9901");
//    private static BigInteger[][] dpChoose;
//    private static BigInteger[][][] dpSearch;
//
//    public static void main(String[] args) throws IOException
//    {
//        Scanner in = new Scanner(new BufferedReader(new FileReader("nocows.in")));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
//
//        String[] line = in.nextLine().split(" ");
//        n = Integer.parseInt(line[0]);
//        k = Integer.parseInt(line[1]);
//
//        dpSearch = new BigInteger[200][n][k+1];
//        dpChoose = new BigInteger[200][200];
//
//        if (n % 2 == 0)
//            out.println(0);
//        else
//            out.println(search(2, n - 3, 2).mod(MOD).intValue());
//
//        out.close();
//    }
//
//    private static BigInteger search(int openings, int nodes, int level)
//    {
//        if (dpSearch[openings][nodes][level] != null)
//            return dpSearch[openings][nodes][level];
//
//        if (nodes <= 0 && level < k)
//            return BigInteger.ZERO;
//        if (level == k)
//            return nodes == 0 ? BigInteger.ONE : BigInteger.ZERO;
//        BigInteger sum = BigInteger.ZERO;
//        for (int i = 1; 2*i <= nodes; i++)
//            sum = sum.add(choose(openings, i).multiply(search(2*i, nodes - 2*i, level + 1)));
//        dpSearch[openings][nodes][level] = sum;
//        return sum;
//    }
//
//    private static BigInteger choose(int n, int k)
//    {
//        if (dpChoose[n][k] != null)
//            return dpChoose[n][k];
//
//        BigInteger ans = BigInteger.ONE;
//        for (int i = n; i > n - k; i--)
//            ans = ans.multiply(new BigInteger("" + i));
//        for (int i = 1; i <= k; i++)
//            ans = ans.divide(new BigInteger("" + i));
//        dpChoose[n][k] = ans;
//        return ans;
//    }