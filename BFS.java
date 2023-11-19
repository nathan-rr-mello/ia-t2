import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    
    public static void run(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>();
        int nodesCreated = 0;

        LinkedList<Board> queue = Board.getNextStates(initialBoard);

        while (!queue.isEmpty()) {
            Board curr = queue.removeFirst();
            if (curr.equals(finalState)) {
                Board.findPath(curr);
                System.out.println("nodes created: " + nodesCreated);
                return;
            }
            if (!visited.contains(curr)) {
                queue.addAll(Board.getNextStates(curr));
            }
            visited.add(curr);
            nodesCreated++;
        }
    }

}
