package jpa.runner;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner
{
	CourseService cService;
	StudentService sService;

	SMSRunner()
	{
		cService = new CourseService();
		sService = new StudentService();
		Scanner scan = new Scanner(System.in);
			System.out.println("Enter email: ");
			String email = scan.nextLine();
			System.out.println("Enter password: ");
			String password = scan.nextLine();
			if(sService.validateStudent(email, password))
			{
				Student s = sService.getStudentByEmail(email);
				System.out.println("Student is currently enrolled in: ");
				for(Course c : s.getsCourses()) System.out.println(c.getId() + ". " + c.getName() + ", taught by " + c.getInstructor() + ".");
				System.out.println("Register to class? (y/n)");
				if(scan.nextLine().equals("y"))
				{
					System.out.println("All courses available: ");
					List<Course> courses = cService.getAllCourses();
					for(Course c : courses) System.out.println(c.getId() + ". " + c.getName() + ", taught by " + c.getInstructor() + ".");
					System.out.println("Which course would you like to add? (id)");
					int id = Integer.parseInt(scan.nextLine());
					boolean register = true;
					for(Course c : s.getsCourses()) if(c.getId() == id)
					{
						System.out.println("You're already registered for that course!");
						register = false;
						break;
					}
					if(register) sService.registerStudentToCourse(s.getEmail(), id);
					s = sService.getStudentByEmail(email);
					System.out.println("Courses: ");
					for(Course c : s.getsCourses()) System.out.println(c.getId() + ". " + c.getName() + ", taught by " + c.getInstructor() + ".");
				} else
				{
					System.out.println("Goodbye.");
				}
			} else System.out.println("Invalid credentials.");
		scan.close();
	}
	
	public static void main(String[] args){ new SMSRunner(); }
}