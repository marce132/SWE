package SWE.usermanagement.users;

/**
 * 
 * @author Katharina Ehrenhuber
 * Änderung Klassendiagramm: createHotel in HotelManagement
 * keine eigenen Attribute und Methoden - nötig für spätere Unterscheidung in User/HotelManagement
 */

public class Hotelier extends AbstractUser {
	public Hotelier(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}
}
