import java.io.*;
import java.util.PriorityQueue;

public class cardgame
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));

        int n = Integer.parseInt(in.readLine());

        boolean[] isElsie = new boolean[2 * n];
        PriorityQueue<Integer> eh = new PriorityQueue<>();
        PriorityQueue<Integer> el = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++)
        {
            int e = Integer.parseInt(in.readLine()) - 1;
            isElsie[e] = true;
            if (i < n / 2)  eh.add(e);
            else el.add(e);
        }

        PriorityQueue<Integer> bh = new PriorityQueue<>();
        PriorityQueue<Integer> bl = new PriorityQueue<>((a, b) -> b - a);
        int bn = 0;

        for (int i = 0; i < 2 * n; i++)
        {
            if (!isElsie[i])
            {
                if (bn < n / 2) bl.add(i);
                else bh.add(i);
                bn++;
            }
        }

        int win = 0;
        while (!bh.isEmpty())
        {
            int b = bh.remove();
            if (b > eh.peek())
            {
                eh.remove();
                win++;
            }
        }
        while (!bl.isEmpty())
        {
            int b = bl.remove();
            if (b < el.peek())
            {
                el.remove();
                win++;
            }
        }

        out.println(win);

        out.close();
    }
}