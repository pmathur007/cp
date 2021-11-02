import java.io.*;
import java.util.Arrays;

public class hoofball
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hoofball.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hoofball.out")));

        int n = Integer.parseInt(in.readLine());
        String[] input = in.readLine().split(" ");
        int[] ordered = new int[n];
        for (int i = 0; i < n; i++)
            ordered[i] = Integer.parseInt(input[i]);
        Arrays.sort(ordered);

        if (ordered.length <= 2)
        {
            out.println(1);
        }
        else
        {
            boolean[] passedTo = new boolean[n];

            for (int i = 0; i < n; i++)
            {
                int passed = nearest(ordered, i);
                passedTo[passed] = true;
            }

            int numNotPassedTo = 0;
            for (int i = 0; i < n; i++)
            {
                if (!passedTo[i])
                    numNotPassedTo++;
            }

            int numIndepCycles = 0;
            for (int i = 0; i < n - 1; i++)
            {
                if (isIndepCycle(ordered, i, i + 1))
                    numIndepCycles++;
            }

            out.println(numNotPassedTo + numIndepCycles);
        }

        out.close();
    }

    private static boolean isIndepCycle (int[] ordered, int i, int j)
    {
        boolean prevNotToi = false;
        boolean nextNotToj = false;
        boolean iToj = false;

        if (i == 0 || nearest(ordered, i - 1) != i)
            prevNotToi = true;

        if (j == ordered.length - 1 || nearest(ordered, j + 1) != j)
            nextNotToj = true;

        if (nearest(ordered, i) == j && nearest(ordered, j) == i)
            iToj = true;

        return prevNotToi && nextNotToj && iToj;
    }

    private static int nearest(int[] ordered, int i)
    {
        if (i == 0)
            return 1;
        if (i == ordered.length - 1)
            return ordered.length - 2;
        if (ordered[i] - ordered[i - 1] <= ordered[i + 1] - ordered[i])
            return i - 1;
        return i + 1;
    }
}
