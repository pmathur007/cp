import java.util.Scanner;
import static java.lang.Math.*;

// Ancient Berland Circus

public class P1C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] coords = new double[3][2];
        for (int i = 0; i < 3; i++) {
            String[] stringCoord = scanner.nextLine().split(" ");
            coords[i][0] = Double.parseDouble(stringCoord[0]);
            coords[i][1] = Double.parseDouble(stringCoord[1]);
        }

        double a = sqrt(pow(coords[0][0] - coords[1][0], 2) + pow(coords[0][1] - coords[1][1], 2));
        double b = sqrt(pow(coords[1][0] - coords[2][0], 2) + pow(coords[1][1] - coords[2][1], 2));
        double c = sqrt(pow(coords[2][0] - coords[0][0], 2) + pow(coords[2][1] - coords[0][1], 2));

        double alpha = acos((pow(b, 2) + pow(c, 2) - pow(a, 2)) / (2 * b * c));
        double beta = acos((pow(a, 2) + pow(c, 2) - pow(b, 2)) / (2 * a * c));
        double gamma = (PI - alpha - beta);
//        float alpha = (float) acos((pow(b, 2) + pow(c, 2) - pow(a, 2)) / (2 * b * c));
//        float beta = (float) acos((pow(a, 2) + pow(c, 2) - pow(b, 2)) / (2 * a * c));
//        float gamma = (float) (PI - alpha - beta);

//        int sides = 0;
//        for (int i = 3; i <= 100; i++) {
//            double angle = PI / i;
//            if (alpha % angle == 0 && beta % angle == 0 && gamma % angle == 0) {
//                sides = i;
//                break;
//            }
        long sides = Math.round(PI / gcd(alpha, gcd(beta, gamma)));

        double triangleArea = (sin(alpha) * b * c) / 2;
        double circumradius = (a * b * c) / (4 * triangleArea);
        double finalArea = ((sides * (circumradius * circumradius * sin(2 * PI / sides))) / 2);
        System.out.printf("%.6f", finalArea);
    }

    private static double gcd(double a, double b) {
        if (a < b)
            return gcd(b, a);
        if (Math.abs(b) < 0.001)
            return a;
        return (gcd(b, a - Math.floor(a / b) * b));
    }
}
