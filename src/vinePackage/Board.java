package vinePackage; 
import javax.swing.JFrame;

public class Board extends JFrame
{
	//declaration of variables
	public static int screenWidth;
	public static int screenHeight;
	
	public Board()
	{
		//sets size of screen
		setSize(1000,1000);
		
		//makes it not resizable
		setResizable(true);
		
		//sets the frame to exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//sets screen height/width variables
		screenHeight = size().height - 40;
		screenWidth = size().width - 20;
	}
	
	//returns the boards width	
	public static int getBoardWidth()
	{
		return screenWidth;
	}
	
	//returns the boards height
	public static int getBoardHeight()
	{
		return screenHeight;
	}
}