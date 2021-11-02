import java.io.*;

public class balance
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("balance.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));

        int n = Integer.parseInt(in.readLine());

        out.close();
    }
}
// IN CONTEST BS
//    public static void main(String[] args) throws IOException
//    {
//        BufferedReader in = new BufferedReader(new FileReader("balance.in"));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balance.out")));
//
//        int n = Integer.parseInt(in.readLine());
//        StringTokenizer st = new StringTokenizer(in.readLine());
//
//        int[] bArr = new int[n];
//        int[] eArr = new int[n];
//        int[] bPre = new int[n]; // number of ones
//        int[] ePre = new int[n]; // number of ones
//        int bInv = 0;
//        int eInv = 0;
//
//        for (int i = 0; i < n; i++)
//        {
//            bArr[i] = Integer.parseInt(st.nextToken());
//            bPre[i] = bArr[i] + (i == 0 ? 0 : bPre[i - 1]);
//            if (bArr[i] == 0)
//                bInv += bPre[i];
//        }
//        for (int i = 0; i < n; i++)
//        {
//            eArr[i] = Integer.parseInt(st.nextToken());
//            ePre[i] = eArr[i] + (i == 0 ? 0 : ePre[i - 1]);
//            if (eArr[i] == 0)
//                eInv += ePre[i];
//        }
//
//        if (bInv == eInv)
//            out.println(0);
//        else
//        {
//            if (bArr[n - 1] != eArr[0])
//            {
//                int preDiff = Math.abs(bInv - eInv);
//                int potBInv = bInv, potEInv = eInv;
//                if (bArr[n - 1] == 0)
//                {
//                    potBInv -= bPre[n - 1];
//                    potEInv -= n - ePre[n - 1];
//                }
//                else
//                {
//                    potBInv += bPre[n - 1];
//                    potEInv += n - ePre[n - 1];
//                }
//
//                if (Math.abs(potBInv - potEInv) < preDiff)
//                    out.println(1 + (Math.abs(potBInv - potEInv)));
//                else
//                    out.println(preDiff);
//            }
//            else
//                out.println(Math.abs(bInv - eInv));
//        }
//
//        out.close();
//    }