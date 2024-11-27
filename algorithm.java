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



}