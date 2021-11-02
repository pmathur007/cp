import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B618
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int x = 0; x < t; x++)
        {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[2 * n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < 2 * n; i++)
                a[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(a);
            System.out.println(Math.abs(a[n - 1] - a[n]));
        }
    }
}