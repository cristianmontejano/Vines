package vinesPackage;


import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.IOException;

public class GameFrame extends JFrame 
{	
	//Declarations
	public static int screenWidth;
	public static int screenHeight;
	boolean isDone;
	Game g;
	
	//Overloaded constructor
	public GameFrame(int rounds,int playerNum) 
	{
		isDone = false;
		g = new Game(rounds,playerNum);
		add(g);
		setSize(646,545);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Vines");
		setVisible(true);
		
		screenWidth = 646;
		screenWidth = 545;
		
	}

	public static int getScreenWidth()
	{
		return screenWidth;
	}
	
	public static int getScreenHeight()
	{
		return screenHeight;
	}
	
	
	//returns whether or not the game is done
	public boolean isDone()
	{
		return g.isDone();
	}

	//Resets game
	public void Reset() 
	{
		g.reset();
	}

	//Returns the game score
	public String getScore() 
	{
		String output = "";
		
		if(g.getPlayers().size() == 1)
		{
			output += g.getPlayers().get(0).getScore();			
		}
		else if(g.getPlayers().size() == 2)
		{
			output += g.getPlayers().get(0).getScore() + "\n" + 
				g.getPlayers().get(1).getScore();	
		}
		else if(g.getPlayers().size() == 3)
		{
			output += g.getPlayers().get(0).getScore() + "\n" + 
				g.getPlayers().get(1).getScore() + "\n" + 
				g.getPlayers().get(2).getScore();
		}
		else if(g.getPlayers().size() == 4)
		{
			output += g.getPlayers().get(0).getScore() + "\n" + 
			g.getPlayers().get(1).getScore() + "\n" + 
			g.getPlayers().get(2).getScore()+ "\n" + 
			g.getPlayers().get(3).getScore();
		}	
		
		return output;
	}
	
	public void kill()
	{
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
}
