public class play_logic {

    public State move_right(State new_state) {
        if (new_state.can_move_right()) {
            State newState = new State(new_state);
            newState.move_right();
            //if it can't move return the previous state
            return newState;
        }
            return new_state;
        }


    public State move_left(State new_state) {
        if (new_state.can_move_left()) {
            State newState = new State(new_state);
            newState.move_left();
            return newState;
        }
        return new_state ;
    }

    public State move_up(State new_state) {
        if (new_state.can_move_up()) {
            State newState = new State(new_state);
            newState.move_up();
            return newState;
        }
        return new_state ;
    }


    public State move_down(State new_state) {
        if(new_state.can_move_down()){
        State newState = new State(new_state);
        newState.move_down();
        return newState;
    }
        return new_state;
}


}
