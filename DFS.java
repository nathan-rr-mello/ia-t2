import java.util.*;
public class DFS {
    
    public static void run(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);
        HashSet<Board> visited = new HashSet<>(); // hashset que guarda os estados já visitados

        Stack<Board> toBeVisited = getNextStates(initialBoard); // pilha que guarda os estados possíveis a serem visitados

        int totalNodes = 0; // variável para guardar a quantidade total de nodos criados
        totalNodes++; //adiciona o estado inicial na contagem de nodos criados

        while (!toBeVisited.isEmpty()) {
            Board curr = toBeVisited.pop(); // se acordo com a política LIFO, visita o elemento do topo da pilha

            if (curr.equals(finalState)) { // se o estado atual for igual ao target, procura a sequência de estados que levam até ele
                System.out.println("Encontrou!");
                System.out.println("Path to taget:");
                //Board.findPath(curr);
                System.out.printf("\nNumber of nodes created: %d\n", totalNodes);
                return;
            }
            if (!visited.contains(curr)) { // se ainda não visitou o estado atual, procura os estados possíveis e os adiciona na pilha dos nodos a serem explorados
                Stack<Board> elementsToAdd = getNextStates(curr);
                for (Board element : elementsToAdd) {
                    toBeVisited.push(element);
                }

                visited.add(curr); // adiciona o estado atual no set de estados já visitados
                totalNodes += elementsToAdd.size(); // incrementa os nodos criados
            }
            else { // não explora se já passou pelo estado atual
                continue;
            }
        }
    }

    private static Stack<Board> getNextStates(Board current) { // função que devolve os estados possíveis a serem explorados a partir do estado atual
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

    private static void findPath(Board board) { // função para printar a sequência de estados que levam até o estado resposta
        if (board == null) {
            return;
        }

        System.out.println("States until target:");
        while (board.parent != null) {
            System.out.println(board.parent.toString());
            board = board.parent;
        }
       
    }
}