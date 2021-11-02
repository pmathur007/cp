/*
ID: pranavm7
LANG: JAVA
TASK: palsquare
PROG: palsquare
*/

import java.io.*;

public class palsquare
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int base = Integer.parseInt(in.readLine());
        for (int i = 1; i <= 300; i++)
        {
            if (isPalindrome(base10ToB(i * i, base)))
            {
                out.println(base10ToB(i, base) + " " + base10ToB(i * i, base));
            }
        }

        System.out.println(base10ToB(255, 16));
        out.close();
    }

    private static String base10ToB(int num, int base)
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
