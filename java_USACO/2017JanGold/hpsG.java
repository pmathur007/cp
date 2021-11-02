import java.io.*;

public class hpsG
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);

        int[] moves = new int[n];
        for (int i = 0; i < n; i++)
        {
            char move = in.readLine().charAt(0);
            moves[i] = move == 'H' ? 0 : (move == 'P' ? 1 : 2);
        }

        int[][][] dp = new int[n + 1][k + 1][3];
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= k; j++)
            {
                for (int state = 0; state < 3; state++)
                {
                    if (i == 0)
                        dp[i][j][state] = 0;
                    else
                    {
                        if (j == 0)
                            dp[i][j][state] = dp[i - 1][j][state] + (moves[i - 1] == state ? 1 : 0);
                        else
                            dp[i][j][state] = Math.max(Math.max(dp[i - 1][j][state], dp[i - 1][j - 1][(state + 1) % 3]), dp[i - 1][j - 1][(state + 2) % 3]) + (moves[i - 1] == state ? 1 : 0);
                    }
                }
            }
        }

        out.println(Math.max(Math.max(dp[n][k][0], dp[n][k][1]), dp[n][k][2]));
        out.close();
    }
}
