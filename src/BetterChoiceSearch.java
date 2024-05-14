import java.util.*;

class BetterChoiceSearch {

    int search(PuzzleState initialState) {
        Queue<PuzzleState> open = new LinkedList<>();
        HashSet<String> closed = new HashSet<>();
        HashMap<String, PuzzleState> cameFrom = new HashMap<>();

        open.add(initialState);
        closed.add(Arrays.deepToString(initialState.puzzle));

        int validationsCount = 0;
        while (!open.isEmpty()) {
            validationsCount++;
            PuzzleState currentState = open.poll();

            if (currentState.isFinalState()) {
                List<PuzzleState> path = new ArrayList<>();
                PuzzleState current = currentState;
                while (current != null) {
                    path.addFirst(current);
                    String currentStateString = Arrays.deepToString(current.puzzle);
                    current = cameFrom.get(currentStateString);
                }
                initialState.printPuzzle(path);
                return validationsCount;
            }

            List<PuzzleState> foundStates = currentState.findPossibleNewStates();

            for (PuzzleState state : foundStates) {
                String stateString = Arrays.deepToString(state.puzzle);
                if (!closed.contains(stateString)) {
                    open.add(state);
                    closed.add(stateString);
                    cameFrom.put(stateString, currentState);
                }
            }

            // Reordenar os estados abertos pelo mérito heurístico (melhor mais à esquerda)
            List<PuzzleState> openList = new ArrayList<>(open);
            openList.sort(Comparator.comparingInt(s -> calculateHeuristic(s, cameFrom)));
            open.clear();
            open.addAll(openList);
        }

        System.out.println("Não há solução para o quebra-cabeça.");
        return validationsCount;
    }

    private int calculateHeuristic(PuzzleState state, HashMap<String, PuzzleState> cameFrom) {
        // g(n) - custo real do caminho (neste caso, o número de movimentos já realizados)
        int g = 0;
        PuzzleState current = state;
        while (current != null) {
            g++;
            current = cameFrom.get(Arrays.deepToString(current.puzzle));
        }

        // h(n) - número de peças fora do lugar
        int h = 0;
        int[][] finalState = state.FINAL_STATE;
        for (int i = 0; i < state.puzzle.length; i++) {
            for (int j = 0; j < state.puzzle[i].length; j++) {
                if (state.puzzle[i][j] != finalState[i][j]) {
                    h++;
                }
            }
        }

        // f(n) = g(n) + h(n)
        return g + h;
    }
}