import java.io.*;

public class mixmilk
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));

        String[] line = in.readLine().split(" ");
        int aCap = Integer.parseInt(line[0]);
        int aMilk = Integer.parseInt(line[1]);

        line = in.readLine().split(" ");
        int bCap = Integer.parseInt(line[0]);
        int bMilk = Integer.parseInt(line[1]);

        line = in.readLine().split(" ");
        int cCap = Integer.parseInt(line[0]);
        int cMilk = Integer.parseInt(line[1]);

        for (int i = 0; i < 33; i++)
        {
            // pour into b
            int bAvail = bCap - bMilk;
            if (aMilk > bAvail)
            {
                aMilk -= bAvail;
                bMilk = bCap;
            }
            else
            {
                bMilk += aMilk;
                aMilk = 0;
            }

            // pour into c
            int cAvail = cCap - cMilk;
            if (bMilk > cAvail)
            {
                bMilk -= cAvail;
                cMilk = cCap;
            }
            else
            {
                cMilk += bMilk;
                bMilk = 0;
            }

            // pour into a
            int aAvail = aCap - aMilk;
            if (cMilk > aAvail)
            {
                cMilk -= aAvail;
                aMilk = aCap;
            }
            else
            {
                aMilk += cMilk;
                cMilk = 0;
            }
        }

        int bAvail = bCap - bMilk;
        if (aMilk > bAvail)
        {
            aMilk -= bAvail;
            bMilk = bCap;
        }
        else
        {
            bMilk += aMilk;
            aMilk = 0;
        }

        out.println(aMilk);
        out.println(bMilk);
        out.println(cMilk);

        out.close();
    }
}