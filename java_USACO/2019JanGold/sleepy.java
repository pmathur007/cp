import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class sleepy
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));

        int n = Integer.parseInt(in.readLine());
        LinkedList<Integer> line = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");

        for (int i = 0; i < n; i++)
            line.add(Integer.parseInt(st.nextToken()));

        int[] fwt = new int[n + 1];
        int last = line.removeLast();
        updateFwt(fwt, last, 1);
        while (line.getLast() < last)
        {
            last = line.removeLast();
            updateFwt(fwt, last, 1);
        }

        out.println(line.size());
        while (!line.isEmpty())
        {
            int cow = line.removeFirst();
            out.print((line.size() + queryFwt(fwt, cow)) + (line.isEmpty() ? "" : " "));
            updateFwt(fwt, cow, 1);
        }

        out.close();
    }

    private static int queryFwt(int[] fwt, int i)
    {
        int ans = 0;
        for (; i > 0; i -= (i & (-i)))
            ans += fwt[i];
        return ans;
    }

    private static void updateFwt(int[] fwt, int i, int v)
    {
        for (; i < fwt.length; i += (i & (-i)))
            fwt[i] += v;
    }
}

// IN CONTEST, TEST CASE 3 AND 12
//        int n = Integer.parseInt(in.readLine());
//        LinkedList<Integer> list = new LinkedList<>();
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        boolean isSorted = true;
//        for (int i = 0; i < n; i++)
//        {
//            list.add(Integer.parseInt(st.nextToken()));
//            if (i > 0 && list.get(i) < list.get(i - 1))
//                isSorted = false;
//        }
//        if (isSorted)
//        {
//            out.println(0);
//            out.println();
//        }
//        else
//        {
//            LinkedList<Integer> sorted = new LinkedList<>();
//            sorted.add(list.removeLast());
//            while (list.getLast() < sorted.getFirst())
//            {
//                sorted.addFirst(list.removeLast());
//            }
//            int numSteps = list.size();
//
//            LinkedList<Integer> steps = new LinkedList<>();
//            while (!list.isEmpty())
//            {
//                int first = list.removeFirst();
//                int index = insert(sorted, first);
//                steps.add(list.size() + index);
//            }
//
//            out.println(numSteps);
//            for (Integer i : steps)
//            {
//                numSteps--;
//                out.print(i + (numSteps > 0 ? " " : ""));
//            }
//            out.println();
//        }
//        out.close();
//    }
//
//    private static int insert(LinkedList<Integer> sorted, int n)
//    {
//        int index = 0;
//        ListIterator<Integer> itr = sorted.listIterator();
//        while (itr.hasNext())
//        {
//            int next = itr.next();
//            if (n < next)
//            {
//                itr.set(n);
//                itr.add(next);
//                break;
//            }
//            index++;
//        }
//        return index;
//    }