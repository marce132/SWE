/**
 * 
 */
package SWE.Interface;
import java.util.UUID;

import SWE.hotelmanagement.Buchung;
import SWE.hotelmanagement.hotels.Hotel;
import SWE.hotelmanagement.hotels.Room;
//import SWE.usermanagement.users.AbstractUser;
import java.util.ArrayList;


/**
 * @author
 *
 */
public interface Hotelinterface {
	public abstract Hotel getHotelbyID(UUID id); // gets hotel by ID
	public abstract Hotel getHotelbyName(String name); //gets hotel by name
	//public abstract AbstractUser getHotelier(Hotel hotel); //gets othel by Hotelier input hotel, return Hotelier useing user id
	public abstract ArrayList<Hotel> getHotelList(); // gets the hotel list saved
	public abstract void saveHotel(Hotel save); // saves a hotel to the file and arraylist
	public abstract void deleteHotel(Hotel delete); // Deletes hotel from Arraylist and overrides file
	//public abstract void updateHotel(Hotel update) ; // updates a hotel (overrides object)

	public void saveRoom (Room room);
	public void saveBuchung (Buchung buchung);
}
