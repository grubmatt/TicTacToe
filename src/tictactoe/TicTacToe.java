package tictactoe;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TicTacToe extends JApplet implements MouseListener
{
	int xpos, ypos;
	int boardSizeX = 340;
	int boardSizeY = 300;
	int playerTurn = 1;
	int[][] board = new int[3][3];
	String[][] strBoard = new String[3][3];
	int player1Wins = 0;
	int player2Wins = 0;
	boolean player1Won = false;
	boolean player2Won = false;
	boolean isFull = false;

	public void init()

	{
		setSize(340,300);

		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				strBoard[i][j] = "";
			}
		}

		addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		if(player1Won == true)
		{
			g.setFont(new Font("TimesRoman", Font.BOLD, 45));
			g.drawString("Player 1 has won", 0, boardSizeY/2);
			player1Won = false;
		}
		else if(player2Won == true)
		{
			g.setFont(new Font("TimesRoman", Font.BOLD, 45));
			g.drawString("Player 2 has won", 0, boardSizeY/2);
			player2Won = false;
		}
		else if(isFull == true)
		{
			g.setFont(new Font("TimesRoman", Font.BOLD, 45));
			g.drawString("Tie Game", 0, boardSizeY/2);
			restart(0);
		}
		else
		{
			// Rows
			g.drawLine(0, boardSizeY/3, boardSizeX, boardSizeY/3);
			g.drawLine(0, boardSizeY/3*2, boardSizeX, boardSizeY/3*2);

			// Columns
			g.drawLine(boardSizeX/3, 0, boardSizeX/3, boardSizeY);
			g.drawLine(boardSizeX/3*2, 0, boardSizeX/3*2, boardSizeY);

			g.setFont(new Font("TimesRoman", Font.BOLD, 145));

			// Draw X's and O's
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					g.drawString(strBoard[i][j],boardSizeX/3*j, (boardSizeY/3*(i+1))-2);
				}
			}
		}
	}

	public void game()
	{

		updateBoard();
		repaint();

		int winner = hasWon();
		if (winner != 0)
			restart(winner);

		if(playerTurn == 1)
			playerTurn = 2;
		else
			playerTurn = 1;

		isFull = boardFull();
	}

	public boolean boardFull()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j] == 0)
				{
					return false;
				}
			}
		}
		return true;
	}

	public void updateBoard()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if (xpos < boardSizeX/3 * (j+1) && xpos > boardSizeX/3 * j
					&& ypos < boardSizeY/3 *(i+1) && ypos > boardSizeY/3 * i)
						mark(i,j);
			}
		}

		// Set String board according to int board
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]==1)
					strBoard[i][j] = "X";
				else if(board[i][j]==2)
					strBoard[i][j] = "O";
			}
		}
	}

	public void mark(int row,int col)
	{
		if (playerTurn == 1 && board[row][col] == 0)
		{
			board[row][col] = 1;
		}
		if (playerTurn == 2 && board[row][col] == 0)
		{
			board[row][col] = 2;
		}
	}

	public int hasWon()
	{
		// Diagonals
		int one = board[0][0];
		int two = board[1][1];
		int three = board[2][2];

		if(one==two && two==three)
			// returns # that won
			return one;

		one = board[0][2];
	    two = board[1][1];
		three = board[2][0];

		if(one==two && two==three)
			return one;

		// Rows
		for(int i=0;i<3;i+=3)
		{
			one = board[i][0];
			two = board[i][1];
			three = board[i][2];

			if(one==two && two==three)
				return one;
		}
		// Columns
		for(int i=0; i<3;i+=1)
		{
			one = board[0][i];
			two = board[1][i];
			three = board[2][i];

			if(one==two && two==three)
				return one;
		}

		// in case none of the above conditions are met
		return 0;
	}


	public void restart(int winner)
	{
		if(winner==1)
			player1Won = true;
		else if(winner==2)
			player2Won = true;

		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				strBoard[i][j] = "";
				board[i][j] = 0;
			}
		}

		repaint();
	}

	public void mouseClicked (MouseEvent me)
	{
		xpos = me.getX();
		ypos = me.getY();

		game();
	}
	public void mouseEntered (MouseEvent me) {}
	public void mousePressed (MouseEvent me) {}
	public void mouseReleased (MouseEvent me) {}
	public void mouseExited (MouseEvent me) {}

}