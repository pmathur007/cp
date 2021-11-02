/*
ID: pranavm7
LANG: JAVA
TASK: combo
PROG: combo
*/

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class combo
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        int n = Integer.parseInt(in.readLine()); // maximum number in combo lock

        String[] farmerComboLine = in.readLine().split(" "); // read farmer's combo into array
        int[] farmerCombo = new int[3];
        farmerCombo[0] = Integer.parseInt(farmerComboLine[0]);
        farmerCombo[1] = Integer.parseInt(farmerComboLine[1]);
        farmerCombo[2] = Integer.parseInt(farmerComboLine[2]);

        String[] masterComboLine = in.readLine().split(" ");
        int[] masterCombo = new int[3];
        masterCombo[0] = Integer.parseInt(masterComboLine[0]); // read master combo into array
        masterCombo[1] = Integer.parseInt(masterComboLine[1]);
        masterCombo[2] = Integer.parseInt(masterComboLine[2]);

        Set<Combination> combos = new HashSet<>(); // use Set to avoid duplicates
        generateCombos(farmerCombo, combos, n);
        generateCombos(masterCombo, combos, n);

        out.println(combos.size());
        out.close();
    }

    private static void generateCombos(int[] original, Set<Combination> combos, int n)
    {
        for (int a = original[0] - 2; a < original[0] + 3; a++)
        {
            int first = a;
            if (a <= 0)
            {
                first = n + a;
                first = Math.max(first, 1);
            }
            else if (a > n)
            {
                first = Math.min(a - n, n);
            }
            for (int b = original[1] - 2; b < original[1] + 3; b++)
            {
                int second = b;
                if (b <= 0)
                {
                    second = n + b;
                    second = Math.max(second, 1);
                }
                else if (b > n)
                {
                    second = Math.min(b - n, n);
                }
                for (int c = original[2] - 2; c < original[2] + 3; c++)
                {
                    int third = c;
                    if (c <= 0)
                    {
                        third = n + c;
                        third = Math.max(third, 1);
                    }
                    else if (c > n)
                    {
                        third = Math.min(c - n, n);
                    }
                    if (third <= 0)
                        third = 1;
                    Combination temp = new Combination(first, second, third);
                    System.out.println(temp.toString());
                    combos.add(new Combination(first, second, third));
                }
            }
        }
    }
}

class Combination
{
    private final int first;
    private final int second;
    private final int third;

    public Combination(int first, int second, int third)
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public int hashCode()
    {
        return first * first + second * second + third * third - first - second - third;
    }

    @Override
    public boolean equals(Object obj)
    {
        Combination c2 = (Combination) obj;
        return (this.first == c2.first) && (this.second == c2.second) && (this.third == c2.third);
    }

    @Override
    public String toString()
    {
        return first + " " + second + " " + third;
    }
}
