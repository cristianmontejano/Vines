package core;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Game extends JPanel implements Runnable, KeyListener 
{
	//Declaration of variables
	private Thread th;
	private Head guy;
	private Head guy2;
	private Sun sun;
	private boolean play;
	private int score;
	private int score_2;
	private boolean gameOver;
	private int speed;
	private boolean reset;
	private String winner;
	private boolean guy_won;
	private boolean guy2_won;
	
	//Default game constructor
	public Game()
	{	
		play = true;
		reset = false;
		speed = 20;
		gameOver = false;
		score = 0;
		score_2 = 0;
		guy = new Head(250,250);
		guy2 = new Head(750,250);
		addSun();	
		setFocusable(true);
		addKeyListener(this);
		th = new Thread(this);
		th.start();
	}
	
	//run method
	public void run()
	{
		while(true)
		{
			
			//Try catch that updates every 30 ms
				try
				{	
					if(play)
					{
					//Saves the heads previous location
					int prevX = guy.getX();
					int prevY = guy.getY();
					int prevX_2 = guy2.getX();
					int prevY_2 = guy2.getY();
					
					//Moves the snake's head
					guy.move();
					guy2.move();
					
					if(guy.getTail().size() == 0)
					{
						guy.addToEnd(guy.getX()+10,guy.getY());
						guy.addToEnd(guy.getX()+20,guy.getY());
						guy.addToEnd(guy.getX()+20,guy.getY()+10);
						guy.addToEnd(guy.getX()+20,guy.getY()+20);
					}
					
					if(guy2.getTail().size() == 0)
					{
						guy2.addToEnd(guy2.getX()+10,guy2.getY());
						guy2.addToEnd(guy2.getX()+20,guy2.getY());
						guy2.addToEnd(guy2.getX()+20,guy2.getY()+10);
						guy2.addToEnd(guy2.getX()+20,guy2.getY()+20);
					}
					
					//if the tail arraylist isn't empty
					if((guy.getTail().size() > 0) && guy.isMoving())
					{					
						//Removes the last tail piece
						guy.getTail().remove(guy.getTail().size()-1);
						
						//Adds a new tail piece where the snake head was
						guy.addToHead(prevX, prevY);
					}
					
					//for guy2
					if((guy2.getTail().size() > 0) && guy2.isMoving())
					{					
						//Removes the last tail piece
						guy2.getTail().remove(guy2.getTail().size()-1);
						
						//Adds a new tail piece where the snake head was
						guy2.addToHead(prevX_2, prevY_2);
					}
									
					
					//checks to see if there were any collisions
					collisionCheck();
					}
					//repaints the screen
					repaint();
					
					//if the game is over then reset
					if(gameOver)
					{
						//sleep for 2.5 secs
					//	Thread.sleep(2500);
						
						if(reset)
						{
							//Reset
							reset();						
						}
					}
				//Sleep for 30 ms
				Thread.sleep(speed);	
				}
				catch(Exception e)
				{
					
				}
				
			}	
		}
	
	//Key pressed method
	public void keyPressed(KeyEvent e)
	{
		//gets the key pressed
		int key = e.getKeyCode();
		
		//Depending on the key pressed the head's direction will change
		if(key == KeyEvent.VK_UP && guy.getDirection() != Head.SOUTH)
		{
			guy.changeDirection(Head.NORTH);
		}
		else if(key == KeyEvent.VK_DOWN && guy.getDirection() != Head.NORTH)
		{
			guy.changeDirection(Head.SOUTH);
		}
		else if(key == KeyEvent.VK_LEFT && guy.getDirection() != Head.EAST)
		{
			guy.changeDirection(Head.WEST);
		}
		else if(key == KeyEvent.VK_RIGHT && guy.getDirection() != Head.WEST && guy.isMoving())
		{
			guy.changeDirection(Head.EAST);
		}
		else if(key == KeyEvent.VK_R && gameOver)
		{
			reset = true;
		}
		else if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		else if(key == KeyEvent.VK_P)
		{
			if(isRunning())
				pause();
			else
				resume();
		}
		//controls for player 2
		if(key == KeyEvent.VK_W && guy2.getDirection() != Head.SOUTH)
		{
			guy2.changeDirection(Head.NORTH);
		}
		else if(key == KeyEvent.VK_S && guy2.getDirection() != Head.NORTH)
		{
			guy2.changeDirection(Head.SOUTH);
		}
		else if(key == KeyEvent.VK_A && guy2.getDirection() != Head.EAST)
		{
			guy2.changeDirection(Head.WEST);
		}
		else if(key == KeyEvent.VK_D && guy2.getDirection() != Head.WEST && guy2.isMoving())
		{
			guy2.changeDirection(Head.EAST);
		}
		else if(key == KeyEvent.VK_R && gameOver)
		{
			reset = true;
		}
		else if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		else if(key == KeyEvent.VK_P)
		{
			if(isRunning())
				pause();
			else
				resume();
		}
	}
	
	//Required: Key typed
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	//Required: keyreleased
	public void keyReleased(KeyEvent e)
	{
		
	}
	
	//Checks for collision
	public void collisionCheck()
	{
		//sees if head hits tail
		for(Tail t : guy.getTail())
		{
			for(Tail t2 : guy2.getTail()) {
				//guy collision check
				if(guy.getBounds().intersects(t.getBounds()) || guy.getBounds().intersects(guy2.getBounds()) ||
						((guy.getX() == t2.getX()) && (guy.getY() == t2.getY()))) {
					guy.stop();
					guy2.stop();
					winner = "Player 2";
					if (guy_won)
						guy2_won = false;
					guy2_won = true;
					gameOver = true;
					}
			//guy2 collision check
				if(guy2.getBounds().intersects(t2.getBounds()) || guy2.getBounds().intersects(guy.getBounds()) ||
						((guy2.getX() == t.getX()) && (guy2.getY() == t.getY()))) {
					guy.stop();
					guy2.stop();
					winner = "Player 1";
					guy_won = true;
					if (guy2_won)
						guy_won = false;
					gameOver = true;
					}
				}
		}
		/*
		//guy2
		for(Tail t : guy2.getTail())
		{
			if(guy2.getBounds().intersects(t.getBounds()))
			{
				guy.stop();
				guy2.stop();
				winner = "Player 1";
				gameOver = true;
			}	
		}
		*/
		//Checks to see if head hits apple
		if(guy.getBounds().intersects(sun.getBounds()))
		{
			score++;
			addSun();
			guy.addToEnd(guy.getX(),guy.getY());
			guy.addToEnd(guy.getX(),guy.getY());
			
			if(((score % 5) == 0)&&speed >= 45)
				speed -= 5;
		}
		
		if(guy.getX() < 0)
			guy.setX(Board.getBoardWidth());
		else if(guy.getX() > Board.getBoardWidth())
			guy.setX(0);
		else if(guy.getY() < 0)
			guy.setY(Board.getBoardHeight());
		else if(guy.getY() > Board.getBoardHeight())
			guy.setY(0);
		
		//check for player 2
		if(guy2.getBounds().intersects(sun.getBounds()))
		{
			score_2++;
			addSun();
			guy2.addToEnd(guy2.getX(),guy2.getY());
			guy2.addToEnd(guy2.getX(),guy2.getY());
			
			if(((score_2 % 5) == 0)&&speed >= 45)
				speed -= 5;
		}
		
		if(guy2.getX() < 0)
			guy2.setX(Board.getBoardWidth());
		else if(guy2.getX() > Board.getBoardWidth())
			guy2.setX(0);
		else if(guy2.getY() < 0)
			guy2.setY(Board.getBoardHeight());
		else if(guy2.getY() > Board.getBoardHeight())
			guy2.setY(0);
	}
	
	//Paints objects	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setFont(new Font("Helvetica", Font.BOLD, 14));
		
		g.setColor(Color.WHITE);
		
		g.drawImage(new ImageIcon("vines_background.png").getImage(),0,0,1000,1000,null);
		
		g.drawString("Press 'Esc' to Exit   &   'P' to Pause", Board.getBoardWidth()/2 - 90,15);
		
		g.setColor(new Color(128,0,128));
		
		//g.fillOval(guy.getX(),guy.getY(),10,10);
		g.drawImage(new ImageIcon("grapes.png").getImage(),guy.getX(),guy.getY(),30,30,null);
		//guy2
		g.drawImage(new ImageIcon("melon.png").getImage(),guy2.getX(),guy2.getY(),30,30,null);
		
		g.setColor(Color.yellow);
		
		for(Tail t : guy.getTail())
			g.drawImage(new ImageIcon("pic.png").getImage(),t.getX(),t.getY(),25,25,null);
			//g.fillOval(t.getX(),t.getY(),10,10);
		for(Tail t : guy2.getTail())
			g.drawImage(new ImageIcon("pic.png").getImage(),t.getX(),t.getY(),25,25,null);
		
		g.setColor(Color.WHITE);
		
		g.drawImage(sun.getPicture().getImage(),sun.getX(),sun.getY(),60,60, null);
		
		//prints score
		if(!gameOver) {
			g.drawString("Player 1(Grapes): " + (score*25), 20,Board.getBoardHeight()-15);
			g.drawString("Player 2(Melon): " + (score_2*25), 360,Board.getBoardHeight()-15);
		}
		//prints game over if the game ended
		if(gameOver)
		{
			g.setFont(new Font("Helvetica", Font.BOLD, 30));
			g.drawString(winner + " wins!", Board.getBoardWidth()/2 - 75,Board.getBoardHeight()/2);
			g.setFont(new Font("Helvetica", Font.BOLD, 15));
			if (guy_won) {
				g.drawString("Score: " + (score*25), Board.getBoardWidth()/2 - 75,Board.getBoardHeight()/2 + 30);
				g.drawString("Press 'R' to Reset", Board.getBoardWidth()/2 - 75,Board.getBoardHeight()/2 + 60);
			} else {
				g.drawString("Score: " + (score_2*25), Board.getBoardWidth()/2 - 75,Board.getBoardHeight()/2 + 30);
				g.drawString("Press 'R' to Reset", Board.getBoardWidth()/2 - 75,Board.getBoardHeight()/2 + 60);
			}		
		}
		
		if(!isRunning())
		{
			g.setFont(new Font("Helvetica", Font.BOLD, 30));
			g.drawString("Paused", Board.getBoardWidth()/2 - 40,Board.getBoardHeight()/2-30);
		}	
	}
	
	//Adds a sun to a random area thats not occupied my a trail or head piece
	public void addSun()
	{
		boolean good = true;
		int distance = 0;
		int tX = (int)(Math.random() * (Board.getBoardWidth() - 100)) + 50;
		int tY = (int)(Math.random() * (Board.getBoardHeight() - 100)) + 50;
		
		distance = (int)Math.sqrt(Math.pow(guy.getX() - tX,2) + Math.pow(guy.getY() - tY,2));		
		
		Rectangle tApple = new Rectangle(tX,tY,10,10);
		
		for(Tail t: guy.getTail())
			if((tApple.getBounds().intersects(t.getBounds()))|| 
			  (tApple.getBounds().intersects(guy.getBounds())))
				good = false;	
		
		if(good && (distance >= 200))
			sun = new Sun(tX,tY);	
		else
			addSun();									
	}
	
	//resets the game
	public void reset()
	{
		gameOver = false;
		score = 0;
		score_2 = 0;
		speed = 30;
		guy = new Head(250,250);
		guy2 = new Head(750,250);
		addSun();	
		reset = false;
	}
	
	public void pause()
	{
		play = false;
	}
	
	public void resume()
	{
		play = true;
	}
	
	public boolean isRunning()
	{
		return play;
	}
}