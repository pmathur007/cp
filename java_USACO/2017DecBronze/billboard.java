import java.io.*;
import java.util.StringTokenizer;

public class billboard
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("billboard.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));

        // Billboard 1
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int b1x1 = Integer.parseInt(st.nextToken());
        int b1y1 = Integer.parseInt(st.nextToken());
        int b1x2 = Integer.parseInt(st.nextToken());
        int b1y2 = Integer.parseInt(st.nextToken());

        // Billboard 2
        st = new StringTokenizer(in.readLine(), " ");
        int b2x1 = Integer.parseInt(st.nextToken());
        int b2y1 = Integer.parseInt(st.nextToken());
        int b2x2 = Integer.parseInt(st.nextToken());
        int b2y2 = Integer.parseInt(st.nextToken());

        // Truck
        st = new StringTokenizer(in.readLine(), " ");
        int tx1 = Integer.parseInt(st.nextToken());
        int ty1 = Integer.parseInt(st.nextToken());
        int tx2 = Integer.parseInt(st.nextToken());
        int ty2 = Integer.parseInt(st.nextToken());

        int area = 0;
        for (int x = b1x1; x < b1x2; x++)
        {
            for (int y = b1y1; y < b1y2; y++)
            {
                if (!isBlocked(x, y, tx1, ty1, tx2, ty2))
                    area++;
            }
        }

        for (int x = b2x1; x < b2x2; x++)
        {
            for (int y = b2y1; y < b2y2; y++)
            {
                if (!isBlocked(x, y, tx1, ty1, tx2, ty2))
                    area++;
            }
        }

        out.println(area);
        out.close();
    }

    private static boolean isBlocked(int x, int y, int tx1, int ty1, int tx2, int ty2)
    {
        return (x >= tx1) && (x < tx2) && (y >= ty1) && (y < ty2);
    }

}
