import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B577
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int l = n - 2, r = n - 1;
        while (l >= 0 && r >= 0)
        {
            if (arr[l] < arr[r])
            {
                arr[r] -= arr[l];
                arr[l] = 0;
                l--;
            }
            else if (arr[l] > arr[r])
            {
                arr[l] -= arr[r];
                arr[r] = 0;
                r = l;
                l--;
            }
            else
            {
                arr[l] = 0;
                arr[r] = 0;
                r = l - 1;
                l = l - 2;
            }
        }

        boolean zeros = true;
        for (int i = 0; i < n; i++)
        {
            if (arr[i] != 0)
            {
                zeros = false;
                break;
            }
        }
        System.out.println(zeros ? "YES" : "NO");
    }
}

//        int l = 0, r = n - 1;
//        while (l < r)
//        {
//            if (arr[l] < arr[r])
//            {
//                arr[r] -= arr[l];
//                arr[l] = 0;
//                l++;
//            }
//            else if (arr[l] > arr[r])
//            {
//                arr[l] -= arr[r];
//                arr[r] = 0;
//                r--;
//            }
//            else
//            {
//                arr[l] = 0;
//                arr[r] = 0;
//                l++;
//                r--;
//            }
//        }
//
//        if (arr[l] == 0 && arr[r] == 0)
//            System.out.println("YES");
//        else
//            System.out.println("NO");