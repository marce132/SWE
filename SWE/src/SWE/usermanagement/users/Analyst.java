/**
 * 
 */
package SWE.usermanagement.users;

/**
 * @author Katharina Ehrenhuber
 * Änderung Klassendiagramm: Statistiken in Hotel- und UserManagement
 * keine eigenen Attribute und Methoden - nötig für spätere Unterscheidung in User/HotelManagement
 */
public class Analyst extends AbstractUser {

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Analyst(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}
}