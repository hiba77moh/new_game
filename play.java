import java.util.Scanner;

public class play {
    Scanner scanner = new Scanner(System.in);

    play_logic play = new play_logic();

    public void start_play(State new_state) {
        while (!new_state.check_win()) {
            System.out.print("enter a move : ");
            char move = scanner.next().charAt(0);
            switch (move) {
                case 'd':
                    new_state = play.move_right(new_state);
                    break;
                case 'a':
                    new_state = play.move_left(new_state);
                    break;
                case 's':
                    new_state = play.move_down(new_state);
                    break;
                case 'w':
                    new_state = play.move_up(new_state);
                    break;
                default:
                    System.out.println("Invalid move");
                    break;
            }
            State.print_grid(new_state);
            if (new_state.check_win()) {
                System.out.println("winner");
                break;
            } else
                System.out.println("keep moving");
        }
    }



}
