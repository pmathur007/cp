import java.util.Scanner;

// Spreadsheet

public class P1B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] letterTranslation = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        int n = Integer.parseInt(scanner.nextLine());
        String[] inputs = new String[n];
        for (int i = 0; i < inputs.length; i++)
            inputs[i] = scanner.nextLine();

        for (String input : inputs) {
            String changedCoords = "";

            if (input.matches("R[0-9]+C[0-9]+")) {
                int rows = Integer.parseInt(input.substring(input.indexOf('R') + 1, input.indexOf('C')));
                int cols = Integer.parseInt(input.substring(input.indexOf('C') + 1));
                int remainder;

                while (cols / 26 != 0 || cols % 26 != 0) {
                    remainder = cols % 26;
                    if (remainder == 0) {
                        changedCoords = "Z" + changedCoords;
                        cols = cols / 26 - 1;
                        if (cols == 0)
                            break;
                    }
                    else{
                        changedCoords = letterTranslation[remainder - 1] + changedCoords;
                        cols /= 26;
                    }
                }
                System.out.println(changedCoords + rows);
            }

            else {
                char[] coordsChars = input.toCharArray();
                int index = 0;
                while (!Character.isDigit(coordsChars[index]))
                    index++;

                String cols = input.substring(0, index);
                changedCoords += "R" + input.substring(index) + "C";
                int colsVal = 0;

                for (int j = 0; j < cols.length(); j++) {
                    colsVal += (int) ((cols.charAt(j) - 'A' + 1) * Math.pow(26, cols.length() - j - 1));
                }
                System.out.println(changedCoords + colsVal);
            }
        }
    }
}
