package SWE.Interface.DAO;

import java.io.*;
import java.util.*;
import SWE.Interface.Userinterface;
import SWE.usermanagement.users.AbstractUser;

public class AbstractUserDAO implements Userinterface 
{
	ArrayList<AbstractUser> abstractList=new ArrayList<AbstractUser>();
	int n=0;
	
	private String dname=null;

	public AbstractUserDAO(String dname)
	{
this.dname=dname; //frage wegen path uebergabe
		
		File test=new File(dname);
		if(test.exists())
		{
			
			try
			{
				InputStream osu = new FileInputStream(dname);
				ObjectInputStream o = new ObjectInputStream(osu); //opens stream to file
				
			
				 while (osu.available()>0)
				{
					AbstractUser getUser= (AbstractUser) o.readObject();
					abstractList.add(getUser);
				}
				
				n=abstractList.size();
				o.close();
				osu.close();
			}
			catch(IOException er)
			{
				System.err.println(er);
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	//ueberschriebt das ganze File von der abstractList
	public void updateList()
	{
		try
	      {
	         FileOutputStream isu = new FileOutputStream(dname);
	         ObjectOutputStream o = new ObjectOutputStream(isu);
	         
	         
	         for(int i=0; n>i;i+=1)
	         { 
	      	 if(abstractList.get(i).getLogin().isEmpty() || abstractList.get(i).getLogin().isEmpty() || abstractList.get(i).getPassword().isEmpty())
		        	 o.close();
	        	 
	         if(abstractList.get(i).getLogin().isEmpty() || abstractList.get(i).getLogin().isEmpty() || abstractList.get(i).getPassword().isEmpty()) throw new IllegalArgumentException("Object Not complete");
	        	
	         o.writeObject(abstractList.get(i));
	         
	         }
	         o.close();
	         isu.close();
	         
	      }
	         
		catch(IOException er)
			{
				System.err.println(er);
			} 
		
	}
	
	//findet den User aus der Abstract liste falls enthalten.
	@Override
	public AbstractUser getUserbyLogin(String login) 
	{

		for(int i=0; n>i ;i+=1)
		{
			AbstractUser compare=abstractList.get(i);
			if(login.equals(compare.getLogin()))
			{
				return compare;
			}
			
		}
		
		
		throw new IllegalArgumentException("no match");
	}
	//Returnes the Abstract List
	@Override
	public ArrayList<AbstractUser> getUserList()  
	{
		if(abstractList.isEmpty()) throw new IllegalArgumentException("Userlist is Empty");
		return abstractList;
	}
	// saves a user to the abstract list and triggers the function updateList
	@Override
	public void saveUser(AbstractUser save) 
	{
		
		for(int i=0; n>i;i+=1)
		{
			AbstractUser compare=abstractList.get(i);
			if(save.getLogin().equals(compare.getLogin())) throw new IllegalArgumentException("Username already exists");
		}
		
		n+=1;
		
		abstractList.add(save);
		updateList();
	}
	//deletes a user from abstract list and triggers updateList()
	@Override
	public void deleteUser(AbstractUser delete) 
	{
		
		for(int i=0; n>i;i+=1)
		{
			AbstractUser compare=abstractList.get(i);
			if(delete.getLogin().equals(compare.getLogin()))
			{
				abstractList.remove(i);
				n-=1;
				updateList();
				break;
			}
			else if(n==(i+1)) throw new IllegalArgumentException("No Match");
		}

	}
	//updates Abstract list and triggers update List
	@Override
	public void updateUser(AbstractUser update) 
	{
	
		for(int i=0; n>i;i+=1)
		{
			AbstractUser compare=abstractList.get(i);
			if(update.getLogin().equals(compare.getLogin()))
			{
				abstractList.set(i, update);
				updateList();
				break;
			}
			else if(n==(i+1)) throw new IllegalArgumentException("No Match");
		}
		
		
	}
	

}
