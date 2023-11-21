import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class GBFS {
    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {

        //variaveis para a execuçao do codigo
        Board initialBoard = new Board(initialMatrix, x, y);
        Board finalState = new Board(finalMatrix);

        List<Board> visited = new ArrayList<>();
        LinkedList<Board> queue = new LinkedList<>();

        int nodesCreated = 0;
        nodesCreated++; //adiciona o estado inicial na contagem de nodos criados

        //adiciona o board inicial
        queue.add(ordenaFuncaoHeuristica(Board.getNextStates(initialBoard)).get(0));

        while (!queue.isEmpty()) {
            Board currentBoard = queue.removeFirst();

            //se o estado atual for igual o estado desejado paramos a execucao e imprimimos o caminho e numero de nodos
            if (currentBoard.equals(finalState)) {
                Board.findPath(currentBoard);
                System.out.println("nodes created: " + nodesCreated);
                return;
            }

            //se o estado atual ainda não foi visitado
            if (!visited.contains(currentBoard)) {
                visited.add(currentBoard);

                var nextStates = Board.getNextStates(currentBoard);
                nodesCreated += nextStates.size();

                //percorre todos os proximos estados já ordenados pela funcao heuristica
                for (Board nextState : ordenaFuncaoHeuristica(nextStates)) {
                    //se ele não foi visitado ele é adicionado como proximo estado
                    if (!visited.contains(nextState)) {
                        queue.add(nextState);
                    }
                }
            }
        }
    }

    private static int funcaoHeuristica(Board currentBoard) {
        int distance = 0;
        //percorre o X e o Y
        for (int i = 0; i < currentBoard.state.length; i++) {
            for (int j = 0; j < currentBoard.state[0].length; j++) {
                var element = currentBoard.state[i][j];
                //se o elemento nao for zero calcula a distancia entre a posicao atual e a posicao desejada
                if (element != 0) {
                  distance += Board.desirePosition(element, i, j);
                }
            }
        }
        return distance;
    }

    //usa a funçao heuristica para ordenar a lista de posiveis estados
    private static LinkedList<Board> ordenaFuncaoHeuristica(LinkedList<Board> possibleStates) {
        possibleStates.sort(Comparator.comparingInt(GBFS::funcaoHeuristica));
        return possibleStates;
    }
}
