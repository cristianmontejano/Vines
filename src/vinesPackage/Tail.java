package vinesPackage;

import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Tail
{	
	//Declarations and Initializations
	public final int UP = 0;
	public final int RIGHT = 90;
	public final int DOWN = 180;
	public final int LEFT = 270;
	private final ImageIcon STRAIGHT;
	private final ImageIcon ACROSS;
	private final ImageIcon UP_RIGHT_LEFT_DOWN;
	private final ImageIcon UP_LEFT_RIGHT_DOWN;
	private final ImageIcon DOWN_LEFT_RIGHT_UP;
	private final ImageIcon DOWN_RIGHT_LEFT_UP;
	private int x;
	private int y;
	private ImageIcon vector;
	private Rectangle bounds;
	
	//Default Constructor
	public Tail()
	{
		STRAIGHT = new ImageIcon("Gstraight.png");
		ACROSS = new ImageIcon("Gacross.png");
		UP_RIGHT_LEFT_DOWN = new ImageIcon("Gupright_leftdown.png");
		UP_LEFT_RIGHT_DOWN  = new ImageIcon("Gupleft_rightdown.png");
		DOWN_LEFT_RIGHT_UP  = new ImageIcon("Gdownleft_rightup.png");
		DOWN_RIGHT_LEFT_UP  = new ImageIcon("Gdownright_leftup.png");
		vector = STRAIGHT;
		x = 0;
		y = 0;	
		bounds = new Rectangle(getX(),getY(),10,10);
	}
	
	//Overloaded Constructor
	public Tail(int tmpX, int tmpY, int pDir, int aDir, int Pnum)
	{
		if(Pnum == 1)
		{
			STRAIGHT = new ImageIcon("Gstraight.png");
			ACROSS = new ImageIcon("Gacross.png");
			UP_RIGHT_LEFT_DOWN = new ImageIcon("Gupright_leftdown.png");
			UP_LEFT_RIGHT_DOWN  = new ImageIcon("Gupleft_rightdown.png");
			DOWN_LEFT_RIGHT_UP  = new ImageIcon("Gdownleft_rightup.png");
			DOWN_RIGHT_LEFT_UP  = new ImageIcon("Gdownright_leftup.png");			
		}
		else if(Pnum == 2)
		{
			STRAIGHT = new ImageIcon("Pstraight.png");
			ACROSS = new ImageIcon("Pacross.png");
			UP_RIGHT_LEFT_DOWN = new ImageIcon("Pupright_leftdown.png");
			UP_LEFT_RIGHT_DOWN  = new ImageIcon("Pupleft_rightdown.png");
			DOWN_LEFT_RIGHT_UP  = new ImageIcon("Pdownleft_rightup.png");
			DOWN_RIGHT_LEFT_UP  = new ImageIcon("Pdownright_leftup.png");			
		}
		else if(Pnum == 3)
		{
			STRAIGHT = new ImageIcon("Rstraight.png");
			ACROSS = new ImageIcon("Racross.png");
			UP_RIGHT_LEFT_DOWN = new ImageIcon("Rupright_leftdown.png");
			UP_LEFT_RIGHT_DOWN  = new ImageIcon("Rupleft_rightdown.png");
			DOWN_LEFT_RIGHT_UP  = new ImageIcon("Rdownleft_rightup.png");
			DOWN_RIGHT_LEFT_UP  = new ImageIcon("Rdownright_leftup.png");
		}
		else if(Pnum == 4)
		{
			STRAIGHT = new ImageIcon("Bstraight.png");
			ACROSS = new ImageIcon("Bacross.png");
			UP_RIGHT_LEFT_DOWN = new ImageIcon("Bupright_leftdown.png");
			UP_LEFT_RIGHT_DOWN  = new ImageIcon("Bupleft_rightdown.png");
			DOWN_LEFT_RIGHT_UP  = new ImageIcon("Bdownleft_rightup.png");
			DOWN_RIGHT_LEFT_UP  = new ImageIcon("Bdownright_leftup.png");
		}
		else 
		{
			STRAIGHT = null;
			ACROSS = null;
			UP_RIGHT_LEFT_DOWN = null;
			UP_LEFT_RIGHT_DOWN  = null;
			DOWN_LEFT_RIGHT_UP  = null;
			DOWN_RIGHT_LEFT_UP  = null;
		}
				
		x = tmpX;
		y = tmpY;
		decideVector(pDir,aDir);
		bounds = new Rectangle(getX(),getY(),10,10);	
	}
	
	//Getter for x
	public int getX()
	{
		return x;
	}
	
	//Getter for y
	public int getY()
	{
		return y;
	}
	
	//Setter for x 
	public void setX(int tmpX)
	{
		x = tmpX;
	}
	
	//Setter for y
	public void setY(int tmpY)
	{
		y = tmpY;
	}
	
	//Decides vector image
	public void decideVector(int firstPos, int secondPos)
	{
		if((firstPos == UP && secondPos== RIGHT)||(firstPos == LEFT && 
			secondPos== DOWN))
		{
			vector = UP_RIGHT_LEFT_DOWN;
			
		}
		 
		else if((firstPos == LEFT && secondPos== UP)||(firstPos == DOWN && 
			secondPos== RIGHT))
		{
			vector = DOWN_RIGHT_LEFT_UP;
		}
		
		else if((firstPos == RIGHT && secondPos== DOWN)||(firstPos == UP && 
			secondPos== LEFT))
		{
			vector = UP_LEFT_RIGHT_DOWN;
		}
		
		else if((firstPos == DOWN && secondPos== LEFT)||(firstPos == RIGHT &&
			 secondPos== UP))
		{
			vector = DOWN_LEFT_RIGHT_UP; 
		}
		
		else 
		{
			if((secondPos == UP)||(secondPos == DOWN))
				vector = STRAIGHT;	
			
			else if((secondPos == LEFT)||(secondPos == RIGHT))
				vector = ACROSS;
		}	
	}
	
	//Getter for vector image
	public ImageIcon getVector() 
	{
		return vector;
	}
	
	//Getter for bounds 
	public Rectangle getBounds()
	{
		return bounds;
	}
}
