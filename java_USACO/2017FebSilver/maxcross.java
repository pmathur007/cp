import java.io.*;

public class maxcross
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);
        int b = Integer.parseInt(meta[2]);

        boolean[] broken = new boolean[n];
        for (int i = 0; i < b; i++)
        {
            broken[Integer.parseInt(in.readLine()) - 1] = true;
        }

        int curFix = 0;
        int curI = 0;
        while (curI < k)
        {
            if (broken[curI])
                curFix++;
            curI++;
        }
        int min = curFix;
        while (curI < n)
        {
            if (broken[curI - k])
                curFix--;
            if (broken[curI])
                curFix++;
            min = curFix < min ? curFix : min;
            curI++;
        }

        out.println(min);

        out.close();
    }
}