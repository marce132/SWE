/**
 * 
 */
package SWE.hotelmanagement;

import java.util.ArrayList;
import java.util.UUID;

import SWE.hotelmanagement.hotels.Hotel;
import SWE.hotelmanagement.hotels.Room;
import SWE.usermanagement.users.AbstractUser;
import SWE.usermanagement.users.Hotelier;

import org.joda.time.DateTime;

/**
 * @author Katharina Ehrenhuber
 *
 */
public class HotelManagement {
	private static HotelDAO hotelDAO;
	private static UserDAO userDAO;
	private AbstractUser session;
	
	/**
	 * @return the hotelDAO
	 */
	public HotelDAO getHotelDAO() {
		return hotelDAO;
	}
	/**
	 * @param filename the HotelDAO to set
	 */
	public void setHotelDAO(String filename) {	
		hotelDAO = new HotelDAO(filename);
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}
	/**
	 * @param filename the UserDAO to set
	 */
	public void setUserDAO(String filename) {	
		userDAO = new UserDAO(filename);
	}	
	
	/**
	 * erstellen eines neuen Hotels durch einen Hotelier
	 * @param name
	 * @param numberSingleRooms
	 * @param numberDoubleRooms
	 * @param priceSingleRooms
	 * @param priceDoubleRooms
	 * @param category
	 * @param userID
	 * @param postalCode
	 * @param adress
	 */
	public void createHotel(String name, int numberSingleRooms,int numberDoubleRooms, double priceSingleRooms,
			double priceDoubleRooms, int category, UUID userID, int postalCode, String adress) {
		if(session instanceof Hotelier) {		// nur erlaubt für Hoteliers
			Hotel hotel=new Hotel(name, numberSingleRooms, numberDoubleRooms, priceSingleRooms, 
					priceDoubleRooms, category, userID, postalCode, adress);
			for (int i=0;i<numberDoubleRooms;i++) {			// erstelllt und speichert die Zimmer des Hotels (param: HotelID, RoomType, bookedDates)
				createRoom(hotel.getHotelID(),0, new ArrayList <DateTime>());
			}
			for (int i=0;i<numberSingleRooms;i++) {
				createRoom(hotel.getHotelID(),1, new ArrayList <DateTime>());
			}		
			hotelDAO.saveHotel(hotel);
		}
		else {
			new  IllegalArgumentException("Die Aktion 'createHotel' kann nur von einem Hotelier durchgeführt werden.");
		}
	}
	
	/**
	 * erstellen eines neuen Zimmers für ein vorhandenes Hotel durch einen Hotelier
	 * @param hotelID
	 * @param roomType
	 * @param bookedDates
	 */
	public void createRoom(UUID hotelID, int roomType, ArrayList <DateTime> bookedDates) {
		
		if(session instanceof Hotelier) {
			Room room = new Room(hotelID, roomType, bookedDates);
			hotelDAO.saveRoom(room);
		}
		else {
			new  IllegalArgumentException("Die Aktion 'createRoom' kann nur von einem Hotelier durchgeführt werden.");
		}
	}
	
    /**
     *  Anzahl aller erfassten Benutzer ausgeben
     *  @return int Alle User
     */
    public int numberOfUsers() {
    	int anzahlUser=userDAO.getUserList().size();
    	return anzahlUser;
    }
	/**
	 * Anzahl aller auf der Seite registrierten Hotels
	 * @return int
	 */
    public int numberOfHotels() {
    	int anzahlHotel=hotelDAO.getHotelList().size();
    	return anzahlHotel;
    }
    
    /**
     * durchschnittliche Bewertung eines Hotels berechnen
     * @param hotel
     * @return
     */
    public double averageBewertungHotel(Hotel hotel) {
    	ArrayList<Integer> bewertung = hotel.getBewertung();
    	double wert=0;
    	for (int i=0;i<bewertung.size();i++) {
    		wert+=bewertung.get(i);
    	}
    	wert=wert/bewertung.size();
    	return wert;
    }
    
