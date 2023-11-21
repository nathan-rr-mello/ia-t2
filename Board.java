import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import org.w3c.dom.Node;

public class Board { //classe que representa o estado do jogo
    int[][] state;
    int emptyX, emptyY;
    Board parent;
    final static String[] target = {"00", "01", "02", "10", "11", "12", "20", "21"};

    int costFromStart = 0; //variaveis para o A*
    int estimatedHeuristic = 0;
    int estimatedTotalCost = 0;

    //utilizado para determinar o pai e o filho sempre que cria um novo board
    private Board(int[][] state, int x, int y, Board parent) {
        this.state = state;
        this.emptyX = x;
        this.emptyY = y;
        this.parent = parent;
    }

    //cria o board quando tem a posicao X e Y do 0
    Board(int[][] state, int x, int y) {
        this.state = state;
        this.emptyX = x;
        this.emptyY = y;
    }

    //cria o board quando nao tem a posicao X e Y do 0
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

    private int[][] copyState() { //metodo que copia o estado atual, para que ele possa ser alterado sem alterar o estado do nodo pai
        int[][] copy = new int[state.length][state[0].length];
        for (var i = 0; i < state.length; i++) {
            copy[i] = Arrays.copyOf(state[i], state[i].length);
        } 

        return copy;    
    }

    public Board switchEmpty(int x, int y) { //metodo que troca a casa vazia com uma casa
        var copy =  copyState();
        int elem = copy[x][y];
        copy[x][y] = 0;
        copy[emptyX][emptyY] = elem;
        return new Board(copy, x, y, this);
    }

    public Board clone() { //metodo que clona o estado atual
        return new Board(copyState(), emptyX, emptyY);
    }

    public String toString() { //metodo para imprimir corretamente o estado atual
        StringBuilder builder = new StringBuilder();
        for (int[] line : state) {
            builder.append(Arrays.toString(line));
            builder.append('\n');
        }
        return builder.toString();
    }

    public boolean equals(Board b) { //metodo que testa igualdade de dois estados
        if (b.state.length != this.state.length) {
            return false;
        }

        for (int i = 0; i < b.state.length; i++) {
            if (!Arrays.equals(this.state[i], b.state[i])) {
                return false;
            }
        }

        return true;
    }

    //metodo recursivo que acha o caminho completo do ultimo nodo e imprime no terminal
    public static void findPath(Board board) {
        if (board == null) {
            return;
        }

        findPath(board.parent);

        System.out.println(board);
    }

    //metodo que calcula os proximos estados do board com base no estado atual
    public static LinkedList<Board> getNextStates(Board current) {
        LinkedList<Board> possibleStates = new LinkedList<>();
        //can move down?
        if (current.emptyX < current.state.length - 1) {
            var b = current.switchEmpty(current.emptyX + 1, current.emptyY);
            possibleStates.add(b);
        }
        //can move right?
        if (current.emptyY < current.state[0].length - 1) {
            var b = current.switchEmpty(current.emptyX, current.emptyY + 1);
            possibleStates.add(b);
        }
        //can move left?
        if (current.emptyY > 0) {
            var b = current.switchEmpty(current.emptyX, current.emptyY - 1);
            possibleStates.add(b);
        }
        //can move up?
        if (current.emptyX > 0) {
            var b = current.switchEmpty(current.emptyX - 1, current.emptyY);
            possibleStates.add(b);
        }
        return possibleStates;
    }

    //metodo que calcula a posicao desejada
    public static int desirePosition(int i, int x, int y) {
        //variaveis que calculam a posicao desejada
        var goalX = Integer.parseInt(target[i-1].substring(0,1));
        var goalY = Integer.parseInt(target[i-1].substring(1));
        //retorna a posicao desejada - a posicao atual
        return Math.abs(goalX - x) + Math.abs(goalY - y);
    }

    // override equals() e hashCode() para comparar e armazenar corretamente com HashSet
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Board other = (Board) obj;
        return Arrays.deepEquals(this.state, other.state) && this.emptyX == other.emptyX && this.emptyY == other.emptyY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(this.state), this.emptyX, this.emptyY);
    }
}