import java.util.Scanner;
import java.util.Arrays;

class TicTacToe {

    public static void main(String[] args) {
        int rows = 3, columns = 3;
        // createGrid is called to initialize the array
        char [][] grid = createGrid(rows, columns);
        
        Arrays.fill(grid[0], ' ');
        Arrays.fill(grid[1], ' ');
        Arrays.fill(grid[2], ' ');

        System.out.println("\n*********** WELCOME ***********");
        startGame(grid);
    }

    public static void startGame(char[][] grid) {
        boolean winner = false;
        char x = 'X', o = 'O';
        boolean playerO = false;

        while (!winner) {
            if (playerO == false) {
                boolean playerX = player(grid, 'X');
                checkWinner(grid);
                if (playerX == false) {
                    continue;
                }
            }

            playerO = player(grid, 'O');
            checkWinner(grid);
            if (playerO == false) {
                playerO = true;
                continue;
            }
            playerO = false;
        }
    }

    public static void checkWinner(char[][] grid) {
        int cntX = 0;
        int cntO = 0;

        // horizontal winner check
        for (int i = 0; i <= 2; i++) {
            cntX = 0; // Reset for each row
            cntO = 0;
            for (int j = 0; j <= 2; j++) {
                if (grid[i][j] == 'X') {
                    cntX++;
                } else if (grid[i][j] == 'O') {
                    cntO++;
                }
            }
            if (cntX == 3) {
                displayGrid(grid);
                System.out.println("\nX is winner");
                System.exit(0);
            } else if (cntO == 3) {
                displayGrid(grid);
                System.out.println("\n O is winner");
                System.exit(0);
            }
        }

        // vertical winner check
        cntX = 0;
        cntO = 0;
        for (int j = 0; j <= 2; j++) {
            cntX = 0; // Reset for each column
            cntO = 0;
            for (int i = 0; i <= 2; i++) {
                if (grid[i][j] == 'X') {
                    cntX++;
                } else if (grid[i][j] == 'O') {
                    cntO++;
                }
            }
            if (cntX == 3) {
                displayGrid(grid);
                System.out.println("\nX is winner");
                System.exit(0);
            }
            // Logic for cntO == 3 follows similar pattern
        }

        // diagonal conditions
        if ((grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') || 
            (grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X')) {
            displayGrid(grid);
            System.out.println("\nX is winner");
            System.exit(0);
        }
        
        if ((grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') || 
            (grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O')) {
            displayGrid(grid);
            System.out.println("\nO is winner");
            System.exit(0);
        }

        // check for draw
        boolean grids = false;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                char ch = grid[i][j];
                if (ch == ' ') {
                    return;
                }
            }
        }
        if (grids == false) {
            System.out.println("\n ITS A DRAW \n");
            System.exit(0);
        }
    }

    public static boolean player(char[][] grid, char player) {
        displayGrid(grid);
        System.out.println();
        System.out.print("Enter your response player " + player + " : ");
        String response = new Scanner(System.in).next().toUpperCase();
        boolean check = checkResponse(grid, response, player);
        if (!check) {
            System.out.println("\nPlayer " + player + " should get a chance\n");
            return false;
        }
        return true;
    }

    public static boolean checkResponse(char[][] grid, String response, char player) {
        int i = response.charAt(0) - 65; // 'A' becomes 0
        int j = response.charAt(1) - 49; // '1' becomes 0
        
        if (i < 0 || i > 2 || j < 0 || j > 2) {
            System.out.println("\nInvalid Response\n");
            return false;
        }
        
        if (grid[i][j] == ' ') {
            grid[i][j] = player;
            return true;
        } else {
            System.out.println("\n Response already submitted \n");
            return false;
        }
    }

    public static char[][] createGrid(int rows, int columns) {
        char[][] grid = new char[rows][columns];
        return grid;
    }

    public static void displayGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.printf("%2c", grid[i][j]);
                if (j < 2) {
                    System.out.printf("%s", " | ");
                }
            }
            if (i < 2) {
                System.out.printf("%n %5s %n", "_________");
            }
        }
        System.out.println();
    }
}
