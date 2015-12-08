package vinePackage;

/**
 * 
 * @authors Cristian Montejano
 *          Mark Arishita
 *          Joshua Martinez
 *          Quentin Heard
 *
 */
public class Client
{
	public static void main (String [] args)
	{
		try
		{
			//makes a new game board
			Board gameBoard = new Board();
			
			//adds a new game to the game board
			gameBoard.add(new Game());	
				
			//sets the gameboard visible
			gameBoard.setVisible(true);	
		}
		catch(Exception e)
		{
			System.out.println("Caught");
		}	
	}
}