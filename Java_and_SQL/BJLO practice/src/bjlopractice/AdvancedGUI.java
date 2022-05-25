import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

class AdvancedGUI 
{

	public static void main(String[] args)
	{
		new ThreeButtons3();
	}

}

class CalcTest extends JFrame
{
	CalcTest()
	{
		JPanel keypadPanel = new JPanel();
		keypadPanel.setLayout(new BorderLayout());
		
	}
}

/*
1 Write an application with three buttons labeled “Red”, “Green”, and “Blue” that
changes the background color of a panel in the center of the frame to red, green,
or blue.*/
class ThreeButtons extends JFrame
{
	JPanel btns;
	JPanel coloredPanel;
	ColoredPanel panelColor;
	Color selectedColor;
	
	ThreeButtons()
	{
		btns = new JPanel();
		coloredPanel = new JPanel();
		panelColor = new ColoredPanel();
		
		coloredPanel.setSize(200, 200);
		panelColor.setPreferredSize(new Dimension(200, 200));
		
		String[] colorNames = {"Red", "Blue", "Yellow"};
		Color[] colors = {Color.red, Color.blue, Color.yellow};
		
		btns.setLayout(new GridLayout(1, 3));
		for(int i = 0; i < 3; i++)
		{
			JButton btn = new JButton(colorNames[i]);
			btn.addActionListener(new BtnColorChange(colors[i]));
			btns.add(btn);
		}
		
		coloredPanel.add(panelColor);
		this.add(btns, BorderLayout.NORTH);
		this.add(coloredPanel, BorderLayout.CENTER);
		
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class BtnColorChange implements ActionListener
	{
		
		Color color;
		
		BtnColorChange(Color color)
		{
			this.color = color;
		}
		
		public void actionPerformed(ActionEvent e) {
			selectedColor = color;
			panelColor.repaint();
		}
	}
	
	class ColoredPanel extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			g.setColor(selectedColor);
			g.fillRect(0, 0, coloredPanel.getWidth(), coloredPanel.getHeight());
		}
	}
}
/*
•• P11.2 Add icons to the buttons of Exercise P11.1. Use a JButton constructor with an Icon
argument and supply an ImageIcon.
• P11.3 Write an application with three radio buttons labeled “Red”, “Green”, and “Blue”
that changes the background color of a panel in the center of the frame to red, green,
or blue. */
class ThreeButtons2 extends JFrame
{
	JPanel radioPanel, coloredPanelPanel;
	ButtonGroup radios;
	JRadioButton[] colorRadios;
	Color selectedColor;
	ColoredPanel coloredPanel;
	
	ThreeButtons2()
	{
		radioPanel = new JPanel();
		radios = new ButtonGroup();
		colorRadios = new JRadioButton[3];
		colorRadios[0] = radio("Red", Color.red);
		colorRadios[1] = radio("Green", Color.green);
		colorRadios[2] = radio("Blue", Color.blue);
		coloredPanelPanel = new JPanel();
		coloredPanel = new ColoredPanel();
		
		for(JRadioButton btn : colorRadios)
		{
			radioPanel.add(btn);
			radios.add(btn);
		}
		
		radioPanel.setBorder(new EtchedBorder());
		
		coloredPanel.setPreferredSize(new Dimension(200, 200));
		coloredPanelPanel.add(coloredPanel);
		
		this.add(radioPanel, BorderLayout.NORTH);
		this.add(coloredPanelPanel, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	JRadioButton radio(String name, Color color)
	{
		JRadioButton radio = new JRadioButton(name);
		radio.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Iterator<AbstractButton> it = radios.getElements().asIterator();
				while(it.hasNext()) {
					if(it.next().isSelected())
					{
						selectedColor = color;
						coloredPanel.repaint();
					}
				}
			}
		});
		
		return radio;
	}
	
	class ColoredPanel extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			g.setColor(selectedColor);
			g.fillRect(0, 0, 500, 500);
		}
	}
}
/*
• P11.4 Write an application with three check boxes labeled “Red”, “Green”, and “Blue”
that adds a red, green, or blue component to the background color of a panel in the
center of the frame. This application can display a total of eight color combinations.*/

class ThreeButtons3 extends JFrame
{
	JPanel panel, coloredPanelPanel;
	JCheckBox[] colorChecks;
	Color selectedColor;
	ColoredPanel coloredPanel;
	int[] rgb = {0, 0, 0};
	
	ThreeButtons3()
	{
		panel = new JPanel();
		colorChecks = new JCheckBox[3];
		colorChecks[0] = radio("Red", Color.red);
		colorChecks[1] = radio("Green", Color.green);
		colorChecks[2] = radio("Blue", Color.blue);
		coloredPanelPanel = new JPanel();
		coloredPanel = new ColoredPanel();
		
		for(JCheckBox btn : colorChecks)
		{
			panel.add(btn);
		}
		
		coloredPanel.setPreferredSize(new Dimension(200, 200));
		coloredPanelPanel.add(coloredPanel);
		
		this.add(panel, BorderLayout.NORTH);
		this.add(coloredPanelPanel, BorderLayout.CENTER);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	JCheckBox radio(String name, Color color)
	{
		JCheckBox checkBox = new JCheckBox(name);
		checkBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

				for(int i = 0; i < colorChecks.length; i++)
				{
					if(colorChecks[i].isSelected())
					{
						rgb[i] = 255;
					} else
					{
						rgb[i] = 0;
					}
				}
				selectedColor = new Color(rgb[0], rgb[1], rgb[2]);
				coloredPanel.repaint();
			}
		});
		
		return checkBox;
	}
	
	class ColoredPanel extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			g.setColor(selectedColor);
			g.fillRect(0, 0, 500, 500);
		}
	}
}

