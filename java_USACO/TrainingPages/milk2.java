/*
ID: pranavm7
LANG: JAVA
TASK: milk2
PROG: milk2
*/

import java.io.*;
import java.util.StringTokenizer;

public class milk2
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int n = Integer.parseInt(in.readLine());
        int[][] timesOrig = new int[n][2];
        int start = 1000000;
        int end = 0;

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(in.readLine());

            timesOrig[i][0] = Integer.parseInt(st.nextToken());
            timesOrig[i][1] = Integer.parseInt(st.nextToken());

            if (timesOrig[i][0] < start)
                start = timesOrig[i][0];

            if (timesOrig[i][1] > end)
                end = timesOrig[i][1];
        }

        int[] times = new int[end - start];

        for (int[] interval : timesOrig)
        {
            for (int i = interval[0]; i < interval[1]; i++)
            {
                times[i - start] = 1;
            }
        }

        int milking = times[0];
        int maxMilking = 0;
        int currentMilking = 0;
        int maxIdle = 0;
        int currentIdle = 0;

        for (int i : times)
        {
            if (i == 1)
                currentMilking++;
            else
                currentIdle++;

            if (i != milking)
            {
                if (milking == 1)
                {
                    if (currentMilking > maxMilking)
                        maxMilking = currentMilking;
                    currentMilking = 0;
                    milking = 0;
                }
                else
                {
                    if (currentIdle > maxIdle)
                        maxIdle = currentIdle;
                    currentIdle = 0;
                    milking = 1;
                }
            }
        }
        if (currentMilking > maxMilking)
            maxMilking = currentMilking;
        if (currentIdle > maxIdle)
            maxIdle = currentIdle;

        out.println(maxMilking + " " + maxIdle);
        out.close();
    }
}

//1
//100 200

//    BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
//    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
//
//    int n = Integer.parseInt(in.readLine());
//    int[][] timeIntervals = new int[n][2];
//    int startTime = 1_000_000;
//    int endTime = 0;
//
//        for (int i = 0; i < n; i++)
//        {
//        StringTokenizer st = new StringTokenizer(in.readLine());
//
//        timeIntervals[i][0] = Integer.parseInt(st.nextToken());
//        timeIntervals[i][1] = Integer.parseInt(st.nextToken());
//
//        if (timeIntervals[i][0] < startTime)
//        startTime = timeIntervals[i][0];
//        if (timeIntervals[i][1] > endTime)
//        endTime = timeIntervals[i][1];
//        }
//
//        int[] combinedTimes = new int[endTime - startTime + 1];
//
//        for (int[] interval : timeIntervals)
//        for (int i = interval[0] - startTime; i <= interval[1] - startTime; i++)
//        combinedTimes[i] = 1;
//
//        int current = combinedTimes[0];
//        int currentMilkTime = 0;
//        int currentIdleTime = 0;
//        int maxMilkTime = 0;
//        int maxIdleTime = 0;
//
//        for (int isMilking : combinedTimes)
//        {
//        if (isMilking == current)
//        {
//        if (current == 1)
//        currentMilkTime++;
//        else
//        currentIdleTime++;
//        }
//        else
//        {
//        if (current == 0)
//        {
//        if (currentIdleTime > maxIdleTime)
//        maxIdleTime = currentIdleTime;
//        currentIdleTime = 0;
//        }
//        else
//        {
//        if (currentMilkTime > maxMilkTime)
//        maxMilkTime = currentMilkTime;
//        currentMilkTime = 0;
//        }
//        current = (current == 0 ? 1 : 0);
//        }
//        }
//
//        out.println(maxMilkTime + " " + (++maxIdleTime));
//        out.close();
