public class Grid {

    int n;
    int m;
    String[][] grid;

    public void level_one(State state) {
        this.n = 3;
        this.m = 8;
        this.grid = new String[][] {
                {"X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", "a", " ", " ", " ", " ", "A", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X"}
        };
        state.set_n(n);
        state.set_m(m);
        state.set_grid(grid);
    }

    public void level_tow(State state) {
        this.n = 5;
        this.m = 9;
        this.grid = new String[][] {
                {"X", "X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", " ", " ", "X", " ", " ", " ", " ", "X"},
                {"X", "a", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "A", "X", "X", "X", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X", "X"},
        };
        state.set_n(n);
        state.set_m(m);
        state.set_grid(grid);
    }

    public void level_three(State state) {
        this.n = 8;
        this.m = 13;
        this.grid = new String[][] {
                {" ", " ", "X", "X", "X", "X", "X", "X", "X","X", "X", "X", "X"},
                {" ", " ", "X", "a", " ", " ", " ", " ", " ", " ", " "," ", "X"},
                {"X", "X", "X", " ", " ", "X", "X", "X", "X","X", "X", " ", "X"},
                {"X", " ", " ", " ", " ", " ", "A", " ", " ", " ", "X"," ", "X"},
                {"X", " ", "X", " ", " ", " ", " ", " ", " ", " ", "X"," ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "," ", "X"},
                {"X", "X", "X", "X", " ", " ", " ", " ", " ", " ", " "," ", "X"},
                {" ", " ", " ", " ", "X", "X", "X", "X", "X","X", "X", "X", "X"},
        };
        state.set_n(n);
        state.set_m(m);
        state.set_grid(grid);
    }


    public void level_sex(State state) {
        this.n = 5;
        this.m = 9;
        this.grid = new String[][] {
                {"X", "X", "X", "X", "X", "X", "X", "X", "X"},
                {"X", "A", " ", " ", " ", "X", "X", "X", "X"},
                {"X", " ", " ", "B", " ", " ", "X", "X", "X"},
                {"X", " ", " ", " ", " ", " ", "b", "a", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X", "X"},
        };
        state.set_n(n);
        state.set_m(m);
        state.set_grid(grid);
    }

    public void level_seven(State state) {
        this.n = 8;
        this.m = 9;
        this.grid = new String[][] {
                {" ", " ", " ", " ", "X", "X", "X", "X", "X"},
                {" ", " ", " ", " ", "X", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "A", "X", " ", "X"},
                {"X", " ", " ", " ", "a", " ", "X", " ", "X"},
                {"X", " ", " ", "B", "X", "b", "X", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", " ", " ", " ", " ", " ", " ", " ", "X"},
                {"X", "X", "X", "X", "X", "X", "X", "X", "X"},
        };
        state.set_n(n);
        state.set_m(m);
        state.set_grid(grid);
    }


}