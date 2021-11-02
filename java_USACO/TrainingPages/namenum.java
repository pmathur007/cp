/*
ID: pranavm7
LANG: JAVA
TASK: namenum
PROG: namenum
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class namenum
{
    private static final Map<String, String> idToLetter;
    static
    {
        idToLetter = new HashMap<>();
        idToLetter.put("2", "ABC");
        idToLetter.put("3", "DEF");
        idToLetter.put("4", "GHI");
        idToLetter.put("5", "JKL");
        idToLetter.put("6", "MNO");
        idToLetter.put("7", "PRS");
        idToLetter.put("8", "TUV");
        idToLetter.put("9", "WXY");
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        BufferedReader dictIn = new BufferedReader(new FileReader("dict.txt"));
        ArrayList<String> dict = new ArrayList<>();
        for (int i = 0; i < 5000; i++)
        {
            dict.add(dictIn.readLine());
        }

        String num = in.readLine();
        ArrayList<String> names = new ArrayList<>();
        generateNames(num, "", names, dict);

        if (names.size() == 0)
            out.println("NONE");
        else
            for (String name : names)
                out.println(name);
        out.close();
    }

    private static void generateNames(String num, String combo, ArrayList<String> array, ArrayList<String> dict)
    {
        if (combo.length() == num.length())
        {
            if (dictContains(combo, dict))
                array.add(combo);
        }
        else
        {
            String numToGet = num.substring(combo.length(), combo.length() + 1);
            generateNames(num, combo + idToLetter.get(numToGet).charAt(0), array, dict);
            generateNames(num, combo + idToLetter.get(numToGet).charAt(1), array, dict);
            generateNames(num, combo + idToLetter.get(numToGet).charAt(2), array, dict);
        }
    }

    private static boolean dictContains(String word, ArrayList<String> dict)
    {
        int start = 0;
        int end = dict.size();
        int mid;

        while (start < end)
        {
            mid = (start + end) / 2;
            if (word.compareTo(dict.get(mid)) == 0)
            {
                return true;
            }
            else if (word.compareTo(dict.get(mid)) < 0)
            {
                end = mid;
            }
            else
            {
                start = mid + 1;
            }
        }
        return false;
    }
}
