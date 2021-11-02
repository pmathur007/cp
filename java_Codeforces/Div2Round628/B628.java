import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B628
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++)
        {
            int n = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());
            HashSet<Integer> set = new HashSet<>();

            for (int j = 0; j < n; j++)
                set.add(Integer.parseInt(st.nextToken()));

            System.out.println(set.size());
        }
    }
}