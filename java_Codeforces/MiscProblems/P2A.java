import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Winner

public class P2A {
    public static void main(String[] args) {
        Scanner readScanner = new Scanner(System.in);

        int rounds = Integer.parseInt(readScanner.nextLine());
        Map<String, Integer> totalScore = new HashMap<>();
        String[] names = new String[rounds];
        int[] scores = new int[rounds];

        for (int i = 0; i < rounds; i++) {
            String[] input = readScanner.nextLine().split(" ");
            int newScore = totalScore.getOrDefault(input[0], 0) + Integer.parseInt(input[1]);
            totalScore.put(input[0], newScore);
            names[i] = input[0];
            scores[i] = newScore;
        }

        int max = findMax(totalScore);
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] >= max && totalScore.get(names[i]) == max) {
                System.out.println(names[i]);
                break;
            }
        }
    }

    private static int findMax(Map<String, Integer> score) {
        int max = 0;
        for (int i : score.values()) {
            if (i > max)
                max = i;
        }
        return max;
    }
}
