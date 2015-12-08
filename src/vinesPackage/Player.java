/* Vines
 * Player class
 * 
 */

package vinesPackage;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

//import vines.Tail;

public class Player implements KeyListener
{
	private Fruit tFruit;
	private ArrayList <Tail> tTail;
	private int prevDir;
	private boolean isFlying;
	private int playerNum;
	private int up;
	private int down;
	private int left;
	private int right;
	private int wins;
	private int losses;
	private boolean ready;
	//private String pressedKey;
	private boolean read;
	
	//Overloaded constructor
	public Player(int tnum, int tX, int tY, int tDir, int tWins, int tLosses)
	{
		wins = tWins;
		losses = tLosses;
		tFruit = new Fruit(tX, tY, tDir);
		tTail = (new  ArrayList<Tail>());
		prevDir = tFruit.getDirection();
		isFlying = false;
		playerNum = tnum;
		decideKeys();
		ready = false; 
		read = false;
	}
	
	//Method that decides the keys	
	private void decideKeys() 
	{		
		if(playerNum == 1)
		{
			up = KeyEvent.VK_W;
			down = KeyEvent.VK_S;
			left = KeyEvent.VK_A;
			right =	KeyEvent.VK_D;
		}
		else if(playerNum == 2)
		{
			up = KeyEvent.VK_UP;
			down = KeyEvent.VK_DOWN;
			left = KeyEvent.VK_LEFT;
			right =	KeyEvent.VK_RIGHT;		
		}
		else if (playerNum == 3)
		{
			up = KeyEvent.VK_Y;
			down = KeyEvent.VK_H;
			left = KeyEvent.VK_G;
			right = KeyEvent.VK_J;
		}
		
		else if (playerNum == 4)
		{
			up = KeyEvent.VK_P;
			down = KeyEvent.VK_SEMICOLON;
			left = KeyEvent.VK_L;
			right = KeyEvent.VK_QUOTE;
		}
	}

	//Key reader if pressed
	public void keyPressed(KeyEvent e) 
	{
		if(read)
		{
			int key = e.getKeyCode();
			
			if(!ready)
			{
				if(key == up)
				{
					tFruit.setDirection(tFruit.NORTH);	
				}
				else if(key == down)
				{
					tFruit.setDirection(tFruit.SOUTH);
				}
				else if(key == left)
				{
					tFruit.setDirection(tFruit.WEST);
				}
				else if(key == right)
				{
					tFruit.setDirection(tFruit.EAST);
				}				
			}
	
					
			if(ready)
			{
				if(tFruit.isAlive())
				{	
					if(key == up)
					{
						
						if(tFruit.getDirection() == tFruit.SOUTH)
						{
							tFruit.setDirection(tFruit.NORTH);
							tFruit.remove();
							
						}
						else
						{
							isFlying = true;
					
							prevDir = tFruit.getDirection();
							tFruit.setDirection(tFruit.NORTH);	
							tFruit.setXVel(0);
							tFruit.setYVel(-10);	
								
						}	
					}
					
					else if(key == down)
					{
						
						if(tFruit.getDirection() == tFruit.NORTH)
						{
							
							tFruit.setDirection(tFruit.SOUTH);
							tFruit.remove();
						}
						else
						{
							isFlying = true;
							
							prevDir = tFruit.getDirection();
							tFruit.setDirection(tFruit.SOUTH);
							tFruit.setXVel(0);
							tFruit.setYVel(10);
							
						
						}	
					}
					
					else if(key == left)
					{
						
						if(tFruit.getDirection() == tFruit.EAST)
						{
							tFruit.setDirection(tFruit.EAST);
							tFruit.remove();
						}
						else
						{
							isFlying = true;
							
							prevDir = tFruit.getDirection();
							tFruit.setDirection(tFruit.WEST);
							tFruit.setXVel(-10);
							tFruit.setYVel(0);
							
						}	
					}
					
					else if(key == right)
					{
						if(tFruit.getDirection() == tFruit.WEST)
						{
							tFruit.setDirection(tFruit.EAST);
							tFruit.remove();
						}
						else
						{
							isFlying = true;
							
							prevDir = tFruit.getDirection();
							tFruit.setXVel(10);
							tFruit.setYVel(0);
							tFruit.setDirection(tFruit.EAST);				
						}	
					}	
				}
				
			}
			tFruit.setImage(tFruit.getRelativeImage(tFruit.getDirection()));	
		}
	}
	
	//Key reader if typed
	public void keyTyped(KeyEvent e){}
		
	//Key reader if released
	public void keyReleased(KeyEvent e){}
	
	//Getter for pilots image
	public ImageIcon getFruitImage()
	{
		return tFruit.getImage();
	}
	
	//Getter for pilot x coor
	public int getFruitX()
	{
		return tFruit.getX();
	}
	
	//Getter for piloy y cor
	public int getFruitY()
	{
		return tFruit.getY();
	}
	
	//Moves player
	public void move()
	{
		tFruit.setX(tFruit.getX() + tFruit.getXVel());
		tFruit.setY(tFruit.getY() + tFruit.getYVel());
		prevDir = getPresentDir();
		tFruit.updateBounds();	
		
 		if((tFruit.getX()> 635)||(tFruit.getY()> 490)||(tFruit.getX()<0)||
 			(tFruit.getY()<0))
 		{
				tFruit.remove();
 		}	
	}

	//Returns the players trail arraylist
	public ArrayList <Tail> getTheTail() 
	{
		return tTail;
	}

	//Adds to players trail
	public void addTail(Tail t) 
	{
		tTail.add(t);
	}
	
	//Gets previous direction of player
	public int getPrevDir()
	{
		return prevDir;
	}
	
	//Gets the present direction of the player
	public int getPresentDir()
	{
		return tFruit.getDirection();
	}
	
	//Returns boolean id flying
	public boolean isFlying()
	{
		return isFlying;
	}
	
	//Gets the players number
	public int getPlayerNum()
	{
		return playerNum;
	}

	//Returns the fruit
	public Fruit gettFruit()
	{
		return tFruit;
	}
	
	//Makes the player fly   for start
	public void fly()
	{
		isFlying = true;
	}
	
	//Adds a win to the player
	public void addWin()
	{
		wins++;
	}
	
	//Adds loss
	public void addLoss()
	{
		losses++;
	}
	
	//Returns the players score
	public String getScore()
	{
		return ("Ply: " + playerNum + ": " + "(W: " + wins + " L:"+
			" " + losses + ")");	
	}

	//Gets wins
	public int getWins() 
	{
		return wins;
	}
	
	//Gets losses
	public int getLosses()
	{
		return losses;
	}
	
	public boolean isReady()
	{
		return ready;
	}
	
	public void ready()
	{
		ready = true;
	}
	public void quitReading()
	{
		read = false;
	}
	
	public void startReading()
	{
		read = true;
	}
}