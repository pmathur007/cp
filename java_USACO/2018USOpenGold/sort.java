import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class sort
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));

        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(in.readLine());
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted);

        int cur = 0;
        int max = 1;
        HashMap<Integer, Integer> unseen = new HashMap<>();
        HashMap<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < n; i++)
        {
            seen.put(sorted[i], seen.getOrDefault(sorted[i], 0) + 1);
            if (!seen.containsKey(arr[i]))
            {
                unseen.put(arr[i], unseen.getOrDefault(arr[i], 0) + 1);
                cur++;
            }
            if (unseen.containsKey(sorted[i]) && unseen.get(sorted[i]) > 0)
            {
                unseen.put(sorted[i], unseen.get(sorted[i]) - 1);
                cur--;
            }
            max = cur > max ? cur : max;
        }

        out.println(max);
        System.out.println(max);

        out.close();
    }
}