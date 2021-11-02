/*
ID: pranavm7
LANG: JAVA
TASK: sprime
PROG: sprime
*/

import java.io.*;

public class sprime
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        int n = Integer.parseInt(in.readLine());

        printSuperprimes(out, n);

        out.close();
    }

    private static void printSuperprimes(PrintWriter out, int n)
    {
        printSuperprimesHelper(out, n - 1, 2);
        printSuperprimesHelper(out, n - 1, 3);
        printSuperprimesHelper(out, n - 1, 5);
        printSuperprimesHelper(out, n - 1, 7);
    }

    private static void printSuperprimesHelper(PrintWriter out, int n, int current)
    {
        if (!isPrime(current))
            return;

        if (n == 0)
        {
            out.println(current);
        }

        printSuperprimesHelper(out, n - 1, 10 * current + 1);
        printSuperprimesHelper(out, n - 1, 10 * current + 3);
        printSuperprimesHelper(out, n - 1, 10 * current + 5);
        printSuperprimesHelper(out, n - 1, 10 * current + 7);
        printSuperprimesHelper(out, n - 1, 10 * current + 9);
    }

    private static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i += 6)
        {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }

        return true;
    }
}
