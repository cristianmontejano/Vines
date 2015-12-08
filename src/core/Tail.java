
package core;

import java.awt.Rectangle;

public class Tail
{
	//Declaration of variables
	private int x;
	private int y;
	private Rectangle bounds;
	
	//Overloaded Constructor
	public Tail(int tempX, int tempY)
	{
		x = tempX;
		y = tempY;
		bounds = new Rectangle(x,y,10,10);
	}
	
	//Setter for the x coordinate
	public void setX(int tempX)
	{
		x = tempX;	
	}
	
	//Setter for the y coordinate
	public void setY(int tempY)
	{
		y = tempY;
	}
	
	//Getter for x coordinate
	public int getX()
	{
		return x;
	}
	
	//getter for y coordinate
	public int getY()
	{
		return y;
	}
	
	//Returns the Tail's bounds
	public Rectangle getBounds()
	{
		bounds = new Rectangle(x,y,10,10);
		return bounds;
	}
}













