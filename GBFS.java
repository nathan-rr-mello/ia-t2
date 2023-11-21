import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class GBFS {
    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);

        List<Board> visited = new ArrayList<>();
        LinkedList<Board> queue = new LinkedList<>();

        int nodesCreated = 0;

        queue.add(ordenaFuncaoHeuristica(Board.getNextStates(initialBoard)).get(0));

        while (!queue.isEmpty()) {
            Board currentBoard = queue.removeFirst();

            if (currentBoard.equals(finalState)) {
                Board.findPath(currentBoard);
                System.out.println("nodes created: " + nodesCreated);
                return;
            }

            if (!visited.contains(currentBoard)) {
                visited.add(currentBoard);

                var nextStates = Board.getNextStates(currentBoard);
                nodesCreated += nextStates.size();

                for (Board nextState : ordenaFuncaoHeuristica(nextStates)) {
                    if (!visited.contains(nextState)) {
                        queue.add(nextState);
                    }
                }
            }
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

    private static LinkedList<Board> ordenaFuncaoHeuristica(LinkedList<Board> possibleStates) {
        possibleStates.sort(Comparator.comparingInt(GBFS::funcaoHeuristica));
        return possibleStates;
    }
}
