import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pairup
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));

        int n = Integer.parseInt(in.readLine());

        MilkOutput[] outputs = new MilkOutput[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            outputs[i] = new MilkOutput(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(outputs);

        int low = 0;
        int high = n - 1;
        int max = 0;
        while (low < high)
        {
            max = Math.max(max, outputs[low].t + outputs[high].t);
            if (outputs[low].f == outputs[high].f)
            {
                low++;
                high--;
            }
            else if (outputs[low].f < outputs[high].f)
            {
                outputs[high].f = outputs[high].f - outputs[low].f;
                low++;
            }
            else
            {
                outputs[low].f = outputs[low].f - outputs[high].f;
                high--;
            }
        }
        out.println(max);
        out.close();
    }

    private static class MilkOutput implements Comparable<MilkOutput>
    {
        int f, t;

        public MilkOutput(int f, int t)
        {
            this.f = f;
            this.t = t;
        }

        @Override
        public int compareTo(MilkOutput o)
        {
            return this.t - o.t;
        }
    }
}