package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI
{
	
	public static void main(String[] args)
	{
		new GUI();
	}
	
	GUI()
	{
		new Bill();
	}
}
/*
Write a program that shows a square frame filled with 100 buttons labeled 1 to 100.
Nothing needs to happen when you press any of the buttons. */
class P10_1 extends JFrame {
	
	JPanel p = new JPanel();
	
	void addComponents() {
		
		for(int i = 0; i < 100; i++) {
			p.add(new JButton("Hello"));
		}
		
		this.add(p);
		this.setSize(600, 500);
		this.setVisible(true);
	}
}
/*
Enhance the ButtonViewer1 program in Section 10.2.1 so that it prints a message
I was clicked n times!” whenever the button is clicked. The value n should be incremented with each click.

Enhance the ButtonViewer1 program in Section 10.2.1 so that it has two buttons, each
of which prints a message “I was clicked n times!” whenever the button is clicked.
Each button should have a separate click count.

Enhance the ButtonViewer1 program in Section 10.2.1 so that it has two buttons
labeled A and B, each of which prints a message “Button x was clicked!”, where x is
A or B.

Implement a ButtonViewer1 program as in Exercise P10.3 using only a single listener
class. Hint: Pass the button label to the constructor of the listener

Enhance the ButtonViewer1 program so that it prints the date and time at which the
button was clicked. Hint: System.out.println(new java.util.Date()) prints the current
date and time. */
class ButtonViewer1 extends JFrame {
	
	JLabel j;
	
	void addComponents() {
		add(btnCounter(0, 0, 'a'));
		add(btnCounter(0, 50, 'b'));
		j = new JLabel();
		add(j);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
	}
	
	JPanel btnCounter(int x, int y, char id) {
		JPanel p = new JPanel();
		JButton b = new JButton("Click me!");
		JLabel l = new JLabel(String.valueOf(0));
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(l.getText());
				l.setText(String.valueOf(n + 1));
				j.setText("Button " + id + " was clicked.");
				System.out.println("Button " + id + " was clicked at " + new Date());
			}
		});
		
		p.add(b);
		p.add(l);
		//Need to work on placement and relative placement. Component positions are wonky:
		p.setBounds(x, y, 100, 50);
		return p;
	}
}

/*
Implement the ClickListener in the ButtonViewer2 program of Section 10.2.2 as a
regular class (that is, not an inner class). Hint: Store a reference to the label. Add a
constructor to the listener class that sets the reference. */
class ButtonViewer2 extends JFrame {
	JLabel j = new JLabel("...");
	ClickListener cl = new ClickListener(j);
	JButton btn = new JButton("Click me.");
	
	void addComponents() {
		JPanel p = new JPanel();
		btn.addActionListener(new ClickListener(j));
		p.add(btn);
		p.add(j);
		this.add(p);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}
} class ClickListener implements ActionListener {

	JLabel j;
	
	public ClickListener(JLabel j) {
		this.j = j;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		j.setText("Test");
	}
}

/*
Add error handling to the program in Section 10.3.2. If the interest rate is not a
floating-point number, or if it less than 0, display an error message, using a JOptionPane (see Special Topic 2.5).
P10.22 Improve the output quality of the investment application in Section 10.3.2. Format
the numbers with two decimal digits, using the String.format method. Set the font of
the text area to a fixed width font, using the call
textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); */
class InvestmentFrame3 extends JFrame {
	
	JLabel rateLabel;
	JTextField rateField;
	JButton btn;
	JTextArea resultArea;
	double balance;
	
	public InvestmentFrame3() {
		balance = 1000;
		resultArea = new JTextArea(10, 30);
		resultArea.setText(balance + "\n");
		resultArea.setEditable(false);
		
		rateLabel = new JLabel("Interest rate: ");
		rateField = new JTextField(10);
		rateField.setText("" + 0);
		
		btn = new JButton("Click me.");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double rate = Double.parseDouble(rateField.getText());
				if(rate < 0 || (rate % 1 != 0)) {
					JOptionPane.showMessageDialog(null, "Please enter a whole number greater than 0.", "Whoops!", JOptionPane.ERROR_MESSAGE);
				} else {
					double interest = balance * rate / 100;
					balance += interest;
					resultArea.append(String.format("%.2f", balance) + "\n");
				}
			}
		});
		JPanel p = new JPanel();
		p.add(btn);
		p.add(rateLabel);
		p.add(rateField);
		p.add(resultArea);
		add(p);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setVisible(true);
	}
}


