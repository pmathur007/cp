import java.io.*;

public class tamingG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("taming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));

        int n = Integer.parseInt(in.readLine());
        out.println();
    }
}

//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("taming.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
//
//        int n = Integer.parseInt(in.readLine());
//        StringTokenizer st = new StringTokenizer(in.readLine());
//
//        int[] log = new int[n];
//        for (int i = 0; i < n; i++)
//            log[i] = Integer.parseInt(st.nextToken());
//
//        int[] realLog = new int[n];
//        boolean[] breakout = new boolean[n];
//        int numDif = 0;
//        for (int i = 0; i < n; i++)
//        {
//            realLog[i] = i;
//            if (log[i] != i)
//                numDif++;
//        }
//        breakout[0] = true;
//        out.println(numDif);
//
//        for (int i = 1; i < n; i++)
//        {
//            int min = 0;
//            int minDif = 1000;
//            for (int j = 0; j < n; j++)
//            {
//                if (!breakout[j])
//                {
//                    int changeDif = 0;
//                    for (int k = j; k < n && !breakout[k]; k++)
//                    {
//                        if (log[k] != realLog[k] && log[k] == k - j)
//                            changeDif--;
//                        else if (log[k] == realLog[k] && log[k] != k - j)
//                            changeDif++;
//                    }
//                    if (numDif + changeDif < minDif)
//                    {
//                        minDif = numDif + changeDif;
//                        min = j;
//                    }
//                }
//            }
//            out.println(minDif);
//            numDif = minDif;
//            change(realLog, breakout, min);
//            breakout[min] = true;
//        }
//
//        out.close();
//    }
//
//    private static void change(int[] log, boolean[] breakout, int start)
//    {
//        for (int i = start; i < log.length && !breakout[i]; i++)
//            log[i] = i - start;
//    }
//
//    private static int countDif(int[] log, int[] newLog)
//    {
//        int numDif = 0;
//        for (int i = 0; i < log.length; i++)
//            if (log[i] != newLog[i])
//                numDif++;
//        return numDif;
//    }