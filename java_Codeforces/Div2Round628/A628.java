import java.io.*;

public class A628
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        for (int i = 0; i < t; i++)
            System.out.println(1 + " " + (Integer.parseInt(in.readLine()) - 1));
    }
}