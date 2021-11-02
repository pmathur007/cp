import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("convention.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

        String[] meta = in.readLine().split(" ");

        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);
        int c = Integer.parseInt(meta[2]);

        int[] arrivals = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            arrivals[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrivals);

        out.println(searchForT(arrivals, m, c, 0, 1000000000));

        out.close();
    }

    private static int searchForT(int[] arrivals, int m, int c, int low, int high)
    {
        if (low == high)
            return low;
        if (low + 1 == high)
        {
            if (timePossible(arrivals, m, c, low))
                return low;
            return high;
        }
        int mid = (low + high) / 2;
        if (timePossible(arrivals, m, c, mid))
            return searchForT(arrivals, m, c, low, mid);
        return searchForT(arrivals, m, c, mid + 1, high);
    }


    private static boolean timePossible(int[] arrivals, int m, int c, int t)
    {
        int buses = 1;
        int firstTime = arrivals[0];
        int firstIndex = 0;

        for (int i = 0; i < arrivals.length; i++)
        {
            if (arrivals[i] - firstTime > t || i - firstIndex + 1 > c)
            {
                buses += 1;
                firstTime = arrivals[i];
                firstIndex = i;
            }
        }

        return buses <= m;
    }
}

// IN CONTEST SOLUTION, FAILED
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("convention.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
//
//        String[] meta = in.readLine().split(" ");
//        int n = Integer.parseInt(meta[0]);
//        int m = Integer.parseInt(meta[1]);
//        int c = Integer.parseInt(meta[2]);
//
//        int[] arrivalTimes = new int[n + 1];
//
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        for (int i = 1; i <= n; i++)
//        {
//            arrivalTimes[i] = Integer.parseInt(st.nextToken());
//        }
//
//        Arrays.sort(arrivalTimes);
//
//        boolean[] buses = new boolean[n + 1];
//        buses[n] = true;
//
//        int min = dfsForMin(arrivalTimes, buses,6, m - 1, c);
//        out.println(min);
//
//        out.close();
//    }
//
//    private static int dfsForMin(int[] arrivalTimes, boolean[] buses, int lastBus, int m, int c)
//    {
//        if (m == 0)
//        {
//            int maxWaiting = 0;
//            int currentStart = arrivalTimes[1];
//            for (int i = 1; i < arrivalTimes.length; i++)
//            {
//                if (buses[i])
//                {
//                    maxWaiting = Math.max(maxWaiting, arrivalTimes[i] - currentStart);
//                    if (i != arrivalTimes.length - 1)
//                        currentStart = arrivalTimes[i + 1];
//                }
//            }
//            return maxWaiting;
//        }
//
//        int minMaxWaiting = Integer.MAX_VALUE;
//        int start = m * c;
//        if (start >= lastBus)
//        {
//            start = lastBus - 1;
//        }
//        for (int i = start; lastBus - i <= c; i--)
//        {
//            buses[start] = true;
//            minMaxWaiting = Math.min(minMaxWaiting, dfsForMin(arrivalTimes, buses, start, m - 1, c));
//            buses[start] = false;
//        }
//
//        return minMaxWaiting;
//    }