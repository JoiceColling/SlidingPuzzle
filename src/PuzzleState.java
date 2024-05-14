import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PuzzleState {
    private static final int PUZZLE_SIZE = 3;
    final int[][] FINAL_STATE = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}};

    int[][] puzzle;

    public PuzzleState(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    boolean isFinalState() {
        return Arrays.deepEquals(this.puzzle, FINAL_STATE);
    }

    List<PuzzleState> findPossibleNewStates() {
        List<PuzzleState> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int blankRow = -1;
        int blankCol = -1;
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (this.puzzle[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break;
                }
            }
        }

        for (int[] direction : directions) {
            int newRow = blankRow + direction[0];
            int newCol = blankCol + direction[1];

            if (newRow >= 0 && newRow < PUZZLE_SIZE && newCol >= 0 && newCol < PUZZLE_SIZE) {
                PuzzleState newPuzzle = new PuzzleState(new int[PUZZLE_SIZE][PUZZLE_SIZE]);
                for (int i = 0; i < PUZZLE_SIZE; i++) {
                    newPuzzle.puzzle[i] = this.puzzle[i].clone();
                }
                newPuzzle.puzzle[blankRow][blankCol] = this.puzzle[newRow][newCol];
                newPuzzle.puzzle[newRow][newCol] = 0;
                neighbors.add(newPuzzle);
            }
        }
        return neighbors;
    }

    void printPuzzle(List<PuzzleState> solutions) {
        System.out.println("Solução encontrada: \n");

        for (PuzzleState solution : solutions) {
            for (int i = 0; i < PUZZLE_SIZE; i++) {
                for (int j = 0; j < PUZZLE_SIZE; j++) {
                    System.out.print(solution.puzzle[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.printf("Quantidade de passos: %d\n", solutions.size());
    }
}