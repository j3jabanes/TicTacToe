import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        int level;
        boolean inputValid = true;
        Board board = new Board();
        Scanner in = new Scanner(System.in);
        System.out.println("Level 0 - the agent makes random (but valid) moves");
        System.out.println("Level 1 - the agent uses a hard-coded table that generates a move for every possible state/configuration");
        System.out.println("Level 2 - the agent uses a search strategy to find the “best” move given the current configuration");
        System.out.print("Choose Level (input integer): ");
        level = in.nextInt();
        in.nextLine();
        do{
            inputValid = true;
            if(level < 0 || level > 2)
                inputValid = false;
        }while (!inputValid);
        board.printBoard();

        while (board.checkWin() == null) {
            Integer[] pos;

            do {
                System.out.print("Enter position (x, y): ");
                inputValid = true;
                String[] position = in.nextLine().split(",");
                pos = new Integer[2];
                if(position.length < 2) {
                    inputValid = false;
                    System.out.println("Invalid input");
                    continue;
                }
                try {
                    pos[0] = Integer.parseInt(position[1].trim());
                    pos[1] = Integer.parseInt(position[0].trim());
                } catch (NumberFormatException e) {
                    inputValid = false;
                    System.out.println("Invalid input");
                }
                if((pos[0] < 0 || pos[0] > 2) || (pos[1] < 0 || pos[1] > 2)){
                    inputValid = false;
                    System.out.println("Invalid input");
                    continue;
                }
                if(board.getBoard()[pos[0]][pos[1]] != Board.CellState.BLANK) {
                    inputValid = false;
                    System.out.println("Box already filled");
                }
            } while(!inputValid);

            board.place(pos, Board.CellState.O);

            board.printBoard();
            System.out.println();
            if (board.checkWin() != null) {
                break;
            }
            System.out.println("AI is playing...");
            switch(level){
                case 0 : board.place(AI.getRandomMove(board), Board.CellState.X);break;
                case 1 : break;
                case 2 : break;
            }

            board.printBoard();
        }

        System.out.println("=======================");
        System.out.println("Game Over!");
        board.printBoard();
        if (board.checkWin() == AI.ai) {
            System.out.println("AI WON!");
        } else if (board.checkWin() == AI.human) {
            System.out.println("YOU WON!");
        } else {
            System.out.println("TIE");
        }
    }
}