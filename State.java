import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class State {

    State parent ;
    int cost ;

    String[][] grid;
    int n;
    int m;

    public State() {
        this.n = 0;
        this.m = 0;
    }

    public void set_grid(String[][] grid) {
        this.grid = grid;
    }

    public void set_n(int n) {
        this.n = n;
    }

    public void set_m(int m) {
        this.m = m;
    }

    public String[][] getGrid() {
        return grid;
    }

    public int get_n() {
        return n;
    }

    public int get_m() {
        return m;
    }

    public State(int n, int m, String[][] grid) {
        this.m = m;
        this.n = n;
        /* Deep copy the grid to avoid shallow copy issues */
        this.grid = new String[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, m);
        }
    }

    /////////////////////////// deep copy //////////////////////////
    public State(State state) {
        this.n = state.n;
        this.m = state.m;
        this.grid = new String[n][m];
        // Deep copy each value in passed array, to current instance of game
        for (int i = 0; i < this.n; i++) {
            System.arraycopy(state.grid[i], 0, this.grid[i], 0, this.m);
        }
    }

    /////////////////////////// printing the grid  //////////////////////////
    public static void print_grid(State state) {
        for (int i = 0; i < state.get_n(); i++) {
            for (int j = 0; j < state.get_m(); j++) {
                System.out.print(state.grid[i][j].charAt(0) + " ");
            }
            System.out.println();
        }
    }

    /////////////////////////// winning check  //////////////////////////
    public boolean check_win() {
        boolean win = true;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                if (grid[i][j].charAt(0) != 'X' && grid[i][j].charAt(0) != ' ') {
                    win = false;
                    break;
                }
            }
        }
        return win;
    }

    ////////////////////////////// check_move functions/////////////////////////////////////
    public boolean can_move_right() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = this.m - 1; j >= 0; j--) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = j;
                    while (targetIndex + 1 < this.m &&
                            !Character.isLowerCase(grid[i][targetIndex + 1].charAt(0)) &&
                            grid[i][targetIndex + 1].charAt(0) != 'X') {
                        if (grid[i][targetIndex + 1].charAt(0) == Character.toUpperCase(c)) {
                            return true;
                        }
                        targetIndex++;
                    }
                    if (targetIndex != j) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean can_move_left() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = j;
                    while (targetIndex - 1 >= 0 &&
                            !Character.isLowerCase(grid[i][targetIndex - 1].charAt(0)) &&
                            grid[i][targetIndex - 1].charAt(0) != 'X') {
                        if (grid[i][targetIndex - 1].charAt(0) == Character.toUpperCase(c)) {
                            return true;
                        }
                        targetIndex--;
                    }
                    if (targetIndex != j) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean can_move_up() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = i;
                    while (targetIndex - 1 >= 0 &&
                            !Character.isLowerCase(grid[targetIndex - 1][j].charAt(0)) &&
                            grid[targetIndex - 1][j].charAt(0) != 'X') {
                        if (grid[targetIndex - 1][j].charAt(0) == Character.toUpperCase(c)) {
                            return true;
                        }
                        targetIndex--;
                    }
                    if (targetIndex != i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean can_move_down() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = i;
                    while (targetIndex + 1 < this.n &&
                            !Character.isLowerCase(grid[targetIndex + 1][j].charAt(0)) &&
                            grid[targetIndex + 1][j].charAt(0) != 'X') {
                        if (grid[targetIndex + 1][j].charAt(0) == Character.toUpperCase(c)) {
                            return true; // A move is possible
                        }
                        targetIndex++;
                    }
                    if (targetIndex != i) {
                        return true; // A move is possible
                    }
                }
            }
        }
        return false; // No moves are possible
    }


    ////////////////////////////// moving funcs/////////////////////////////////////
    public State move_right() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = this.m - 1; j >= 0; j--) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = j;
                    boolean found = false;
                    while (targetIndex + 1 < this.m &&
                            !Character.isLowerCase(grid[i][targetIndex + 1].charAt(0)) &&
                            grid[i][targetIndex + 1].charAt(0) != 'X') {
//                        targetIndex++;
                        if (grid[i][targetIndex + 1].charAt(0) == Character.toUpperCase(c)) {
                            //update the new postion
                            grid[i][targetIndex + 1] = " ";
                            found = true;
                            //update the old position
                            if (s.length() > 1) {
                                grid[i][j] = s.substring(1);
                            } else {
                                grid[i][j] = " ";
                            }
                        } else {
                            targetIndex++;
                        }
                    }
                    if (!found && targetIndex != j) {
                        if (s.length() > 1) {
                            grid[i][targetIndex] = c + grid[i][targetIndex];
                        } else {
                            grid[i][targetIndex] = c + grid[i][targetIndex];
                        }
                        if (s.length() > 1) {
                            grid[i][j] = s.substring(1);
                        } else {
                            grid[i][j] = " ";
                        }
                    }
                }
            }
        }
        return this;
    }

    public State move_left() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = j;
                    boolean found = false;
                    while (targetIndex - 1 >= 0 &&
                            !Character.isLowerCase(grid[i][targetIndex - 1].charAt(0)) &&
                            grid[i][targetIndex - 1].charAt(0) != 'X') {
                        if (grid[i][targetIndex - 1].charAt(0) == Character.toUpperCase(c)) {
                            // Update the new position
                            grid[i][targetIndex - 1] = " ";
                            found = true;
                            // Update the old position
                            if (s.length() > 1) {
                                grid[i][j] = s.substring(1);
                            } else {
                                grid[i][j] = " ";
                            }
                        } else {
                            targetIndex--;
                        }
                    }
                    if (!found && targetIndex != j) {
                        if (s.length() > 1) {
                            grid[i][targetIndex] = c + grid[i][targetIndex];
                        } else {
                            grid[i][targetIndex] = c + grid[i][targetIndex];
                        }
                        if (s.length() > 1) {
                            grid[i][j] = s.substring(1);
                        } else {
                            grid[i][j] = " ";
                        }
                    }
                }
            }
        }
        return this;
    }

    public State move_up() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = i;
                    boolean found = false;
                    while (targetIndex - 1 >= 0 &&
                            !Character.isLowerCase(grid[targetIndex - 1][j].charAt(0)) &&
                            grid[targetIndex - 1][j].charAt(0) != 'X') {
                        if (grid[targetIndex - 1][j].charAt(0) == Character.toUpperCase(c)) {
                            // Update the new position
                            grid[targetIndex - 1][j] = " ";
                            found = true;
                            // Update the old position
                            if (s.length() > 1) {
                                grid[i][j] = s.substring(1);
                            } else {
                                grid[i][j] = " ";
                            }
                        } else {
                            targetIndex--;
                        }
                    }
                    if (!found && targetIndex != i) {
                        if (s.length() > 1) {
                            grid[targetIndex][j] = c + grid[targetIndex][j];
                        } else {
                            grid[targetIndex][j] = c + grid[targetIndex][j];
                        }
                        if (s.length() > 1) {
                            grid[i][j] = s.substring(1);
                        } else {
                            grid[i][j] = " ";
                        }
                    }
                }
            }
        }
        return this;
    }

    public State move_down() {
        for (int i = this.n - 1; i >= 0; i--) {
            for (int j = 0; j < this.m; j++) {
                String s = grid[i][j];
                if (Character.isLowerCase(s.charAt(0))) {
                    char c = s.charAt(0);
                    int targetIndex = i;
                    boolean found = false;
                    while (targetIndex + 1 < this.n &&
                            !Character.isLowerCase(grid[targetIndex + 1][j].charAt(0)) &&
                            grid[targetIndex + 1][j].charAt(0) != 'X') {
                        if (grid[targetIndex + 1][j].charAt(0) == Character.toUpperCase(c)) {
                            // Update the new position
                            grid[targetIndex + 1][j] = " ";
                            found = true;
                            // Update the old position
                            if (s.length() > 1) {
                                grid[i][j] = s.substring(1);
                            } else {
                                grid[i][j] = " ";
                            }
                        } else {
                            targetIndex++;
                        }
                    }
                    if (!found && targetIndex != i) {
                        if (s.length() > 1) {
                            grid[targetIndex][j] = c + grid[targetIndex][j];
                        } else {
                            grid[targetIndex][j] = c + grid[targetIndex][j];
                        }
                        if (s.length() > 1) {
                            grid[i][j] = s.substring(1);
                        } else {
                            grid[i][j] = " ";
                        }
                    }
                }
            }
        }
        return this;
    }


    ////////////////////////////// next state /////////////////////////////////////
    public ArrayList<State> next_state(State state) {
        ArrayList<State> can = new ArrayList<>();
        if (state.can_move_right()) {
            State news = new State(state);
            news.move_right();
            can.add(news);
        }
        if (state.can_move_left()) {
            State news = new State(state);
            news.move_left();
            can.add(news);
        }
        if (state.can_move_down()) {
            State news = new State(state);
            news.move_down();
            can.add(news);
        }
        if (state.can_move_up()) {
            State news = new State(state);
            news.move_up();
            can.add(news);
        }
        return can;

    }

    ////////////////////////////// check if equal /////////////////////////////////////

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        //creates a new reference to the existing object.
        State newstate = (State)obj;
        return Arrays.deepEquals(newstate.getGrid(),this.getGrid());}

    //This method is overridden to provide a hash code for the State object.
    // A hash code is a numerical value that represents an object.
    // It's used to quickly determine whether two objects are likely to be equal.
    @Override
    public int hashCode() {
        return Objects.hash(n, m, Arrays.deepHashCode(grid));
    }

}