/*
P10.9 Write a graphical application simulating a bank account. Supply text fields and buttons for depositing and withdrawing money, and for displaying the current balance
in a label. */
class BankAccountGUI extends JFrame {
	
	JLabel balanceLabel;
	double balance;
	
	BankAccountGUI() {
		add(transaction("Deposit: ", true, 0, 0));
		add(transaction("Withdrawal: ", false, 100, 100));
		balanceLabel = new JLabel();
		this.add(balanceLabel);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 300);
		this.setVisible(true);
	}
	
	JPanel transaction(String label, boolean deposit, int x, int y) {
		
		JPanel transaction = new JPanel();
		JTextField amount = new JTextField(10);
		JButton btn = new JButton("Confirm");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(amount.getText().matches("\\d+")) {
					if(deposit) {
						balance += Integer.parseInt(amount.getText());
					} else {
						balance -= Integer.parseInt(amount.getText());
					}
					balanceLabel.setText(String.valueOf(balance));
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a number that's greater than 0", "Whoops!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//todo: clean up positioning of JPanels.
		transaction.setBounds(x, y, 100, 100);
		transaction.add(new JLabel(label));
		transaction.add(amount);
		transaction.add(btn);
		
		return transaction;
	}
}
/*
P10.10 Write a graphical application describing an earthquake, as in Section 3.3. Supply a
text field and button for entering the strength of the earthquake. Display the earthquake description in a label. */
class EarthQuakeDisplay extends JFrame {
	
	JPanel panel;
	JLabel label;
	JTextField userText;
	JButton btn;
	JLabel text;
	
	EarthQuakeDisplay() {
		panel = new JPanel();
		label = new JLabel("Earthquake strength: ");
		userText = new JTextField(10);
		btn = new JButton("Confirm");
		text = new JLabel();
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text.setText(userText.getText());
			}
		});
		panel.add(label);
		panel.add(userText);
		panel.add(btn);
		panel.add(text);
		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350, 100);
		this.setVisible(true);
	}
}
/*
P10.11 Write a graphical application for computing statistics of a data set. Supply a text
field and button for adding floating-point values, and display the current minimum,
maximum, and average in a label. */

class Statistics extends JFrame {
	
	JLabel nLabel;
	JTextField nField;
	JButton btn;
	JTextArea table;
	JLabel stats;
	double min;
	double max;
	double average = 0;
	double n;
	
	public Statistics() {
		table = new JTextArea(10, 30);
		table.setEditable(false);
		
		nLabel = new JLabel("Number to add: ");
		nField = new JTextField(10);
		nField.setText("" + 0);
		
		btn = new JButton("Click me.");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String txt = nField.getText();
				System.out.println(txt);
				if(txt.matches("-?\\d+(\\.{1}\\d+)?")) {
					n = Double.parseDouble(nField.getText());
					table.append(String.valueOf(n) + "\n");
					
					if(n < min) {
						min = n;
					} else if(n > max) {
						max = n;
					}
					
					average *= table.getText().split("\\n").length - 1;
					average += n;
					average /= table.getText().split("\\n").length;
					
					stats.setText("Min: " + min + " Max : " + max + " Average: " + average);
				} else {
					JOptionPane.showMessageDialog(null, "Please enter a number greater than 0.", "Whoops!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		stats = new JLabel();
		
		JPanel p = new JPanel();
		p.add(btn);
		p.add(nLabel);
		p.add(nField);
		p.add(table);
		p.add(stats);
		add(p);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(true);
	}
}

/*
P10.12 Write an application with three labeled text fields, one each for the initial amount of
a savings account, the annual interest rate, and the number of years. Add a button
“Calculate” and a read-only text area to display the balance of the savings account
after the end of each year.

P10.13 In the application from Exercise P10.12, replace the text area with a bar chart that
shows the balance after the end of each year. */
class Account extends JFrame {
	
	JPanel p;
	JLabel initial, interest, years;
	JTextField initialF, interestF, yearsF;
	JButton cal;
	//JTextArea balance;
	ChartComponent chart;
	
	Account(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		
		p = new JPanel();
		
		initial = new JLabel("Initial savings: ");
		interest = new JLabel("Annual interest rate: ");
		years = new JLabel("Number of years");
		
		initialF = new JTextField(10);
		interestF = new JTextField(10);
		yearsF = new JTextField(10);
		
		chart = new ChartComponent(0, 200);
		chart.setPreferredSize(new Dimension(800, 800));
		
		cal = new JButton("Calculate");
		cal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = Integer.parseInt(initialF.getText());
				for(int i = 0; i < Integer.parseInt(yearsF.getText()); i++) {
					result *= Integer.parseInt(interestF.getText());
				}
				chart.addN(result);
			}
			
		});
		//balance = new JTextArea(1, 10);
		//balance.setEditable(false);
		
