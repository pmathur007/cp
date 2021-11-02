import java.io.*;

public class crossroad
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("crossroad.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));

        int n = Integer.parseInt(in.readLine());

        int[] locs = new int[10];
        for (int i = 0; i < 10; i++)
        {
            locs[i] = -1;
        }

        int numCrossings = 0;

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            int id = Integer.parseInt(line[0]) - 1;
            int loc = Integer.parseInt(line[1]);

            if (locs[id] == -1)
            {
                locs[id] = loc;
            }
            else
            {
                if (locs[id] != loc)
                {
                    numCrossings++;
                    locs[id] = loc;
                }
            }
        }

        out.println(numCrossings);
        out.close();
    }
}
