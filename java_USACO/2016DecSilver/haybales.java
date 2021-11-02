import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class haybales
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int q = Integer.parseInt(meta[1]);

        int[] haybales = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        for (int i = 0; i < n; i++)
        {
            haybales[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(haybales);

        for (int i = 0; i < q; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            out.println(indexOfNearest(haybales, b, false) - indexOfNearest(haybales, a, true) + 1);
        }

        out.close();
    }

    private static int indexOfNearest(int[] haybales, int n, boolean up)
    {
        int size = haybales.length;

        if (n <= haybales[0])
            return 0;
        if (n >= haybales[size - 1])
            return size - 1;

        int start = 0;
        int end = size;
        int mid = 0;
        while (start < end)
        {
            mid = (start + end) / 2;
            if (haybales[mid] == n)
                return mid;

            if (n < haybales[mid])
            {
                if (mid > 0 && n > haybales[mid - 1])
                    return up ? mid : mid - 1;
                end = mid;
            }

            else
            {
                if (mid < size - 1 && n < haybales[mid + 1])
                    return up ? mid + 1 : mid;
                start = mid + 1;
            }
        }

        return mid;
    }
}