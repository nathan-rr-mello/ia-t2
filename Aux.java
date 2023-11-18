import java.util.LinkedList;

public class Aux {
    public static void findPath(Board board) {
        if (board == null) {
            return;
        }

        findPath(board.parent);

        System.out.println(board);
    }

    public static LinkedList<Board> getNextStates(Board current) {
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
        }
        //can move up?
        if (current.emptyX > 0) {
            var b = current.switchEmpty(current.emptyX-1, current.emptyY);
            possibleStates.add(b);
        }

        return possibleStates;
    }
}
