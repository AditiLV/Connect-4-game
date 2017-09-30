/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4_connect4;

import static A4_connect4.player.random_num;
import static java.lang.System.exit;

/**
 *
 * @author The 3a
 */
public class Board {
    
   private int[][] board = new int[6][7];// 6row and 7 coloum board
   
   private boolean gameComplete=false; //flag
    
   private boolean pMove=false;
   public Board() {
        this.setBoard(board);
    }
  
/**
 * setting the board positions as per the move of player
 * @param board 
 */
   
    public void setBoard(int[][] board) {
    //initializing the board to 0
    this.board=board;    
    }

    public boolean getmove() {
         return pMove;
    }

    public void setmove(boolean pMove) {
        this.pMove = pMove;
    }
    
    public int[][] getBoard() {
        return board;
    }

    public boolean getGameOver() {
        return gameComplete;
    }

    public void setGameOver(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }

    
     
    public void Init_board()
    {    //  Initializing the board

        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                this.board[i][j]=0;
            }
        }    
    }
     
   
    public void DisplayBoard()
    {//printing the board
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                System.out.print( board[i][j]+"\t");
            }
                System.out.println("");
        }
    }
    
    public synchronized void WinCheck() {
       for(;;)
       {
     
     
        try
        {
            while(getmove()== true)
                wait();
            
            setmove(true);       
            board= getBoard();   
            if(this.VerticalCheck() == 0 && this.DiagonalCheck() == 0 && this.checkTie() == 0)
            {
                setmove(true);
                notifyAll();
            }
            else
            {
                setGameOver(true);
               // System.out.println("Game Complete!!!");
                notifyAll();
                return;
            }
        }
        catch(Exception e){
            System.err.println("Exception in checkWin() \t"+e);
        }
       }
    }
     public  synchronized void  playerMove(int playerMove) throws InterruptedException
    {
        
         
         int i,columnNo,findNewCol=0;
           
                 
         for(;;)
            {
                
                board=getBoard();
                
                
                try
                {
                    
                    while(getmove()== false)
                        wait();
                    setmove(false);
                    if(getGameOver())
                    {
                        notifyAll();
                        exit(0);
                    }
                    else
                    {
                         System.out.printf("Player %d is taking turn\n", playerMove);
                        do
                        {
                  
                            columnNo = random_num.nextInt(7) + 1;
                            System.out.println("\nColumn num is:\t"+columnNo);
                            for(i = 5; i >= 0; i--)
                            { 
                                
                                if(board[i][columnNo - 1] == 0)
                                { 
                                    System.out.println("in if condition of player move");
                                    board[i][columnNo - 1] = playerMove;
                                    setBoard(board);
                                    DisplayBoard();
                                
                                    //System.out.printf("..Referee to Checking for the winner..\n\n");
                                    findNewCol = 0;
                                    notifyAll();
                                    break;
                                }
                                else
                                {
                                    findNewCol = 1;
                                }
                            }
                        }while(findNewCol==1);
                    }
                }
                
                catch(Exception e)
                {
                    System.err.println("exception in playerMove()"+e);
                }   
            }
    }

    public synchronized int checkTie() {
        board= getBoard();
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<7;j++)
            {
                if(board[i][j] == 0){
                    notifyAll();
                    return(0);
                }
                    
            }
            System.out.println("Game Tie");
            notifyAll();
            exit(0);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int VerticalCheck() throws ArrayIndexOutOfBoundsException
        {
          int i,j;
          for(i=0;i<6;i++)
          {
            for(j=0;j<7;j++)
            {
                if((i+1)<6 && (i+2)<6 && (i+3)<6)
                {
                    if(board[i][j]==board[i+1][j] && board[i][j]==board[i+2][j] && board[i][j]==board[i+3][j] &&  board[i][j] != 0)
                    {
                        System.out.println("Verticle Win..!! N Winner is Player " + board[i][j]);
                        return(1);
                    }
                }
               
            }
        }
        return(0);

      }
 
    public int DiagonalCheck()
    {
         for(int i = 0; i < 6;i++)
        {
            for(int j = 0; j < 7; j++)
            {
                if(j+1 < 7 && 
                   j+2 < 7 && 
                   j+3 < 7 && 
                   i+1 < 6 && 
                   i+2 < 6 && 
                   i+3 < 6)
                        
                        {
                    if (( board[i][j] == board[i+1][j+1] &&  
                         board[i][j] == board[i+2][j+2] && 
                         board[i][j] == board[i+3][j+3]) &&
                        board[i][j] != 0)
                    {
                        System.out.println("Diagonal Win!!");
                        System.out.println("Winner is Player"+ board[i][j]);
                        return(1);
                    }
                }
                        
                if (j-1 > -1 && 
                   j-2 > -1 && 
                   j-3 > -1 && 
                   i+1 < 6 && 
                   i+2 < 6 && 
                   i+3 < 6)
                {
                    if (
                         (board[i][j] == board[i+1][j-1] && 
                         board[i][j] == board[i+2][j-2] &&
                         board[i][j] == board[i+3][j-3]) &&
                        board[i][j] != 0)
                    {
                        System.out.println("Diagonal Win!!");
                        System.out.println("Winner is Player"+ board[i][j]);
                        return(1);
                    }
                }
            }
        }
        return(0);
    }

}
