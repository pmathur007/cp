import java.io.*;
import java.util.StringTokenizer;

public class C620
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(in.readLine());

        for (int x = 0; x < q; x++)
        {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long lp = m, hp = m, tp = 0;
            boolean works = true;

            for (int i = 0; i < n; i++)
            {
                st = new StringTokenizer(in.readLine());
                if (!works)
                    continue;

                long t = Integer.parseInt(st.nextToken());
                long l = Integer.parseInt(st.nextToken());
                long h = Integer.parseInt(st.nextToken());

                lp = lp - (t - tp);
                hp = hp + (t - tp);

                if (hp < l || h < lp)
                {
                    System.out.println("NO");
                    works = false;
                }
                else
                {
                    lp = Math.max(lp, l);
                    hp = Math.min(hp, h);
                    tp = t;
                }
            }

            if (works)
                System.out.println("YES");
        }
    }
}