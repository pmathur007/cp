import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C638
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;

        for (int t = 0; t < T; t++)
        {
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] freq = new int[26];
            String s = in.readLine();
            for (int i = 0; i < N; i++)
                freq[s.charAt(i) - 97]++;

            char lastLetter = 'a';
            for (char i = 'a'; i <= 'z'; i++)
                if (freq[i - 97] > 0)
                    lastLetter = i;

            String[] groups = new String[K];
            char fi = 'a';
            while (fi <= 'z' && freq[fi - 97] == 0)
                fi++;
            for (int i = 0; i < K; i++)
            {
                groups[i] = "" + fi;
                freq[fi - 97]--;
                while (fi <= 'z' && freq[fi - 97] == 0)
                    fi++;
            }

            if (!groups[0].equals(groups[K - 1]))
                System.out.println(groups[K - 1]);
            else if (fi != lastLetter)
            {
                StringBuilder ans = new StringBuilder(groups[0]);
                while (fi <= 'z' && freq[fi - 97] == 0)
                    fi++;
                while (fi <= 'z')
                {
                    ans.append(fi);
                    freq[fi - 97]--;
                    while (fi <= 'z' && freq[fi - 97] == 0)
                        fi++;
                }
                System.out.println(ans.toString());
            }
            else
            {
                StringBuilder ans = new StringBuilder(groups[0]);
                for (int i = 0; i < freq[fi - 97] / K + (freq[fi - 97] % K == 0 ? 0 : 1); i++)
                    ans.append(fi);
                System.out.println(ans.toString());
            }
        }
    }
}