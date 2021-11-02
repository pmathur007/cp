import java.io.*;

public class teleport
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));

        String[] input = in.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int x = Integer.parseInt(input[2]);
        int y = Integer.parseInt(input[3]);

        int noTel = Math.abs(a - b);
        int useX = Math.abs(a - x) + Math.abs(b - y);
        int useY = Math.abs(a - y) + Math.abs(b - x);

        out.println(Math.min(noTel, Math.min(useX, useY)));

        out.close();
    }
}
