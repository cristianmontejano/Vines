
package vinePackage;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Head
{
	//Declaration of final variables
	public static final int NORTH = 0;
	public static final int SOUTH = 180;
	public static final int EAST = 90;
	public static final int WEST = 270;
	
	//declaration of variables
	private ArrayList <Tail> tail = new ArrayList <Tail> ();
	private Rectangle bounds;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private int direction;
	private boolean canMove;
	
	//Default constructor
	public Head(int x_start, int y_start)
	{
		x = x_start;
		y = y_start;
		xVel = 0;
		yVel = 0;	
		direction = -1;
		bounds = new Rectangle(x,y,10,10);
		canMove = true;
	}
	
	//Moves the head 
	public void move()
	{
		if(canMove)
		{
			xVel = 0;
			yVel = 0;
			
			if(direction == NORTH)
			{
				yVel = -10;
			}
			else if(direction == SOUTH)
			{
				yVel = 10;
			}
			else if(direction == WEST)
			{
				xVel = -10;
			}
			else if(direction == EAST)	
			{
				xVel = 10;
			}
			
			x += xVel;
			y += yVel;			
		}
	}
	
	//Changes the heads direction
	public void changeDirection(int tDir)
	{
		direction = tDir;
	}
	
	//sets x variable
	public void setX(int tempX)
	{
		x = tempX;	
	}
	
	//sets y variable
	public void setY(int tempY)
	{
		y = tempY;
	}
	
	//getter of x
	public int getX()
	{
		return x;
	}
	
	//getter of y
	public int getY()
	{
		return y;
	}
	
	//adds to the beginning of the tail
	public void addToHead(int tX, int tY)
	{
		tail.add(0,new Tail(tX,tY));
	}
	
	//adds to the end of the tail
	public void addToEnd(int tX, int tY)
	{
		tail.add(new Tail(tX,tY));
	}
	
	//returns the tail arraylist
	public ArrayList<Tail> getTail()
	{
		return tail;
	}
	
	//returns the head's direction
	public int getDirection()
	{
		return direction;
	}
	
	//Returns a rectangle that represents the boundaries of the head
	public Rectangle getBounds()
	{
		bounds = new Rectangle(x,y,10,10);
		return bounds;
	}
	
	//stops the head from moving
	public void stop()
	{
		canMove = false;
	}
	
	public boolean isMoving()
	{
		if(direction == -1)
			return false;
		return true;
	}
}







