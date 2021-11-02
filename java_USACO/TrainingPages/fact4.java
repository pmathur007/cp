/*
ID: pranavm7
LANG: JAVA
TASK: fact4
PROG: fact4
*/

import java.io.*;

public class fact4
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

        int n = Integer.parseInt(in.readLine());
        int ans = 1;
        for (int i = 1; i <= n; i++)
        {
            ans *= i;
            while (ans % 10 == 0)
                ans /= 10;
            ans = ans % 10000;
        }

        out.println(ans % 10);
        out.close();
    }
}