package core;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sun
{
	//Declaration of Variables
	private int x;
	private int y;
	private Rectangle bounds;
	private ImageIcon pic = new ImageIcon("sunpixel.png");
	
	//Overloaded constructor
	public Sun(int tX, int tY)
	{
		x = tX;
		y = tY;
		bounds = new Rectangle(x,y,60,60);
	}
	
	//Returns a rectangle thats representative of the objects bounds
	public Rectangle getBounds()
	{
		//resets bounds
		bounds = new Rectangle(x,y,60,60);
		
		return bounds;
	}
	
	//Returns the x variable
	public int getX()
	{
		return x;
	}
		
	//Returns the y variable	
	public int getY()
	{
		return y;
	}
	
	//returns the sun image
	public ImageIcon getPicture()
	{
		return pic;
	}
}









