import java.io.*;
import java.util.*;

public class measurement
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));

        Map<String, Integer> cows = new HashMap<>();
        cows.put("Bessie", 7);
        cows.put("Elsie", 7);
        cows.put("Mildred", 7);

        int n = Integer.parseInt(in.readLine());

        Observation[] measurements = new Observation[n];
        for (int i = 0; i < n; i++)
        {
            measurements[i] = new Observation(in.readLine());
        }
        Arrays.sort(measurements);

        Map<String, Boolean> leaderboard = new HashMap<>();
        leaderboard.put("Bessie", true);
        leaderboard.put("Elsie", true);
        leaderboard.put("Mildred", true);

        int numChanges = 0;

        for (Observation m : measurements)
        {
            cows.put(m.getCow(), cows.get(m.getCow()) + m.getAdjustment());
            Map<String, Boolean> newLeaderboard = adjustLeaderboard(cows);
            if ((leaderboard.get("Bessie") != newLeaderboard.get("Bessie")) || (leaderboard.get("Elsie") != newLeaderboard.get("Elsie")) || (leaderboard.get("Mildred") != newLeaderboard.get("Mildred")))
                numChanges++;
            leaderboard = newLeaderboard;
        }

        out.println(numChanges);
        out.close();
    }

    private static Map<String, Boolean> adjustLeaderboard(Map<String, Integer> cows)
    {
        int max = Math.max(cows.get("Bessie"), Math.max(cows.get("Elsie"), cows.get("Mildred")));

        Map<String, Boolean> newLeaderboard = new HashMap<>();
        newLeaderboard.put("Bessie", cows.get("Bessie") == max);
        newLeaderboard.put("Elsie", cows.get("Elsie") == max);
        newLeaderboard.put("Mildred", cows.get("Mildred") == max);

        return newLeaderboard;
    }

}

class Observation implements Comparable<Observation>
{
    private int day;
    private String cow;
    private int adjustment;

    public Observation(String measurement)
    {
        StringTokenizer st = new StringTokenizer(measurement, " ");
        this.day = Integer.parseInt(st.nextToken());
        this.cow = st.nextToken();
        this.adjustment = Integer.parseInt(st.nextToken());
    }

    public int compareTo(Observation o)
    {
        return this.day - o.day;
    }

    public String getCow()
    {
        return cow;
    }

    public int getAdjustment()
    {
        return adjustment;
    }
}

