import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B638
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;

        for (int t = 0; t < T; t++)
        {
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            HashSet<Integer> distinct = new HashSet<>();
            ArrayList<Integer> a = new ArrayList<>();

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; i++)
            {
                int ai = Integer.parseInt(st.nextToken());
                a.add(ai);
                distinct.add(ai);
            }

            if (distinct.size() > K)
                System.out.println(-1);
            else
            {
                for (int v : distinct)
                    a.add(0, v);

                int[] sequence = new int[K];
                int i = 0;
                int ai = 0;
                while (ai < a.size())
                {
                    if (i < K)
                    {
                        sequence[i] = a.get(i);
                        i++; ai++;
                    }
                    else
                    {
                        if (a.get(ai) != sequence[i % K])
                            a.add(ai, sequence[i % K]);
                        i++; ai++;
                    }
                }
                int s = a.size();
                System.out.println(s);
                for (int idx = 0; idx < s; idx++)
                {
                    System.out.print(a.get(idx) + (idx == s - 1 ? "\n" : " "));
                }
            }
        }
    }
}