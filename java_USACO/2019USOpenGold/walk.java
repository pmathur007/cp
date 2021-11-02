import java.io.*;

public class walk
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("walk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));

        String[] meta = in.readLine().split(" ");
        long n = Long.parseLong(meta[0]);
        long k = Long.parseLong(meta[1]);

        long ans = (2019201913L * (k-1) + 2019201949L * n) % 2019201997L;
        out.println(ans);

        out.close();
    }
}

//        long minX = 1;
//        long minY = 1;
//        long min = 2019201997;
//        for (long x = 1; x <= 7500; x++)
//        {
//            for (long y = 1; y <= 7500; y++)
//            {
//                if ((2019201913*x+2019201949*y) % 2019201997 < min)
//                {
//                    min = (2019201913*x+2019201949*y) % 2019201997;
//                    minX = x;
//                    minY = y;
//                }
//            }
//        }
//        System.out.println(min);
//        System.out.println(minX + " " + minY);