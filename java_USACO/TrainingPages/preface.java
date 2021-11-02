/*
ID: pranavm7
LANG: JAVA
TASK: preface
PROG: preface
*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class preface
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("preface.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

        Map<String, Integer> frequencies = new HashMap<>();
        frequencies.put("I", 0);
        frequencies.put("V", 0);
        frequencies.put("X", 0);
        frequencies.put("L", 0);
        frequencies.put("C", 0);
        frequencies.put("D", 0);
        frequencies.put("M", 0);

        int n = Integer.parseInt(in.readLine());

        for (int i = 1; i <= n; i++)
        {
            update(frequencies, i);
        }

        out.println("I " + frequencies.get("I"));
        if (n >= 4)
            out.println("V " + frequencies.get("V"));
        if (n >= 9)
            out.println("X " + frequencies.get("X"));
        if (n >= 40)
            out.println("L " + frequencies.get("L"));
        if (n >= 90)
            out.println("C " + frequencies.get("C"));
        if (n >= 400)
            out.println("D " + frequencies.get("D"));
        if (n >= 900)
            out.println("M " + frequencies.get("M"));

        out.close();
    }

    private static void update(Map<String, Integer> frequencies, int i)
    {
        if (i >= 1000)
            updateThousands(frequencies, i / 1000);
        if (i >= 100)
            updateHundreds(frequencies, (i / 100) % 10);
        if (i >= 10)
            updateTens(frequencies, (i / 10) % 10);
        updateOnes(frequencies, i % 10);
    }

    private static void updateOnes(Map<String, Integer> frequencies, int digit)
    {
        switch (digit)
        {
            case 1:
                frequencies.put("I", frequencies.get("I") + 1);
                break;
            case 2:
                frequencies.put("I", frequencies.get("I") + 2);
                break;
            case 3:
                frequencies.put("I", frequencies.get("I") + 3);
                break;
            case 4:
                frequencies.put("I", frequencies.get("I") + 1);
                frequencies.put("V", frequencies.get("V") + 1);
                break;
            case 5:
                frequencies.put("V", frequencies.get("V") + 1);
                break;
            case 6:
                frequencies.put("V", frequencies.get("V") + 1);
                frequencies.put("I", frequencies.get("I") + 1);
                break;
            case 7:
                frequencies.put("V", frequencies.get("V") + 1);
                frequencies.put("I", frequencies.get("I") + 2);
                break;
            case 8:
                frequencies.put("V", frequencies.get("V") + 1);
                frequencies.put("I", frequencies.get("I") + 3);
                break;
            case 9:
                frequencies.put("I", frequencies.get("I") + 1);
                frequencies.put("X", frequencies.get("X") + 1);
                break;
        }
    }

    private static void updateTens(Map<String, Integer> frequencies, int digit)
    {
        switch (digit)
        {
            case 1:
                frequencies.put("X", frequencies.get("X") + 1);
                break;
            case 2:
                frequencies.put("X", frequencies.get("X") + 2);
                break;
            case 3:
                frequencies.put("X", frequencies.get("X") + 3);
                break;
            case 4:
                frequencies.put("X", frequencies.get("X") + 1);
                frequencies.put("L", frequencies.get("L") + 1);
                break;
            case 5:
                frequencies.put("L", frequencies.get("L") + 1);
                break;
            case 6:
                frequencies.put("L", frequencies.get("L") + 1);
                frequencies.put("X", frequencies.get("X") + 1);
                break;
            case 7:
                frequencies.put("L", frequencies.get("L") + 1);
                frequencies.put("X", frequencies.get("X") + 2);
                break;
            case 8:
                frequencies.put("L", frequencies.get("L") + 1);
                frequencies.put("X", frequencies.get("X") + 3);
                break;
            case 9:
                frequencies.put("X", frequencies.get("X") + 1);
                frequencies.put("C", frequencies.get("C") + 1);
                break;
        }
    }

    private static void updateHundreds(Map<String, Integer> frequencies, int digit)
    {
        switch (digit)
        {
            case 1:
                frequencies.put("C", frequencies.get("C") + 1);
                break;
            case 2:
                frequencies.put("C", frequencies.get("C") + 2);
                break;
            case 3:
                frequencies.put("C", frequencies.get("C") + 3);
                break;
            case 4:
                frequencies.put("C", frequencies.get("C") + 1);
                frequencies.put("D", frequencies.get("D") + 1);
                break;
            case 5:
                frequencies.put("D", frequencies.get("D") + 1);
                break;
            case 6:
                frequencies.put("D", frequencies.get("D") + 1);
                frequencies.put("C", frequencies.get("C") + 1);
                break;
            case 7:
                frequencies.put("D", frequencies.get("D") + 1);
                frequencies.put("C", frequencies.get("C") + 2);
                break;
            case 8:
                frequencies.put("D", frequencies.get("D") + 1);
                frequencies.put("C", frequencies.get("C") + 3);
                break;
            case 9:
                frequencies.put("C", frequencies.get("C") + 1);
                frequencies.put("M", frequencies.get("M") + 1);
                break;
        }
    }

    private static void updateThousands(Map<String, Integer> frequencies, int digit)
    {
        switch (digit)
        {
            case 1:
                frequencies.put("M", frequencies.get("M") + 1);
                break;
            case 2:
                frequencies.put("M", frequencies.get("M") + 2);
                break;
            case 3:
                frequencies.put("M", frequencies.get("M") + 3);
                break;
        }
    }
}