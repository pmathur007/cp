import java.io.*;
import java.util.Arrays;

public class two48
{
    private static int N;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("248.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));

        N = Integer.parseInt(in.readLine());
        arr = new int[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            arr[i] = Integer.parseInt(in.readLine());
            Arrays.fill(dp[i], -1);
            dp[i][i] = arr[i];
        }

        for (int size = 1; size < N; size++)
        {
            for (int i = 0; i < N - size; i++)
            {
                int j = i + size;
                for (int k = i; k < j; k++)
                {
                    if (dp[i][k] != -1 && dp[k + 1][j] != -1 && dp[i][k] == dp[k + 1][j])
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                ans = Math.max(ans, dp[i][j]);

        out.println(ans);
        out.close();
    }
}

// SUBMISSION 1

//    private static int N;
//    private static HashMap<String, Integer> dp = new HashMap<>();
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("two48.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("two48.out")));
//
//        N = Integer.parseInt(in.readLine());
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < N; i++)
//            sb.append(Integer.parseInt(in.readLine()));
//
//        out.println(dfs(sb.toString()));
//
//        out.close();
//    }
//
//    private static int dfs(String state)
//    {
//        if (dp.containsKey(state))
//            return dp.get(state);
//        int max = 0;
//        for (int i = 0; i < state.length(); i++)
//            max = Math.max(max, Character.getNumericValue(state.charAt(i)));
//
//        for (int i = 0; i < state.length() - 1; i++)
//        {
//            if (state.charAt(i) == state.charAt(i + 1))
//                max = Math.max(max, dfs(state.substring(0, i) + (Character.getNumericValue(state.charAt(i)) + 1) + state.substring(i + 2)));
//        }
//
//        dp.put(state, max);
//        return max;
//    }