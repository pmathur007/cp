/*
ID: pranavm7
LANG: JAVA
TASK: runround
PROG: runround
*/

import java.io.*;

public class runround
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("runround.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

        int n = Integer.parseInt(in.readLine()) + 1;
        while (!isRunround("" + n))
        {
            n++;
        }

        out.println(n);

        out.close();
    }

    private static boolean isRunround(String s)
    {
        boolean[] visited = new boolean[s.length()];
        boolean[] digits = new boolean[10];
        int index = 0;

        for (int i = 0; i < visited.length; i++)
        {
            int newIndex = (index + Integer.parseInt(s.substring(index, index + 1))) % s.length();
            if (visited[newIndex] || digits[Integer.parseInt(s.substring(newIndex, newIndex + 1))])
                return false;

            visited[newIndex] = true;
            digits[Integer.parseInt(s.substring(newIndex, newIndex + 1))] = true;
            index = newIndex;
        }

        return true;
    }
}

// V1, FAIL
//        String s = in.readLine();
//
//        boolean[] visited = new boolean[s.length()];
//        String[] finalString = new String[s.length()];
//
//        int index = 0;
//        for (int i = 0; i < s.length(); i++)
//        {
//            int x = Integer.parseInt(s.substring(index, index + 1));
//            int newIndex = (index + x) % s.length();
//            visited[index] = true;
//
//            while (i < s.length() - 1 ? visited[newIndex] : newIndex != 0)
//            {
//                newIndex = (newIndex + 1) % s.length();
//                x++;
//            }
//
//            finalString[index] = "" + x;
//
//            index = newIndex;
//        }
//
//        for (String f : finalString)
//            out.print(f);
//        out.println();