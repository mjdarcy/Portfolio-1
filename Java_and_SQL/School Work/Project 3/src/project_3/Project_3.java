package project_3;

public class Project_3 {
	
	abstract class Mammal{
		void nursesYoung() {
			System.out.println("I am a "+this.getClass().getSimpleName()+". I am nursing.");
		}
	}
	
	interface RuminantTester{
		void testIfRuminant();
		void testHasMultipleStomachs();
	}
	
	abstract class GrazingMammal extends Mammal implements RuminantTester{
		void grazes() {
			System.out.println("I am a " + this.getClass().getSimpleName() + ". I am grazing.");
		}
	     @Override
	     public void testHasMultipleStomachs()  {
	         String className = this.getClass().getName(); 
	         if (this instanceof Ruminant) 
	            System.out.println("I am a " + className + ". I have multiple stomachs."); 
	        else 
	           System.out.println("I am a " + className + ". I do not have multiple stomachs."); 
	     } 

	     @Override
	     public void testIfRuminant()  {
	        String className = this.getClass().getName(); 
	        if (this instanceof Ruminant ) 
	           System.out.println("I am a " + className + ". I am a Ruminant."); 
	        else 
	           System.out.println("I am a " + className + ". I am not a Ruminant."); 
	      }
	}
	
	abstract class Ruminant extends GrazingMammal{
		void chewsCud() {
			System.out.println("I am a " + this.getClass().getSimpleName() + ". I am chewing chud.");
		}
	}
	
	class Cow extends Ruminant{
		
	}
	
	class Goat extends Ruminant{
		
	}
	
	class Horse extends GrazingMammal{
		
	}
	
	

	public static void main(String[] args) {
		Project_3 p = new Project_3();
		p.run();
	}
	
	void run() {
        Cow cow = new Cow();
        cow.nursesYoung(); 
        cow.grazes(); 
        cow.chewsCud();
        cow.testIfRuminant();
        cow.testHasMultipleStomachs();
        System.out.println("\n"); 
        Goat goat = new Goat();
        goat.nursesYoung(); 
        goat.grazes(); 
        goat.chewsCud();
        goat.testIfRuminant();
        goat.testHasMultipleStomachs();
        System.out.println("\n"); 
        Horse horse  = new Horse();
        horse.nursesYoung(); 
        horse.grazes(); 
        horse.testIfRuminant();
        horse.testHasMultipleStomachs(); 
	}
}