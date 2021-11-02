import java.io.*;

public class cowcode
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));

        String[] line = in.readLine().split(" " );
        String s = line[0];
        long n = Integer.parseInt(line[1]);

        out.println(charAtN(s, n));

        out.close();
    }

    private static char charAtN(String s, long n)
    {
        int len = s.length();
        int k = 0;

        while (n > len)
        {
            len *= 2;
            k++;
        }

        for (int i = k; i > 0; i--)
        {
            len = len / 2;
            if (n == len + 1)
                n = len;
            else
                n = (n - 1) % len;
        }

        return s.charAt((int) (n - 1));
    }
}