		p.add(initial);
		p.add(initialF);
		p.add(interest);
		p.add(interestF);
		p.add(years);
		p.add(yearsF);
		p.add(cal);
		//p.add(balance);
		p.add(chart);
		
		this.add(p);
		this.setVisible(true);
	}
	
	class ChartComponent extends JComponent {
		
		int xPos, yPos, xSize, ySize;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		ChartComponent(int xPos, int yPos){
			this.xPos = xPos;
			this.yPos = yPos;
			this.ySize = 10;
		}
		
		void addN(int n){
			arr.add(n);
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			for(int i = 0; i < arr.size(); i++) {
				xSize = arr.get(i);
				yPos = 15 * i;
				g.fillRect(xPos, yPos, xSize, ySize);
			}
		}
		
	}
}
/*
P10.14 Write a graphics program that draws your name in red, contained inside a blue rectangle.
Provide a class NameViewer and a class NameComponent. */
class NameViewer extends JFrame {
	
	NameComponent name;
	
	NameViewer() {
		
		name = new NameComponent(220, 200, 360, 240);
		add(name);
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class NameComponent extends JComponent {
		
		int x, y, length, height;
		
		NameComponent(int x, int y, int length, int height) {
			this.x = x;
			this.y = y;
			this.length = length;
			this.height = height;
		}
		
		public void paintComponent(Graphics g) {
			System.out.println("Test");
			g.setColor(Color.BLUE);
			g.fillRect(x, y, length, height);
			g.setColor(Color.RED);
			g.drawString("Alijah", 390, 320);
		}
	}
}
/*
P10.15 Write a graphics program that draws 12 strings, one each for the 12 standard colors,
besides Color.WHITE, each in its own color. Provide a class ColorNameViewer and a class
ColorNameComponent. */
class ColorNameViewer extends JFrame {
	
	ColorNameComponent name;
	
	ColorNameViewer() {
		
		name = new ColorNameComponent();
		add(name);
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class ColorNameComponent extends JComponent {
		
		ColorNameComponent() {
			
		}
		
		public void paintComponent(Graphics g) {
			
			Color[] colors = new Color[] {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
			String[] colorsName = new String[] {"Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red", "Yellow"};
			
			for(int i = 0; i < colors.length; i++) {
				g.setColor(colors[i]);
				g.drawString(colorsName[i], 0, (i + 1) * 15);
			}
		}
	}
}
/* P10.16 Write a program that draws two solid squares: one in pink and one in purple. Use
a standard color for one of them and a custom color for the other. Provide a class
TwoSquareViewer and a class TwoSquareComponent. */

class TwoSquareViewer extends JFrame {
	
	TwoSquareComponent square;
	
	TwoSquareViewer() {
		square = new TwoSquareComponent();
		add(square);
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	class TwoSquareComponent extends JComponent {
		
		TwoSquareComponent() {
			
		}
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.PINK);
			g.fillRect(0, 0, 100, 100);
			g.setColor(new Color(93, 63, 211));
			g.fillRect(0, 100, 100, 100);
		}
	}
}
/* 504 Chapter 10 Graphical User Interfaces
P10.17 Write a program to plot the following face. Provide a class FaceViewer and a class FaceComponent. */
class FaceViewer extends JFrame {
	
	FaceComponent face;
	
	FaceViewer() {
		face = new FaceComponent();
		add(face);
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	
	class FaceComponent extends JComponent {
		
		FaceComponent() {
			
		}
		
		public void paintComponent(Graphics g) {
			g.drawOval(0, 0, 100, 100);
			g.drawOval(25, 25, 10, 10);
			g.drawOval(65, 25, 10, 10);
			g.drawLine(25, 60, 75, 60);
		}
	}
}
/*
P10.18 Draw a “bull’s eye”—a set of concentric rings in alternating black and white colors.
Hint: Fill a black circle, then fill a smaller white circle on top, and so on. Your program should be composed of classes BullsEyeComponent and BullsEyeViewer.*/
class BullsEyeViewer extends JFrame {

