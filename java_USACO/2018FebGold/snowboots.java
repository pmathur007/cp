import java.io.*;
import java.util.*;

public class snowboots
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(in.readLine());
        LinkedList<Integer> tiles = new LinkedList<>();
        int[] snow = new int[n];
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++)
        {
            snow[i] = Integer.parseInt(st.nextToken());
            tiles.add(i);
            if (i != 0)
                prev[i] = i - 1;
            if (i != n - 1)
                next[i] = i + 1;
        }
        Collections.sort(tiles, (i1, i2) -> snow[i1] - snow[i2]);

        Boot[] boots = new Boot[b];
        for (int i = 0; i < b; i++)
        {
            st = new StringTokenizer(in.readLine());
            boots[i] = new Boot(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(boots);

        boolean[] canGo = new boolean[b];
        int maxD = 1;
        for (Boot boot : boots)
        {
            while (snow[tiles.getLast()] > boot.s)
            {
                int rm = tiles.removeLast();
                prev[next[rm]] = prev[rm];
                next[prev[rm]] = next[rm];
                maxD = Math.max(maxD, next[rm] - prev[rm]);
            }
            if (maxD <= boot.d)
                canGo[boot.x] = true;
        }

        for (boolean bool : canGo)
            out.println(bool ? 1 : 0);
        out.close();
    }

    private static class Boot implements Comparable<Boot>
    {
        private int x, s, d;

        public Boot(int x, int s, int d)
        {
            this.x = x;
            this.s = s;
            this.d = d;
        }

        @Override
        public int compareTo(Boot o)
        {
            return o.s - this.s;
        }
    }
}