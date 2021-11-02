import java.io.*;
import java.util.StringTokenizer;

public class A610
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        StringTokenizer st;
        for (int i = 0; i < t; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int start = Math.min(a, b);
            int end = Math.max(a, b);
            int lc = c - r;
            int rc = c + r;
            if ((lc <= start && rc <= start) || (lc >= end && rc >= end))
                System.out.println(end - start);
            else if (lc >= start && rc <= end)
                System.out.println(end - start - (rc - lc));
            else if (lc <= start && rc >= end)
                System.out.println(0);
            else if (lc >= start)
                System.out.println(lc - start);
            else
                System.out.println(end - rc);
        }

    }
}