import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

/**
 A class that stores e-mail messages.
 */
class Mailbox
{
    private String user;
    private ArrayList<Message> messages;
    private  ArrayList<Message> sentMessages;

    public String getUser() {
        return user;
    }

    /**
     Initializes an empty mailbox.
     */
    public Mailbox(String user)
    {
        this.user = user;
        messages = new ArrayList<Message>();
        sentMessages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    /**
     Adds a new message.
     @param m the message
     */
    public void addMessage(Message m)
    {

        // Implement me
    }

    public void addSentMessage(Message m)
    {
        // Implement me
    }

    /**
     Gets the ith message.
     @param i the message number to get
     @return the ith message
     */
    public Message getMessage(int i)
    {
        // Implement me - return a message
        return null;
    }

    public Message getSentMessage(int i)
    {
        // Implement me - return a message

        return null;
    }

    /**
     Removes the ith message.
     @param i the message number to remove
     */
    public void removeMessage(int i)
    {
        // Implement me
    }

    public void removeSentMessage(int i)
    {
        // Implement me
    }

}


/**
 A class that models an e-mail message.
 */
class Message
{
    private String recipient;
    private String sender;
    private String subject;
    private String messageText;
    private Date date;

    /**
     Takes the sender and recipient
     @param recipient the recipient
     @param sender the sender
     */
    public Message(String recipient, String sender, String subject)
    {
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        messageText = "";
        this.date = Calendar.getInstance().getTime();
    }

    /**
     Appends a line of text to the message body.
     @param line the line to append
     */
    public void append(String line)
    {
        // Implement me
    }

    /**
     Makes the message into one long string.
     */
    public String toString()
    {
        // Implement me - return a string
        return "";
    }

    public String getMessageHeader() {
        // Implement me - return a string
        return "";
    }

    /**
     Prints the message text.
     */
    public void print()
    {
        System.out.print(this.toString());
    }

    public void send(Mailbox sender, Mailbox recipient, Message message) {
        // Implement me
    }

}
class MailboxList {

    private MailboxList() {}

    private  static ArrayList<Mailbox> mailboxList = new ArrayList<>();
    public static Mailbox GetUserMailbox(String user) {
        for (Mailbox mailbox: mailboxList) {
            if (mailbox.getUser().equals(user))
                return mailbox;
        }
        // user doesn't exist so create this user
        Mailbox mailbox = new Mailbox(user);
        mailboxList.add(mailbox);
        return mailbox;
    }
}

public class EmailServer
{
    //  This classs is a sample test driver.  You should change the messages and users to make it your own.
    public static void main(String[] args)
    {
   //     MailboxList mailboxOwners = new MailboxList();
        Mailbox sender = MailboxList.GetUserMailbox("Tobias");
        Mailbox recipient = MailboxList.GetUserMailbox("Rolf");
        Message email = new Message("Rolf", "Tobias", "First Joke");
        email.append("What do you call an alligator in a vest?");
        email.append("An investigator!");

        email.print();
        email.send(sender, recipient, email);

        email = new Message("Rolf", "Tobias",  "Second Joke");
        email.append("Did you hear about the two antennae that got married?");
        email.append("The ceremony was terrible but the reception was amazing.");
        email.print();
        email.send(sender, recipient, email);


        sender = MailboxList.GetUserMailbox("Rolf");
        recipient = MailboxList.GetUserMailbox("Tobias");
        Message emailResponse = new Message("Tobias", "Rolf", "New Joke");
        emailResponse.append("When is it time to go to the dentist?");
        emailResponse.append("Tooth hurty");
        emailResponse.print();
        emailResponse.send(sender, recipient, emailResponse);
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


