import java.io.*;
import java.util.ArrayList;

public class tttt
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("tttt.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tttt.out")));

        ArrayList<Character> inBoard = new ArrayList<>();
        String[] board = new String[3];

        for (int i = 0; i < 3; i++)
        {
            board[i] = in.readLine();
            for (int j = 0; j < 3; j++)
            {
                if (!inBoard.contains(board[i].charAt(j)))
                    inBoard.add(board[i].charAt(j));
            }
        }

        int numSingleVics = 0;
        for (char c : inBoard)
        {
            if (oneCanClaim(board, c))
                numSingleVics++;
        }
        out.println(numSingleVics);

        int numTeamVics = 0;
        for (int i = 0; i < inBoard.size(); i++)
        {
            for (int j = i + 1; j < inBoard.size(); j++)
            {
                if (twoCanClaim(board, inBoard.get(i), inBoard.get(j)))
                    numTeamVics++;
            }
        }
        out.println(numTeamVics);

        out.close();
    }

    private static boolean oneCanClaim(String[] board, char cow)
    {
        for (int r = 0; r < 3; r++)
        {
            if (board[r].charAt(0) == cow && board[r].charAt(1) == cow && board[r].charAt(2) == cow)
                return true;
        }

        for (int c = 0; c < 3; c++)
        {
            if (board[0].charAt(c) == cow && board[1].charAt(c) == cow && board[2].charAt(c) == cow)
                return true;
        }

        if (board[0].charAt(0) == cow && board[1].charAt(1) == cow && board[2].charAt(2) == cow)
            return true;

        if (board[0].charAt(2) == cow && board[1].charAt(1) == cow && board[2].charAt(0) == cow)
            return true;

        return false;
    }

    private static boolean twoCanClaim(String[] board, char cow1, char cow2)
    {
        for (int r = 0; r < 3; r++)
        {
            if (!allOneRow(board, cow1, r) && !allOneRow(board, cow2, r) && (board[r].charAt(0) == cow1 || board[r].charAt(0) == cow2) && (board[r].charAt(1) == cow1 || board[r].charAt(1) == cow2) && (board[r].charAt(2) == cow1 || board[r].charAt(2) == cow2))
                return true;
        }

        for (int c = 0; c < 3; c++)
        {
            if (!allOneCol(board, cow1, c) && !allOneCol(board, cow2, c) && (board[0].charAt(c) == cow1 || board[0].charAt(c) == cow2) && (board[1].charAt(c) == cow1 || board[1].charAt(c) == cow2) && (board[2].charAt(c) == cow1 || board[2].charAt(c) == cow2))
                return true;
        }

        if (!allOneDiag(board, cow1, true) && !allOneDiag(board, cow2, true) && (board[0].charAt(0) == cow1 || board[0].charAt(0) == cow2) && (board[1].charAt(1) == cow1 || board[1].charAt(1) == cow2) && (board[2].charAt(2) == cow1 || board[2].charAt(2) == cow2))
            return true;

        if (!allOneDiag(board, cow1, false) && !allOneDiag(board, cow2, false) && (board[0].charAt(2) == cow1 || board[0].charAt(2) == cow2) && (board[1].charAt(1) == cow1 || board[1].charAt(1) == cow2) && (board[2].charAt(0) == cow1 || board[2].charAt(0) == cow2))
            return true;

        return false;
    }

    private static boolean allOneRow(String[] board, char cow, int row)
    {
        return board[row].charAt(0) == cow && board[row].charAt(1) == cow && board[row].charAt(2) == cow;
    }

    private static boolean allOneCol(String[] board, char cow, int col)
    {
        return board[0].charAt(col) == cow && board[1].charAt(col) == cow && board[2].charAt(col) == cow;
    }

    private static boolean allOneDiag(String[] board, char cow, boolean tb)
    {
        if (tb)
            return board[0].charAt(0) == cow && board[1].charAt(1) == cow && board[2].charAt(2) == cow;
        return board[0].charAt(2) == cow && board[1].charAt(1) == cow && board[2].charAt(0) == cow;
    }
}
