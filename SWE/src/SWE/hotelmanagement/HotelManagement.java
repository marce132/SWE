/**
 *

 */
package SWE.hotelmanagement;

import java.util.ArrayList;
import java.util.UUID;

import SWE.Interface.DAO.HotelDAO;
import SWE.hotelmanagement.hotels.Hotel;
import SWE.hotelmanagement.hotels.Room;
import SWE.usermanagement.users.AbstractUser;
import SWE.usermanagement.users.Analyst;
import SWE.usermanagement.users.Hotelier;
import SWE.usermanagement.users.PrivateUser;

import org.joda.time.DateTime;

/**
 * @author Katharina Ehrenhuber
 *
 */
public class HotelManagement {
	private HotelDAO hotelDAO;
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
			double priceDoubleRooms, int category, int postalCode, String adress) {
		if(session instanceof Hotelier) {		// nur erlaubt für Hoteliers
			UUID userID = session.getUserID();
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
	 * Buchung erstellen für eingeloggten PrivateUser
	 * @param checkIn
	 * @param checkOut
	 * @param room
	 */
	public void createBuchung(DateTime checkIn, DateTime checkOut, Room room) {
		if(session instanceof PrivateUser) {
			Buchung buchung = new Buchung (session.getUserID(), checkIn, checkOut, room);
			hotelDAO.saveBuchung(buchung);
		}
		else {
			new  IllegalArgumentException("Die Aktion 'createBuchung' kann nur von einem privaten Benutzer durchgeführt werden.");
		}
	}
	
	
	/**
	 * ueberpruefen, ob Zimmer zu bestimmtem Datum frei ist
	 * @param checkIn
	 * @param checkOut
	 * @param bookedRoom
	 * @return boolean - true, falls Zimmer frei; false, falls Zimmer besetzt
	 */
	public boolean checkRoomAvailability(DateTime checkInDate, DateTime checkOutDate, Room bookedRoom) {
		ArrayList<DateTime> bookedDates = bookedRoom.getBookedDate();
		while(checkOutDate.isAfter(checkInDate)) {
			if(bookedDates.contains(checkInDate)) {
				return false;
			}
			checkInDate.plusDays(1);
		}
		return true; 	// nur aufgerufen, falls in Schleife nicht vorhanden
	}
	
	
	/**
	 * Anzahl aller auf der Seite registrierten Hotels (nur von Analyst ausführbar)
	 * @return int
	 */
    public int numberOfHotels() {
		if(session instanceof Analyst) {
			int anzahlHotel=hotelDAO.getHotelList().size();
			return anzahlHotel;
		}
		else {
			new  IllegalArgumentException("Die Aktion 'numberOfHotels' kann nur von einem Analyst durchgeführt werden.");
			return 0;
		}
    }
    
    /**
     * durchschnittliche Bewertung eines Hotels berechnen (nur von Analyst ausführbar)
     * @param hotel
     * @return
     */
    public double averageBewertungHotel(Hotel hotel) {
		if(session instanceof Analyst) {
			ArrayList<Integer> bewertung = hotel.getBewertung();
			double wert=0;
			for (int i=0;i<bewertung.size();i++) {
				wert+=bewertung.get(i);
			}
			wert=wert/bewertung.size();
			return wert;
		}
		else {
			new  IllegalArgumentException("Die Aktion 'averageBewertungHotel' kann nur von einem Analyst durchgeführt werden.");
			return 0;
		}
    }
    
    /**
     * Hotel mit der besten durchschnittlichen Bewertung finden (nur von Analyst ausführbar)
     * @return
     */
    public Hotel bestHotel() {
		if(session instanceof Analyst) {
			ArrayList<Hotel> hotels = hotelDAO.getHotelList();
			Hotel bestHotel=hotels.get(0);
			for (int i=0; i<hotels.size();i++) {
				if(averageBewertungHotel(bestHotel)<averageBewertungHotel(hotels.get(i))) {
					bestHotel=hotels.get(i);
				}
			}
			return bestHotel;
		}
		else {
			new  IllegalArgumentException("Die Aktion 'averageBewertungHotel' kann nur von einem Analyst durchgeführt werden.");
			return null;
		}
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
   
   	// Bekommt null übergeben
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
    
    public ArrayList<Room> sucheZimmer(Hotel hotel, DateTime CheckIn, DateTime CheckOut, int zimmertyp){
    	ArrayList<Room> gesuchteZimmer = new ArrayList<Room>();
    	
    	
    	return gesuchteZimmer;
    }
}