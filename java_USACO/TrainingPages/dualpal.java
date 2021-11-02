/*
ID: pranavm7
LANG: JAVA
TASK: dualpal
PROG: dualpal
*/

import java.io.*;

public class dualpal
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int s = Integer.parseInt(input[1]);

        s++;
        while (n > 0)
        {
            int count = 0;
            if (isPalindrome("" + s))
                count++;

            for (int i = 2; i <= 9; i++)
            {
                if (isPalindrome(base10To(s, i)))
                    count++;
                if (count == 2)
                {
                    out.println(s);
                    n--;
                    break;
                }
            }
            s++;
        }

        out.close();
    }

    private static String base10To(int num, int base)
    {
        String converted = "";
        while (num > 0)
        {
            int remainder = num % base;
            if (remainder >= 10)
                converted = ((char) (remainder + 55)) + converted;
            else
                converted = remainder + converted;
            num /= base;
        }
        return converted;
    }

    private static boolean isPalindrome(String num)
    {
        for (int i = 0; i < num.length() / 2; i++)
        {
            if (num.charAt(i) != num.charAt(num.length() - i - 1))
                return false;
        }
        return true;
    }
}
