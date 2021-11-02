import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class poetry
{
    private static int n, m, k;
    private static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("poetry.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        HashMap<Integer, LinkedList<Word>> rhymeWords = new HashMap<>();
        Word[] words = new Word[n];
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken()) - 1;
            words[i] = new Word(s, r);
            if (!rhymeWords.containsKey(r))
                rhymeWords.put(r, new LinkedList<>());
            rhymeWords.get(r).add(words[i]);
        }

        int[] numRhymes = new int[26];
        for (int i = 0; i < m; i++)
        {
            int index = in.readLine().charAt(0) - 65;
            numRhymes[index]++;
        }

        long[] dp = new long[k + 1];
        long[] r = new long[n];
        dp[0] = 1;

        for (int i = 0; i <= k; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (words[j].s + i > k)
                    continue;
                if (words[j].s + i == k)
                    r[words[j].r] = (r[words[j].r] + dp[i] + MOD) % MOD;
                dp[words[j].s + i] = (dp[words[j].s + i] + dp[i] + MOD) % MOD;
            }
        }

        long ans = 1;
        for (int i = 0; i < numRhymes.length; i++)
        {
            if (numRhymes[i] > 0)
            {
                long sum = 0;
                for (int j = 0; j < n; j++)
                {
                    if (r[j] != 0)
                        sum = (sum + fastExp(r[j], numRhymes[i]) + MOD) % MOD;
                }
                ans = (ans * sum + MOD) % MOD;
            }
        }

        out.println(ans);

        out.close();
    }

        private static long fastExp(long b, int e)
    {
        if (e == 0)
            return 1;
        if (e == 1)
            return (b + MOD) % MOD;
        long ans = fastExp(b, e / 2);
        ans = (ans * ans + MOD) % MOD;
        if (e % 2 == 1)
            ans = (ans * b + MOD) % MOD;
        return (ans + MOD) % MOD;
    }

    private static class Word
    {
        private int s, r;

        public Word(int s, int r)
        {
            this.s = s;
            this.r = r;
        }
    }
}

// SOLUTION 1 - FAILED

//    private static final long MOD = 1000000007L;
//    private static int n, m, k;
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("poetry.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
//
//        StringTokenizer st = new StringTokenizer(in.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        k = Integer.parseInt(st.nextToken());
//
//        LinkedList<Word> words = new LinkedList<>();
//        HashMap<Integer, LinkedList<Word>> rhymes = new HashMap<>();
//        for (int i = 0; i < n; i++)
//        {
//            st = new StringTokenizer(in.readLine());
//            int sy = Integer.parseInt(st.nextToken());
//            int rh = Integer.parseInt(st.nextToken());
//            words.add(new Word(sy, rh));
//            if (!rhymes.containsKey(rh))
//                rhymes.put(rh, new LinkedList<>());
//            rhymes.get(rh).add(new Word(sy, rh));
//        }
//
//        HashMap<Character, Integer> freqs = new HashMap<>();
//        for (int i = 0; i < m; i++)
//        {
//            char c = in.readLine().charAt(0);
//            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
//        }
//
//        long[] dp = new long[k + 1];
//        for (int i = 1; i <= k; i++)
//        {
//            for (Word w : words)
//            {
//                if (w.sy == i)
//                    dp[i]++;
//                else if (w.sy < i)
//                    dp[i] += dp[i - w.sy];
//            }
//        }
//
//        long ans = 1;
//        for (char c : freqs.keySet())
//        {
//            int e = freqs.get(c);
//            long schemePos = 0;
//            for (int rh : rhymes.keySet())
//            {
//                long rhymePos = 0;
//                for (Word w : rhymes.get(rh))
//                    rhymePos += dp[k - w.sy];
//                schemePos = (schemePos + fastExp(rhymePos, e) + MOD) % MOD;
//            }
//            ans = (ans * schemePos + MOD) % MOD;
//        }
//
//        System.out.println(ans);
//        out.println(ans);
//
//        out.close();
//    }
//
//    private static long fastExp(long b, int e)
//    {
//        if (e == 0)
//            return 1;
//        if (e == 1)
//            return (b + MOD) % MOD;
//        long ans = fastExp(b, e / 2);
//        ans = (ans * ans + MOD) % MOD;
//        if (e % 2 == 1)
//            ans = (ans * b + MOD) % MOD;
//        return (ans + MOD) % MOD;
//    }
//
//private static class Word
//{
//    private int sy, rh;
//    public Word(int sy, int rh)
//    {
//        this.sy = sy;
//        this.rh = rh;
//    }
//}