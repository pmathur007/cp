import java.io.*;

public class D628
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(in.readLine());
            int min = (int) (Math.log(N) / Math.log(2));
            System.out.println(min);
        }


    }
}