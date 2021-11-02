import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

public class fcolor
{
    private static int N, M;
    private static LinkedList<Integer>[] admires, admiredBy;
    private static Set<Integer>[] nodesOfColor;
    private static int[] colorInto;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("fcolor.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        admires = new LinkedList[N];
        admiredBy = new LinkedList[N];
        nodesOfColor = new HashSet[N];
        colorInto = new int[N];
        for (int i = 0; i < N; i++)
        {
            admires[i] = new LinkedList<>();
            admiredBy[i] = new LinkedList<>();
            nodesOfColor[i] = new HashSet<>();
            nodesOfColor[i].add(i);
            colorInto[i] = (i + 1) % N;
        }

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(in.readLine()) - 1;
            int b = Integer.parseInt(in.readLine()) - 1;
            admires[b].add(a);
            admiredBy[a].add(b);
        }

        LinkedList<Integer> needToFix = new LinkedList<>();
        for (int i = 0; i < N; i++)
        {
            if (admiredBy[i].size() >= 2)
                needToFix.add(i);
        }



        out.close();
    }
}