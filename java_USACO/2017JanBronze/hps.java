import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class hps
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("hps.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        Map<String, String> hps = new HashMap<>();
        hps.put("1", "hoof");
        hps.put("2", "paper");
        hps.put("3", "scissors");

        Map<String, String> hsp = new HashMap<>();
        hsp.put("1", "hoof");
        hsp.put("2", "scissors");
        hsp.put("3", "paper");

        Map<String, String> phs = new HashMap<>();
        phs.put("1", "paper");
        phs.put("2", "hoof");
        phs.put("3", "scissors");

        Map<String, String> psh = new HashMap<>();
        psh.put("1", "paper");
        psh.put("2", "scissors");
        psh.put("3", "hoof");

        Map<String, String> sph = new HashMap<>();
        sph.put("1", "scissors");
        sph.put("2", "paper");
        sph.put("3", "hoof");

        Map<String, String> shp = new HashMap<>();
        shp.put("1", "scissors");
        shp.put("2", "hoof");
        shp.put("3", "paper");

        int n = Integer.parseInt(in.readLine());
        String[][] games = new String[n][2];

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split( " ");
            games[i][0] = line[0];
            games[i][1] = line[1];
        }

        int maxWon = 0;
        int numWon;

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(hps, game);
        }
        maxWon = Math.max(numWon, maxWon);

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(hsp, game);
        }
        maxWon = Math.max(numWon, maxWon);

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(phs, game);
        }
        maxWon = Math.max(numWon, maxWon);

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(psh, game);
        }
        maxWon = Math.max(numWon, maxWon);

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(sph, game);
        }
        maxWon = Math.max(numWon, maxWon);

        numWon = 0;
        for (String[] game : games)
        {
            numWon += p1Winner(psh, game);
        }
        maxWon = Math.max(numWon, maxWon);

        out.println(maxWon);
        out.close();
    }

    private static int p1Winner(Map<String, String> assignment, String[] game)
    {
        String p1 = assignment.get(game[0]);
        String p2 = assignment.get(game[1]);

        if (p1.equals("hoof") && p2.equals("scissors"))
            return 1;
        if (p1.equals("scissors") && p2.equals("paper"))
            return 1;
        if (p1.equals("paper") && p2.equals("hoof"))
            return 1;
        return 0;
    }
}
