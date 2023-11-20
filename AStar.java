import java.util.*;
public class AStar {
    public static void run (int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
    PriorityQueue<Board> open = new PriorityQueue<Board>(Comparator.comparingInt(board -> board.estimatedTotalCost));
    Set<String> closed = new HashSet<String>();
    Board initialBoard = new Board(initialMatrix, x, y);
    Board finalState = new Board(finalMatrix);
    boolean found = false;
    //  0, calculateMisplacedTiles(initialMatrix),

    open.add(initialBoard);
    while (!open.isEmpty()) {
        
        Board currentBoard = open.poll();
        

        if (currentBoard.equals(finalState)) {
            found = true;
            System.out.println("\nnodes created: " + (closed.size() + open.size()));
            System.out.println("\nPath generated: ");
            currentBoard.findPath(currentBoard);
            return;
        }
        closed.add(Arrays.deepToString(currentBoard.state));

        List<Board> nextStates = Board.getNextStates(currentBoard);
        for (Board nextBoard : nextStates) {
            String nextBoardString = Arrays.deepToString(nextBoard.state);
            if (!closed.contains(nextBoardString)) {
                nextBoard.estimatedHeuristic = calculateMisplacedTiles(nextBoard.state);
                nextBoard.costFromStart = currentBoard.costFromStart + 1;
                nextBoard.estimatedTotalCost = nextBoard.costFromStart + nextBoard.estimatedHeuristic;
                // System.out.println("estimativas: " + nextBoard.estimatedHeuristic + " " + nextBoard.costFromStart + " " + nextBoard.estimatedTotalCost);

                open.add(nextBoard);
                // System.out.println(nextBoard);
            }
        }

        // System.out.println("----------------------");
    }
    if (!found){
        System.out.println("Não foi possível encontrar uma solução");
    }

    }

    public static int calculateMisplacedTiles(int receivedMatrix[][]) {
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
