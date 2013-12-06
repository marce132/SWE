package SWE.Interface.DAO;

import java.util.ArrayList;
import java.util.UUID;
import java.io.*;
import java.util.*;
import SWE.Interface.Hotelinterface;
import SWE.hotelmanagement.hotels.Hotel;

/**
 * 
 * @author Stefan Starflinger
 * @HotelDAO Saves Hotels to a Serialized File
 * @Methods getHotelbyID, getHotelbyName, getHotelList, saveHotel, deleteHotel, updateHotel
 *
 */
public class HotelDAO implements Hotelinterface 
{
	//read to abstract here
	ArrayList<Hotel> abstractList=new ArrayList<Hotel>();
	int n=0;
	//private String path= "/Users/stefanstarflinger/Documents/workspace/aufgabeuno/";
	private String dname=null;
	
	/*
	 * konstruktor nimmt den filename
	 * und lie�t was in dem File schon enthalten ist.
	 */
	

	
	
	public HotelDAO(String dname)
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
					Hotel getUser= (Hotel) o.readObject();
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
	      	 if(abstractList.get(i).getAdress().isEmpty() || abstractList.get(i).getName().isEmpty())
		        	 o.close();
	        	 
	         if(abstractList.get(i).getAdress().isEmpty() || abstractList.get(i).getName().isEmpty()) throw new IllegalArgumentException("Object Not complete");
	        	
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


	@Override
	public Hotel getHotelbyID(UUID id) {
		for(int i=0; n>i ;i+=1)
		{
			Hotel compare=abstractList.get(i);
			if(id.equals(compare.getHotelID()))
			{
				return compare;
			}
			
		}
		throw new IllegalArgumentException("no match");
	}

	@Override
	public Hotel getHotelbyName(String name) 
	{
		for(int i=0; n>i ;i+=1)
		{
			Hotel compare=abstractList.get(i);
			if(name.equals(compare.getName()))
			{
				return compare;
			}
			
		}
		throw new IllegalArgumentException("no match");
	}
	
	@Override
	public ArrayList<Hotel> getHotelList() {
		if(abstractList.isEmpty()) throw new IllegalArgumentException("Hotel list is Empty");
		return abstractList;
	}

	@Override
	public void saveHotel(Hotel save) {

		for(int i=0; n>i;i+=1)
		{
			Hotel compare=abstractList.get(i);
			if(save.getHotelID().equals(compare.getHotelID())) throw new IllegalArgumentException("Hotel already exists");
		}
		
		n+=1;
		
		abstractList.add(save);
		updateList();
		
	}

	@Override
	public void deleteHotel(Hotel delete) {
		
		for(int i=0; n>i;i+=1)
		{
			Hotel compare=abstractList.get(i);
			if(delete.getHotelID().equals(compare.getHotelID()))
			{
				abstractList.remove(i);
				n-=1;
				updateList();
				break;
			}
			else if(n==(i+1)) throw new IllegalArgumentException("Hotel does not exist");
		}
		
	}

	/*@Override
	public void updateHotel(Hotel update) 
	{
		for(int i=0; n>i;i+=1)
		{
			Hotel compare=abstractList.get(i);
			if(update.getHotelID().equals(compare.getHotelID()))
			{
				abstractList.set(i, update);
				updateList();
				break;
			}
			else if(n==(i+1)) throw new IllegalArgumentException("No Match");
		}

		
	}*/

}
