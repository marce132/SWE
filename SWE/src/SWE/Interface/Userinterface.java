/**
 * 
 */
package SWE.Interface;
import java.util.ArrayList;
import SWE.usermanagement.users.*;

/**
 * @author
 *
 */
public interface Userinterface {
	
	public abstract AbstractUser getUserbyLogin(String login);
	public abstract ArrayList<AbstractUser> getUserList();
	public abstract void saveUser(AbstractUser save); 
	public abstract void deleteUser(AbstractUser delete);
	public abstract void updateUser(AbstractUser update) ;
	
}