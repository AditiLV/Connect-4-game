package A4_connect4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
*/
//import A_connect4.*;
import static java.lang.System.exit;
import java.security.SecureRandom;

/**
 *
 * @author 3a
 */
public class player implements Runnable
{

    public static SecureRandom random_num = new SecureRandom();
    private Board b;
    private int[][] board;
private int playervalue;

    public player(Board b,int playervalue)
    {
    this.b=b;
    this.setplayervalue(playervalue);
    this.board = new int[6][7];
    }

   

    public int getplayervalue() {
        return playervalue;
    }

    public void setplayervalue(int playervalue) {
        this.playervalue = playervalue;
    }
    @Override
    public void run() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if(b.getGameOver())
        {
            exit(0);
        } 
     else
        {
            try
            {
                        b.playerMove(playervalue);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
        
    
}
