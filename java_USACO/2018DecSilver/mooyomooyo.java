import java.io.*;

public class mooyomooyo
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));

        String[] meta = in.readLine().split(" ");
        int n = Integer.parseInt(meta[0]);
        int k = Integer.parseInt(meta[1]);

        int[][] board = new int[n][10];
        String line;
        for (int i = 0; i < n; i++)
        {
            line = in.readLine();
            for (int j = 0; j < 10; j++)
            {
                board[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        boolean[][] visited = new boolean[n][10];
        boolean[][] tempVisited = new boolean[n][10];
        int numRemoved = -1;
        while (numRemoved != 0)
        {
            numRemoved = 0;
            for (int r = n - 1; r >= 0; r--)
            {
                for (int c = 0; c < 10; c++)
                {
                    if (!visited[r][c] && board[r][c] != 0)
                    {
                        int numConnected = countRegion(board, visited, board[r][c], k, r, c);
                        if (numConnected >= k)
                        {
                            removeRegion(board, tempVisited, r, c, board[r][c]);
                            tempVisited = new boolean[n][10];
                            numRemoved++;
                        }
                    }
                }
            }

            fall(board);

//            System.out.println(numRemoved);
//            for (int r = 0; r < n; r++)
//            {
//                for (int c = 0; c < 10; c++)
//                {
//                    System.out.print(board[r][c]);
//                }
//                System.out.println();
//            }
//            System.out.println();

            visited = new boolean[n][10];
        }

        for (int r = 0; r < n; r++)
        {
            for (int c = 0; c < 10; c++)
            {
                out.print(board[r][c]);
            }
            out.println();
        }
        out.close();
    }

    private static int countRegion(int[][] board, boolean[][] visited, int toRemove, int k, int r, int c)
    {
        if (r < 0 || r >= board.length || c < 0 || c >= 10 || visited[r][c] || board[r][c] != toRemove)
            return 0;

        int numConnected= 1;
        visited[r][c] = true;

        numConnected  = numConnected + countRegion(board, visited, toRemove, k - 1, r + 1, c);
        numConnected  = numConnected + countRegion(board, visited, toRemove, k - 1, r - 1, c);
        numConnected  = numConnected + countRegion(board, visited, toRemove, k - 1, r, c + 1);
        numConnected  = numConnected + countRegion(board, visited, toRemove, k - 1, r, c - 1);

        return numConnected;
    }

    private static void removeRegion(int[][] board, boolean[][] visited, int r, int c, int toRemove)
    {
        if (r < 0 || r >= board.length || c < 0 || c >= 10 || visited[r][c] || board[r][c] != toRemove)
            return;

        board[r][c] = 0;
        removeRegion(board, visited, r + 1, c, toRemove);
        removeRegion(board, visited, r - 1, c, toRemove);
        removeRegion(board, visited, r , c + 1, toRemove);
        removeRegion(board, visited, r, c - 1, toRemove);
    }

    private static void fall(int[][] board)
    {
        int n = board.length;
        for (int r = n - 1; r > 0; r--)
        {
            for (int c = 0; c < 10; c++)
            {
                if (board[r][c] == 0)
                {
                    int nextNumR = 0;
                    for (int curR = r; curR >= 0; curR--)
                    {
                        if (board[curR][c] != 0)
                        {
                            nextNumR = curR;
                            break;
                        }
                    }

                    board[r][c] = board[nextNumR][c];
                    board[nextNumR][c] = 0;
                }
            }
        }
    }
}