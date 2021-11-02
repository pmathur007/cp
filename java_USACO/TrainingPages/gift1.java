/*
ID: pranavm7
LANG: JAVA
TASK: gift1
PROG: gift1
*/

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class gift1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        // Populate map
        int np = Integer.parseInt(in.readLine());
        Map<String, Integer> accounts = new LinkedHashMap<>();
        for (int i = 0; i < np; i++)
            accounts.put(in.readLine(), 0);

        // Calculate how much money each person gives
        for (int i = 0; i < np; i++)
        {
            String giver = in.readLine();
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int money = Integer.parseInt(st.nextToken());
            int numGifts = Integer.parseInt(st.nextToken());

            if (numGifts == 0)
                accounts.put(giver, accounts.get(giver) - money);
            else
            {
                accounts.put(giver, accounts.get(giver) + (money % numGifts) - money);
                int amountToGive = money / numGifts;
                for (int j = 0; j < numGifts; j++) {
                    String receiver = in.readLine();
                    accounts.put(receiver, accounts.get(receiver) + amountToGive);
                }
            }
        }

        // Print the result to the output file
        for (String name : accounts.keySet())
            out.println(name + " " + accounts.get(name));

        out.close();
    }
}
