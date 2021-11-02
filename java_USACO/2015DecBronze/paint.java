import java.io.*;

public class paint
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("paint.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));

        String[] fjLine = in.readLine().split(" ");
        String[] bLine = in.readLine().split(" ");

        int a = Integer.parseInt(fjLine[0]);
        int b = Integer.parseInt(fjLine[1]);
        int c = Integer.parseInt(bLine[0]);
        int d = Integer.parseInt(bLine[1]);

        if (c >= b || a >= d)
            out.println((b - a) + (d - c));
        else
        {
            out.println(Math.max(b, d) - Math.min(a, c));
        }

        out.close();
    }
}