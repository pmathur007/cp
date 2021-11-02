import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class reststops
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));

        String[] meta = in.readLine().split(" " );
        int l = Integer.parseInt(meta[0]);
        int n = Integer.parseInt(meta[1]);
        int rf = Integer.parseInt(meta[2]);
        int rb = Integer.parseInt(meta[3]);

        Stop[] allStops = new Stop[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            allStops[i] = new Stop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(allStops, (s1, s2) -> s2.loc - s1.loc);

        long max = 0;
        long last = -1;
        LinkedList<Stop> stops = new LinkedList<>();

        for (int i = 0; i < n; i++)
        {
            if (allStops[i].t > max)
            {
                stops.addFirst(allStops[i]);
                max = allStops[i].t;
            }
        }

        long total = 0;
        last = 0;
        long ftime = 0;
        long btime = 0;

        for (Stop s : stops)
        {
            ftime += (s.loc - last) * rf;
            btime += (s.loc - last) * rb;
            total += (ftime - btime) * s.t;
            btime = ftime;
            last = s.loc;
        }

        out.println(total);

        out.close();
    }

    private static long change(Stop[] allStops, long total, int last, int cur, int rf, int rb)
    {
        if (last == -1)
        {
            total = (allStops[cur].loc * rf - allStops[cur].loc * rb) * allStops[cur].t;
        }
        else
        {
            long lastGain = (allStops[last].loc * rf - allStops[last].loc * rb) * allStops[last].t;
            long curGain = (allStops[cur].loc * rf - allStops[cur].loc * rb) * allStops[cur].t;
            long lastGainWCur = ((allStops[last].loc - allStops[cur].loc) * rf - (allStops[last].loc - allStops[cur].loc) * rb) * allStops[last].t;
            total = total - lastGain + curGain + lastGainWCur;
        }
        return total;
    }

    private static class Stop
    {
        private int loc, t;

        public Stop(int loc, int t)
        {
            this.loc = loc;
            this.t = t;
        }
    }
}