	BullsEyeViewer() {
		add(new JComponent() {
			public void paintComponent(Graphics g) {
				g.fillOval(0, 0, 100, 100);
				g.setColor(Color.WHITE);
				g.fillOval(10, 10, 80, 80);
				g.setColor(Color.BLACK);
				g.fillOval(20, 20, 60, 60);
				g.setColor(Color.WHITE);
				g.fillOval(30, 30, 40, 40);
				g.setColor(Color.BLACK);
				g.fillOval(40, 40, 20, 20);
			}
		});
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
/*
P10.19 Write a program that draws a picture of a house.
It could be as simple as the accompanying figure, or if you like,
make it more elaborate (3-D, skyscraper, marble columns in the entryway, whatever).
P10.20 Extend Exercise P10.19 by supplying a drawHouse method in which you can specify
the position and size. Then populate your frame with a few houses of different sizes.
P10.21 Extend Exercise P10.20 so that you can make the houses appear in different colors.
The color should be passed as an argument to the drawHouse method. Populate your
frame with houses of different colors.*/
class HouseViewer extends JFrame {
	
	int x, y, width, height;
	int[] rgb;
	
	HouseViewer(int x, int y, int width, int height, int[] rgb) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rgb = rgb;
		
		add(new JComponent() {
			
			public void paintComponent(Graphics g) {
				g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
				g.drawRect(x, y, width, height);
				g.drawLine(x, y, x+(width/2), y-(height/2));
				g.drawLine(x+(width/2), y-(height/2), x+width, y);
			}
			
		});
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}

/*
P10.23 Write a program that draws a 3D view of a cylinder.*/
class CylinderViewer extends JFrame {

	CylinderViewer() {

		add(new JComponent() {
			
			public void paintComponent(Graphics g) {
				g.drawOval(100, 100, 100, 50);
				g.drawLine(100, 128, 100, 230);
				g.drawOval(100, 205, 100, 50);
				g.drawLine(200, 128, 200, 230);
			}
			
		});
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
/*
P10.24 Write a program to plot the string “HELLO”, using only lines and circles. Do
not call drawString, and do not use System.out. Make classes LetterH, LetterE, LetterL,
and LetterO.*/
class HelloViewer extends JFrame {

	HelloViewer() {

		add(new JComponent() {
			
			public void paintComponent(Graphics g) {
				g.drawLine(100, 100, 100, 300);
				g.drawLine(100, 200, 200, 200);
				g.drawLine(200, 100, 200, 300);
			}
			
		});
		
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
/*
P10.25 Modify the drawItalianFlag method in How To 10.1 to draw any flag with three horizontal colored stripes.
Write a program that displays the German and Hungarian
flags.*/
class Flag extends JFrame {
	
	Color a, b, c;
	
	Flag(Color a, Color b, Color c) {
		add(new JComponent() {
			public void paintComponent(Graphics g) {
				g.setColor(a);
				g.fillRect(0, 0, 100, 25);
				g.setColor(b);
				g.fillRect(0, 25, 100, 25);
				g.setColor(c);
				g.fillRect(0, 50, 100, 25);
			}
		});
	
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	Flag(String nation) {
		if(nation.equals("germany")) {
			this.a = Color.black;
			this.b = Color.red;
			this.c = Color.yellow;
		} else if(nation.equals("hungary")) {
			this.a = Color.red;
			this.b = Color.white;
			this.c = Color.green;
		}
		add(new JComponent() {
			public void paintComponent(Graphics g) {
				g.setColor(a);
				g.fillRect(0, 0, 100, 25);
				g.setColor(b);
				g.fillRect(0, 25, 100, 25);
				g.setColor(c);
				g.fillRect(0, 50, 100, 25);
			}
		});
	
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

/*
Programming Exercises 505
P10.26 Write a program that displays the Olympic rings. Color the rings in the Olympic
colors. Provide a method drawRing that draws a ring of a given position and color. */
class OlympicRings extends JFrame 
{

	OlympicRings()
	{
		add(new JComponent()
		{
			public void paintComponent(Graphics g)
			{
				g.setColor(Color.blue);
				g.drawOval(0, 0, 100, 100);
				g.setColor(Color.yellow);
				g.drawOval(50, 50, 100, 100);
				g.setColor(Color.black);
				g.drawOval(100, 0, 100, 100);
				g.setColor(Color.green);
				g.drawOval(150, 50, 100, 100);
				g.setColor(Color.red);
				g.drawOval(200, 0, 100, 100);
			}
		});
	
		this.setSize(600, 500);
		this.setVisible(true);
	
		drawRing(100, 200, Color.CYAN);
	}
	
	void drawRing(int x, int y, Color c)
	{
		add(new JComponent() {
			public void paintComponent(Graphics g) 
			{
				g.setColor(c);
				g.drawOval(x, y, 100, 100);
			}
		});
	}
	
}

/*
P10.27 Write a program that prompts the user to enter an integer in a text field. When a
Draw button is clicked, draw as many rectangles at random positions in a component as the user requested.*/
class RandRect extends JFrame
{
	Random m;
	
	JPanel p;
	JTextField f;
	JButton btn;
	Paint draw;
	int rectCount;
	
	RandRect()
	{
		
		m = new Random();
		
		p = new JPanel();
		f = new JTextField(10);
		btn = new JButton("Add rectangles");
		draw = new Paint();
		rectCount = 0;
		
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				rectCount = Integer.parseInt(f.getText());
				System.out.println(rectCount);
				draw.repaint();
			}
		});
		
		draw.setPreferredSize(new Dimension(500, 500));
		
		p.add(f);
		p.add(btn);
		p.add(draw);
		add(p);
		
		setSize(600, 600);
		setVisible(true);
	}
	
	class Paint extends JComponent
	{
		
		public void paintComponent(Graphics g)
		{
			for(int i = rectCount; i > 0; i--) {
				g.drawRect(m.nextInt(400), m.nextInt(400), 100, 100);
			}
		}
	}
}

/*P10.28 Write a program that asks the user to enter an integer n into a text field. When a
Draw button is clicked, draw an n-by-n grid in a component.*/
class Grid extends JFrame
{
	
	int n = 1;
	JPanel p;
	JTextField field;
	JButton btn;
	DrawGrid grd;
	
	Grid()
	{
		field = new JTextField(10);
		btn = new JButton("Paint");
		grd = new DrawGrid();
		p = new JPanel();
		
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				n = Integer.parseInt(field.getText());
				grd.repaint();
			}
		});
		
		grd.setPreferredSize(new Dimension(155, 155));
		
		p.add(field);
		p.add(btn);
		p.add(grd);
		add(p);
		
		setSize(250, 250);
		setVisible(true);
	}
	
	class DrawGrid extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			
			int sqrSize = 150/n;
			
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					g.drawRect(i*sqrSize, j*sqrSize, sqrSize, sqrSize);
				}
			}
		}
	}
}

/*
P10.29 Write a program that has a Draw button and a component in which a random mixture of rectangles, ellipses, and lines, with random positions, is displayed each time
the Draw button is clicked.*/
class DrawBtn extends JFrame
{
	
