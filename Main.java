import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        State state = new State();
        Grid grid = new Grid();

////////////////////////////// game levels /////////////////////////////////////
//    grid.level_one(state);
//    grid.level_tow(state);
    grid.level_three(state);
//    grid.level_sex(state);
//    grid.level_seven(state);


    State.print_grid(state);
    System.out.println("----------------------");
    State newState = new State(state);


    ////////////////////////////// user playing /////////////////////////////////////
//   play new_play = new play();
//   new_play.start_play(newState);


   algorithm n = new algorithm();
   ////////////////////////////// BFS /////////////////////////////////////
//   System.out.println("the BFS ");
//   n.BFS(state);
//   System.out.println("done");
//
//   System.out.println("-----------------------");

   ////////////////////////////// DFS /////////////////////////////////////
//   System.out.println("the DFS ");
//   n.DFS(state);
//   System.out.println("done");
//
//   System.out.println("-----------------------");

   ////////////////////////////// DFS in recursion /////////////////////////////////////
//   System.out.println("the DFS_R ");
//   n.DFS_R(state);
//   System.out.println("done");
//
//   System.out.println("-----------------------");

   ////////////////////////////// UCS /////////////////////////////////////
//   System.out.println("the UCS ");
//   n.UCS(state);
//   System.out.println("done");

////////////////////////////// heuristic /////////////////////////////////////
        System.out.println("the heuristic ");
        System.out.println(n.count_heuristic(state));
        System.out.println("done");



////////////////////////////// equal /////////////////////////////////////
//        State test = new State();
//        State test = new State(newState);
//        boolean areEqual = test.equals(newState);
//        if (areEqual) {
//            System.out.println("The two states are equal.");
//        } else {
//            System.out.println("The two states are not equal.");
//        }





    }
}


