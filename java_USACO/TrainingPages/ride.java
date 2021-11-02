/*
ID: pranavm7
LANG: JAVA
TASK: ride
PROG: ride
*/

import java.io.*;

public class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        String comet = in.readLine();
        String group = in.readLine();
        int cometProduct = 1;
        int groupProduct = 1;

        for (int i = 0; i < comet.length(); i++)
            cometProduct *= (comet.charAt(i) - 64);
        for (int i = 0; i < group.length(); i++)
            groupProduct *= (group.charAt(i) - 64);

        if (cometProduct % 47 == groupProduct % 47)
            out.print("GO\n");
        else
            out.print("STAY\n");
        out.close();
    }
}
