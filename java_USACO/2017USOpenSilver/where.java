import java.io.*;
import java.util.HashSet;

public class where
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("where.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));

        int n = Integer.parseInt(in.readLine());

        char[][] farm = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            String line = in.readLine();
            for (int j = 0; j < n; j++)
                farm[i][j] = line.charAt(j);
        }

        HashSet<PCL> pcls = new HashSet<>();

        for (int r1 = 0; r1 < n; r1++)
        {
            for (int c1 = 0; c1 < n; c1++)
            {
                for (int r2 = r1; r2 < n; r2++)
                {
                    for (int c2 = c1; c2 < n; c2++)
                    {
                        if (isPCL(farm, r1, c1, r2, c2))
                            pcls.add(new PCL(r1, c1, r2, c2));
                    }
                }
            }
        }

        out.close();
    }

    private static boolean isPCL(char[][] farm, int r1, int c1, int r2, int c2)
    {
        HashSet<Character> distinct = new HashSet<>();
        for (int i = r1; i <= r2; i++)
        {
            for (int j = c1; j <= c2; j++)
            {
                if (distinct.size() < 2)
                    distinct.add(farm[i][j]);
                else if (!distinct.contains(farm[i][j]))
                    return false;
            }
        }

        boolean[][] visited = new boolean[farm.length][farm.length];
        int numCompartments = 0;
        for (int i = r1; i <= r2; i++)
        {
            for (int j = c1; j <= c2; j++)
            {
                if (!visited[i][j])
                {
                    numCompartments++;
                    if (numCompartments > 3);
                }
            }
        }
        return false;
    }

    private static class PCL
    {
        int r1, c1, r2, c2;

        public PCL(int r1, int c1, int r2, int c2)
        {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
}