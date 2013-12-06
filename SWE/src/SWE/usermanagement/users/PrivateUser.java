package SWE.usermanagement.users;

import java.util.ArrayList;
import SWE.hotelmanagement.Buchung;


/**
 * @author Katharina Ehrenhuber
 * Änderung Klassendiagramm: kein register in PrivateUser (von UserManagement implementiert)
 
 */
public class PrivateUser extends AbstractUser {
	private ArrayList<Buchung> buchungen;

	/**
	 * Constructor for PrivateUser
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public PrivateUser(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		setBuchungen(new ArrayList<Buchung>());
	}
	
	/**
	 * @return the buchungen
	 */
	public ArrayList<Buchung> getBuchungen() {
		return buchungen;
	}

	/**
	 * @param buchungen the buchungen to set
	 */
	public void setBuchungen(ArrayList<Buchung> buchungen) {
		this.buchungen = buchungen;
	}
}
