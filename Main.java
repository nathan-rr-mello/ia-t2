public class Main {

    static void solveBreadthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        BFS.run(initialMatrix, finalMatrix, x, y);
    }

    static void solveDepthFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        DFS.run(initialMatrix, finalMatrix, x, y);
    }

    static void solveGreedyBestFirst(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        GBFS.run(initialMatrix, finalMatrix, x, y);
    }

    static void solveAStar(int[][] initialMatrix, int[][] finalMatrix, int x, int y) {
        AStar.run(initialMatrix, finalMatrix, x, y);
    }

    public static void main(String[] args) {
       // tabela 1
    //    int[][] currentMatrix = {
    //            {1,2,3},
    //            {4,5,6},
    //            {0,7,8},
    //    };
    //    int emptyX = 2;
    //    int emptyY = 0;
//
       // tabela 2
    //    int[][] currentMatrix = {
    //            {1,3,0},
    //            {4,2,5},
    //            {7,8,6},
    //    };
    //    int emptyX = 0;
    //    int emptyY = 2;
//
        // tabela 3
        // int[][] currentMatrix = {
        //         {1,3,5},
        //         {2,6,0},
        //         {4,7,8},
        // };
        // int emptyX = 1;
        // int emptyY = 2;

//        // tabela 4
    //    int[][] currentMatrix = {
    //            {1,8,3},
    //            {4,2,6},
    //            {7,5,0},
    //    };
    //    int emptyX = 2;
    //    int emptyY = 2;
//
//        // tabela 5
       int[][] currentMatrix = {
               {1,2,3},
               {7,0,6},
               {4,8,5},
       };
       int emptyX = 1;
       int emptyY = 1;


        int[][] targetMatrix = {
            {1,2,3},
            {4,5,6},
            {7,8,0}
        };

        
        // BFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
        DFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
        // GBFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
        // AStar.run(currentMatrix, targetMatrix, emptyX, emptyY);
    }
}