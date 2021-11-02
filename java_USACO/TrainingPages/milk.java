/*
ID: pranavm7
LANG: JAVA
TASK: milk
PROG: milk
*/

import java.io.*;

public class milk
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);


        int[] prices = new int[m];
        int[] amounts = new int[m];
        for (int i = 0; i < m; i++)
        {
            String[] farmer = in.readLine().split(" ");
            prices[i] = Integer.parseInt(farmer[0]);
            amounts[i] = Integer.parseInt(farmer[1]);
        }
        sortFarmers(prices, amounts);

        int currentMilk = 0;
        int price = 0;
        for (int i = 0; i < m; i++)
        {
            if (currentMilk + amounts[i] >= n)
            {
                price += (n - currentMilk) * prices[i];
                break;
            }
            else
            {
                currentMilk += amounts[i];
                price += prices[i] * amounts[i];
            }
        }
        out.println(price);

        out.close();
    }

    private static void sortFarmers(int[] prices, int[] amounts)
    {
        for (int i = 0; i < prices.length; i++)
        {
            int price = prices[i];
            int amount = amounts[i];
            int j = i - 1;
            while (j >= 0 && price < prices[j])
            {
                prices[j + 1] = prices[j];
                amounts[j + 1] = amounts[j];
                j -= 1;
            }
            prices[j + 1] = price;
            amounts[j + 1] = amount;
        }
    }
}
