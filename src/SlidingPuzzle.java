public class SlidingPuzzle {
    private static final int[][] INITIAL_STATE = {
            {5, 6, 0},
            {1, 2, 3},
            {4, 7, 8}};

    public static void main(String[] args) {
        //início tempo
        long startTime = System.currentTimeMillis();

        //início uso memória
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        //-----------------------
        PuzzleState initialState = new PuzzleState(INITIAL_STATE);

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        int validations = breadthFirstSearch.search(initialState);

        //BetterChoiceSearch betterChoiceSearch = new BetterChoiceSearch();
        //int validations = betterChoiceSearch.search(initialState);
        //-----------------------


        System.out.printf("Quantidade de validações: %d\n", validations);

        //fim uso memória
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("Uso de memória: %,d bytes\n", memoryAfter - memoryBefore);

        //fim tempo
        long endTime = System.currentTimeMillis();
        System.out.printf("Tempo de execução: %.3f\n", (endTime - startTime) / 1000d);
    }
}

