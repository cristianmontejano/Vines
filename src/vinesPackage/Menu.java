package vinesPackage;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class Menu extends JFrame
{
	//Declarations
	JPanel p;
	
	//Constructor Default
	public Menu() throws IOException
	{
		setSize(500,500);
		p = new JPanel();
		p.setLayout(new BorderLayout());
		BufferedImage myPicture = ImageIO.read(new File("tan.png"));
		JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		p.add( picLabel,BorderLayout.CENTER);
		
		add(p);
		
		setTitle("Vines Menu");
		setVisible(true);
		
		setResizable(false);
	}
	
	//Returns Panel
	public JPanel getPanel()
	{
		return p;
	}
	
	public void kill()
	{
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}	
}
