import java.io.*;
import java.util.StringTokenizer;

public class A618
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int x = 0; x < t; x++)
        {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            int ans = 0;
            for (int i = 0; i < n; i++)
            {
                a[i] = Integer.parseInt(st.nextToken());
                if (a[i] == 0)
                {
                    a[i]++;
                    ans++;
                }
            }

            boolean allNegOne = true;
            int sum = 0;
            for (int i = 0; i < n; i++)
            {
                sum += a[i];
                if (a[i] != -1)
                    allNegOne = false;
            }

            if (sum == 0)
                ans += allNegOne ? 2 : 1;

            System.out.println(ans);
        }
    }
}