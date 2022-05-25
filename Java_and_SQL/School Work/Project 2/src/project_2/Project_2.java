package project_2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Project_2{
	
	static ArrayList<Mailbox> arr = new ArrayList<Mailbox>();
	
	class MailBoxList{
		
		Mailbox getUserMailbox(String user) {
			for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i).getUser() != null && arr.get(i).getUser().equals(user)){
					return arr.get(i);
				}
			}
			arr.add(new Mailbox(user));
			return arr.get(arr.size()-1);
		}
	}
	
	class Mailbox{
		
		String user;
		ArrayList<Message> messages;
		ArrayList<Message> sentMessages;
		
		public Mailbox(String user) {
			this.user = new String(user);
			messages = new ArrayList<Message>();
			sentMessages = new ArrayList<Message>();
		}

		ArrayList<Message> getSentMessages(){
			return sentMessages;
		}
		
		public String getUser() {
			return user;
		}

		ArrayList<Message> getMessages(){
			return messages;
		}
		
		void addSentMessage(Message m){
			sentMessages.add(m);
		}

		void addMessage(Message m){
			messages.add(m);
		}
		
		Message getMessage(int i){
			return messages.get(i);
		}
		
		Message getSentMessage(int i){
			return sentMessages.get(i);
		}
		
		void removeSentMessage(int i){
			sentMessages.remove(i);
		}
		
		void removeMessage(int i){
			messages.remove(i);
		}
		
		public void send(Message m, String r) {
			MailBoxList mailboxes = new MailBoxList();
			Mailbox recM = mailboxes.getUserMailbox(r);
			recM.addMessage(m);
			this.addSentMessage(m);
		}
	}
	
	class Message{
		
		String recipient;
		String sender;
		String subject;
		String messageText;
		Date date;
		
		Message(){
			
		}
		
		Message(String recipient, String sender, String subject) {
			super();
			this.recipient = recipient;
			this.sender = sender;
			this.subject = subject;
			this.messageText = "";
			this.date = new Date();
		}

		private void append(String line){
			if(messageText.length() > 0) {
				messageText = messageText.concat("\n" + line);
			} else {
				messageText = messageText.concat(line);
			}
		}
		
		private String getMessageHeader(){
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MM dd");
			return sender + "\nSubject: " + subject + "\nDate: " + sdf.format(date) + "\nMessage Text: " + messageText.split("\\n")[0] + "\n";
		}

		@Override
		public String toString(){
			return "Message [recipient=" + recipient + ", sender=" + sender + ", subject=" + subject + ", messageText="
					+ messageText + ", date=" + date + "]\n";
		}
		
		private void print(){
			System.out.println(this.toString());
		}
		
		private void printHeader(){
			System.out.println(this.getMessageHeader());
		}

		private String getSender() {
			return sender;
		}

		private String getRecipient() {
			return recipient;
		}

		private void send(Mailbox sender, Mailbox receiver) {
			/*I'm unsure whether this method or a send method within Mailbox is better.
			send within Mailbox seems like what I would do but
			there's something I like about this method.
			Maybe because it acts like a static method and is similar
			to what I would have done if done within main.
			The question is is a message sent from an individuals mailbox or an individual I guess.
			Having a send method within Mailbox would prevent the need to search for the sender every time*/
			receiver.addMessage(this);
			sender.addSentMessage(this);
		}
	}

	public static void main(String[] args){
		Project_2 p = new Project_2();
		p.run();

	}
	
	void run() {

		/*
		Message msg = new Message("Bob Ross", "Alijah Chandler", "Hello from AC", "Hello Bob. How are you?", new Date());
		msg.send();
		
		msg = new Message("Bob Ross", "Robert Paulson", "Hello from RP", "Hello Bob. How are you?", new Date());
		msg.append("This text was appended and will not show in the message header because appending adds a new line.");
		msg.print();
		msg.send();
		
		MailBoxList mailboxes = new MailBoxList();
		
		ArrayList<Message> bobsMsgs = mailboxes.getUserMailbox("Bob Ross").getMessages();
		for(int i = 0; i < bobsMsgs.size(); i++) {
			bobsMsgs.get(i).printHeader();
		}
		System.out.println("\nThis is from the users sent message folder:");
		mailboxes.getUserMailbox("Alijah Chandler").getSentMessage(0).printHeader();
		System.out.println("\nSent:");
		mailboxes.getUserMailbox("Robert Paulson").getSentMessage(0).printHeader();
		*/
		MailBoxList list = new MailBoxList();
		Mailbox sender = list.getUserMailbox("Robert Paulson");
        Mailbox recipient = list.getUserMailbox("Tim Brown");
        Message email = new Message("Robert Paulson", "Tim Brown", "Hello.");
        email.append("Hello, Tim.");
        email.append("It's Robert Paulson");

        email.print();
        email.send(sender, recipient);

        email = new Message("Robert Paulson", "Tim Brown",  "Hello again.");
        email.append("Hello again, Tim.");
        email.append("It's Robert Paulson, again.");
        email.print();
        email.send(sender, recipient);


        sender = list.getUserMailbox("Tim Brown");
        recipient = list.getUserMailbox("Robert Paulson");
        Message emailResponse = new Message("Tim Brown", "Robert Paulson", "Hello back.");
        emailResponse.append("Hello, Robert Paulson.");
        emailResponse.append("It's Tim. I'm saying hi back.");
        emailResponse.print();
        emailResponse.send(sender, recipient);
        System.out.println("Inbox:");
        for (Message msg: sender.getMessages()) {
            System.out.println(msg.getMessageHeader());
        }
        System.out.println("Sent:");
        for (Message msg: sender.getSentMessages()) {
            System.out.println(msg.getMessageHeader());
        }

        System.out.println("Inbox:");
        for (Message msg: recipient.getMessages()) {
            System.out.println(msg.getMessageHeader());
        }
        System.out.println("Sent:");
        for (Message msg: recipient.getSentMessages()) {
            System.out.println(msg.getMessageHeader());
        }
		
	}
}