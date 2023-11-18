import java.util.HashSet;
import java.util.LinkedList;

public class GBFS {
    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>();

        LinkedList<Board> queue = ordenaFuncaoHeuristica(Aux.getNextStates(initialBoard));

        int nodesCreated = 0;

        while (!queue.isEmpty()) {
            Board curr = queue.removeFirst();
            if (curr.equals(finalState)) {
                Aux.findPath(curr);
                System.out.println("nodes created: " + nodesCreated);
                return;
            }
            if (!visited.contains(curr)) {
                queue.addAll(ordenaFuncaoHeuristica(Aux.getNextStates(curr)));
            }
            visited.add(curr);
            nodesCreated++;
        }

    }

    private static int funcaoHeuristica(Board currentBoard) {
        int distance = 0;
        for (int i = 0; i < currentBoard.state.length; i++) {
            for (int j = 0; j < currentBoard.state[0].length; j++) {
                if (currentBoard.state[i][j] != 0) {
                    int goalX = currentBoard.state.length - 1 - i;
                    int goalY = currentBoard.state[0].length - 1 - j;
                    distance += Math.abs(goalX - i) + Math.abs(goalY - j);
                }
            }
        }
        return distance;
    }

    private static LinkedList<Board> ordenaFuncaoHeuristica(LinkedList<Board> possibleStates) {
        possibleStates.sort((a, b) -> funcaoHeuristica(a) - funcaoHeuristica(b));
        return possibleStates;
    }


}
