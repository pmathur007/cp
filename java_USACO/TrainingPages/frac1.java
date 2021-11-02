/*
ID: pranavm7
LANG: JAVA
TASK: frac1
PROG: frac1
*/

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class frac1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

        int n = Integer.parseInt(in.readLine());
        boolean[] fracs = new boolean[((n + 1) * (n + 2)) / 2 - 1];
        LinkedList<Fraction> orderedFracs = new LinkedList<>();

        int offset;
        for (int den = 1; den <= n; den++)
        {
            offset = (den * (den + 1)) / 2 - 1;
            for (int num = 0; num <= den; num++)
            {
                if (!fracs[offset + num])
                {
                    orderedFracs.add(new Fraction(num, den));
                    for (int i = 2; i * den <= n; i++)
                    {
                        fracs[((i * den) * (i * den + 1)) / 2 + (i * num) - 1] = true;
                    }
                }
            }
        }

        Collections.sort(orderedFracs);

        for (Fraction f : orderedFracs)
            out.println(f);

        out.close();
    }
}

class Fraction implements Comparable<Fraction>
{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator()
    {
        return numerator;
    }

    public int getDenominator()
    {
        return denominator;
    }

    @Override
    public int compareTo(Fraction o)
    {
        return this.numerator * o.denominator - this.denominator * o.numerator;
    }

    @Override
    public String toString()
    {
        return numerator + "/" + denominator;
    }
}
