import java.io.*;

public class beads
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int numBeads = Integer.parseInt(in.readLine());
        String necklace = in.readLine();
        int maxBeads = 0;

        if (!necklace.contains("r") || !necklace.contains("b"))
            out.println(numBeads);
        else
        {
            for (int i = 0; i < numBeads; i++)
            {
                char frontBreakerChar = (necklace.indexOf('r') < necklace.indexOf('b') ? 'b' : 'r');
                char endBreakerChar = (necklace.lastIndexOf('r') < necklace.lastIndexOf('b') ? 'r' : 'b');

                int beadsToCollect = (necklace.indexOf(frontBreakerChar)) + (necklace.length() - necklace.lastIndexOf(endBreakerChar) - 1);
                if (beadsToCollect > maxBeads)
                    maxBeads = beadsToCollect;

                necklace = necklace.substring(1) + necklace.substring(0, 1);
            }
            maxBeads = (maxBeads > numBeads ? numBeads : maxBeads);
            out.println(maxBeads);
        }
        out.close();
    }
}

/*
29
wwwbbrwrbrbrrbrbrwrwwrbwrwrrb
*/
