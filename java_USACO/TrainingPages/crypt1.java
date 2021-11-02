/*
ID: pranavm7
LANG: JAVA
TASK: crypt1
PROG: crypt1
*/

import java.io.*;
import java.util.StringTokenizer;

public class crypt1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int n = Integer.parseInt(in.readLine());

        int[] digits = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < digits.length; i++)
        {
            digits[i] = Integer.parseInt(st.nextToken());
        }

        int solutions = 0;

        for (int a : digits)
        {
            for (int b : digits)
            {
                for (int c : digits)
                {
                    for (int d : digits)
                    {
                        for (int e : digits)
                        {
                            int abc = 100 * a + 10 * b + c;
                            int de = 10 * d + e;

                            if (e * abc < 1000
                                    && d * abc < 1000
                                    && (abc * de >= 1000 && abc * de < 10000)
                                    && numberValid(e * abc, digits)
                                    && numberValid(d * abc, digits)
                                    && numberValid(abc * de, digits))
                                solutions++;
                        }
                    }
                }
            }
        }

        out.println(solutions);
        out.close();
    }

    private static boolean numberValid(int n, int[] digits)
    {
        while (n > 0)
        {
            if (!digitValid(n % 10, digits))
                return false;
            n /= 10;
        }
        return true;
    }

    private static boolean digitValid(int n, int[] digits)
    {
        for (int i = 0; i < digits.length; i++)
        {
            if (n == digits[i])
                return true;
        }
        return false;
    }
}
