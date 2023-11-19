import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class GBFS {
    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        List<Board> visited = new ArrayList<>();
        int nodesCreated = 0;
        LinkedList<Board> queue = new LinkedList<>();

        queue.add(ordenaFuncaoHeuristica(Board.getNextStates(initialBoard), initialBoard));
        while (!queue.isEmpty()) {
            Board currentBoard = queue.removeFirst();
            if (currentBoard.equals(finalState)) {
                System.out.println(currentBoard);
                System.out.println("nodes created: " + nodesCreated);
                return;
            }
            if (!visited.contains(currentBoard)) {
                queue.add(ordenaFuncaoHeuristica(Board.getNextStates(currentBoard), currentBoard));
            }
            visited.add(currentBoard);
            nodesCreated++;
            System.out.println(currentBoard);
        }
    }

    private static int funcaoHeuristica(Board currentBoard) {
        int distance = 0;
        for (int i = 0; i < currentBoard.state.length; i++) {
            for (int j = 0; j < currentBoard.state[0].length; j++) {
                var element = currentBoard.state[i][j];
                if (element != 0) {
                  distance += Board.desirePosition(element, i, j);
                }
            }
        }
        return distance;
    }

    private static Board ordenaFuncaoHeuristica(LinkedList<Board> possibleStates, Board current) {
        possibleStates.sort(Comparator.comparingInt(GBFS::funcaoHeuristica));
        if (current.parent != null && possibleStates.get(0).equals(current.parent)){
            return possibleStates.get(1);
        }
        return possibleStates.get(0);
    }
}
