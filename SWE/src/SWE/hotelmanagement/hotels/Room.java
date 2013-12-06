/**
 * 
 */
package SWE.hotelmanagement.hotels;

import java.util.ArrayList;
import org.joda.time.DateTime;
import java.util.UUID;


/**
 * @author Katharina Ehrenhuber
 * 
 * using joda-time-library for extra calendar functions
 * Userguide: http://www.joda.org/joda-time/userguide.html
 * 		1) download joda-time-... from "http://sourceforge.net/projects/joda-time/files/joda-time/"
 * 		2) in Eclipse Project: New - Folder - neuen Folder "libs"
 * 		3) joda-time-... entpacken und joda-time-x-x.jar in libs-Folder kopieren
 * 		4) in Eclipse: File - Refresh --> sollte Folder "libs" mit "joda-time-x-x.jar" im Projekt anzeigen
 * 		5) in Eclipse: Rechtsklick "joda-time-x-x.jar" - Build Path - Add to Build Path
 * 		6) in Klasse "import org.joda.time.DateTime;" 
 */
public class Room {
	private UUID roomID;
	private UUID hotelID;
	/**
	 * int room Type --> 0=doubleRoom (Standard), 1=SingleRoom
	 */
	private int roomType;		
	private ArrayList<DateTime> bookedDate;

	
	/**
	 * @param roomID
	 * @param hotelID
	 * @param bookedDate
	 */
	public Room(UUID hotelID, int roomType, ArrayList<DateTime> bookedDate) {
		setRoomID(UUID.randomUUID());
		setHotelID(hotelID);
		setRoomType(roomType);
		setBookedDate(bookedDate);
	}
	
	/**
	 * @return the roomID
	 */
	public UUID getRoomID() {
		return roomID;
	}
	/**
	 * @param roomID the roomID to set
	 */
	public void setRoomID(UUID roomID) {
		this.roomID = roomID;
	}
	/**
	 * @return the hotelID
	 */
	public UUID getHotelID() {
		return hotelID;
	}
	/**
	 * @param hotelID the hotelID to set
	 */
	public void setHotelID(UUID hotelID) {
		this.hotelID = hotelID;
	}
	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the bookedDate
	 */
	public ArrayList<DateTime> getBookedDate() {
		return bookedDate;
	}
	/**
	 * @param bookedDate the bookedDate to set
	 */
	public void setBookedDate(ArrayList<DateTime> bookedDate) {
		this.bookedDate = bookedDate;
	}

	
	/**
	 * reserviert das Zimmer für den Zeitraum zwischen CheckIn und CheckOut-Datum
	 * @param checkInDate
	 * @param checkOutDate
	 */
	public void bookRoom(DateTime checkInDate, DateTime checkOutDate) {
		while(checkOutDate.isAfter(checkInDate)) {
			bookedDate.add(checkInDate);
			checkInDate.plusDays(1);
		}
	}
}