	Random r;
	
	JPanel p;
	JButton btn;
	DrawShapes draw;
	
	DrawBtn()
	{
		r = new Random();
		
		p = new JPanel();
		btn = new JButton("Draw");
		draw = new DrawShapes();
		
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				draw.repaint();
			}
		});
		
		draw.setPreferredSize(new Dimension(200, 200));
	
		p.add(btn);
		p.add(draw);
		this.add(p);
	
		this.setSize(250, 250);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	class DrawShapes extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			for(int i = 0; i < r.nextInt(10); i++)
			{
				int shape = r.nextInt(3);
				
				if(shape == 1)
				{
					g.drawRect(r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100));
				} else if(shape == 2)
				{
					g.drawOval(r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100));
				} else if(shape == 3)
				{
					g.drawLine(r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100));
				}
			}
		}
	}
}

/*
P10.30 Make a bar chart to plot the following data set. Label each bar. Provide a class
BarChartViewer and a class BarChartComponent.
Bridge Name Longest Span (ft)
Golden Gate 4,200
Brooklyn 1,595
Delaware Memorial 2,150
Mackinac 3,800*/
class BarChartViewer extends JFrame
{
	
	BarChartComponent bar;
	
	BarChartViewer()
	{
		
		bar = new BarChartComponent();
		
		this.add(bar);
		this.setSize(1000, 550);
		this.setVisible(true);
	}
	
	class BarChartComponent extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			
			int startX, startY;
			startX = 50;
			startY = 450;
			
