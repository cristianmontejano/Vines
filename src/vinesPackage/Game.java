package vinesPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.io.IOException;

public class Game extends JPanel implements ActionListener, Runnable
{
	//Declarations	
	Image dbi; 
	Graphics dbg; 
	Player p;
	Player p2;
	Player p3;
	Orb orb;
	boolean finished;
	Timer t;
	int round;
	int endRound;
	ArrayList<Player> players;
	Thread th;
	
	//Overloaded constructor 	
	public Game(int tRounds, int numPlayers)
	{
		round = 1;
		endRound = tRounds;
		finished = false;
		players = new ArrayList<Player>();
		
		players.add(new Player(1,200,150,-1,0,0));
		players.add(new Player(2,400,150,-1,0,0));
		players.add(new Player(3,200,350,-1,0,0));
		players.add(new Player(4,400,350,-1,0,0));
		addOrb();	
		
		for(Player p: players)
		{
			addKeyListener(p);
		}
		
		for(Player p: players)
		{
			p.startReading();
		}
		
		//Allows JPanel to be focusable 
		setFocusable(true);
		
		Timer t = new Timer(60,this);
		
		t.start();	
		
		th = new Thread(this);
		th.start();
	
	}
	
	//Paint   for double buffer
	public void paint(Graphics g)
	{
		
		dbi = createImage(getWidth(),getHeight());
		dbg = dbi.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbi,0,0,this);
		
	}
	
	//Action performed for timer, plays game
	public void actionPerformed(ActionEvent e) 
	{
		int deathCount = 0;
		
		if(players.size()==4)
		{
			Player p1 = players.get(0);
			Player p2 = players.get(1);
			Player p3 = players.get(2);
			Player p4 = players.get(3);	
			
			if((p1.gettFruit().getDirection() != -1) && (p2.gettFruit().getDirection() != -1)&&(p3.gettFruit().getDirection() != -1) && (p4.gettFruit().getDirection() != -1))
			{
				p1.ready();
				p2.ready();
				p3.ready();
				p4.ready();
				
				for(Player p: players)
				{
					if(p.gettFruit().getDirection() == 0)
					{
						p.gettFruit().setXVel(0);
						p.gettFruit().setYVel(-10);
						p.fly();
					}
					else if(p.gettFruit().getDirection() == 90)
					{
						p.gettFruit().setXVel(10);
						p.gettFruit().setYVel(0);
						p.fly();
					}
					else if(p.gettFruit().getDirection() == 180)
					{
						p.gettFruit().setXVel(0);
						p.gettFruit().setYVel(10);
						p.fly();
					}
					else if(p.gettFruit().getDirection() == 270)
					{
						p.gettFruit().setXVel(-10);
						p.gettFruit().setYVel(0);
						p.fly();
					}										
				}
			}
			
			else 
			{
				p1.gettFruit().setImage(p1.gettFruit().getRelativeImage(0));
				p2.gettFruit().setImage(p2.gettFruit().getRelativeImage(0));
				p3.gettFruit().setImage(p3.gettFruit().getRelativeImage(0));
				p4.gettFruit().setImage(p4.gettFruit().getRelativeImage(0));
			}
		
			
			if(p1.isFlying())
			{
				//This draws the Tail
				p1.addTail(new Tail(p1.getFruitX(),p1.getFruitY(),
					p1.getPrevDir(),p1.getPresentDir(),p1.getPlayerNum()));
				
			}

			if(p2.isFlying())
			{
				p2.addTail(new Tail(p2.getFruitX(),p2.getFruitY(),
					p2.getPrevDir(),p2.getPresentDir(),p2.getPlayerNum()));	
			}
			
			if(p3.isFlying())
			{
				p3.addTail(new Tail(p3.getFruitX(),p3.getFruitY(),
					p3.getPrevDir(),p3.getPresentDir(),p3.getPlayerNum()));	
			}
			
			if(p4.isFlying())
			{
				p4.addTail(new Tail(p4.getFruitX(),p4.getFruitY(),
					p4.getPrevDir(),p4.getPresentDir(),p4.getPlayerNum()));		
			}
		}
		
		//Removes player if dead		
		for(Player p: players)
		{
			if(!p.gettFruit().isAlive())
			{
				p.gettFruit().remove();
			}
		}
		
		
		//This moves the ship and updates the Tail
		for(Player p: players)
		{
			p.move();///////////////
		}
						
		collisionCheck();
		
		//Repaints the screen
		repaint();
		
		if(players.size()>1)
		{
			for(Player p: players)
			{
				if(!p.gettFruit().isAlive())
					deathCount++;
			}
			
			if(deathCount >= players.size()-1)
			{
				for(Player p: players)
				{
					if(p.gettFruit().isAlive())
						p.addWin();
					else if(!p.gettFruit().isAlive())
						p.addLoss();
				}
				
				
				reset();
				
				round++;
							
				if(round > endRound)
				{
					finished = true;
				}
			}
		}
		
		if(players.size() == 1)
		{
			if(!players.get(0).gettFruit().isAlive())
			{
				players.get(0).addLoss();
				
				reset();
				
				round++;
											
				if(round > endRound)
				{
					finished = true;
				}	
			}
		}	
		
	}
	
	//Checks for collision between players
	private void collisionCheck() 
	{
		int size = players.size();
		
		if(size == 4)
		{
			Fruit tempFruit1 = players.get(0).gettFruit();
			Fruit tempFruit2 = players.get(1).gettFruit();
			Fruit tempFruit3 = players.get(2).gettFruit();
			Fruit tempFruit4 = players.get(3).gettFruit();
			ArrayList  <Tail> tempTail1  = players.get(0).getTheTail();
			ArrayList <Tail> tempTail2 = players.get(1).getTheTail();
			ArrayList <Tail> tempTail3 = players.get(2).getTheTail();
			ArrayList <Tail> tempTail4 = players.get(3).getTheTail();
			ArrayList <Tail> totTail = new ArrayList<Tail>();
			
			for(int i = 0; i < tempTail1.size()-1;i++)
				totTail.add(tempTail1.get(i));
			
			for(int i = 0; i < tempTail2.size()-1;i++)
				totTail.add(tempTail2.get(i));
			
			for(int i = 0; i < tempTail3.size()-1;i++)
				totTail.add(tempTail3.get(i));
			
			for(int i = 0; i < tempTail4.size()-1;i++)
				totTail.add(tempTail4.get(i));
			
			for(Tail t: totTail)
			{
	  			if(tempFruit1.getBounds().intersects(t.getBounds()))
	 			{
	  				tempFruit1.remove();
	 			}	
	  			
	  			if(tempFruit2.getBounds().intersects(t.getBounds()))
	 			{
	  				tempFruit2.remove();
	 			}
	  			
	  			if(tempFruit3.getBounds().intersects(t.getBounds()))
	 			{
	  				tempFruit3.remove();
	 			}
	  			
	  			if(tempFruit4.getBounds().intersects(t.getBounds()))
	 			{
	  				tempFruit4.remove();
	 			}
			}
			
			if((tempFruit1.getBounds().intersects(tempFruit2.getBounds()))&&((tempFruit1.getXVel() == 0 && tempFruit2.getXVel() != 0)||(tempFruit2.getXVel() == 0 && tempFruit1.getXVel() != 0)))
			{
				tempFruit1.remove();
				tempFruit2.remove();
			}
			
			if((tempFruit1.getBounds().intersects(tempFruit3.getBounds()))&&((tempFruit1.getXVel() == 0 && tempFruit3.getXVel() != 0)||(tempFruit3.getXVel() == 0 && tempFruit1.getXVel() != 0)))
			{
				tempFruit1.remove();
				tempFruit3.remove();
			}
			
			if((tempFruit1.getBounds().intersects(tempFruit4.getBounds()))&&((tempFruit1.getXVel() == 0 && tempFruit4.getXVel() != 0)||(tempFruit4.getXVel() == 0 && tempFruit1.getXVel() != 0)))
			{
				tempFruit1.remove();
				tempFruit4.remove();
			}
			
			if((tempFruit2.getBounds().intersects(tempFruit3.getBounds()))&&((tempFruit2.getXVel() == 0 && tempFruit3.getXVel() != 0)||(tempFruit3.getXVel() == 0 && tempFruit2.getXVel() != 0)))
			{
				tempFruit2.remove();
				tempFruit3.remove();
			}
			
			if((tempFruit2.getBounds().intersects(tempFruit4.getBounds()))&&((tempFruit2.getXVel() == 0 && tempFruit4.getXVel() != 0)||(tempFruit4.getXVel() == 0 && tempFruit2.getXVel() != 0)))
			{
				tempFruit2.remove();
				tempFruit4.remove();
			}
			
			if((tempFruit3.getBounds().intersects(tempFruit4.getBounds()))&&((tempFruit3.getXVel() == 0 && tempFruit4.getXVel() != 0)||(tempFruit4.getXVel() == 0 && tempFruit3.getXVel() != 0)))
			{
				tempFruit3.remove();
				tempFruit4.remove();
			}
		}
	}
	
	//Actual game painter of components
	public void paintComponent(Graphics g)
	{		
		//The paint component g is sent to the paint component of heiarchy  
		super.paintComponent(g);
		
		g.drawImage(new ImageIcon("background.png").getImage(), 0, 0,780,520,this );
		
		for(Player p: players)
		{
			g.drawImage((p.getFruitImage()).getImage(), p.getFruitX(),
				p.getFruitY(),10,10,this );
		}		
		
		for(Player p: players)
		{
			//This paints the Tail components of the ArrayList 
			for(Tail t:p.getTheTail())
			{
				g.drawImage((t.getVector()).getImage(), t.getX(), t.getY(),
					10,10,this );
				//Removes the last tail
				p.getTheTail().remove(p.getTheTail().size()-1);
			}
		}
		
		g.setColor(Color.WHITE);
		
		g.drawString(players.get(0).getScore(), 0, 15);
		g.drawString(players.get(1).getScore(), 100, 15);
		g.drawString(players.get(2).getScore(), 200, 15);
		g.drawString(players.get(3).getScore(), 300, 15);			
	}

	//Adds an Orb
	public void addOrb()
	{
		boolean good = true;
		int distance = 0;
		int tX = (int)(Math.random() * (GameFrame.getScreenWidth() - 100)) + 50;
		int tY = (int)(Math.random() * (GameFrame.getScreenHeight() - 100)) + 50;
		
		distance = (int)Math.sqrt(Math.pow((players.get(0)).getFruitX() - tX,2) + Math.pow((players.get(0)).getFruitY() - tY,2));		
		
		Rectangle tApple = new Rectangle(tX,tY,10,10);
		
		for (Player p: players)
		{
			for(Tail t: p.getTheTail())
				if((tApple.getBounds().intersects(t.getBounds()))|| 
				  (tApple.getBounds().intersects(p.gettFruit().getBounds())))
					good = false;	
		}
		if(good && (distance >= 200))
			orb = new Orb(tX,tY);	
		else
			addOrb();									
	}
	
	//Returns if the game is done
	public boolean isDone() 
	{
		return finished;
	}

	//Resets game
	public void reset()
	{
		
		//Pauses the game
		try
		{
			Thread.sleep(2000);
		}
		catch(InterruptedException e )
		{	
		}
				
		finished = false;
		int size = players.size();
		ArrayList <Player> tplayers = new ArrayList<Player>();
			
		if(size == 1)
		{
			tplayers.add(new Player(1,200,250,-1,players.get(0).getWins(),
				players.get(0).getLosses()));						
		}
		if(size == 2)
		{
			tplayers.add(new Player(1,200,250,-1,players.get(0).getWins(),
				players.get(0).getLosses()));
			tplayers.add(new Player(2,400,250,-1,players.get(1).getWins(),
				players.get(1).getLosses()));
		}
		if(size == 3)
		{
			tplayers.add(new Player(1,200,250,-1,players.get(0).getWins(),
				players.get(0).getLosses()));
			tplayers.add(new Player(2,400,250,-1,players.get(1).getWins(),
				players.get(1).getLosses()));
			tplayers.add(new Player(3,300,150,-1,players.get(2).getWins(),
				players.get(2).getLosses()));			
		}
		if(size == 4)
		{
			tplayers.add(new Player(1,200,150,-1,players.get(0).getWins(),
				players.get(0).getLosses()));
			tplayers.add(new Player(2,400,150,-1,players.get(1).getWins(),
				players.get(1).getLosses()));
			tplayers.add(new Player(3,200,350,-1,players.get(2).getWins(),
				players.get(2).getLosses()));
			tplayers.add(new Player(4,400,350,-1,players.get(3).getWins(),
				players.get(3).getLosses()));
		}
		
		players = tplayers;
		
		
		for(Player p: players)
		{
			addKeyListener(p);
		} 
	}
	
	//Returns player arraylist
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public void run()
	{
		collisionCheck();
	}	
}