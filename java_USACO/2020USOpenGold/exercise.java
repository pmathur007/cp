import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class exercise
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("exercise.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        HashSet<Integer>[] canGet = new HashSet[N];

        for (int i = 0; i < N; i++)
        {
            canGet[i] = new HashSet<>();

        }

        out.close();
    }
}