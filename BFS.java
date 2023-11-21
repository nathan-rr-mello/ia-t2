import java.util.HashSet;
import java.util.LinkedList;

public class BFS {
    
    public static void run(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y); //inicializa o estado inicial e final
        Board finalState = new Board(finalMatrix);

        HashSet<Board> visited = new HashSet<>(); //arranjo que guarda os estados já visitados
        int nodesCreated = 0;

        LinkedList<Board> queue = Board.getNextStates(initialBoard); //fila que guarda os estados possíveis a serem visitados
        nodesCreated++; //adiciona o estado inicial na contagem de nodos criados

        while (!queue.isEmpty()) { //repete até que a fila esteja vazia
            Board curr = queue.removeFirst();
            if (curr.equals(finalState)) {//se o estado atual for igual ao estado final, imprime o caminho percorrido
                Board.findPath(curr);     //e o número de nodos criados e encerra o programa
                System.out.println("nodes created: " + nodesCreated);
                return;
            }
            if (!visited.contains(curr)) { //se o estado atual ainda não foi visitado, adiciona seus filhos na fila
                LinkedList<Board> nextStates = Board.getNextStates(curr);
                nodesCreated += nextStates.size();
                queue.addAll(nextStates);
            }
            visited.add(curr); //adiciona o estado atual no conjunto de estados já visitados
        }
    }

}
