/*
ID: pranavm7
LANG: JAVA
TASK: holstein
PROG: holstein
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class holstein
{
    private static int[] bestScoops = null;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        int v = Integer.parseInt(in.readLine());
        int[] requirements = new int[v];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for (int i = 0; i < v; i++)
        {
            requirements[i] = Integer.parseInt(st.nextToken());
        }
        int g = Integer.parseInt(in.readLine());
        int[][] vitamins = new int[g][v];
        for (int i = 0; i < g; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            for (int j = 0; j < v; j++)
            {
                vitamins[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= g; i++)
        {
            if (bestScoops == null)
                generateSizeN(requirements, vitamins, i, 0, 0, new int[i]);
        }

        out.print(bestScoops.length);
        for (int i = 0; i < bestScoops.length; i++)
        {
            out.print(" " + (bestScoops[i] + 1));
        }
        out.println();

        out.close();
    }

    private static void generateSizeN(int[] requirements, int[][] vitamins, int n, int curIndex, int dataIndex, int[] scoop)
    {
        if (bestScoops != null)
            return;

        if (dataIndex == n)
        {
            if (satisfied(requirements, vitamins, scoop))
            {
                bestScoops = scoop;
            }
            return;
        }

        if (curIndex >= vitamins.length)
            return;

        scoop[dataIndex] = curIndex;
        generateSizeN(requirements, vitamins, n, curIndex + 1, dataIndex + 1, scoop);
        generateSizeN(requirements, vitamins, n, curIndex + 1, dataIndex, scoop);
    }

    private static boolean satisfied(int[] requirements, int[][] vitamins, int[] scoop)
    {
        int[] afterEating = Arrays.copyOf(requirements, requirements.length);
        for (int i = 0; i < scoop.length; i++)
        {
            for (int j = 0; j < vitamins[0].length; j++)
            {
                afterEating[j] = afterEating[j] - vitamins[scoop[i]][j];
            }
        }

        for (int i = 0; i < afterEating.length; i++)
        {
            if (afterEating[i] > 0)
                return false;
        }

        return true;
    }
}

// RECURSIVE APPROACH, FAILED

//    private static LinkedList<Integer> bestScoops = null;
//    private static int amtOver = Integer.MAX_VALUE;
//
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
//
//        int v = Integer.parseInt(in.readLine());
//        int[] requirements = new int[v];
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        for (int i = 0; i < v; i++)
//        {
//            requirements[i] = Integer.parseInt(st.nextToken());
//        }
//        int g = Integer.parseInt(in.readLine());
//        int[][] vitamins = new int[g][v];
//        for (int i = 0; i < g; i++)
//        {
//            st = new StringTokenizer(in.readLine(), " ");
//            for (int j = 0; j < v; j++)
//            {
//                vitamins[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        LinkedList<Integer> scoops = new LinkedList<>();
////        LinkedList<Integer> bestScoops = null;
//
//        optimalScoops(requirements, vitamins, scoops);
//
//        Collections.sort(bestScoops);
//        out.print(bestScoops.size());
//        for (int i : bestScoops)
//        {
//            out.print(" " + (i + 1));
//        }
//        out.println();
//        out.close();
//    }
//
//    private static void optimalScoops(int[] requirements, int[][] vitamins, LinkedList<Integer> currentScoops)
//    {
//        boolean recursed = false;
//        for (int i = 0; i < vitamins.length; i++)
//        {
//            if (shouldEat(requirements, vitamins[i]) && (bestScoops == null || currentScoops.size() < bestScoops.size()))
//            {
//                recursed = true;
//                eatUneat(requirements, vitamins[i], true);
//                currentScoops.add(i);
//                optimalScoops(requirements, vitamins, currentScoops);
//                currentScoops.removeLast();
//                eatUneat(requirements, vitamins[i], false);
//            }
//        }
//        if (!recursed && satisfied(requirements))
//        {
//            if (bestScoops == null || currentScoops.size() < bestScoops.size() || (currentScoops.size() == bestScoops.size() && amtOver(requirements) < amtOver))
//            {
//                bestScoops = new LinkedList<>(currentScoops);
//                amtOver = amtOver(requirements);
//            }
//        }
//    }
//
//    private static boolean satisfied(int[] requirements)
//    {
//        for (int i = 0; i < requirements.length; i++)
//        {
//            if (requirements[i] > 0)
//                return false;
//        }
//        return true;
//    }
//
//    private static int amtOver(int[] requirements)
//    {
//        int amtOver = 0;
//        for (int i = 0; i < requirements.length; i++)
//        {
//            amtOver += 0 - requirements[i];
//        }
//        return amtOver;
//    }
//
//
//    private static boolean shouldEat(int[] requirements, int[] scoop)
//    {
//        for (int i = 0; i < requirements.length; i++)
//        {
//            if (requirements[i] > 0 && scoop[i] > 0)
//            {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static void eatUneat(int[] requirements, int[] scoop, boolean eat)
//    {
//        for (int i = 0; i < requirements.length; i++)
//        {
//            requirements[i] = requirements[i] + (eat ? -1 : 1) * scoop[i];
//        }
//    }



// GREEDY APPROACH, FAILED

//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
//
//        int v = Integer.parseInt(in.readLine());
//        int[] requirements = new int[v];
//        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
//        for (int i = 0; i < v; i++)
//        {
//            requirements[i] = Integer.parseInt(st.nextToken());
//        }
//        int g = Integer.parseInt(in.readLine());
//        int[][] vitamins = new int[g][v];
//        for (int i = 0; i < g; i++)
//        {
//            st = new StringTokenizer(in.readLine(), " ");
//            for (int j = 0; j < v; j++)
//            {
//                vitamins[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        LinkedList<Integer> scoops = new LinkedList<>();
//
//        int lastEaten;
//        do
//        {
//            lastEaten = optimalChoice(requirements, vitamins);
//            scoops.add(lastEaten);
//        } while (eatVitamin(requirements, vitamins[lastEaten]));
//
//        Collections.sort(scoops);
//
//        out.print(scoops.size());
//        for (int i : scoops)
//            out.print(" " + (i + 1));
//        out.println();
//        out.close();
//    }
//
//    private static int optimalChoice(int[] requirements, int[][] vitamins)
//    {
//        int bestVitamin = -1;
//        int bestNeeded = Integer.MAX_VALUE;
//        for (int i = 0; i < vitamins.length; i++)
//        {
//            int needed = numNeeded(requirements, vitamins[i]);
//            if (needed < bestNeeded && needed > 0)
//            {
//                bestVitamin = i;
//                bestNeeded = needed;
//            }
////            else if (needed == bestNeeded)
////            {
////                int difference = 0;
////                for (int j = 0; j < requirements.length; j++)
////                {
////                    difference += (vitamins[i][j] - vitamins[bestNeeded][j]);
////                }
////                if (difference > 0)
////                {
////                    bestVitamin = i;
////                }
////            }
//        }
//        return bestVitamin;
//    }
//
//    private static int numNeeded(int[] requirements, int[] scoop)
//    {
//        int maxNeeded = -1;
//        int needed;
//        for (int i = 0; i < requirements.length; i++)
//        {
//            if (requirements[i] != 0 && scoop[i] != 0)
//            {
//                needed = requirements[i] / scoop[i] + (requirements[i] % scoop[i] == 0 ? 0 : 1);
//                if (needed > maxNeeded)
//                    maxNeeded = needed;
//            }
//        }
//        return maxNeeded;
//    }
//
//    private static boolean eatVitamin(int[] requirements, int[] scoop)
//    {
//        boolean endLoop = false;
//        for (int i = 0; i < requirements.length; i++)
//        {
//            requirements[i] = requirements[i] - scoop[i];
//            if (requirements[i] > 0)
//                endLoop = true;
//        }
//        return endLoop;
//    }