import java.util.Scanner;
import java.util.StringTokenizer;

public class B532
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        String[] meta = in.nextLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);

        StringTokenizer st = new StringTokenizer(in.nextLine(), " ");
        StringBuilder output = new StringBuilder();

        int[] freq = new int[n];
        int numDist = 0;

        for (int i = 0; i < m; i++)
        {
            int prob = Integer.parseInt(st.nextToken()) - 1;
            if (freq[prob] == 0)
                numDist++;
            freq[prob] = freq[prob] + 1;

            if (numDist == n)
            {
                output.append("1");
                for (int j = 0; j < n; j++)
                {
                    freq[j] = freq[j] - 1;
                    if (freq[j] == 0)
                        numDist--;
                }
            }
            else
            {
                output.append("0");
            }
        }

        System.out.println(output.toString());
    }
}