import java.io.*;
import java.util.LinkedList;

public class cowqueue
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowqueue.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowqueue.out")));

        int n = Integer.parseInt(in.readLine());
        int[] times = new int[10000000];

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            times[Integer.parseInt(line[0]) - 1] = Integer.parseInt(line[1]);
        }

        int timeCounter = 0;
        int numProccessed = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        boolean questioning = false;
        int remainingTime = -1;

        while (numProccessed != n && timeCounter < 1000001)
        {
            if (questioning)
            {
                remainingTime--;
                if (remainingTime == 0)
                {
                    questioning = false;
                    numProccessed++;
                    if (queue.size() >= 1)
                    {
                        questioning = true;
                        remainingTime = queue.poll();
                    }
                }
                if (times[timeCounter] > 0)
                {
                    if (questioning)
                    {
                        queue.add(times[timeCounter]);
                    }
                    else
                    {
                        questioning = true;
                        remainingTime = times[timeCounter];
                    }
                }
            }
            else
            {
                if (queue.size() >= 1)
                {
                    questioning = true;
                    remainingTime = queue.poll();
                }
                else if (times[timeCounter] > 0)
                {
                    questioning = true;
                    remainingTime = times[timeCounter];
                }
            }
            timeCounter++;
        }

        out.println(timeCounter);
        out.close();
    }
}
