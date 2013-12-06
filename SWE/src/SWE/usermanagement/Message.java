package SWE.usermanagement;


/**
 * @author Katharina Ehrenhuber
 *
 */
public class Message {
	private int empfaenger;
	private int sender;
	private String message;
	
	/**
	 * Konstruktor Message
	 * @param empfaenger
	 * @param sender
	 * @param message
	 */
	public Message(int empfaenger, int sender, String message) {
		setEmpfaenger(empfaenger);
		setSender(sender);
		setMessage(message);
	}
	
	/**
	 * @return the empfaenger
	 */
	public int getEmpfaenger() {
		return empfaenger;
	}

	/**
	 * @param empfaenger the empfaenger to set
	 */
	public void setEmpfaenger(int empfaenger) {
		this.empfaenger = empfaenger;
	}

	/**
	 * @return the sender
	 */
	public int getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}	
}
