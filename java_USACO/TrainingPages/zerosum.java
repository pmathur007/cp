/*
ID: pranavm7
LANG: JAVA
TASK: zerosum
PROG: zerosum
*/

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class zerosum
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));

        int n = Integer.parseInt(in.readLine());

        LinkedList<String> exps = new LinkedList<>();
        gen(exps, "1", n, 2);

        Collections.sort(exps);
        for (String s : exps)
            out.println(s);

        out.close();
    }

    private static void gen(LinkedList<String> exps, String s, int n, int cur)
    {
        if (cur > n)
        {
            if (eval(s) == 0)
                exps.add(s);
        }
        else
        {
            gen(exps, s + "+" + cur, n, cur + 1);
            gen(exps, s + "-" + cur, n, cur + 1);
            gen(exps, s + " " + cur, n, cur + 1);
        }
    }

    private static int eval(String s)
    {
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        String cur = "";

        for (int i = s.length() - 1; i >= 0; i--)
        {
            char c = s.charAt(i);
            if (c == '+' || c == '-')
            {
                ops.push(c);
                nums.push(Integer.parseInt(cur));
                cur = "";
            }
            else if (c != ' ')
                cur = c + cur;
        }
        nums.push(Integer.parseInt(cur));

        while (!ops.empty())
        {
            char op = ops.pop();
            int a = nums.pop();
            int b = nums.pop();
            nums.push(a + (op == '+' ? 1 : -1) * b);
        }

        return nums.pop();
    }
}