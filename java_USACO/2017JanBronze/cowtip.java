import java.io.*;

public class cowtip
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));

        int n = Integer.parseInt(in.readLine());

        int[][] field = new int[n][n];
        String[] line;
        for (int i = 0; i < n; i++)
        {
            line = in.readLine().split("");
            for (int j = 0; j < n; j++)
            {
                field[i][j] = Integer.parseInt(line[j]);
            }
        }

        int numTimesApplied = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            for (int j = i; j >= 0; j--)
            {
                if (field[i][j] == 1)
                {
                    apply(field, n, i, j);
                    numTimesApplied++;
                }

                if (field[j][i] == 1)
                {
                    apply(field, n, j, i);
                    numTimesApplied++;
                }
            }
        }

        out.println(numTimesApplied);
        out.close();
    }

    private static void apply(int[][] field, int n, int i, int j)
    {
        for (int r = 0; r < i + 1; r++)
        {
            for (int c = 0; c < j + 1; c++)
            {
                field[r][c] = field[r][c] == 1 ? 0 : 1;
            }
        }
    }
}
