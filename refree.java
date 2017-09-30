package A4_connect4;

//import A_connect4.*;
import static java.lang.System.exit;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 3a
 */
public class refree implements Runnable
{
private Board b;
//private final int row=6;
//private final int col=7;
private int[][] board= new int[6][7];

public refree(Board b)
{
    this.b=b;
  //  board = new char[b.getRow()][b.getCol()];
}


    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    b.WinCheck();
        b.checkTie();

    }
    
}
