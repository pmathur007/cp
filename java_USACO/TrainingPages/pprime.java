/*
ID: pranavm7
LANG: JAVA
TASK: pprime
PROG: pprime
*/

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class pprime
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        String[] input = in.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);

        LinkedList<Integer> primes = new LinkedList<>();
        for (int i = input[0].length(); i <= input[1].length(); i++)
        {
            printPrimePals(primes, i, a, b);
        }
        Collections.sort(primes);

        for (int i : primes)
            out.println(i);

        out.close();
    }

    private static void printPrimePals(LinkedList<Integer> primes, int n, int a, int b)
    {
        if (n % 2 == 0)
        {
            printPrimePalsHelper(primes, n, a, b, "");
        }
        else
        {
            for (int i = 0; i <= 9; i++)
            {
                printPrimePalsHelper(primes, n, a, b, "" + i);
            }
        }
    }

    private static void printPrimePalsHelper(LinkedList<Integer> primes, int n, int a, int b, String c)
    {
        if (c.length() == n)
        {
            int pot = Integer.parseInt(c);
            if (pot >= a && pot <= b && isPrime(pot))
                primes.add(Integer.parseInt(c));
        }
        else
        {
            if (n - c.length() > 2)
                printPrimePalsHelper(primes, n, a, b, "0" + c + "0");
            for (int i = 1; i <= 9; i++)
            {
                printPrimePalsHelper(primes, n, a, b, i + c + i);
            }
//            printPrimePalsHelper(n, "1" + c + "1");
//            printPrimePalsHelper(n, "2" + c + "2");
//            printPrimePalsHelper(n, "3" + c + "3");
//            printPrimePalsHelper(n, "4" + c + "4");
//            printPrimePalsHelper(n, "5" + c + "5");
//            printPrimePalsHelper(n, "6" + c + "6");
//            printPrimePalsHelper(n, "7" + c + "7");
//            printPrimePalsHelper(n, "8" + c + "8");
//            printPrimePalsHelper(n, "9" + c + "9");
        }
    }

    private static boolean isPrime(int n)
    {
//        if (n <= 1)
//            return false;
//
//        for (int i = 2; i * i <= n; i++)
//        {
//            if (n % i == 0)
//                return false;
//        }
//
//        return true;
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
}
