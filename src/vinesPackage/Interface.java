package vinesPackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Interface implements ActionListener
{
	//Declarations
	GameFrame g;
	Menu m;
	int players;
	int roundNums;
	
	//JButton p_num, r_num, st_but, cl_but;//
	
	//Constructor
	public Interface() throws IOException
	{
		setUpMenu();
		players = -1;
		roundNums = -1;
		
		//Checker for game end
		Timer t = new Timer(1,new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(g!=null && g.isDone())
				{
					JOptionPane.showMessageDialog(g,"Final Score:\n" +
						 g.getScore());
					g.dispose();
					g.Reset();

					try 
					{
						setUpMenu();
						players = -1;
						roundNums = -1;
						
					} 
					catch (IOException e) 
					{
						
						
					}
				}
			}    
		});
		
		t.start();	
	}

	//Sets up the menu
	private void setUpMenu() throws IOException 
	{
		m = new Menu();
		
		/* Button Menu
		JPanel button_pan = new JPanel();
		button_pan.setLayout(new BoxLayout(button_pan, BoxLayout.Y_AXIS));
		p_num = new JButton("Player #?");
		r_num = new JButton("Rounds #?");
		st_but = new JButton("Start");
		cl_but = new JButton("Close");
		
		button_pan.add(Box.createVerticalStrut(100));

		button_pan.add(p_num);
		p_num.setAlignmentX(Component.CENTER_ALIGNMENT); 
		button_pan.add(Box.createVerticalStrut(20));
		button_pan.add(r_num);
		r_num.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_pan.add(Box.createVerticalStrut(20));
		button_pan.add(st_but);
		st_but.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_pan.add(Box.createVerticalStrut(20));
		button_pan.add(cl_but);
		cl_but.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		m.add(button_pan);
		p_num.setActionCommand("Player #?");
		p_num.addActionListener(this);
		r_num.setActionCommand("Rounds#?");
		r_num.addActionListener(this);
		st_but.setActionCommand("Start");
		st_but.addActionListener(this);
		cl_but.setActionCommand("Close");
		cl_but.addActionListener(this);
		*/
		
		JMenuBar b = new JMenuBar();
		JMenu file = new JMenu("Options");
		b.add(file,JMenu.CENTER);
		JMenuItem players = new JMenuItem("Player #?");
		file.add(players);
		JMenuItem rounds = new JMenuItem("Rounds#?");
		file.add(rounds);
		
		JMenuItem start = new JMenuItem("Start");
		file.add(start);
		JMenuItem end = new JMenuItem("Close");
		file.add(end);
		
		start.addActionListener(this);
		end.addActionListener(this);
		players.addActionListener(this);
		rounds.addActionListener(this);
		m.getPanel().add(b,BorderLayout.BEFORE_FIRST_LINE);
		
	}

	//Action listener for menu
	public void actionPerformed(ActionEvent e) 
	{
		String key = e.getActionCommand();
		
		if(key.equals("Start"))
		{	
			if((players >=1 && players <=4)&& (roundNums >= 1 && 
				roundNums <= 10))
			{
				m.dispose();
							
				g = new GameFrame(roundNums,players);
				JOptionPane.showMessageDialog(g,"Instructions:\nPlayer 1: WAS"+
					"D\nPlayer 2: Arrow Keys\nPlayer 3: YGHJ\nPlayer 4: pl;'"+
					"\n\nFor differing amounts of players, the controls are"+
					" added accordingly.\nThe first player to press his "+
					"control keys starts the game.\nIf you move opposite to"+
					" your direction you die, your primary direction is North"+
					".\n\nPlayer orientation depends on amount of players:"+
					"\nTwo Players: 1 Left, 2 Right\nThree Players: 1 Left,"+
					" 2 Right, 3 Center\nFour Players: 1 Up Left"+
					", 2 Up Right, 3 Down Left, 4 Down Right");
			}
			else if((players < 1 || players > 4)&&
				(roundNums < 1 || roundNums > 10))
			{
				JOptionPane.showMessageDialog(m,"Please choose the amount of "+
					"players and rounds you want?");
			}
			
			else if(players < 1 || players > 4)
			{
				JOptionPane.showMessageDialog(m,"Please choose the amount of "+
					"players you want?");	
			}
			else if(roundNums < 1 || roundNums > 10)
			{
				JOptionPane.showMessageDialog(m,"Please choose the amount of "+
					"rounds you want?");
			}
		}
		
		if(key.equals("Player #?"))
		{
			try
			{
				players = Integer.parseInt(JOptionPane.showInputDialog(m,"How "+
					"Many Players?"));
			}
			catch(NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(m, "Please enter a correct amou"+
					"nt of players.");
			}
			
		}
		
		if(key.equals("Rounds#?"))
		{
			try
			{
				roundNums = Integer.parseInt(JOptionPane.showInputDialog(m,"Ho"+
					"w Many Rounds?"));
			}
			catch(NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(m, "Please enter a correct "+
					"amount of rounds.");
			}	
		}
		
		if(key.equals("Close"))
		{
			try
			{
				m.kill();
				g.kill();
				m.dispose();
				g.dispose();
			}
			catch(NullPointerException e2)
			{	
			}	
		}		
	}
}
