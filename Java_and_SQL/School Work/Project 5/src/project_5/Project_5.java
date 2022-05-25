package project_5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Project_5 {
	
	enum Priority {
	     URGENT,
	     HIGH,
	     NORMAL, 
	     LOW}

	enum Status{
	     NOT_STARTED,
	     IN_PROGRESS, 
	     WAITING,
	     DEFERRED
	}

	
	class Task implements Comparable<Task> {
		 int taskId;
		 String subject;
		 Priority priority; 
		 Status status; 
		 LocalDateTime startDate;
		 LocalDateTime dueDate;
		
		 public Task(int taskId, String subject, Priority priority, Status status, LocalDateTime dueDate) {
			this.taskId = taskId;
			this.subject = subject;
			this.priority = priority;
			this.status = status;
			this.dueDate = dueDate;
			this.startDate = LocalDateTime.now();
		 }
		 
		 int getTaskId() {
			 return this.taskId;
		 }
		
		 @Override
		public String toString() {
			return "Task [taskId=" + taskId + ", subject=" + subject + ", priority=" + priority + ", status=" + status
					+ ", startDate=" + startDate + ", dueDate=" + dueDate + "]";
		}

		 

		private Priority getPriority() {
			return priority;
		}

		@Override
		 public int compareTo(Task o) {
			return this.getPriority().compareTo(o.getPriority());
	} 
	}
	
	void addTask(PriorityQueue<Task> q, int id, String s, Priority p, LocalDateTime dueDate) {
		q.add(new Task(id, s, p, Status.NOT_STARTED, dueDate));
		id++;
	}

	public static void main(String[] args) {
		Project_5 p = new Project_5();
		p.driver();
	}
	
	void driver() {
		PriorityQueue<Task> q = new PriorityQueue<Task>();
		int id = 1;
		String input;
		String option = "";
		String[] details;
		System.out.println("Options: \n"
				+ "add (subject, priority_from_1-4, due_date(year, month, day))]\n"
				+ "view next OR view (id)\n"
				+ "list\n"
				+ "remove (id)\n"
				+ "exit\n");
		while(true) {
			input = new Scanner(System.in).nextLine();
			if(input.contains(" ")) {
				option = input.substring(0, input.indexOf(" "));
				details = input.substring(option.length()+1).split(", ");
			} else {
				option = input;
				details = new String[]{input};
			}
			if(option.equals("add")) {
				Priority p = null;
				LocalDateTime d = LocalDateTime.of(Integer.parseInt(details[2]), Integer.parseInt(details[3]), Integer.parseInt(details[4]), 1, 1);
				int n = Integer.parseInt(details[1]);
				if(n == 1) {
					p = Priority.LOW;
				} else if(n == 2) {
					p = Priority.NORMAL;
				} else if(n == 3) {
					p = Priority.HIGH;
				} else if(n == 4) {
					p = Priority.URGENT;
				}
				q.add(new Task(id, details[0], p, Status.NOT_STARTED, d));
				id++;
			} else if(option.equals("view")) {
				if(details[0].equals("next")) {
					System.out.println(q.peek());
				} else {
					System.out.println(q.toArray()[Integer.parseInt(details[0])-1]);
				}
			} else if(option.equals("list")) {
				Iterator<Task>  it = q.iterator();
				if(it.hasNext()) {
					System.out.print(it.next());
				}
				while(it.hasNext()) {
					System.out.print(", \n" + it.next());
				}
				System.out.println();
			} else if(option.equals("remove")) {
				Iterator<Task>  it = q.iterator();
				Task t;
				while(it.hasNext()) {
					t = it.next();
					if(t.getTaskId() == Integer.parseInt(details[0])) {
						q.remove(t);
						break;
					}
				}
			} else if(option.equals("exit")) {
				System.out.println("Goodbye");
				break;
			}
		}
	}
}