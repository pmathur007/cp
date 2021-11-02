import java.util.Scanner;
import java.util.StringTokenizer;

public class A532
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String[] meta = in.nextLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);

        StringTokenizer st = new StringTokenizer(in.nextLine());
        int[] tabs = new int[n];
        int numE = 0;
        int numS = 0;
        for (int i = 0; i < n; i++)
        {
            tabs[i] = Integer.parseInt(st.nextToken());
            if (tabs[i] == 1)
                numE++;
            else
                numS++;
        }

        int rmE = 0;
        int rmS = 0;
        int maxDif = 0;
        for (int i = 0; i < k; i++)
        {
            for (int j = i; j < n; j += k)
            {
                if (tabs[j] == 1)
                    rmE++;
                else
                    rmS++;
            }
            int tempDif = Math.abs((numE - rmE) - (numS - rmS));
            if (tempDif > maxDif)
                maxDif = tempDif;

            rmE = 0;
            rmS = 0;
        }
        System.out.println(maxDif);
    }
}