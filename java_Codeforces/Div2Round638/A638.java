import java.io.*;

public class A638
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(in.readLine());
            long p1 = 0;
            long p2 = 0;
            for (int i = 1; i <= N; i++)
            {
                if (i < N / 2 || i == N)
                    p1 += Math.pow(2, i);
                else
                    p2 += Math.pow(2, i);
            }
            System.out.println(p1 - p2);
        }
    }
}