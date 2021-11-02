import java.util.Scanner;

// Theater Square

public class P1A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] splitInput = input.split(" ");

        double n = Double.parseDouble(splitInput[0]);
        double m = Double.parseDouble(splitInput[1]);
        double a = Double.parseDouble(splitInput[2]);

        System.out.println((long) (Math.ceil(n/a) * Math.ceil(m/a)));
    }
}
