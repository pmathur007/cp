import java.util.Scanner;

public class P1253A
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int tc = 0; tc < t; tc++)
        {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextInt();
            int lastDif = 0;
            int numChanges = 0;
            for (int i = 0; i < n; i++)
            {
                int dif = in.nextInt() - a[i];
                if (dif < 0)
                {
                    numChanges = 2;
                    continue;
                }
                else if (dif != lastDif)
                {
                    numChanges += (dif == 0 ? 0 : 1);
                    lastDif = dif;
                }
            }
            System.out.println(numChanges <= 1 ? "YES" : "NO");
        }
    }
}
