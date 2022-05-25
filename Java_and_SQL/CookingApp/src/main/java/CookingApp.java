import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class CookingApp {

	//making CL version before UI.
	//UI ui;
	CookBook book;
	HashSet<String> ownedCookwear;
	final String dir = System.getProperty("user.dir");
	Connection con;
	Scanner scan;
	boolean running;
	
	CookingApp()
	{
		try { con = DriverManager.getConnection("jdbc:sqlite:../../../dataCookingApp.db", "", ""); } catch (SQLException e1) { e1.printStackTrace(); }
		//ui = new UI();
		this.book = new CookBook();
		this.ownedCookwear = new HashSet<String>();
		try { loadData(); } catch (SQLException e) { e.printStackTrace(); }
		scan = new Scanner(System.in);
		running = true;
		
		System.out.println("Either: view all, view (recipe name), start (recipe name), add (recipe name), edit (recipe name), delete (recipe name) add cookwear (cookwear name).\nType help at any time to repeat commands.");
		while(running)
		{
			String option = scan.nextLine();
			if(option.equals("help")) System.out.println("Either: view all, view (recipe name), start (recipe name), add (recipe name), edit (recipe name), delete (recipe name) add cookwear (cookwear name).");
			else if(option.equals("view all")) for(String key : book.getRecipeNames()) System.out.println(key);
			else if(option.length() > 5 && option.substring(0, 5).equals("view "))
			{
				String recipeToView = option.substring(5);
				Recipe recipe = book.getRecipe(recipeToView);
				if(recipe != null)
				{
					System.out.println("--------------\n" + recipe.name + "\n" + "--------------");
					for(Cookwear cw : recipe.neededCookwear) System.out.println(cw.name + " - " + cw.description);
					System.out.println("--------------");
					for(Ingredient in : recipe.neededIngredients) System.out.println(in.name + " - " + in.amount);
					System.out.println("--------------");
				} else System.out.println("Recipe DNE.");
				
			} else if(option.length() > 6 && option.substring(0, 6).equals("start "))
			{
				String recipeToStart = option.substring(6);
				Recipe recipe = book.getRecipe(recipeToStart);
				if(recipe != null) startRecipe(recipe);
				else System.out.println("Recipe DNE.");
			} else if(option.length() > 5 && option.substring(0, 5).equals("edit "))
			{
				String recipeToEdit = option.substring(5);
				Recipe recipe = book.getRecipe(recipeToEdit);
				System.out.println("Enter new values. Otherwise press enter to skip.");
				System.out.println("Current name: " + recipe.name + ". New name: ");
				String newName = scan.nextLine();
				if(newName.strip() != "") recipe.name = newName;
				Direction curr = null;
				for(Iterator<Direction> it = recipe.directions.iterator(); it.hasNext(); curr = it.next())
				{
					System.out.println("Current description: " + curr.description + ". New Description: ");
					String newDesc = scan.nextLine();
					if(newDesc.strip() != "") curr.description = newDesc;
				}
			} else if(option.equals("add "))
			{
				
			} else
			{
				System.out.println("Unrecognized command. Please try again.");
				continue;
			}
		}
		scan.close();
	}
	

	void startRecipe(Recipe recipe) {
		scan = new Scanner(System.in);
		System.out.println("--------------");
		System.out.println("Needed ingredients: ");
		for(Ingredient ing : recipe.neededIngredients) System.out.println(ing.name + " - " + ing.amount);
		System.out.println("--------------");
		System.out.println("Needed cookwear: ");
		for(Cookwear c : recipe.neededCookwear)
		{
			if(ownedCookwear.contains(c.name)) System.out.println(c.name);
			else System.out.println("You don't own the required " + c.name + ". ");
		}
		System.out.println("--------------");
		while(!recipe.complete())
		{
			System.out.println(recipe.currDir.description);
			//Add a timer message.
			if(recipe.currDir.timer != null) recipe.currDir.timer.start();
			if(scan.nextLine().equals("next")) continue;
			else break;
		}
	}
	
	void addOrEditRecipe()
	{
		System.out.println("This recipe already exists. Either: edit or start over.");
	}
	
	void loadData() throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:sqlite:../../../dataCookingApp.db", "", "");
		Statement stm = con.createStatement();
		String getUserData = "SELECT * FROM Recipe WHERE username = 'alijahjchandler@gmail.com'";
		ResultSet recipes = stm.executeQuery(getUserData);
		while(recipes.next())
		{
			//Shouldn't concatenate strings like this in none-personal apps. Or so my potentially outdated textbook says.
			String recipeName = recipes.getString("Name");
			Cookwear[] ckwr;
			Ingredient[] ing;
			Direction[] dir;
			//I could make the below a loop.
			//There won't be many if any more CookBook params so it's unnecessary but would be more proper.
			//I could use an object factory, because of the varying object constructors.
			int cookwearN = stm.executeQuery("SELECT count(*) from Cookwear WHERE Recipe = '" + recipeName + "'").getInt(1);
			ckwr = new Cookwear[cookwearN];
			ResultSet cookwear = stm.executeQuery("SELECT * FROM Cookwear WHERE Recipe = '" + recipeName + "'");
			int i = 0;
			while(cookwear.next()) { ckwr[i] = new Cookwear(cookwear.getString("Name"), cookwear.getString("Description")); i++; }
			int ingredientN = stm.executeQuery("SELECT count(*) from Ingredient WHERE Recipe = '" + recipeName + "'").getInt("count(*)");
			ing = new Ingredient[ingredientN];
			ResultSet ingredient = stm.executeQuery("SELECT * FROM Ingredient WHERE Recipe = '" + recipeName + "'");
			i = 0;
			while(ingredient.next()) { ing[i] = new Ingredient(ingredient.getString("Name"), ingredient.getInt("Amount")); i++; }

			int directionN = stm.executeQuery("SELECT count(*) from Direction WHERE Recipe = '" + recipeName + "'").getInt("count(*)");
			dir = new Direction[directionN];
			ResultSet direction = stm.executeQuery("SELECT * FROM Direction WHERE Recipe = '" + recipeName + "'");
			i = 0;
			while(direction.next()) { dir[i] = new Direction(direction.getInt("Number"), direction.getString("Description"), direction.getInt("Time"), direction.getString("TimerDesc")); i++; }
			
			Recipe currRecipe = new Recipe("Spaghetti", ing, ckwr, dir);
			book.add(currRecipe);
			
			ResultSet ownedCookwear = stm.executeQuery("SELECT * FROM OwnedCookwear WHERE Username = '" + "alijahjchandler@gmail.com" + "'");
			while(ownedCookwear.next()) this.ownedCookwear.add(ownedCookwear.getString("Cookwear"));
		}
	}


	class Recipe
	{
		String name;
		Ingredient[] neededIngredients;
		Cookwear[] neededCookwear;
		LinkedList<Direction> directions;
		Direction currDir;
		
		Recipe(String name, Ingredient[] ingredients, Cookwear[] cookwear, Direction[] dir)
		{
			this.name = name;
			this.neededIngredients = ingredients;
			this.neededCookwear = cookwear;
			this.directions = new LinkedList<Direction>();
			//There's a more self-organizing way of doing the below, I'm sure.
			Arrays.sort(dir);
			for(Direction d : dir) directions.addFirst(d);
		}
		
		boolean complete()
		{
			currDir = directions.removeLast();
			if(currDir.complete) return true;
			currDir.setComplete(true);
			directions.push(currDir);
			return false;
		}
		
	}
	
	class Direction implements Comparable<Direction>
	{
		int N;
		String description;
		Timer timer;
		boolean complete;
		
		Direction(int n, String des, int time, String timerDesc)
		{
			this.N = n;
			if(timerDesc != null) this.description = des + " (Timer)";
			else this.description = des;
			if(timerDesc != null) this.timer = new Timer(time, timerDesc);
			else this.timer = null;
			complete = false;
		}
		
		Direction(String des)
		{
			this.description = des;
		}
		
		boolean getComplete() { return this.complete; }
		void setComplete(boolean complete) { this.complete = complete; }

		public int compareTo(Direction d) {
			return N <  d.N ? -1 : 1;
		}
	}
	
	class CookBook
	{
		Map<String, Recipe> recipes;
		//Using more sophisticated data structures is unneeded because of low n but I believe it to be most proper.
		
		//amount of ingredient always in grams, amount of time always in minutes.
		//A Conversion table of grams to amount of noodles may be more convoluted than including "unit" as a param.
		CookBook()
		{
			recipes = new TreeMap<String, Recipe>();
		}
		
		Map<String, Recipe> getRecipes() { return this.recipes; }
		Recipe getRecipe(String name) { return recipes.get(name); }
		//having recipe name as key and a value of the Recipe object doesn't make sense. May make Recipe class not include name;
		//I'd have to follow the logic from DB to front-end to make sure doing this makes sense.
		void add(Recipe recipe) { recipes.put(recipe.name, recipe); }
		Set<String> getRecipeNames() { return recipes.keySet(); }
	}
	
	class Ingredient
	{
		String name;
		int amount;
		
		Ingredient(String name, int amount)
		{
			this.name = name;
			this.amount = amount;
		}
	}
	
	class Cookwear
	{
		String name;
		String description;
		
		Cookwear(String name, String description)
		{
			this.name = name;
			this.description = description;
		}
	}
	
	class Timer
	{
		int time;
		String msg;
		
		Timer(int time, String msg)
		{
			this.time = 5000;
			this.msg = msg;
		}
		
		void start()
		{
			Thread t = new Thread(new Runnable() 
			{

				public void run()
				{ 
					try { Thread.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); } System.out.println("Timer complete - " + msg); 
				}	
			});
			t.start();
		}

	}
	/*
	class UI extends JFrame
	{
		UI()
		{
			Direction[] dirs = new Direction[] {new Direction("Start recipe"), new Direction("Continue recipe")};
			JScrollPane dirComp = directionBox(dirs, new int[] {100, 100});
			this.add(dirComp);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(1024, 768);
			this.setVisible(true);
		}
		
		JScrollPane directionBox(Direction[] dirs, int[] size)
		{
			JPanel container = new JPanel();
			container.setPreferredSize(new Dimension(size[0], size[1]));
			//container.setAutoscrolls(true);
			for(int i = 0; i < dirs.length; i++) container.add(new DirectionComp(i + 1, dirs[i], new int[] {300, 100}));
			JScrollPane pane = new JScrollPane(container);
			pane.setPreferredSize(new Dimension(size[0], size[1]));
			//pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			return pane;
		}
		
		@SuppressWarnings("serial")
		class DirectionComp extends JComponent
		{
			Direction dir;
			int dirN, x, y;
			
			DirectionComp(int dirN, Direction dir, int[] size)
			{
				this.dirN = dirN;
				this.dir = dir;
				this.x = size[0];
				this.y = size[1];
				this.setPreferredSize(new Dimension(x, y));
			}
			
			protected void paintComponent(Graphics g)
			{
				g.drawRect(0, 0, x - 1, y - 1);
				String line = dir.description;
				int lineN = 0;
				g.drawString("Step " + dirN + " - " + line, 10, 20);
				
				while(line.length() > 0)
				{
					line++;
					int startIndex = 
					g.drawString(line.substring(0, Math.min(10, line.length())), 10, 20 * lineN);
					line = line.substring(Math.min(10, line.length()), line.length());
				}
			}
		}
	}*/
	
	
	public static void main(String[] args) {
		new CookingApp();
	}
}
