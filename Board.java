import java.util.Arrays;

public class Board {
    int[][] state;
    int emptyX, emptyY;
    Board parent;

    private Board(int[][] state, int x, int y, Board parent) {
        this.state = state;
        this.emptyX = x;
        this.emptyY = y;
        this.parent = parent;
    }

    Board(int[][] state, int x, int y) {
        this.state = state;
        this.emptyX = x;
        this.emptyY = y;
    }

    Board(int[][] state) {
        this.state = state;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == 0) {
                    this.emptyX = i;
                    this.emptyY = j;
                }
            }
        }
    }

    private int[][] copyState() {
        int[][] copy = new int[state.length][state[0].length];
        for (var i = 0; i < state.length; i++) {
            copy[i] = state[i].clone();
        } 

        return copy;    
    }

    public Board switchEmpty(int x, int y) {
        var copy =  copyState();
        int elem = copy[x][y];
        copy[x][y] = 0;
        copy[emptyX][emptyY] = elem;
        return new Board(copy, x, y, this);
    }

    public Board clone() {
        return new Board(copyState(), emptyX, emptyY);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] line : state) {
            builder.append(Arrays.toString(line));
            builder.append('\n');
        }
        return builder.toString();
    }

    public boolean equals(Board b) {
        if (b.state.length != state.length || b.state[0].length != state[0].length) {
            return false;
        }

        for (int i = 0; i < b.state.length; i++) {
            if (!Arrays.equals(state[i], b.state[i])) {
                return false;
            }
        }

        return true;
    }
}