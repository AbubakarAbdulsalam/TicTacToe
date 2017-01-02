import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * A TicTacToeFrame class that represents the game tictactoe frame
 * 
 * @author (Abubakar) 
 * @version (5/12/2016)
 */
public class TicTacToeFrame
{

    public static final String PLAYER_X = "X"; // player using "X"
    public static final String PLAYER_O = "O"; // player using "O"
    public static final String EMPTY = " ";  // empty cell
    public static final String TIE = "T"; // game ended in a tie

    private String player;   // current player (PLAYER_X or PLAYER_O)

    private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

    private int numFreeSquares; // number of squares still free
    
    /**
     * the 3X3 buttons that form the basis of the game
     */
    JButton[][] buttons = new JButton[3][3];
    /**
     * the main frame of every instance of the game
     */
    JFrame frame;
    /**
     * the reset button of every instance
     */
    //JButton reset = new JButton("reset");
    /**
     * the status label of any instance
     */
    JLabel label;
    /**
     * represents the menubar of frame of every instance
     */
    JMenuBar menuBar;
    /**
     * Constructor for objects of class TicTacToeFrame
     */
    public TicTacToeFrame()
    {
        // initialise instance variables

        
        
        makeFrame();
        //reset.addActionListener(new ButtonHandler());
        
        
        
        
        clearBoard();
    }
    /**
     * main method class that instantiates the whole game
     */
    public static void main(String[]args)
    {
         TicTacToeFrame a = new TicTacToeFrame();
    }
    /**
     * makes the frame that is the backbone of the game
     */
    private void makeFrame()
    {
        frame  = new JFrame("Tic");
        frame.setSize(325,425);
        Container contentpane = frame.getContentPane();
        
        JPanel game = new JPanel(new GridLayout(3,3));
        JPanel menu = new JPanel(new FlowLayout());
        JPanel main = new JPanel(new BorderLayout());
        JButton n = new JButton("S");
        
        label = new JLabel("Game running player X's turn");
        //menu.add(reset);
        menu.add(label);
        menu.setSize(15,15);
        game.setSize(270,370);
        main.add(menu, BorderLayout.SOUTH);
        main.add(game);
        makeMenuBar();
        
        contentpane.add(main);
        
        frame.setResizable(false);
        for(int i = 0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(new ButtonHandler());
                game.add(buttons[i][j]);
                
            }
        }
        frame.setVisible(true);
    }
    /**
     * attaches the menubar to the frame of the game
     */
    private void makeMenuBar()
    {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu gameItem = new JMenu("Game");
        menuBar.add(gameItem);
        
        JMenuItem New = new JMenuItem("New");
        gameItem.add(New);
        
        JMenuItem Quit = new JMenuItem("Quit");
        gameItem.add(Quit);
        
        Quit.addActionListener(new QuitGameListener());
        New.addActionListener(new NewGameListener());
    }

    /**
     * clears the board of any current progress and returns the whole grame to the initial state
     */
    private void clearBoard()
    {
        for(int i=0; i<3; i++)
        {
            for(int j =0; j<3; j++)
            {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        winner = EMPTY;
        numFreeSquares = 9;
        player = PLAYER_X;
        label.setText("Game in progress " + "player" + player + "'s turn");
    }
    /**
     * checks if there is a winner of the current game
     */
    
    public boolean haveWinner(int row, int col)
    {
        if(numFreeSquares >4) 
        {return false;
        }
        
        if(buttons[row][0].getText().equals(buttons[row][1].getText()) && 
           buttons[row][0].getText().equals(buttons[row][2].getText()) ) return true;
        
        if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
           buttons[0][col].getText().equals(buttons[2][col].getText()))return true;
           
        if(row == col)
        {
            if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
               buttons[0][0].getText().equals(buttons[2][2].getText())) return true;
               
        }
        if(row == 2-col)
        {
            if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
             buttons[0][2].getText().equals(buttons[2][0].getText()) ) 
             return true;
        }
        
        return false;
    }

    /**
     * class buttonhandler handles all the events received by all the listeners listening to the buttons
     */
    public class ButtonHandler implements ActionListener
    {
        public ButtonHandler()
        {
            
        }
        /**
         * carries out respective tasks when buttons are clicked
         */
        public void actionPerformed(ActionEvent e)
        {
            if((e.getSource() == buttons[0][0])&& (winner==EMPTY))
            {
                gamePlay(e.getSource(),0,0);
                
            }
            else if((e.getSource() == buttons[0][1]) &&( winner ==EMPTY)  && (buttons[0][1].isSelected()!=true))
            {
                gamePlay(e.getSource(),0,1);
            }
            else if((e.getSource() == buttons[0][2]) && (winner ==EMPTY ) && (buttons[0][2].isSelected()!=true))
            {
                gamePlay(e.getSource(),0,2);
            }
            else if((e.getSource() == buttons[1][0]) && (winner ==EMPTY)  &&( buttons[1][0].isSelected()!=true))
            {
                gamePlay(e.getSource(),1,0);
            }
            else if((e.getSource() == buttons[1][1]) && (winner ==EMPTY ) && (buttons[1][1].isSelected()!=true))
            {
                gamePlay(e.getSource(),1,1);
            }
            else if((e.getSource() == buttons[1][2])&&( winner ==EMPTY)  && (buttons[1][2].isSelected()!=true))
            {
                gamePlay(e.getSource(),1,2);
            }
            else if((e.getSource() == buttons[2][0]) &&( winner ==EMPTY)  && (buttons[2][0].isSelected()!=true))
            {
                gamePlay(e.getSource(),2,0);
            }
            else if((e.getSource() == buttons[2][1] )&& (winner ==EMPTY ) && (buttons[2][1].isSelected()!=true))
            {
                gamePlay(e.getSource(),2,1);
            }
            else if((e.getSource() == buttons[2][2]) && (winner ==EMPTY)  && (buttons[2][2].isSelected()!=true))
            {
                gamePlay(e.getSource(),2,2);
            }
            //else if((e.getSource() == reset))
            //{
                //clearBoard();
            //}
            

            
        }
        /**
         * controls the flow of the game
         */
        public void gamePlay(Object source,int row, int column)
        {
            buttons[row][column].setText(player);
            buttons[row][column].setEnabled(false);
            numFreeSquares--;
            if(haveWinner(row,column))
            {
                winner = player;
                label.setText("game over player " + player + " wins");
            }
            else if(numFreeSquares == 0)
            {
                winner =TIE;
                label.setText("game over TIE");
            }
            if(player == PLAYER_X && winner== EMPTY)
            {
                player =PLAYER_O;
                label.setText("game in progress player " + player + " 's turn");
            }
            else if(player == PLAYER_O && winner == EMPTY)
            {
                player =PLAYER_X;
                label.setText("game in progress player " + player + " 's turn");
            }
        }
    }
    /**
     * class to handle events generated by the GameMenu item Quit
     */
    private class QuitGameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            //frame.setVisible(false);
            frame.dispose();
            System.exit(0);
        }
    }
    /**
     * class to handle events generated by the GameMenu item New
     */
    class NewGameListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            clearBoard();
        }
    }

}
