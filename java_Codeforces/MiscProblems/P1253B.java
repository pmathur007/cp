import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class P1253B
{
    private static final int OFFSET = 1000000;
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        boolean[] entered = new boolean[2000001];
        Set<Integer> inOffice = new HashSet<>();

        int start = 0;
        LinkedList<Integer> days = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(in.readLine());

        for (int i = 0; i < n; i++)
        {
            int e = Integer.parseInt(st.nextToken());
            if (e > 0)
            {
                if (entered[e + OFFSET])
                {
                    if (inOffice.size() != 0)
                    {
                        System.out.println(-1);
                        return;
                    }
                    else
                    {
                        days.add(i - start);
                        start = i;
                        entered = new boolean[2000001];
                        inOffice = new HashSet<>();
                        entered[e + OFFSET] = true;
                        inOffice.add(e);
                    }
                }
                else
                {
                    entered[e + OFFSET] = true;
                    inOffice.add(e);
                }
            }
            else
            {
                e = -e;
                if (!inOffice.contains(e))
                {
                    System.out.println(-1);
                    return;
                }
                else
                    inOffice.remove(e);
            }
        }
        if (!inOffice.isEmpty())
            System.out.println(-1);
        else
        {
            days.add(n - start);
            System.out.println(days.size());
            int i = 0;
            for (int day : days)
            {
                if (i != days.size() - 1)
                    System.out.print(day + " ");
                else
                    System.out.println(day);
                i++;
            }
        }
    }
}