			//y axis
			g.drawLine(startX, startY, startX, 50);
			//x axis
			g.drawLine(startX, startY, 800, startY);
			
			//lines
			int yLines = 25;
			int dis = (startY-startX)/yLines;
			int chartMax = 5000;
			
			for(int i = 0; i < yLines; i++)
			{
				int lineY = startY-(dis*(i+1));
				g.drawLine(startX-5, lineY, startX+5, lineY);
				int lineValue = (i+1) * (chartMax / yLines);
				g.drawString(String.valueOf(lineValue), startX-35, lineY+5);
			}
			
			//bars
			int[] data = {4200, 1595, 2150, 3800};
			String[] name = {"Golden Gate", "Brooklyn", "Delaware Memorial", "Mackinac"};
			for(int i = 0; i < 4; i++) {
				int barLength = (int) (startY * ((double) data[i]/chartMax));
				g.fillRect(startX+((i+1)*150), startY, 10, -barLength);
				g.drawString(name[i], startX+((i+1)*150), startY+10);
			}
		}
	}
}


/*P10.31 Write a program that draws a clock face with a time that the user enters in two text
fields (one for the hours, one for the minutes).
Hint: You need to determine the angles of the hour hand and the minute hand. The
angle of the minute hand is easy; the minute hand travels 360 degrees in 60 minutes.
The angle of the hour hand is harder; it travels 360 degrees in 12 × 60 minutes.*/
class ClockFace extends JFrame
{
	JPanel panel;
	JTextField hourTxt;
	JTextField minuteTxt;
	int hour, minute;
	DrawClockFace clock;
	
	ClockFace()
	{
		panel = new JPanel();
		hourTxt = new JTextField(10);
		minuteTxt = new JTextField(10);
		clock = new DrawClockFace();
		
		hourTxt.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e)
			{
				hour = Integer.parseInt(hourTxt.getText());
				clock.repaint();
			}

		});
		
		minuteTxt.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e)
			{
				minute = Integer.parseInt(minuteTxt.getText());
				clock.repaint();
			}
		});
		
		clock.setPreferredSize(new Dimension(101, 101));
		panel.add(hourTxt);
		panel.add(minuteTxt);
		panel.add(clock);
		this.add(panel);
		this.setSize(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class DrawClockFace extends JComponent
	{
		
		public void paintComponent(Graphics g)
		{
			double minuteRadians = ((double) minute/60) * 6.28 + (3.14 * 1.5);
			double hourRadians = ((double) hour/12) * 6.28 + (3.14 * 1.5);
			
			int minuteX = (int) (Math.cos(minuteRadians) * 50);
			int minuteY = (int) ((Math.sin(minuteRadians) * 50));
			
			int hourX = (int) (Math.cos(hourRadians) * 50);
			int hourY = (int) (Math.sin(hourRadians) * 50);
			
			System.out.println(minuteX + " " + minuteY);
			
			g.drawOval(0, 0, 100, 100);
			g.drawOval(48, 48, 4, 4);
			g.setColor(Color.orange);
			g.drawLine(50, 50, 50+minuteX, 50+minuteY);
			g.setColor(Color.red);
			g.drawLine(50, 50, 50+hourX, 50+hourY);
		}
	}
}
/*P10.32 Write a program that fills the window with a large ellipse, with a black outline and
filled with your favorite color. The ellipse should touch the window boundaries,
even if the window is resized.*/
class FilledEllipse extends JFrame
{
	
	Draw draw;
	int x, y;
	
	FilledEllipse()
	{
		x = 500;
		y = 500;
		
		draw = new Draw();
		
		this.add(draw);
		this.setSize(x+17, y+40);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class Draw extends JComponent
	{
		public void paintComponent(Graphics g)
		{
			g.drawOval(0, 0, x, y);
			g.drawOval(1, 1, x-2, y-2);
			g.drawOval(2, 2, x-3, y-3);
			g.setColor(Color.cyan);
			g.fillOval(1, 1, x-2, y-2);
		}
	}
}

/*Business P10.33 Implement a graphical application that simulates a cash register. Provide a text field
for the item price and two buttons for adding the item to the sale, one for taxable
items and one for nontaxable items. In a text area, display the register tape that lists
all items (labeling the taxable items with a *), followed by the amount due. Provide
another button for starting a new sale.*/
class RegisterView extends JFrame
{
	
