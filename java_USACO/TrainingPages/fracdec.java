/*
ID: pranavm7
LANG: JAVA
TASK: fracdec
PROG: fracdec
*/

import java.io.*;
import java.util.HashMap;

public class fracdec
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("fracdec.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));

        String[] line = in.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int d = Integer.parseInt(line[1]);

        StringBuilder dec = new StringBuilder();
        dec.append(n / d + ".");
        n = (n % d) * 10;
        if (n == 0)
            out.println(dec.toString() + "0");
        else
        {
            HashMap<Integer, Integer> seen = new HashMap<>();
            while (n != 0 && !seen.containsKey(n))
            {
                seen.put(n, dec.length());
                dec.append(n / d);
                n = (n % d) * 10;
            }
            String finalDec = dec.toString();
            if (n != 0)
            {
                int prev = seen.get(n);
                finalDec = finalDec.substring(0, prev) + "(" + finalDec.substring(prev) + ")";
            }

            while (finalDec.length() > 76)
            {
                out.println(finalDec.substring(0, 76));
                finalDec = finalDec.substring(76);
            }
            out.println(finalDec);
        }

        out.close();
    }
}