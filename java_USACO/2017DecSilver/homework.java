import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class homework
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("homework.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));

        int n = Integer.parseInt(in.readLine());
        double[] scores = new double[n];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        double[] aveScores = new double[n];
        double min = 10001;
        double sum = 0;
        double maxScore = 0;

        for (int i = n - 1; i >= 0; i--)
        {
            sum += scores[i];

            if (scores[i] < min)
            {
                sum = i == n - 1 ? 0 : sum + min - scores[i];
                min = scores[i];
            }

            if (i == n - 1)
                aveScores[i] = -1;
            else
            {
                aveScores[i] = sum / (n - i - 1);
            }

            if (aveScores[i] > maxScore)
            {
                maxScore = aveScores[i];
            }
        }

        ArrayList<Integer> maxKs = new ArrayList<>();
        for (int i = 1; i <= n - 2; i++)
        {
            if (aveScores[i] == maxScore)
                maxKs.add(i);
        }

        for (int i = 0; i < maxKs.size() - 1; i++)
        {
            out.println(maxKs.get(i) + " ");
        }
        out.println(maxKs.get(maxKs.size() - 1));

        out.close();
    }
}