    /**
     * Hotel mit der besten durchschnittlichen Bewertung finden
     * @return
     */
    public Hotel bestHotel() {
    	ArrayList<Hotel> hotels = hotelDAO.getHotelList();
    	Hotel bestHotel=hotels.get(0);
    	for (int i=0; i<hotels.size();i++) {
    		if(averageBewertungHotel(bestHotel)<averageBewertungHotel(hotels.get(i))) {
    			bestHotel=hotels.get(i);
    		}
    	}
    	return bestHotel;
    }
    
    /**
     * Statistiken ausgeben zu Anzahl User, Anzahl Hotels, bestbewertetes Hotel, ...
     * @return
     */
    public String getStatistics() {
    	String statistics;
    	statistics="Anzahl aller User: "+ numberOfUsers() + "\n"
    			+ "Anzahl aller Hotels: " + numberOfHotels() + "\n"
    			+ "Hotel mit der besten durchschnittlichen Bewertung: " + bestHotel().getName() + "\n";
    	return statistics;
    }
    
    /**
     * 
     * @param name
     * @param eingabe
     * @return gesuchteHotel
     * 
     * Attribut name ist das was der Benutzer auf der Webseite eingibt.
     * Attribut eingabe besagt was der Benutzer eingegeben hat(Name des Hotels od. Postleizahl, od Kategorie).
     * Bei Name des Hotels hat eingabe den Wert "name".
     * Bei Postleitzahl hat eingabe den Wert "plz".
     * Bei Kategorie hat eingabe den Wert "kategorie".
     * Jeweilige if Schleife bei eingabe.
     * Habs mir in etwa so gedacht:
     *
     * <form method="POST"
	 *	action="Hotelsuche.do">
	 *	Nach was soll gesucht werden:
	 *	<select name="eingabe" size="1">
	 *	<option value="plz"> Postleitzahl </option>
	 *	<option value="kategorie"> Kategorie </option>
	 *	</select>
	 *	<br><br>
	 *	<center>
	 *	<input type="SUBMIT">
	 *	</center>
	 *	</form></body></html>
     */
    public ArrayList<Hotel> sucheHotel(String name, String eingabe){
    	
   	ArrayList<Hotel> gesuchteHotel = new ArrayList<Hotel>();
   	ArrayList<Hotel> hotels = hotelDAO.getHotelList();

    if(eingabe.equals("name")){
    	for(int i =0;i<hotels.size();i++){
    		Hotel hotel = hotels.get(i);
    		if(hotel.getName().equals(name)){
    			gesuchteHotel.add(hotels.get(i));
    		}
    	}
    		
    	return gesuchteHotel;
    }	
    
    if(eingabe.equals("plz")){
   	int plz = Integer.parseInt(name);
    	for(int i=0;i<hotels.size();i++){
    		Hotel hotel = hotels.get(i);
    		if(hotel.getPostalCode() == plz){
    			gesuchteHotel.add(hotels.get(i));
    		}
    	}
    	return gesuchteHotel;
    }
   	
   	if(eingabe.equals("kategorie")){
   		int kategorie = Integer.parseInt(name);
    	for(int i=0;i<hotels.size();i++){
    		Hotel hotel = hotels.get(i);
    		if(hotel.getCategory() == kategorie){
    			gesuchteHotel.add(hotels.get(i));
    		}
    	}
    	return gesuchteHotel;
    }
    	
    	
    return gesuchteHotel;
   }
}



/**
 * benötigte Klasse mit Methoden...
 * löschen, sobald DAOs fertig
  */
class HotelDAO {
	public HotelDAO(String i) {}
	public HotelDAO(){}
	void saveHotel(Hotel hotel) {}
	void saveRoom (Room room) {}
	public ArrayList<Hotel> getHotelList() {return null;}
}

class UserDAO {
	public UserDAO(String filename) {}
	public ArrayList<AbstractUser> getUserList() {return null;}
}