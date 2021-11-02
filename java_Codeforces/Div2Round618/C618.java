import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class C618
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        LinkedList<Integer>[] bSets = new LinkedList[32];
        for (int i = 0;  i < 32; i++)
            bSets[i] = new LinkedList<>();

        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
        {
            a[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 32; j++)
                if ((1 << j & a[i]) != 0)
                    bSets[j].add(i);
        }

        int first = -1;
        for (LinkedList<Integer> bSet : bSets)
        {
            if (bSet.size() == 1)
                first = bSet.getFirst();
        }

        StringBuilder ans = new StringBuilder();
        if (first != -1)
        {
            ans.append(a[first]);
            ans.append(" ");
        }
        for (int i = 0; i < n; i++)
        {
            if (i != first)
            {
                ans.append(a[i]);
                ans.append(" ");
            }
        }

        System.out.println(ans.toString().trim());
    }
}