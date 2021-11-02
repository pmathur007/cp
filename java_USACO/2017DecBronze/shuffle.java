import java.io.*;
import java.util.StringTokenizer;

public class shuffle
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(in.readLine());
        int[] shuffle = new int[n];
        int[] order = new int[n];
        int[] copyOrder = new int[n];

        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            shuffle[Integer.parseInt(st.nextToken()) - 1] = i;
        }

        st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < n; i++)
        {
            order[i] = Integer.parseInt(st.nextToken());
        }

        for (int i : shuffle)
            System.out.println(i);

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < n; j++)
                copyOrder[shuffle[j]] = order[j];

            for (int j = 0; j < n; j++)
                order[j] = copyOrder[j];
        }

        for (int i = 0; i < n; i++)
        {
            out.println(order[i]);
        }

        out.close();
    }
}
