/**
 * 
 */
package SWE.usermanagement;

import java.util.UUID;

import SWE.Interface.DAO.HotelDAO;
import SWE.hotelmanagement.HotelManagement;
import SWE.hotelmanagement.hotels.Hotel;

/**
 * @author Katharina Ehrenhuber
 *
 */
public class UserManagement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UUID test = new UUID(4, 877);
		Hotel hotel = new Hotel("franz", 0, 0, 0, 0, 0, test, 1010, "fesd");
      //  HotelDAO hotelDAO = new HotelDAO("hjn");
      //  hotelDAO.saveHotel(hotel);
     //   HotelManagement managment = new HotelManagement();
	//    managment.sucheHotel("franz", "name");
}
}