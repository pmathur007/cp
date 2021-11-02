/*
ID: pranavm7
LANG: JAVA
TASK: sort3
PROG: sort3
*/

import java.io.*;

public class sort3
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        int n = Integer.parseInt(in.readLine());
        int[] array = new int[n];
        int numOnes = 0;
        int numTwos = 0;
        int numThrees = 0;

        for (int i = 0; i < n; i++)
        {
            array[i] = Integer.parseInt(in.readLine());
            if (array[i] == 1)
                numOnes++;
            else if (array[i] == 2)
                numTwos++;
            else
                numThrees++;
        }

        int twosInOne = 0;
        int threesInOne = 0;
        int onesInTwo = 0;
        int threesInTwo = 0;
        int onesInThree = 0;
        int twosInThree = 0;

        for (int i = 0; i < numOnes; i++)
        {
            if (array[i] == 2)
                twosInOne++;
            if (array[i] == 3)
                threesInOne++;
        }
        for (int i = numOnes; i < numOnes + numTwos; i++)
        {
            if (array[i] == 1)
                onesInTwo++;
            if (array[i] == 3)
                threesInTwo++;
        }
        for (int i = numOnes + numTwos; i < array.length; i++)
        {
            if (array[i] == 1)
                onesInThree++;
            if (array[i] == 2)
                twosInThree++;
        }

        int numSwaps = twosInOne + threesInOne;

        if (twosInOne > onesInTwo)
        {
            twosInThree += (twosInOne - onesInTwo);
        }
        else if (twosInOne < onesInTwo)
        {
            threesInTwo += (onesInTwo - twosInOne);
        }

        numSwaps += threesInTwo;


        out.println(numSwaps);

        out.close();
    }

    private static void swap(int[] array, int a, int b)
    {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
