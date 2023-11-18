public class Main {

    public static void main(String[] args) {
        
        int[][] currentMatrix = {
            {1,2,3},
            {0,4,6},
            {7,5,8},
        };

        int emptyX = 1;
        int emptyY = 0;

        int[][] targetMatrix = {
            {1,2,3},
            {7,4,0},
            {5,8,6}
        };


        //DFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
        //BFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
        GBFS.run(currentMatrix, targetMatrix, emptyX, emptyY);
    }
}