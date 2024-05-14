import java.util.*;

class BreadthFirstSearch {

    int search(PuzzleState puzzleState) {
        Queue<PuzzleState> open = new LinkedList<>();
        HashSet<String> closed = new HashSet<>();
        HashMap<String, PuzzleState> cameFrom = new HashMap<>();

        open.add(puzzleState);
        closed.add(Arrays.deepToString(puzzleState.puzzle));

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
                puzzleState.printPuzzle(path);
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
        }

        System.out.println("Não há solução para o quebra-cabeça.");
        return validationsCount;
    }
}