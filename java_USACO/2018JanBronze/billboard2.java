import java.io.*;

public class billboard2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));

        String[] input = in.readLine().split(" ");
        int lx1 = Integer.parseInt(input[0]);
        int ly1 = Integer.parseInt(input[1]);
        int lx2 = Integer.parseInt(input[2]);
        int ly2 = Integer.parseInt(input[3]);

        input = in.readLine().split(" ");
        int fx1 = Integer.parseInt(input[0]);
        int fy1 = Integer.parseInt(input[1]);
        int fx2 = Integer.parseInt(input[2]);
        int fy2 = Integer.parseInt(input[3]);

        int maxY = 0;
        int maxX = 0;

        for (int x = lx1; x < lx2; x++)
        {
            int numNotBlocked = 0;
            for (int y = ly1; y < ly2; y++)
            {
                if (!isBlocked(x, y, fx1, fy1, fx2, fy2))
                    numNotBlocked++;
            }
            maxY = Math.max(numNotBlocked, maxY);
        }

        for (int y = ly1; y < ly2; y++)
        {
            int numNotBlocked = 0;
            for (int x = lx1; x < lx2; x++)
            {
                if (!isBlocked(x, y, fx1, fy1, fx2, fy2))
                    numNotBlocked++;
            }
            maxX = Math.max(numNotBlocked, maxX);
        }

        out.println(maxX * maxY);
        out.close();
    }

    private static boolean isBlocked(int x, int y, int fx1, int fy1, int fx2, int fy2)
    {
        return (x >= fx1) && (x < fx2) && (y >= fy1) && (y < fy2);
    }
}
