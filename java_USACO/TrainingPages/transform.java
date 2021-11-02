/*
ID: pranavm7
LANG: JAVA
TASK: transform
PROG: transform
*/

import java.io.*;

public class transform
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int n = Integer.parseInt(in.readLine());

        char[][] before = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            before[i] = in.readLine().toCharArray();
        }

        char[][] after = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            after[i] = in.readLine().toCharArray();
        }

        if (rotate90(before, after, n))
            out.println("1");
        else if (rotate180(before, after, n))
            out.println("2");
        else if (rotate270(before, after, n))
            out.println("3");
        else if (reflection(before, after, n))
            out.println("4");
        else if (combination(before, after, n))
            out.println("5");
        else if (identical(before, after, n))
            out.println("6");
        else
            out.println("7");
        out.close();
    }

    private static boolean rotate90(char[][] before, char[][] after, int n)
    {
        char[][] transformed = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                transformed[j][n - i - 1] = before[i][j];
            }
        }
        return identical(transformed, after, n);
    }

    private static boolean rotate180(char[][] before, char[][] after, int n)
    {
        char[][] transformed = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                transformed[n - i - 1][n - j - 1] = before[i][j];
            }
        }
        return identical(transformed, after, n);
    }

    private static boolean rotate270(char[][] before, char[][] after, int n)
    {
        char[][] transformed = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                transformed[n - j - 1][i] = before[i][j];
            }
        }
        return identical(transformed, after, n);
    }

    private static boolean reflection(char[][] before, char[][] after, int n)
    {
        char[][] transformed = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                transformed[i][n - j - 1] = before[i][j];
            }
        }
        return identical(transformed, after, n);
    }

    private static boolean combination(char[][] before, char[][] after, int n)
    {
        // horizontal reflection
        char[][] reflected = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                reflected[i][n - j - 1] = before[i][j];
            }
        }

        // 90 degrees
        char[][] rotated = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotated[j][n - i - 1] = reflected[i][j];
            }
        }
        if (identical(rotated, after, n))
            return true;

        // 180 degrees
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotated[n - i - 1][n - j - 1] = reflected[i][j];
            }
        }
        if (identical(rotated, after, n))
            return true;

        // 270 degrees
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                rotated[n - j - 1][i] = reflected[i][j];
            }
        }
        return identical(rotated, after, n);
    }

    private static boolean identical(char[][] before, char[][] after, int n)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (before[i][j] != after[i][j])
                    return false;
            }
        }
        return true;
    }
}
