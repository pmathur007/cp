import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class backforth
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("backforth.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));

        ArrayList<Integer> b1 = new ArrayList<>();
        ArrayList<Integer> b2 = new ArrayList<>();

        String[] line1 = in.readLine().split(" ");
        String[] line2 = in.readLine().split(" ");
        for (int i = 0; i < 10; i++)
        {
            b1.add(Integer.parseInt(line1[i]));
            b2.add(Integer.parseInt(line2[i]));
        }

        Set<Integer> finalValues = new HashSet<>();
        generate(b1, b2, finalValues, 1000, 1000, 1);

        for (Integer i : finalValues)
            System.out.println(i);

        out.print(finalValues.size());

        out.close();
    }

    private static void generate(ArrayList<Integer> b1, ArrayList<Integer> b2, Set<Integer> finalValues, int milk1, int milk2, int n)
    {
        if (n == 5)
        {
            finalValues.add(milk1);
        }
        else
        {
            if (n == 1 || n == 3)
            {
                for (int i = 0; i < b1.size(); i++)
                {
                    int bucket = b1.get(i);
                    System.out.println("copy2");
                    ArrayList<Integer> b1Copy = new ArrayList<>(b1);
                    ArrayList<Integer> b2Copy = new ArrayList<>(b2);
                    b1Copy.remove(i);
                    b2Copy.add(bucket);
                    generate(b1Copy, b2Copy, finalValues, milk1 - bucket, milk2 + bucket, n + 1);
                }
            }
            else if (n == 2 || n == 4)
            {
                for (int i = 0; i < b2.size(); i++)
                {
                    int bucket = b2.get(i);
                    System.out.println("copy2");
                    ArrayList<Integer> b1Copy = new ArrayList<>(b1);
                    ArrayList<Integer> b2Copy = new ArrayList<>(b2);
                    b2Copy.remove(i);
                    b1Copy.add(bucket);
                    generate(b1Copy, b2Copy, finalValues, milk1 + bucket, milk2 - bucket, n + 1);
                }
            }
        }
    }
}