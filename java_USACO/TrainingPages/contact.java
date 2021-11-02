/*
ID: pranavm7
LANG: JAVA
TASK: contact
PROG: contact
*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class contact
{
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new BufferedReader(new FileReader("contact.in")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));

        String[] meta = in.nextLine().split(" ");
        int a = Integer.parseInt(meta[0]);
        int b = Integer.parseInt(meta[1]);
        int n = Integer.parseInt(meta[2]);

        StringBuilder messageBuild = new StringBuilder();
        while (in.hasNextLine())
            messageBuild.append(in.nextLine());
        String message = messageBuild.toString();

        String toAdd;
        TreeMap<String, Integer> patterns = new TreeMap<>();
        for (int i = 0; i < message.length(); i++)
        {
            for (int j = a; j <= b; j++)
            {
                if (i + j <= message.length())
                {
                    toAdd = message.substring(i, i + j);
                    if (!patterns.containsKey(toAdd))
                        patterns.put(toAdd, 0);
                    patterns.put(toAdd, patterns.get(toAdd) + 1);
                }
            }
        }

        Pattern[] sortedPatterns = new Pattern[patterns.keySet().size()];
        int i = 0;
        for (String s : patterns.keySet())
        {
            sortedPatterns[i] = new Pattern(s, patterns.get(s));
            i++;
        }
        Arrays.sort(sortedPatterns);

        int last = 0;
        int count = 0;
        for (Pattern p : sortedPatterns)
        {
            if (n >= 0)
            {
                if (p.f != last)
                {
                    n--;
                    if (n < 0)
                        break;
                    if (last != 0)
                        out.println();
                    out.println(p.f);
                    out.print(p.s);
                    last = p.f;
                    count = 1;
                }
                else
                {
                    if (count == 6)
                    {
                        out.println();
                        out.print(p.s);
                        count = 1;
                    }
                    else
                    {
                        out.print(" " + p.s);
                        count++;
                    }
                }
            }
            else
                break;
        }

        out.println();
        out.close();
    }

    private static class Pattern implements Comparable<Pattern>
    {
        private String s;
        private int f;

        public Pattern(String s, int f)
        {
            this.s = s;
            this.f = f;
        }

        @Override
        public int hashCode()
        {
            return s.hashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            return this.s.equals(((Pattern) obj).s);
        }

        @Override
        public int compareTo(Pattern o)
        {
            if (o.f == this.f)
            {
                if (this.s.length() != o.s.length())
                    return this.s.length() - o.s.length();
                return this.s.compareTo(o.s);
            }
            return o.f - this.f;
        }
    }
}