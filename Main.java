public class Main {

    public static void main(String[] args) {
        
        int[][] a = {
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

        var bA = new Board(a);
        var bB = new Board(targetMatrix);

        //System.out.println(bA.equals(bB));

        DFS.run(a, targetMatrix, emptyX, emptyY);
        //BFS.run(a, targetMatrix, emptyX, emptyY);
    }
}