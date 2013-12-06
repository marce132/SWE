//**
 * 
 */
package SWE.hotelmanagement.hotels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Katharina Ehrenhuber
 *
 */
public class Hotel  implements Serializable {
	private UUID hotelID;
	private String name;
	private int numberSingleRooms;
	private int numberDoubleRooms;
	private double priceSingleRooms;
	private double priceDoubleRooms;
	private int category;		// Sterneanzahl
	private UUID userID;
	private int postalCode;
	private String adress;
	private ArrayList<String> kommentar;
	private ArrayList<Integer> bewertung;			// Userbewertung fürs Hotel
	
	
	
	/**
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
	public Hotel(String name, int numberSingleRooms,int numberDoubleRooms, double priceSingleRooms,
			double priceDoubleRooms, int category, UUID userID, int postalCode, String adress) {
		setHotelID(UUID.randomUUID());
		setName(name);
		setNumberSingleRooms(numberSingleRooms);
		setNumberDoubleRooms(numberDoubleRooms);
		setPriceSingleRooms(priceSingleRooms);
		setPriceDoubleRooms(priceDoubleRooms);
		setCategory(category);
		setUserID(userID);
		setPostalCode(postalCode);
		setAdress(adress);
		setKommentar(new ArrayList<String>());
		setBewertung(new ArrayList<Integer>());
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the numberSingleRooms
	 */
	public int getNumberSingleRooms() {
		return numberSingleRooms;			
	}
	/**
	 * @param numberSingleRooms the numberSingleRooms to set
	 */
	public void setNumberSingleRooms(int numberSingleRooms) {
		if (numberSingleRooms<0) {
			throw new IllegalArgumentException("Zimmeranzahl muss positiv sein.");
		}
		else {
			this.numberSingleRooms = numberSingleRooms;
		}
	}
	/**
	 * @return the numberDoubleRooms
	 */
	public int getNumberDoubleRooms() {
		return numberDoubleRooms;
	}
	/**
	 * @param numberDoubleRooms the numberDoubleRooms to set
	 */
	public void setNumberDoubleRooms(int numberDoubleRooms) {
		if (numberDoubleRooms<0) {
			throw new IllegalArgumentException("Zimmeranzahl muss positiv sein.");
		}
		else {
			this.numberDoubleRooms = numberDoubleRooms;
		}
	}
	/**
	 * @return the priceSingleRooms
	 */
	public double getPriceSingleRooms() {
		return priceSingleRooms;
	}
	/**
	 * @param priceSingleRooms the priceSingleRooms to set
	 */
	public void setPriceSingleRooms(double priceSingleRooms) {
		if (priceSingleRooms<0) {
			throw new IllegalArgumentException("Zimmerpreis muss positiv sein.");
		}
		else {
			this.priceSingleRooms = priceSingleRooms;
		}
	}
	/**
	 * @return the priceDoubleRooms
	 */
	public double getPriceDoubleRooms() {
		return priceDoubleRooms;
	}
	/**
	 * @param priceDoubleRooms the priceDoubleRooms to set
	 */
	public void setPriceDoubleRooms(double priceDoubleRooms) {
		if (priceDoubleRooms<0) {
			throw new IllegalArgumentException("Zimmerpreis muss positiv sein.");
		}
		else {
			this.priceDoubleRooms = priceDoubleRooms;
		}
	}
	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}
	/**
	 * @param category the category to set (zwischen 0 und 5 Sternen)
	 */
	public void setCategory(int category) {
		if (category<0 || category>5) {
			throw new IllegalArgumentException("Hotelkategorie kann minimal 0 und maximal 5 sein.");
		}
		else {
			this.category = category;
		}
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
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set (int mit 4 Ziffern)
	 */
	public void setPostalCode(int postalCode) {
		if (postalCode<1000 || postalCode>9999) {
			throw new IllegalArgumentException("Postleitzahl muss eine vierstellige Zahl sein.");
		}
		else {
			this.postalCode = postalCode;			
		}
	}
	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the kommentar
	 */
	public ArrayList<String> getKommentar() {
		return kommentar;
	}
	/**
	 * @param kommentar the kommentar to set
	 */
	public void setKommentar(ArrayList<String> kommentar) {
		this.kommentar = kommentar;
	}
	/**
	 * @return the bewertung
	 */
	public ArrayList<Integer> getBewertung() {
		return bewertung;
	}
	/**
	 * @param bewertung the bewertung to set
	 */
	public void setBewertung(ArrayList<Integer> bewertung) {
		this.bewertung = bewertung;
	}

	public void toString(ArrayList<Hotel> ausgabe){
	 for(int i=0;i<ausgabe.size();i++){
		 Hotel hotel = ausgabe.get(i);
	     System.out.println("Name des Hotels :" +  hotel.getName());
    	 System.out.println("Adresse des Hotels: " + hotel.getAdress());
		 System.out.println("Hotel Bezirk: " + hotel.getPostalCode());
    	 System.out.println("Anzahl der Einbettzimmer: " + hotel.getNumberSingleRooms());
		 System.out.println("Preis pro Einzelzimmer: " + hotel.getPriceSingleRooms());
    	 System.out.println("Anzahl der Doppelzimmer: " + hotel.getNumberDoubleRooms());
    	 System.out.println("Preis pro Doppelzimmer: " +  hotel.getPriceDoubleRooms());
		 
	 }
	}
	
}
