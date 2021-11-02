/*
ID: pranavm7
LANG: JAVA
TASK: lamps
PROG: lamps
*/

import java.io.*;
import java.util.*;

public class lamps
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

        int n = Integer.parseInt(in.readLine());
        int c = Integer.parseInt(in.readLine());

        byte[] finConfig = new byte[6];
        StringTokenizer st = new StringTokenizer(in.readLine());
        int x = 0;
        while ((x = Integer.parseInt(st.nextToken())) != -1)
        {
            if (x % 6 == 0)
                finConfig[5] = 1;
            else
                finConfig[x % 6 - 1] = 1;
        }
        st = new StringTokenizer(in.readLine());
        while ((x = Integer.parseInt(st.nextToken())) != -1)
        {
            if (x % 6 == 0)
                finConfig[5] = -1;
            else
                finConfig[x % 6 - 1] = -1;
        }

        BitSet start = new BitSet(6);
        start.set(0, 6);

        Set<BitSet> finalSet = new HashSet<>();

        if (c % 2 == 1)
        {
            oneSwitch(finalSet, start);
            if (c > 1)
                threeSwitches(finalSet, start);
        }
        else
        {
            finalSet.add(start);
            if (c > 0)
                twoSwitches(finalSet, start);
            if (c > 2)
                fourSwitches(finalSet, start);
        }

        LinkedList<String> finalSorted = new LinkedList<>();
        for (BitSet b : finalSet)
        {
            if (satisfies(b, finConfig))
                finalSorted.add(stringRep(b, n));
        }
        Collections.sort(finalSorted);

        if (finalSorted.size() == 0)
            out.println("IMPOSSIBLE");
        else
            for (String s : finalSorted)
                out.println(s);

        out.close();
    }

    private static void oneSwitch(Set<BitSet> set, BitSet start)
    {
        set.add(toggleAll(start));
        set.add(toggleOdd(start));
        set.add(toggleEven(start));
        set.add(toggleK(start));
    }

    private static void twoSwitches(Set<BitSet> set, BitSet start)
    {
        set.add(toggleAll(toggleOdd(start)));
        set.add(toggleAll(toggleEven(start)));
        set.add(toggleAll(toggleK(start)));
        set.add(toggleOdd(toggleEven(start)));
        set.add(toggleOdd(toggleK(start)));
        set.add(toggleEven(toggleK(start)));
    }

    private static void threeSwitches(Set<BitSet> set, BitSet start)
    {
        set.add(toggleAll(toggleOdd(toggleEven(start))));
        set.add(toggleAll(toggleOdd(toggleK(start))));
        set.add(toggleAll(toggleEven(toggleK(start))));
        set.add(toggleOdd(toggleEven(toggleK(start))));
    }

    private static void fourSwitches(Set<BitSet> set, BitSet start)
    {
        set.add(toggleAll(toggleOdd(toggleEven(toggleK(start)))));
    }

    private static BitSet toggleAll(BitSet start)
    {
        BitSet copy = (BitSet) start.clone();
        copy.flip(0, 6);
        return copy;
    }

    private static BitSet toggleOdd(BitSet start)
    {
        BitSet copy = (BitSet) start.clone();
        copy.flip(0);
        copy.flip(2);
        copy.flip(4);
        return copy;
    }

    private static BitSet toggleEven(BitSet start)
    {
        BitSet copy = (BitSet) start.clone();
        copy.flip(1);
        copy.flip(3);
        copy.flip(5);
        return copy;
    }

    private static BitSet toggleK(BitSet start)
    {
        BitSet copy = (BitSet) start.clone();
        copy.flip(0);
        copy.flip(3);
        return copy;
    }

    private static String stringRep(BitSet start, int n)
    {
        StringBuilder s = new StringBuilder();
        while (n - 6 >= 0)
        {
            for (int i = 0; i < 6; i++)
                s.append(start.get(i) ? "1" : "0");
            n -= 6;
        }
        for (int i = 0; i < n; i++)
            s.append(start.get(i) ? "1" : "0");
        return s.toString();
    }

    private static boolean satisfies(BitSet set, byte[] finConfig)
    {
        for (int i = 0; i < 6; i++)
        {
            if (finConfig[i] == 1 && !set.get(i))
                return false;
            if (finConfig[i] == -1 && set.get(i))
                return false;
        }
        return true;
    }
}