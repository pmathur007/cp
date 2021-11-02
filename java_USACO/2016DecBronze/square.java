import java.io.*;

public class square
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("square.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));

        String[] rec1 = in.readLine().split(" ");
        int x1 = Integer.parseInt(rec1[0]);
        int y1 = Integer.parseInt(rec1[1]);
        int x2 = Integer.parseInt(rec1[2]);
        int y2 = Integer.parseInt(rec1[3]);

        String[] rec2 = in.readLine().split(" ");
        int x3 = Integer.parseInt(rec2[0]);
        int y3 = Integer.parseInt(rec2[1]);
        int x4 = Integer.parseInt(rec2[2]);
        int y4 = Integer.parseInt(rec2[3]);

        int w = Math.abs(Math.max(x2, x4) - Math.min(x1, x3));
        int h = Math.abs(Math.max(y2, y4) - Math.min(y1, y3));

        int s = Math.max(w, h);
        out.println(s * s);

        out.close();
    }
}
