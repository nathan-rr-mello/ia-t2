import java.util.*;
public class DFS {
    
    public static void run(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>();

        Stack<Board> toBeVisited = getNextStates(initialBoard);
        // System.out.println("toBeVisited");
        // System.out.println(toBeVisited);

        int totalNodes = 0;

        while (!toBeVisited.isEmpty()) {
            Board curr = toBeVisited.pop();

            // System.out.println("Current State:");
            // System.out.println(curr);
            if (curr.equals(finalState)) {
                System.out.println("Encontrou!");
                System.out.printf("\nNumber of nodes created: %d\n", totalNodes);
                System.out.println("Target:");
                System.out.println(curr);
                findPath(curr);
                return;
            }
            if (!visited.contains(curr)) {
                Stack<Board> elementsToAdd = getNextStates(curr);
                for (Board element : elementsToAdd) {
                    toBeVisited.push(element);
                }

                visited.add(curr);
                totalNodes += elementsToAdd.size();
            }
            else if(visited.contains(curr)) {
                continue;
                //System.out.println("visited:");
                //toBeVisited.pop();
                // visited.clear();
                //System.out.println(visited);
                //toBeVisited.remove(curr.parent);
            }
        }
    }

    private static Stack<Board> getNextStates(Board current) {
        Stack<Board> possibleStates = new Stack<Board>();

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

        // findPath(board.parent);
        // System.out.println("board");
        // System.out.println(board);

        System.out.println("States until target:");
        while (board.parent != null) {
            System.out.println(board.parent.toString());
            // System.out.println("---------");
            board = board.parent;
        }
        // System.out.println("Target:");
        // System.out.println(board.toString());
    }
}