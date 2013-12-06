package SWE.usermanagement.users;

import java.util.ArrayList;
import java.util.UUID;
import SWE.usermanagement.Message;


/**
 * @author Katharina Ehrenhuber
 * Änderung Klassendiagramm: kein Login in AbstractUser (wird von UserManagement implementiert)
 * Änderung Klassendiagramm: kein send/readMessage in AbstractUser (sollte von UserManagement implementiert werden)
 * Änderung Klassendiagramm: ID nicht als int sondern UUID
 */
public abstract class AbstractUser {
	private UUID userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private ArrayList<Message> messages;

	/**
	 * Constructor for AbstractUser
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public AbstractUser(String firstName, String lastName, String email, String password) {
		setUserID(UUID.randomUUID());
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
	}
	
	/**
	 * @return the userID
	 */
	public UUID getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set (must be >=6 characters and must not contain spaces)
	 */
	public void setPassword(String password) {
		if(password.length()<=6) {
			throw new IllegalArgumentException("Invalid input: Your password is too short. Use a password with at least 7 characters.");
		}
		else {
			if (password.contains(" ")) {
				throw new IllegalArgumentException("Invalid input: Please do not use spaces in your password.");
			}
			else {
				this.password = password;
			}
		}

	}
	/**
	 * @return the messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * checks if the given password matches saved password
	 */
	public boolean verifyPassword(String password) {
		if(password.equals(this.getPassword()))
			return true;
		else
			return false;
	}
	
	
}
