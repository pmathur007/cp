/*
ID: pranavm7
LANG: JAVA
TASK: msquare
PROG: msquare
*/

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class msquare
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        StringTokenizer st = new StringTokenizer(in.readLine());
        String goal = "";
        for (int i = 0; i < 8; i++)
        {
            if (i < 4)
                goal += st.nextToken();
            else
                goal = goal.substring(0, 4) + st.nextToken() + goal.substring(4);
        }

        Queue<Item> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        q.add(new Item("12348765", 0, ""));
        visited.add("12348765");
        while (!q.isEmpty())
        {
            Item i = q.remove();

            if (i.s.equals(goal))
            {
                out.println(i.d);
                out.println(i.path);
                out.close();
                break;
            }

            String a = i.s.substring(4) + i.s.substring(0, 4);
            if (!visited.contains(a))
            {
                visited.add(a);
                q.add(new Item(a, i.d + 1, i.path + "A"));
            }

            String b = i.s.charAt(3) + i.s.substring(0, 3) + i.s.charAt(7) + i.s.substring(4, 7);
            if (!visited.contains(b))
            {
                visited.add(b);
                q.add(new Item(b, i.d + 1, i.path + "B"));
            }

            String c = "" + i.s.charAt(0) + i.s.charAt(5) + i.s.charAt(1) + i.s.charAt(3) + i.s.charAt(4) + i.s.charAt(6) + i.s.charAt(2) + i.s.charAt(7);
            if (!visited.contains(c))
            {
                visited.add(c);
                q.add(new Item(c, i.d + 1, i.path + "C"));
            }
        }
    }

    private static class Item
    {
        private String s, path;
        private int d;

        public Item(String s, int d, String path)
        {
            this.s = s;
            this.d = d;
            this.path = path;
        }
    }
}