import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class B620
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] strings = new String[n];
        for (int i = 0; i < n; i++)
            strings[i] = in.readLine();

        boolean[] used = new boolean[n];
        LinkedList<String> palindrome = new LinkedList<>();
        int numRev = 0;
        String single = null;

        for (int i = 0; i < n; i++)
        {
            boolean hasRev = false;
            for (int j = i + 1; j < n; j++)
            {
                if (isReverse(strings[i], strings[j]))
                {
                    hasRev = true;
                    used[i] = used[j] = true;
                    numRev++;
                    palindrome.addFirst(strings[i]);
                    palindrome.add(strings[j]);
                }
            }
            if (!hasRev && single == null && isPalindrome(strings[i]))
                single = strings[i];
        }

        StringBuilder ansb = new StringBuilder();
        Iterator<String> itr = palindrome.listIterator();
        for (int i = 0; i < 2 * numRev; i++)
        {
            if (i == numRev && single != null)
                ansb.append(single);
            ansb.append(itr.next());
        }
        String ans = ansb.toString();
        if (numRev == 0 && single != null)
            ans = single;

        System.out.println(ans.length());
        System.out.println(ans);
    }

    private static boolean isPalindrome(String a)
    {
        int m = a.length();
        for (int i = 0; i < (m + 1) / 2; i++)
            if (a.charAt(i) != a.charAt(m - i - 1))
                return false;
        return true;
    }

    private static boolean isReverse(String a, String b)
    {
        int m = a.length();
        for (int i = 0; i < m; i++)
            if (a.charAt(i) != b.charAt(m - i  - 1))
                return false;
        return true;
    }
}