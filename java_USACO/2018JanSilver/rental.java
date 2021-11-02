import java.io.*;
import java.util.*;

public class rental
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("rental.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int m = Integer.parseInt(meta[1]);
        int r = Integer.parseInt(meta[2]);

        int[] cows = new int[n];
        for (int i = 0; i < n; i++)
        {
            cows[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(cows);

        LinkedList<Store> stores = new LinkedList<>();
        StringTokenizer st;
        for (int i = 0; i < m; i++)
        {
            st = new StringTokenizer(in.readLine(), " ");
            stores.add(new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(stores, (s1, s2) -> s2.value - s1.value);

        ArrayList<Integer> farmers = new ArrayList<>(r);
        for (int i = 0; i < r; i++)
        {
            farmers.add(Integer.parseInt(in.readLine()));
        }
        Collections.sort(farmers, (i1, i2) -> i2 - i1);

        long total = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            if (i >= r)
                total += buy(stores, cows[i]);
            else
            {
                if (amountFromBuying(stores, cows[i]) >= farmers.get(i))
                    total += buy(stores, cows[i]);
                else
                    total += farmers.get(i);
            }
        }

        out.println(total);

        out.close();
    }

    private static int amountFromBuying(LinkedList<Store> stores, int amount)
    {
        if (stores.isEmpty())
            return 0;
        int profit = 0;
        int curStoreIndex = 0;
        Store curStore = stores.getFirst();

        while (amount > 0 && curStore != null)
        {
            if (curStore.stock >= amount)
            {
                profit += amount * curStore.value;
                amount = 0;
            }
            else
            {
                profit += curStore.stock * curStore.value;
                amount -= curStore.stock;
                curStore = curStoreIndex + 1 < stores.size() ? stores.get(curStoreIndex + 1) : null;
                curStoreIndex += 1;
            }
        }
        return profit;
    }

    private static int buy(LinkedList<Store> stores, int amount)
    {
        int profit = 0;
        Store curStore;
        while (amount > 0 && !stores.isEmpty())
        {
            curStore = stores.getFirst();
            if (curStore.stock > amount)
            {
                profit += amount * curStore.value;
                curStore.stock = curStore.stock - amount;
                amount = 0;
            }
            else if (curStore.stock == amount)
            {
                profit += amount * curStore.value;
                stores.removeFirst();
                amount = 0;
            }
            else
            {
                profit += curStore.stock * curStore.value;
                amount -= curStore.stock;
                stores.removeFirst();
            }
        }
        return profit;
    }

    private static class Store
    {
        private int stock, value;

        public Store(int stock, int value)
        {
            this.stock = stock;
            this.value = value;
        }

//        public int buy(int amount)
//        {
//
//        }
    }
}