import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class mountains
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));

        int n = Integer.parseInt(in.readLine());

        LinkedList<Mountain> mountains = new LinkedList<>();
        StringTokenizer st;
        ListIterator<Mountain> itr;

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            Mountain m = new Mountain(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            itr = mountains.listIterator();
            boolean covered = false;

            while (itr.hasNext() && !covered)
            {
                Mountain inList = itr.next();
                if (inList.covers(m))
                    covered = true;
                else if (m.covers(inList))
                    itr.remove();
            }

            if (!covered)
                mountains.add(m);
        }

        out.println(mountains.size());

        out.close();
    }

    private static class Mountain
    {
        int x, y;

        public Mountain(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public boolean covers(Mountain m)
        {
            return m.y <= (this.y - Math.abs(this.x - m.x));
        }
    }
}