import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class notlast
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));

        int n = Integer.parseInt(in.readLine());
        Map<String, Integer> cows = new HashMap<>();

        cows.put("Bessie", 0);
        cows.put("Elsie", 0);
        cows.put("Daisy", 0);
        cows.put("Gertie", 0);
        cows.put("Annabelle", 0);
        cows.put("Maggie", 0);
        cows.put("Henrietta", 0);

        for (int i = 0; i < n; i++)
        {
            String[] line = in.readLine().split(" ");
            cows.put(line[0], cows.get(line[0]) + Integer.parseInt(line[1]));
        }

        int minMilk = Integer.MAX_VALUE;
        for (int i : cows.values())
        {
            if (i < minMilk)
                minMilk = i;
        }

        String secondCow = "";
        int secondCowMilk = Integer.MAX_VALUE;

        for (String name : cows.keySet())
        {
            if (cows.get(name) > minMilk && cows.get(name) < secondCowMilk)
            {
                secondCow = name;
                secondCowMilk = cows.get(name);
            }
        }

        int numCowsWithMin = 0;
        for (String name : cows.keySet())
        {
            if (cows.get(name) == secondCowMilk)
                numCowsWithMin++;
        }

        if (numCowsWithMin == 0 || numCowsWithMin > 1)
            out.println("Tie");
        else
            out.println(secondCow);

        out.close();
    }
}
