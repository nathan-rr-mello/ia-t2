import java.util.*;
public class AStar {

    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
    PriorityQueue<Board> open = new PriorityQueue<Board>(Comparator.comparingInt(board -> board.estimatedTotalCost)); //PriorityQueue de nodos abertos
    Set<String> closed = new HashSet<String>(); //Conjunto de nodos fechados
    //esse conjunto é de strings para resolver um problema em que a comparação entre matrizes não funcionava corretamente

    Board initialBoard = new Board(initialMatrix, x, y); //inicializa estado inicial 
    Board finalState = new Board(finalMatrix);           //e final

    open.add(initialBoard); //adiciona o estado inicial na fila de prioridade
    while (!open.isEmpty()) { //enquanto a fila de prioridade não estiver vazia
        
        Board currentBoard = open.poll(); //retira o primeiro da fila
        

        if (currentBoard.equals(finalState)) { //testa se é a solução correta, se for: 
            System.out.println("\nnodes created: " + (closed.size() + open.size())); //imprime o número de nodos criados
            System.out.println("\nPath generated: "); //imprime o caminho gerado
            currentBoard.findPath(currentBoard);
            return; //finaliza a execução
        } // e se não: 

        closed.add(Arrays.deepToString(currentBoard.state)); //adiciona o estado atual no conjunto de nodos fechados

        List<Board> nextStates = Board.getNextStates(currentBoard); //gera os filhos desse nodo

        for (Board nextBoard : nextStates) { //percorre os filhos que podem ser obtidos

            String nextBoardString = Arrays.deepToString(nextBoard.state); //método que transforma a matriz em uma string (para resolver o problema citado na linha 7)

            if (!closed.contains(nextBoardString)) { //se o conjunto de nodos fechados não contém o filho gerado:
                nextBoard.estimatedHeuristic = calculateMisplacedTiles(nextBoard.state); //calcula a heurística do filho
                nextBoard.costFromStart = currentBoard.costFromStart + 1;
                nextBoard.estimatedTotalCost = nextBoard.costFromStart + nextBoard.estimatedHeuristic;
                open.add(nextBoard); //adiciona o filho na fila de prioridade
            }
        }
    }
        System.out.println("Não foi possível encontrar uma solução"); //se terminou a execução e não encontrou nenhum filho, não foi possível encontrar uma solução
    }

    public static int calculateMisplacedTiles(int receivedMatrix[][]) { //funcao heuristica que calcula quantas peças estão fora do lugar
        int finalMatrix[][] = {{1,2,3},{4,5,6},{7,8,0}};
        int misplacedTiles = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){ //dois laços para percorrer a matriz
                if(finalMatrix[i][j] != receivedMatrix[i][j] && finalMatrix[i][j] != 0){ //se o valor da matriz recebida estiver na posição correta não precisa calcular a distância
                                                                                               //também não precisa calcular se o valor for a casa vazia (0)
                    misplacedTiles++; //atualiza a quantidade de peças fora do lugar
                }
            }
        }
        return misplacedTiles;
    }

}
