import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class convention2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));

        int n = Integer.parseInt(in.readLine());

        LinkedList<ConventionCow> cows = new LinkedList<>();
        LinkedList<ConventionCow> q = new LinkedList<>();

        for (int i = 0; i < n; i++)
        {
            cows.add(new ConventionCow(i, in.readLine().split(" ")));
        }

        Collections.sort(cows, (c1, c2) ->
        {
            if (c1.getArrival() == c2.getArrival())
                return c1.getSeniority() - c2.getSeniority();
            return c1.getArrival() - c2.getArrival();
        });

        int currentTime = cows.get(0).getArrival();
        ConventionCow current;
        int maxWaitingTime = 0;

        while (!(cows.isEmpty() && q.isEmpty()))
        {
            if (q.size() != 0)
            {
                current = q.poll();
                maxWaitingTime = Math.max(maxWaitingTime, currentTime - current.getArrival());
            }
            else
            {
                current = cows.poll();
                currentTime = current.getArrival();
            }


            currentTime += current.getEating();
            while (cows.size() > 0 && cows.get(0).getArrival() <= currentTime)
            {
                q.add(cows.get(0));
                cows.remove(0);
            }
            Collections.sort(q, (c1, c2) -> c1.getSeniority() - c2.getSeniority());
        }

        out.println(maxWaitingTime);
        out.close();
    }
}

class ConventionCow
{
    private int seniority;
    private int arrival;
    private int eating;

    public ConventionCow(int s, String[] times)
    {
        this.seniority = s;
        this.arrival = Integer.parseInt(times[0]);
        this.eating = Integer.parseInt(times[1]);
    }

    public int getSeniority()
    {
        return seniority;
    }

    public int getArrival()
    {
        return arrival;
    }

    public int getEating()
    {
        return eating;
    }
}