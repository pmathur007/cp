import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

public class dishes
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("dishes.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dishes.out")));

        int n = Integer.parseInt(in.readLine());
        LinkedList<LinkedList<Integer>> soapy = new LinkedList<>();
        Stack<Integer> clean = new Stack<>();
        int[] loc = new int[n + 1];

        for (int i = 0; i < n; i++)
        {
            int x = Integer.parseInt(in.readLine());
            loc[x] = i;
            if (!clean.empty() && x < clean.peek())
            {
                out.println(i);
                out.close();
                return;
            }
            else
            {
                if (soapy.isEmpty() || x > soapy.getLast().getLast())
                {
                    soapy.add(new LinkedList<>());
                    soapy.getLast().addFirst(x);
                }
                else
                {
                    for (LinkedList<Integer> stack : soapy)
                    {
                        if (x < stack.getLast())
                        {
                            while (x > stack.getFirst())
                            {
                                clean.push(stack.removeFirst());
                            }
                            stack.addFirst(x);
                            break;
                        }
                    }
                }
            }
        }

        for (LinkedList<Integer> stack : soapy)
        {
            while (!stack.isEmpty())
            {
                if (!clean.empty() && stack.getFirst() < clean.peek())
                {
                    out.println(loc[stack.getFirst()]);
                    out.close();
                    return;
                }
                else
                    clean.push(stack.removeFirst());
            }
            soapy.removeFirst();
        }

        out.println(n);
        out.close();
    }
}