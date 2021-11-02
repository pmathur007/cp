import java.io.*;

public class badmilk
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("badmilk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);
        int d = Integer.parseInt(meta[2]);
        int s = Integer.parseInt(meta[3]);

        int[][] drinks = new int[m][n];

        for (int i = 0; i < d; i++)
        {
            String[] input = in.readLine().split(" ");
            int person = Integer.parseInt(input[0]) - 1;
            int milk = Integer.parseInt(input[1]) - 1;
            int time = Integer.parseInt(input[2]);

            if (drinks[milk][person] > 0)
                drinks[milk][person] = Math.min(drinks[milk][person], time);
            else
                drinks[milk][person] = time;
        }

        int[] sickPeople = new int[s];
        int[] sickTimes = new int[s];
        for (int i = 0; i < s; i++)
        {
            String[] input = in.readLine().split(" ");
            int person = Integer.parseInt(input[0]) - 1;
            int time = Integer.parseInt(input[1]);

            sickPeople[i] = person;
            sickTimes[i] = time;
        }

        boolean[] potCon = new boolean[m];
        for (int i = 0; i < m; i++)
        {
            boolean potSick = true;
            for (int j = 0; j < s; j++)
            {
                if (drinks[i][sickPeople[j]] == 0 || drinks[i][sickPeople[j]] >= sickTimes[j])
                {
                    potSick = false;
                    break;
                }
            }
            potCon[i] = potSick;
        }

        boolean[] treated = new boolean[n];
        int numTreated = 0;
        for (int i = 0; i < m; i++)
        {
            if (potCon[i])
            {
                for (int j = 0; j < n; j++)
                {
                    if (drinks[i][j] > 0)
                    {
                        numTreated += treated[j] ? 0 : 1;
                        treated[j] = true;
                    }
                }
            }
        }

        out.println(numTreated);

        out.close();
    }
}