/*
• P11.5 Write an application with a combo box containing three items labeled “Red”,
“Green”, and “Blue” that change the background color of a panel in the center of the
frame to red, green, or blue.
• P11.6 Write an application with a Color menu and menu items labeled “Red”, “Green”,
and “Blue” that change the background color of a panel in the center of the frame to
red, green, or blue.
• P11.7 Write a program that displays a number of rectangles at random positions. Supply
menu items “Fewer” and “More” that generate fewer or more random rectangles.
Each time the user selects “Fewer”, the count should be halved. Each time the user
clicks on “More”, the count should be doubled.
•• P11.8 Modify the program of Exercise P11.7 to replace the buttons with a slider to generate more or fewer random rectangles.
•• P11.9 Modify the slider program in Section 11.4 to add a set of tick marks to each slider
that show the exact slider position.
••• P11.10 Enhance the font viewer program to allow the user to select different font faces.
Research the API documentation to find out how to find the available fonts on the
user’s system.
••• P11.11 Write a program that lets users design charts such as the following:
Golden Gate
Brooklyn
Delaware Memorial
Mackinac
Use appropriate components to ask for the length, label, and color, then apply them
when the user clicks an “Add Item” button.
P r o g ra m m i n g E x er c i s e s
Programming Exercises 545
• P11.12 Write a program that uses a timer to print the current time once a second. Hint: The
following code prints the current time:
Date now = new Date();
System.out.println(now);
The Date class is in the java.util package.
••• P11.13 Change the RectangleComponent for the animation in Section 11.5 so that the rectangle
bounces off the edges of the component rather than simply moving outside.
•• P11.14 Change the rectangle animation in Section 11.5 so that it shows two rectangles
moving in opposite directions.
•• P11.15 Write a program that animates a car so that it moves across a frame.
••• P11.16 Write a program that animates two cars moving across a frame in opposite directions
(but at different heights so that they don’t collide.)
••• P11.17 Write a program that displays a scrolling message in a panel. Use a timer for the
scrolling effect. In the timer’s action listener, move the starting position of the message and repaint. When the message has left the window, reset the starting position
to the other corner. Provide a user interface to customize the message text, font,
foreground and background colors, and the scrolling speed and direction.
• P11.18 Change the RectangleComponent for the mouse listener program in Section 11.6 so that
a new rectangle is added to the component whenever the mouse is clicked. Hint:
Store all points on which the user clicked, and draw all rectangles in the paintComponent method.
• P11.19 Write a program that prompts the user to enter the x- and y­positions of a center
point and a radius, using text fields.When the user clicks a “Draw” button, draw a
circle with that center and radius in a component.
•• P11.20 Write a program that allows the user to specify a circle by typing the radius in a text
field and then clicking on the center. Note that you don’t need a “Draw” button.
• P11.21 Write a program that allows the user to specify a circle with two mouse presses,
the first one on the center and the second on a point on the periphery. Hint: In the
mouse press handler, you must keep track of whether you already received the
center point in a previous mouse press.
••• P11.22 Write a program that allows the user to specify a triangle with three mouse presses.
After the first mouse press, draw a small dot. After the second mouse press, draw a
line joining the first two points. After the third mouse press, draw the entire triangle.
The fourth mouse press erases the old triangle and starts a new one.
••• P11.23 Implement a program that allows two players to play
tic-tac-toe. Draw the game grid and an indication of
whose turn it is (X or O). Upon the next click, check
that the mouse click falls into an empty location, fill
the location with the mark of the current player, and
give the other player a turn. If the game is won, indicate the winner. Also supply a button for starting over. 
546 Chapter 11 Advanced User Interfaces
••• P11.24 Write a program that lets users design bar charts with a mouse. When the user clicks
inside a bar, the next mouse click extends the length of the bar to the x-coordinate of
the mouse click. (If it is at or near 0, the bar is removed.) When the user clicks below
the last bar, a new bar is added whose length is the x-coordinate of the mouse click.
•• Business P11.25 Write a program with a graphical interface that allows the user to convert an amount
of money between U.S. dollars (USD), euros (EUR), and British pounds (GBP). The
user interface should have the following elements: a text box to enter the amount to
be converted, two combo boxes to allow the user to select the currencies, a button
to make the conversion, and a label to show the result. Display a warning if the user
does not choose different currencies. Use the following conversion rates:
1 EUR is equal to 1.42 USD.
1 GBP is equal to 1.64 USD.
1 GBP is equal to 1.13 EUR.
•• Business P11.26 Write a program with a graphical interface that implements a login window with text
fields for the user name and password. When the login is successful, hide the login
window and open a new window with a welcome message. Follow these rules for
validating the password:
1. The user name is not case sensitive.
2. The password is case sensitive.
3. The user has three opportunities to enter valid credentials.
Otherwise, display an error message and terminate the program. When the program
starts, read the file users.txt. Each line in that file contains a username and password,
separated by a space. You should make a users.txt file for testing your program.
•• Business P11.27 In Exercise P11.26, the password is shown as it is typed. Browse the Swing documentation to find an appropriate component for entering a password. Improve the
solution of Exercise P11.26 by using this component instead of a text field. Each
time the user types a letter, show a ■ character.
*/