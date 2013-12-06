package SWE.hotelmanagement;

import java.util.UUID;
import org.joda.time.DateTime;
import SWE.hotelmanagement.hotels.Room;


/**
 * @author Katharina Ehrenhuber
 * Klassendiagramm ändern? - sollte Buchung von PrivateUser ausgehen?
 */
public class Buchung {
	private UUID buchungsID;
	private UUID userID;
	private DateTime checkInDate;
	private DateTime checkOutDate;
	private Room room;

	
	/**
	 * @param userID
	 * @param checkInDate
	 * @param checkOutDate
	 * @param room
	 */
	public Buchung(UUID userID, DateTime checkInDate, DateTime checkOutDate, Room bookedRoom) {
		setBuchungsID(UUID.randomUUID());
		setUserID(userID);
		setCheckInDate(checkInDate);
		setCheckOutDate(checkOutDate);
		setRoom(bookedRoom);
	}	
	
	
	/**
	 * @return the buchungsID
	 */
	public UUID getBuchungsID() {
		return buchungsID;
	}
	/**
	 * @param buchungsID the buchungsID to set
	 */
	public void setBuchungsID(UUID buchungsID) {
		this.buchungsID = buchungsID;
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
	 * @return the checkInDate
	 */
	public DateTime getCheckInDate() {
		return checkInDate;
	}
	/**
	 * @param checkInDate the checkInDate to set
	 */
	public void setCheckInDate(DateTime checkInDate) {
		this.checkInDate = checkInDate;
	}
	/**
	 * @return the checkOutDate
	 */
	public DateTime getCheckOutDate() {
		return checkOutDate;
	}
	/**
	 * @param checkOutDate the checkOutDate to set
	 */
	public void setCheckOutDate(DateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
}



/*
 	public DateTime IntegerToDateTime(int year, int monthOfYear, int dayOfMonth) {
 		return(new DateTime(year,monthOfYear,dayOfMonth,0,0));
	}
 */