import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class E532
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        LinkedList<Node>[] graph = new LinkedList[n];
        Node[] nodes = new Node[m];
        for (int i = 0; i < n; i++)
        {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++)
        {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();

            Node toAdd = new Node(from, to, w, i);
            graph[from].add(toAdd);
            nodes[i] = toAdd;
        }

        TreeSet<Integer> reversed = new TreeSet<>();

        int cur = 0;
        boolean[] visited;
        boolean[] stack;
        for (LinkedList<Node> l : graph)
        {
            visited = new boolean[n];
            stack = new boolean[n];
            dfsRmCycles(graph, nodes, reversed, visited, stack, cur);
            cur++;
        }

        int max = 0;
        for (Integer i : reversed)
        {
            max = Math.max(nodes[i].w, max);
        }
        System.out.println(reversed.size() + " " + max);
        // this is where I left off

    }

    private static int min = Integer.MAX_VALUE;
    private static int dfsRmCycles(LinkedList<Node>[] graph, Node[] nodes, TreeSet<Integer> reversed, boolean[] visited, boolean[] stack, int cur)
    {
        if (stack[cur])
        {
            return cur;
        }
        if (visited[cur])
            return Integer.MAX_VALUE;

        visited[cur] = true;
        stack[cur] = true;

        LinkedList<Node> children = graph[cur];
        if (children.isEmpty())
        {
            return -1;
        }
        else
        {
            for (Node n : children)
            {
                int isCycle = dfsRmCycles(graph, nodes, reversed, visited, stack, n.d);
                if (isCycle == cur && !reversed.contains(min + 1))
                {
                    reversed.add(min + 1);
                    graph[nodes[min].f].remove(nodes[min]);
                    graph[nodes[min].d].add(nodes[min]);

                    int temp = nodes[min].f;
                    nodes[min].f = nodes[min].d;
                    nodes[min].d = temp;

                    min = Integer.MAX_VALUE;
                }
                else if (isCycle >= 0)
                {
                    min = n.w < nodes[min].w ? n.i : min;
                }
            }
        }

        stack[cur] = false;
        return -1;
    }

    private static class Node
    {
        private int f, d, w, i;

        public Node(int f, int d, int w, int i)
        {
            this.f = f;
            this.d = d;
            this.w = w;
            this.i = i;
        }
    }
}