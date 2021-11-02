import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C577
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        if (n == 1)
            System.out.println(arr[0] + k);
        else
        {
            int mid = n / 2;
            int right = mid + 1;
            while (right < n)
            {
                if (k > (arr[right] - arr[mid]) * (right - mid))
                {
                    k -= (arr[right] - arr[mid]) * (right - mid);
                    arr[mid] = arr[right];
                    right++;
                }
                else
                {
                    System.out.println(arr[mid] + (k / (right - mid)));
                    return;
                }
            }
            System.out.println(arr[mid] + (k / (n - mid)));
        }
    }
}