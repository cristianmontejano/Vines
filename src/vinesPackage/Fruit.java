/* Vines
 * Fruit Class
 * The fruit class creates the necessary object for the player/AI to control
 */

package vinesPackage;

import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Fruit
{
	//Declaration
	public  final int NORTH = 0;
	public  final int SOUTH = 180;
	public  final int WEST = 270;
	public  final int EAST = 90;
	//NEEDS EDIT
	private ImageIcon fruitPic;
	private int direction;
	private int x;
	private int y;	
	private int xVel;
	private int yVel;
	private boolean isAlive;
	private Rectangle bounds;

	//Default constructor
	public Fruit()
	{
		x = 50;
		y = 50;
		xVel = 0;
		yVel = 0;	
		direction = NORTH;
		
		fruitPic = new ImageIcon("fruitpicUP.png");
		isAlive = true;
		bounds = new Rectangle(getX(),getY(),10,10);
	
	}

	//Overloaded constructor
	public Fruit(int tmpX, int tmpY, int tDir)
	{
		x = tmpX;
		y = tmpY;
		xVel = 0;
		yVel = 0; 
		direction = tDir;
		fruitPic = getRelativeImage(tDir);
		isAlive = true;
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
	
	//Getter for x velocity
	public int getXVel()
	{
		return xVel;
	}
	
	//Getter for y velocity
	public int getYVel()
	{
		return yVel;
	}
	
	//Setter for x velocity
	public void setXVel(int tmpXVel)
	{
		xVel = tmpXVel;
	}
	
	//Setter for y velocity
	public void setYVel(int tmpYVel)
	{
		yVel = tmpYVel;
	}
	
	//Getter for direction	
	public int getDirection()
	{
		return direction;
	}
	
	//Setter for direction
	public void setDirection(int tDir)
	{
		direction = tDir;
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
	
	//Returns the image
	public ImageIcon getImage()
	{
		return fruitPic;
	}
	
	//Setter for image
	public void setImage(ImageIcon img)
	{
		fruitPic = img;
	}
	
	//Retunes the relative image of ship
	public ImageIcon getRelativeImage(int tDir)
	{
		String fileName = "";
		
		if(tDir == NORTH)
			fileName = "fruitpicUP.png";
		
		else if(tDir == WEST)
			fileName = "fruitpicLEFT.png";
		
		else if(tDir == EAST)
			fileName = "fruitpicRIGHT.png";
		
		else if(tDir == SOUTH)
			fileName = "fruitpicDOWN.png";
		
		ImageIcon image = new ImageIcon(fileName);
		
		return image;
	}

	//Returs whether or not the pilot is alive
	public boolean isAlive() 
	{
		return isAlive;
	}

	//Removes the pilot
	public void remove() 
	{		
		setXVel(0);
		setYVel(0);
		isAlive = false;		
	}
		
	//Updates the bounds of the ship
	public void updateBounds()
	{
		bounds = new Rectangle(getX(),getY(),10,10);
	}
	
	//Returns ships bounds
	public Rectangle getBounds()
	{
		return bounds;
	}

}












