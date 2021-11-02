import java.io.*;

public class outofplace
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("outofplace.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("outofplace.out")));

        int n = Integer.parseInt(in.readLine());
        int[] cows = new int[n];
        for (int i = 0; i < n; i++)
        {
            cows[i] = Integer.parseInt(in.readLine());
        }

        int bessie = -1;
        if (cows[0] > cows[1])
            bessie = 0;
        else if (cows[n - 2] > cows[n - 1])
            bessie = n - 1;
        else
        {
            for (int i = 1; i < n - 1; i++)
            {
                if (cows[i] < cows[i - 1] || cows[i] > cows[i + 1])
                {
                    bessie = i;
                    break;
                }
            }
        }

        if (bessie == -1)
            out.println(0);
        else
        {
            int correctLoc = -1;
            if (cows[bessie] < cows[0])
                correctLoc = 0;
            else if (cows[bessie] > cows[n - 1])
                correctLoc = n - 1;
            else
            {
                for (int i = 0; i < n - 1; i++)
                {
                    if (i + 1 != bessie && cows[i] <= cows[bessie] && cows[bessie] <= cows[i + 1])
                    {
                        correctLoc = i + 1;
                        break;
                    }
                }
            }

            int swaps = 0;
            int current = -1;
            int step = correctLoc < bessie ? 1 : -1;
            for (int i = correctLoc; i != bessie; i += step)
            {
                if (cows[i] != current)
                {
                    current = cows[i];
                    swaps++;
                }
            }

            out.println(swaps);
//            int swaps = 1;
//            int current;
//            if (bessie == 0 || cows[bessie] > cows[bessie - 1])
//            {
//                current = cows[bessie + 1];
//                for (int i = bessie + 2; i < n && cows[i] < cows[bessie]; i++)
//                {
//                    if (cows[i] != current)
//                    {
//                        current = cows[i];
//                        swaps++;
//                    }
//                }
//            }
//            else
//            {
//                current = cows[bessie - 1];
//                for (int i = bessie - 2; i >= 0 && cows[i] > cows[bessie]; i--)
//                {
//                    if (cows[i] != current)
//                    {
//                        current = cows[i];
//                        swaps++;
//                    }
//                }
//            }
//            out.println(swaps);
        }

        out.close();
    }
}
