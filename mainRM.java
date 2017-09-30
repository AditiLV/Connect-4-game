package A4_connect4;


//import .*;
//import A_connect4.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 3a
 */
public class mainRM {
    
      public static void main(String[] args) {
        // TODO code application logic here
        Board b = new Board();
        b.Init_board();
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        refree rf = new refree(b);
        player player1 = new player(b,1);
        player player2 = new player(b,2);
        
        es.execute(rf);
        es.execute(player1);
        es.execute(player2);
        
    }
     
}
