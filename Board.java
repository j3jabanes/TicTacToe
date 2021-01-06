import java.util.ArrayList;
import java.util.List;

public class Board {
    private CellState[][] board = new CellState[][]{
            {CellState.BLANK, CellState.BLANK, CellState.BLANK},
            {CellState.BLANK, CellState.BLANK, CellState.BLANK},
            {CellState.BLANK, CellState.BLANK, CellState.BLANK}
    };


    public CellState[][] getBoard() {
        return board;
    }

    public void place(Integer[] location, CellState player) {
        board[location[0]][location[1]] = player;
    }

    public void printBoard(){
        for(int i = 0; i < board.length; i++){
            System.out.print(i + " ");
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != CellState.BLANK){
                    System.out.print(board[i][j]);
                    if(j + 1 == board[i].length){
                        System.out.print("");
                    }
                    else{
                        System.out.print("|");
                    }
                }

                else{
                    System.out.print(" ");
                    if(j + 1 == board[i].length){
                        System.out.print("");
                    }
                    else{
                        System.out.print("|");
                    }
                }
            }

            if(i + 1 == board.length)
                System.out.print(" ");
            else
                System.out.print("\n  -----\n");
        }
        System.out.println("\n  0 1 2");
        System.out.println();
    }

    public CellState checkWin(){
        CellState checker;
        // check columns
        for(int i = 0; i < board.length; i++){
            checker = board[0][i];
            if(checker == CellState.BLANK){
                continue;
            }
            for(int j = 0; j < board[i].length; j++){
                if(board[j][i] != CellState.BLANK) {
                    checker = null;
                    break;
                }
            }

            if (checker != null) {
                return checker;
            }
        }

        //check rows
        for(int i = 0; i < board.length; i++){
            checker = board[i][0];
            if(checker == CellState.BLANK){
                continue;
            }
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != CellState.BLANK) {
                    checker = null;
                    break;
                }
            }

            if (checker != null) {
                return checker;
            }
        }

        //check diagonals
        checker = board[0][0];
        if (board[1][1] == checker && board[2][2] == checker && checker != CellState.BLANK) {
            return checker;
        }

        checker = board[2][0];
        if (board[1][1] == checker && board[0][2] == checker && checker != CellState.BLANK) {
            return checker;
        }

        //check TIE
        //returns null if it finds a blank box

        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == CellState.BLANK)
                    return null;
            }

        return CellState.BLANK;

    }

    public List<Integer[]> getAvailablePositions(){
        ArrayList<Integer[]> availPos = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Board.CellState.BLANK) {
                    availPos.add(new Integer[]{i, j});
                }
            }
        }
        return availPos;
    }


    public enum CellState{
        O ,X, BLANK
    }
}