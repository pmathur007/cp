import java.io.*;
import java.util.StringTokenizer;

public class A577
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[] num = new int[m];
        int[][] ans = new int[m][5];
        for (int i = 0; i < n; i++)
        {
            String test = in.readLine();
            for (int j = 0; j < m; j++)
                ans[j][test.charAt(j) - 65]++;
        }

        StringTokenizer st = new StringTokenizer(in.readLine());
        int maxTotal = 0;
        for (int i = 0; i < m; i++)
        {
            int score = Integer.parseInt(st.nextToken());
            int max = 0;
            for (int j = 0; j < 5; j++)
                max = Math.max(max, ans[i][j]);
            maxTotal += max * score;
        }

        System.out.println(maxTotal);
    }
}