import java.util.Scanner;

public class C532
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int r = in.nextInt();

        double ans = (r * Math.sin(Math.PI / n)) / (1 - Math.sin(Math.PI / n));
        System.out.println(ans);
    }
}