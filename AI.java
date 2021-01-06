import java.util.ArrayList;
import java.util.Random;

public class AI {
    public static Board.CellState ai = Board.CellState.X;
    public static Board.CellState human = Board.CellState.O;

    public static Integer[] getRandomMove(Board board){
        Random rand = new Random();
        ArrayList<Integer[]> availPos = new ArrayList<>();
        availPos = (ArrayList<Integer[]>) board.getAvailablePositions();

        int random = rand.nextInt(availPos.size());
        return availPos.get(random);
    }

}