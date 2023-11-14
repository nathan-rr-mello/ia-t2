import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DFS {

    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board intialState = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>();
        visited.add(intialState);

        searchState(intialState, finalState, visited);
    }

    private static boolean searchState(Board start, Board target, HashSet<Board> visited) {

        System.out.println(start);
        /* if (visited.contains(start)) {
            return false;
        } */

        visited.add(start);

        if (start.equals(target)) {
            //(print result)
            System.out.println("achou");
            return true;
        }

        List<Board> possibleStates = new ArrayList<>();

        //can move down?
        if (start.emptyX < start.state.length - 1) {
            var b = start.switchEmpty(start.emptyX+1, start.emptyY);
            if (!visited.contains(b)) possibleStates.add(b);
        }
        //can move right?
        if (start.emptyY < start.state[0].length - 1) {
            var b = start.switchEmpty(start.emptyX, start.emptyY+1);
            if (!visited.contains(b)) possibleStates.add(b);
        }
        //can move left?
        if (start.emptyY > 0) {
            var b = start.switchEmpty(start.emptyX, start.emptyY-1);
            if (!visited.contains(b)) possibleStates.add(b);
        }//can move up?
        if (start.emptyX > 0) {
            var b = start.switchEmpty(start.emptyX-1, start.emptyY);
            if (!visited.contains(b)) possibleStates.add(b);
        }
        
        System.out.println(start);
        System.out.println(possibleStates);

        for (var b : possibleStates) {
            if (searchState(b, target, visited)) {
                return true;
            }
        }

        return false;
    }
}
