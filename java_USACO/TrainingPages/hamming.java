/*
ID: pranavm7
LANG: JAVA
TASK: hamming
PROG: hamming
*/

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class hamming
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

        String[] input = in.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int d = Integer.parseInt(input[2]);

        LinkedList<Integer> nums = new LinkedList<>();
        nums.add(0);

        int current = 1;

        while (nums.size() < n && current < Math.pow(2, b))
        {
            addIfAllWork(nums, current, d);
            current++;
        }

        int counter = 0;
        ListIterator<Integer> itr = nums.listIterator();
        while (itr.hasNext())
        {
            out.print(itr.next());
            counter++;
            if (counter % 10 == 0 || !itr.hasNext())
                out.println();
            else
                out.print(" ");
        }

        out.close();
    }

    private static void addIfAllWork(LinkedList<Integer> nums, int current, int d)
    {
        int xor;
        for (int i : nums)
        {
            xor = current ^ i;
            if (Integer.bitCount(xor) < d)
                return;
        }
        nums.add(current);
    }
}