/*
ID: pranavm7
LANG: JAVA
TASK: friday
PROG: friday
*/

import java.io.*;

public class friday
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        int years = Integer.parseInt(in.readLine());
        int days = 0;
//        for (int i = 0; i < years; i++)
//        {
//            if (isLeapYear(1900 + i))
//                days += 366;
//            else days += 365;
//        }
        int[] numthirteens = new int[7];

        for (int i = 0; i < years; i++) {
            // January
            numthirteens[days % 7]++;
            days += 31;
            // February
            numthirteens[days % 7]++;
            days += (isLeapYear(1900 + i) ? 29 : 28);
            // March
            numthirteens[days % 7]++;
            days += 31;
            // April
            numthirteens[days % 7]++;
            days += 30;
            // May
            numthirteens[days % 7]++;
            days += 31;
            // June
            numthirteens[days % 7]++;
            days += 30;
            // July
            numthirteens[days % 7]++;
            days += 31;
            // August
            numthirteens[days % 7]++;
            days += 31;
            // September
            numthirteens[days % 7]++;
            days += 30;
            // October
            numthirteens[days % 7]++;
            days += 31;
            // November
            numthirteens[days % 7]++;
            days += 30;
            // December
            numthirteens[days % 7]++;
            days += 31;
        }

        for (int i = 0; i < numthirteens.length; i++)
            out.print(numthirteens[i] + (i < 6 ? " " : "\n"));
        out.close();
    }

    private static boolean isLeapYear(int year)
    {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }
}
