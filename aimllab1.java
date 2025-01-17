
import java.util.Scanner;
class aimllab1 {
    static int movecount = 0;
    static char currentplayer ='X';
    static char[][] board = new char[3][3];
        
    public static void main(String[] args) {
        initializeBoard();
        
        while(true){
            printBoard();
            if(gameover()){
                break;
            }
            playermove();
        }
        
    }
    public static void initializeBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j] = ' ';
            }
        }
    }
    
    // Print the current state of the board
    public static void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static boolean gameover(){
         if (posewin()) {
            printBoard();
            System.out.println("Player " + currentplayer + " wins!");
            return true;
        } else if (movecount == 9) {
            printBoard();
            System.out.println("It's a draw!");
            return true;
        }

        // Change player for the next turn
        currentplayer = (currentplayer == 'X') ? 'O' : 'X';
        return false;
    }
    
     public static void make2(int row, int col) {
        if (board[row][col] == ' ') {
            board[row][col] = currentplayer;
            movecount++;
        } else {
            System.out.println("Cell is already occupied, try again.");
        }
    }
    // check if player wins
    public static boolean posewin() {
        // Check rows and columns for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentplayer && board[i][1] == currentplayer && board[i][2] == currentplayer) {
                return true;
            }
            if (board[0][i] == currentplayer && board[1][i] == currentplayer && board[2][i] == currentplayer) {
                return true;
            }
        }
        // Check diagonals for a win
        if (board[0][0] == currentplayer && board[1][1] == currentplayer && board[2][2] == currentplayer) {
            return true;
        }
        if (board[0][2] == currentplayer && board[1][1] == currentplayer && board[2][0] == currentplayer) {
            return true;
        }
        return false;
    }
    public static void playermove(){
        movecount++;
        Go(movecount);
    }
    
    // Function to handle a move by the current player (Go(N)) // taking input
    public static void Go(int moveNumber) {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int col = -1;

        while (true) {
            System.out.println("Player " + currentplayer + ", enter row (0-2) and column (0-2) to make move (" + moveNumber + "): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row <= 2 && col >= 0 && col <= 2 && board[row][col] == ' ') {
                make2(row, col);
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}