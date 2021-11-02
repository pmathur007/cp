import java.io.*;
import java.util.Arrays;

public class angry
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("angry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);

        int[] haybales = new int[n];
        for (int i = 0; i < n; i++)
            haybales[i] = Integer.parseInt(in.readLine());
        Arrays.sort(haybales);

        int low = 0;
        int high = 500000000;
        int mid = 0;

        while (low < high)
        {
            mid = (low + high) / 2;
            if (canDetonate(haybales, k, mid))
                high = mid;
            else
                low = mid + 1;
        }

        out.println(high);

        out.close();
    }

    private static boolean canDetonate(int[] haybales, int k, int r)
    {
        int start = haybales[0];
        int index = 0;
        for (int i = 0; i < k; i++)
        {
            while (haybales[index] <= start + 2 * r)
            {
                index++;
                if (index >= haybales.length)
                    return true;
            }
            start = haybales[index];
        }
        return index >= haybales.length;
    }
}