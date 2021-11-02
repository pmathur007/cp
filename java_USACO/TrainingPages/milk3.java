/*
ID: pranavm7
LANG: JAVA
TASK: milk3
PROG: milk3
*/

import java.io.*;
import java.util.*;

public class milk3
{
    private static int aCap;
    private static int bCap;
    private static int cCap;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        String[] input = in.readLine().split(" ");
        aCap = Integer.parseInt(input[0]);
        bCap = Integer.parseInt(input[1]);
        cCap = Integer.parseInt(input[2]);

        Set<Integer> outputs = new HashSet<>();

        // visited ID: a(bCap + 1)(cCap + 1) + b(cCap + 1) + c
        boolean[] visited = new boolean[(aCap + 1) * (bCap + 1) * (cCap + 1)];

        generateOutputs(outputs, visited, new int[] {0, 0, cCap});

        LinkedList<Integer> sorted = new LinkedList<>(outputs);
        Collections.sort(sorted);

        for (int i = 0; i < sorted.size(); i++)
        {
            if (i < sorted.size() - 1)
                out.print(sorted.get(i) + " ");
            else
                out.println(sorted.get(i));
        }

        out.close();
    }

    private static void generateOutputs(Set<Integer> outputs, boolean[] visited, int[] current)
    {
        if (visited[current[0] * (bCap + 1) * (cCap + 1) + current[1] * (cCap + 1) + current[2]])
            return;

        visited[current[0] * (bCap + 1) * (cCap + 1) + current[1] * (cCap + 1) + current[2]] = true;

        if (current[0] == 0)
            outputs.add(current[2]);

        generateOutputs(outputs, visited, pour(current, 0, 1));
        generateOutputs(outputs, visited, pour(current, 1, 0));
        generateOutputs(outputs, visited, pour(current, 0, 2));
        generateOutputs(outputs, visited, pour(current, 2, 0));
        generateOutputs(outputs, visited, pour(current, 1, 2));
        generateOutputs(outputs, visited, pour(current, 2, 1));
    }

    private static int[] pour(int[] buckets, int from, int to)
    {
        int[] bucketsCopy = Arrays.copyOf(buckets, 3);
        int fromCap  = (from == 0 ? aCap : (from == 1 ? bCap : cCap));
        int toCap = (to == 0 ? aCap : (to == 1 ? bCap : cCap));

        int available = toCap - bucketsCopy[to];
        if (bucketsCopy[from] > available)
        {
            bucketsCopy[to] = toCap;
            bucketsCopy[from] -= available;
        }
        else
        {
            bucketsCopy[to] += bucketsCopy[from];
            bucketsCopy[from] = 0;
        }
        return bucketsCopy;
    }
}

//        if (cCap <= aCap && cCap <= bCap) // c <= a <= b or c <= b <= a (c is smallest)
//        {
//            outputs.add(0);
//            outputs.add(cCap);
//        }
//        else if (aCap <= cCap && cCap <= bCap) // a <= c <= b
//        {
//            outputs.add(0);
//            outputs.add(cCap);
//            for (int i = 0; i < cCap / aCap; i++)
//            {
//                outputs.add(aCap * (i + 1));
//                outputs.add((cCap % aCap) + i * aCap);
//            }
//        }
//        else if (bCap <= cCap && cCap <= aCap) // b <= c <= a
//        {
//            outputs.add(cCap);
//            outputs.add(cCap - bCap);
//            if (cCap % bCap == 0)
//                outputs.add((cCap / bCap - 1) * bCap);
//            else
//                outputs.add(cCap / bCap * bCap);
//        }
//        else if (aCap <= bCap && bCap <= cCap)
//        {
//            outputs.add(cCap);
//            outputs.add(cCap - bCap);
//            for (int i = 0; i < bCap / aCap; i++)
//            {
//                outputs.add((cCap - bCap) + (i + 1) * aCap);
//                outputs.add(cCap - (i + 1) * aCap);
//            }
//            if (bCap >= cCap - aCap)
//                outputs.add(aCap);
//        }
//        else
//        {
//            outputs.add(cCap);
//            outputs.add(cCap - bCap);
//            outputs.add(cCap - aCap + (aCap / bCap * bCap));
//        }
//
//        LinkedList<Integer> sorted = new LinkedList<>(outputs);
//        Collections.sort(sorted);
//        for (int i = 0; i < sorted.size(); i++)
//        {
//            if (i == sorted.size() - 1)
//                out.println(sorted.get(i));
//            else
//                out.print(sorted.get(i) + " ");
//        }
//
//        out.close();