	JPanel panel;
	JTextField itemPrice;
	JButton saleTaxed;
	JButton saleUntaxed;
	JTextArea receipt;
	JButton newSale;
	JLabel due;
	int amountDue;
	
	RegisterView()
	{
		panel = new JPanel();
		
		itemPrice = new JTextField(10);
		saleTaxed = new JButton("Taxed");
		saleUntaxed = new JButton("Untaxed");
		receipt = new JTextArea(10, 12);
		newSale = new JButton("New sale");
		due = new JLabel("Amount due: $0");
		
		saleTaxed.addActionListener(btn(true));
		saleUntaxed.addActionListener(btn(false));
		newSale.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					receipt.setText("");
					amountDue = 0;
					due.setText("Amount due: $0");
				}
			});
		
		panel.add(itemPrice);
		panel.add(saleTaxed);
		panel.add(saleUntaxed);
		panel.add(receipt);
		panel.add(due);
		panel.add(newSale);
		
		this.add(panel);
		this.setSize(200, 350);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	ActionListener btn(boolean taxed) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				receipt.append("\n" + itemPrice.getText() + (taxed?"*":""));
				amountDue += Integer.parseInt(itemPrice.getText());
				due.setText("Amount due: $" + String.valueOf(amountDue));
			}
		};
	}
	
	class Items
	{
		class Banana
		{
			String name = "banana";
			double price = 1.5;
			boolean taxable = true;
		}
		
		class Orange
		{
			String name = "orange";
			double price = 2.0;
			boolean taxable = false;
		}
	}
}

/*Business 
P10.34 Write a graphical application to implement a currency converter between euros and
U.S. dollars, and vice versa. Provide two text fields for the euro and dollar amounts.
Between them, place two buttons labeled > and < for updating the field on the right
or left. For this exercise, use a conversion rate of 1 euro = 1.42 U.S. dollars.*/
class Converter extends JFrame
{
	JPanel panel;
	JTextField euro, dollar;
	JButton leftBtn, rightBtn;
	
	Converter()
	{
		panel = new JPanel();
		euro = new JTextField(10);
		dollar = new JTextField(10);
		leftBtn = new JButton("< to dollar");
		rightBtn = new JButton("to euro >");
		
		leftBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double n = Double.parseDouble(dollar.getText()) / 1.42;
				euro.setText(String.valueOf(n));
			}
		});
		
		rightBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				double n = Double.parseDouble(euro.getText()) * 1.42;
				dollar.setText(String.valueOf(n));
			}
		});
		
		panel.add(euro);
		panel.add(leftBtn);
		panel.add(rightBtn);
		panel.add(dollar);
		
		this.add(panel);
		this.setSize(600, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

/*Business
P10.35 Write a graphical application that produces a restaurant bill.
Provide buttons for ten popular dishes or drink items. (You
decide on the items and their prices.) Provide text fields for
entering less popular items and prices. In a text area, show the
bill, including tax and a suggested tip. */

class Bill extends JFrame
{
	JPanel panel;
	JTextArea bill;
	JLabel totalLabel;
	JTextField customName, customPrice;
	JButton customBtn;
	int total;
	
	Bill()
	{
		panel = new JPanel();
		bill = new JTextArea(10, 30);
		totalLabel = new JLabel();
		customName = new JTextField(10);
		customPrice = new JTextField(10);
		customBtn = new JButton("Add");
		customBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				bill.append("Name: " + customName.getText() + ", Price: " + customPrice.getText() + "\n");
				total += Integer.valueOf(customPrice.getText());
				totalLabel.setText("Total: " + total + "\n"
						+ ", Tax: " + total * 0.06 + "\n"
						+ ", Tip: " + total * 0.15);
			}
		});
		
		String[] names = {"Banana", "Pear", "Chicken"};
		int[] prices = {1, 2, 5};
		for(int i = 0; i < names.length; i++) 
		{
			panel.add(item(names[i], prices[i]));
		}
		panel.add(bill);
		panel.add(customName);
		panel.add(customPrice);
		panel.add(customBtn);
		panel.add(totalLabel);
		this.add(panel);
		this.setSize(400, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	JButton item(String name, int price)
	{
		JButton btn = new JButton(name);
		btn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						bill.append("Name: " + name + ", Price: " + price + "\n");
						total += price;
						totalLabel.setText("Total: " + total + "\n"
								+ ", Tax: " + total * 0.06 + "\n"
								+ ", Tip: " + total * 0.15);
					}
				});
		return btn;
	}
}