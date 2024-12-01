import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class algorithm {
    Queue<State> qu = new LinkedList<>();
    Stack<State> st = new Stack<>();


    public void BFS(State initial_state) {
        int v = 1;
        int p = 0;
        ArrayList<State> visited = new ArrayList<>();
        qu.add(initial_state);

        while (!qu.isEmpty()) {
            State currentState = qu.poll();
            visited.add(initial_state);

            if (currentState.check_win()) {
                Stack<State> path = new Stack<>();
                while (currentState != null) {
                    path.add(currentState);
                    currentState = currentState.parent;
                }

                while (!path.isEmpty()) {
                    State state = path.pop();
                    State.print_grid(state);
                    p++;
                }
                break;
            }

            for (State nextState : currentState.next_state(currentState)) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    v++;
                    nextState.parent = currentState; // Set the parent for path reconstruction
                    qu.add(nextState);
                }
            }
        }
        System.out.println("visited length : " + v);
        System.out.println("path length : " + p);


    }

    //////////////////////////////////////////////////////////////////////////


    public void DFS(State initial_state) {
        int v = 1;
        int p = 0;
        ArrayList<State> visited = new ArrayList<>();
        st.push(initial_state);
        visited.add(initial_state);

        while (!st.isEmpty()) {
            State currentState = st.pop();

            if (currentState.check_win()) {
                Stack<State> path = new Stack<>();
                // Reconstruct the path from the winning state to the initial state
                while (currentState != null) {
                    path.push(currentState);
                    currentState = currentState.parent;
                    p++;
                }

                // Print the path in reverse order (from initial state to winning state)
                while (!path.isEmpty()) {
                    State state = path.pop();
                    State.print_grid(state);
                }
                break;
            }

            for (State nextState : currentState.next_state(currentState)) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    v++;
                    nextState.parent = currentState;
                    st.push(nextState);
                }
            }
        }
        System.out.println("visited length : " + v);
        System.out.println("path length : " + p);
    }


    //////////////////////////////////////////////////////////////////////////

    ArrayList<State> visited = new ArrayList<>();
    private int v = 1;
    private int p = 0;

    public boolean DFS_R(State currentState) {

        if (currentState.check_win()) {
            State.print_grid(currentState);
            return true;
        } else
            State.print_grid(currentState);


        visited.add(currentState);

        if (currentState.check_win()) {
            Stack<State> path = new Stack<>();
            while (currentState != null) {
                path.add(currentState);
                currentState = currentState.parent;
            }


        }

        assert currentState != null;
        for (State nextState : currentState.next_state(currentState)) {
            if (!visited.contains(nextState)) {
                visited.add(nextState);
                v++;
                nextState.parent = currentState;

                boolean flag = DFS_R(nextState);
                if (flag) {
                    return true;
                }
//                System.out.println("visited length : " + v);
//                System.out.println("path length : " + p);
            }
        }
        return false;
    }

//////////////////////////////////////////////////////////////////////////

    public void UCS(State initial_state) {
        int v = 1;
        int p = 0;
        initial_state.cost = 0;
        ArrayList<State> visited = new ArrayList<>();
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        pq.add(initial_state);

        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            visited.add(currentState);

            if (currentState.check_win()) {
                Stack<State> path = new Stack<>();
                while (currentState != null) {
                    path.add(currentState);
                    currentState = currentState.parent;
                }

                while (!path.isEmpty()) {
                    State state = path.pop();
                    State.print_grid(state);
                    p++;
                }
                break;
            }

            for (State nextState : currentState.next_state(currentState)) {
                int newCost = currentState.cost + 1;
                if (!visited.contains(nextState) || newCost < nextState.cost) {
                    visited.add(nextState);
                    v++;
                    nextState.parent = currentState; // Set the parent for path reconstruction
                    nextState.cost = newCost; // Update the cost of the next state
                    pq.add(nextState);
                }
            }
        }
        System.out.println("visited length : " + v);
        System.out.println("path length : " + p);
    }


    public int count_heuristic(State state) {
        int totalDistance = 0;

        for (int i = 0; i < state.grid.length; i++) {
            for (int j = 0; j < state.grid[i].length; j++) {
                String s = state.grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = c - 'a';

                    int targetX = targetIndex / state.grid[0].length;
                    int targetY = targetIndex % state.grid[0].length;
                    totalDistance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return totalDistance;
    }

    public void A_star(State initial_state) {
        int v = 1;
        int p = 0;
        initial_state.cost = 0;
        ArrayList<State> visited = new ArrayList<>();
        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost + count_heuristic(a), b.cost + count_heuristic(b)));
        pq.add(initial_state);

        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            visited.add(currentState);

            if (currentState.check_win()) {
                Stack<State> path = new Stack<>();
                while (currentState != null) {
                    path.add(currentState);
                    currentState = currentState.parent;
                }

                while (!path.isEmpty()) {
                    State state = path.pop();
                    State.print_grid(state);
                    p++;
                }
                break;
            }

            for (State nextState : currentState.next_state(currentState)) {
                int newCost = currentState.cost + 1;
                if (!visited.contains(nextState) || newCost < nextState.cost) {
                    visited.add(nextState);
                    v++;
                    nextState.parent = currentState;
                    nextState.cost = newCost;
                    pq.add(nextState);
                }
            }
        }
        System.out.println("visited length : " + v);
        System.out.println("path length : " + p);
    }


    public void hill_Climbing(State initialState) {
        Set<State> visited = new HashSet<>();
        int v = 1;
        int p = 0;

        State currentState = initialState;
        currentState.cost = 0;

        while (true) {
            visited.add(currentState);

            if (currentState.check_win()) {
                Stack<State> path = new Stack<>();
                while (currentState != null) {
                    path.push(currentState);
                    currentState = currentState.parent;
                }

                while (!path.isEmpty()) {
                    State state = path.pop();
                    state.getGrid();
                    p++;
                }
                break;
            }

            State bestNeighbor = null;
            int bestCost = currentState.cost + count_heuristic(currentState);

            for (State neighbor : currentState.next_state(currentState)) {
                if (!visited.contains(neighbor)) {
                    int neighborCost = neighbor.cost + count_heuristic(neighbor);
                    if (neighborCost < bestCost) {
                        bestNeighbor = neighbor;
                        bestCost = neighborCost;
                    }
                }
            }

            if (bestNeighbor == null) {
                break;
            }

            currentState = bestNeighbor;
            v++;
        }

        System.out.println("visited length : " + v);
        System.out.println("path length : " + p);
    }
}