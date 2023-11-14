import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    
    public static void run(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>();

        LinkedList<Board> queue = getNextStates(initialBoard);

        while (!queue.isEmpty()) {
            Board curr = queue.removeFirst();
            if (curr.equals(finalState)) {
                System.out.println("achou");
                //System.out.println(curr);
                findPath(curr);
                return;
            }
            if (!visited.contains(curr)) {
                queue.addAll(getNextStates(curr));
            }
            visited.add(curr);
        }
    }

    private static LinkedList<Board> getNextStates(Board current) {
        LinkedList<Board> possibleStates = new LinkedList<>();

        //can move down?
        if (current.emptyX < current.state.length - 1) {
            var b = current.switchEmpty(current.emptyX+1, current.emptyY);
            possibleStates.add(b);
        }
        //can move right?
        if (current.emptyY < current.state[0].length - 1) {
            var b = current.switchEmpty(current.emptyX, current.emptyY+1);
            possibleStates.add(b);
        }
        //can move left?
        if (current.emptyY > 0) {
            var b = current.switchEmpty(current.emptyX, current.emptyY-1);
            possibleStates.add(b);
        }//can move up?
        if (current.emptyX > 0) {
            var b = current.switchEmpty(current.emptyX-1, current.emptyY);
            possibleStates.add(b);
        }

        return possibleStates;
    }

    private static void findPath(Board board) {
        if (board == null) {
            return;
        }

        findPath(board.parent);

        System.out.println(board);
    }
}
