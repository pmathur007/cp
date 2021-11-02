import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class cowdance
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int tMax = Integer.parseInt(meta[1]);

        int[] order = new int[n];
        for (int i = 0; i < n; i++)
        {
            order[i] = Integer.parseInt(in.readLine());
        }

        out.println(binForK(order, tMax));

        out.close();
    }

    private static int binForK(int[] order, int tMax)
    {
        int low = 1;
        int high = order.length;

        int mid = 0;
        while (low < high)
        {
            mid = (low + high) / 2;
            int time = timeForK(order, mid);

            if (time > tMax)
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }
        return high;
    }

    private static int timeForK(int[] order, int k)
    {
        int time = 0;
        int index = k;

        LinkedList<Integer> stage = new LinkedList<>();
        for (int i = 0; i < k; i++)
        {
            addInOrder(stage, order[i]);
        }
        while (!stage.isEmpty())
        {
            time = stage.removeFirst();
            if (index < order.length)
            {
                addInOrder(stage, order[index] + time);
                index++;
            }
        }
        return time;
    }

    private static void addInOrder(LinkedList<Integer> stage, int n)
    {
        boolean added = false;
        if (stage.isEmpty())
        {
            stage.add(n);
            added = true;
        }
        ListIterator<Integer> itr = stage.listIterator();
        while (itr.hasNext())
        {
            if (itr.next() >= n && !added)
            {
                itr.add(n);
                added = true;
            }
        }
        if (!added)
            stage.add(n);
    }
}