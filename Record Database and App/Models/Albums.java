package Models;

import java.sql.*;
public class Albums {
	
	//Fields
	private String albumName,artistName,catilogNum,releaseCountry; 
	private int releaseYear,artistID;
	
	public Albums()
	{
			
	}//end default constructor
	/*
	 * 
	 */
	public Albums(String tempAlbumName, String tempArtistName, int tempReleaseYear, 
			String tempReleaseCountry, String tempCatilogNum)
	{
		albumName= tempAlbumName;
		artistName = tempArtistName;
		//artistID = getArtistID(artistName, "Recrods.db")
		releaseYear = tempReleaseYear;
		releaseCountry = tempReleaseCountry;
		catilogNum = tempCatilogNum;
	}//end constructor
	
	/*
	 * meant to create Album Object when outputStream from SQL select statement
	 */
	public Albums(String tempAlbumName, int tempArtistID, int tempReleaseYear, 
			String tempReleaseCountry, String tempCatilogNum)
	{
		albumName= tempAlbumName;
		artistID = tempArtistID;
		releaseYear = tempReleaseYear;
		releaseCountry = tempReleaseCountry;
		catilogNum = tempCatilogNum;
		
	}//end constructor
	
	/*
	 *@param dbPath: Path from Working Director
	 *@param artistName: name of the artist you are looking for
	 * if the artist is not created method will return null
	 */
	public int getArtistID(String artistName, String dbPath)
	{
		
		int id = (Integer) null;//take out
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select ArtistID from Artists where ArtistName = '"+artistName+"');");
			id = Integer.parseInt(rs.getString(1));	
			
			//closing out all connections
			rs.close();
			stmt.close();
			c.close();
		}
		catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
		return id;
	}//ends getArtistID
	
	/*
	 * Checks to see if all required fields are filled
	 */
	public boolean areAllRequiredsFilled()
	{
		try
		{
			Integer temp = new Integer(artistID);
			Integer temp2 = new Integer(releaseYear);
			if((!albumName.equals(null)) && (!temp.equals(null)) &&(!temp2.equals(null)) && 
				(!releaseCountry.equals(null)) && (!catilogNum.equals(null)))
			{
				return true;
			}
			else 
				return false;
		}
		catch(NullPointerException e)
		{
			return false;
		}
		catch(Exception e)
		{
			System.out.println("other error occoured");
			return false;
		}
	}
	
	
	/*
	 * Commits Album To Database
	 * @param DBName- must be a created Database that is structured for this object
	 */
	public void commitToDatabase(String DBName)
	{
		//TODO: Code commitToDatabase() method
	}//end commitToDatabase
	
	/*
	 * Updates Album In Database
	 * @param DBName- must be a created Database that is structured for this object
	 */
	public void updateToDatabase(String DBName)
	{
		//TODO: Code updatToDatabase() method
	}//ends updateToDatabase()
	
	///////////////////////////////////////////////Accessor Methods///////////////////////////////////////////////////
	public String getAlbumName()
	{
		return albumName;
	}
	
	public String getArtistName()
	{
		return artistName;
	}
	
	public int getArtistID()
	{
		return artistID;
	}
	
	public int getReleaseYear()
	{
		return releaseYear;
	}
	
	public String getReleaseCountry()
	{
		return releaseCountry;
	}
	
	public String getCatilogNum()
	{
		return catilogNum;
	}
	
	///////////////////////////////////////////////Setter Methods////////////////////////////////////////////////////
	//DONE: write all setter methods

	public void setAlbumName(String tempAlbumName)
	{
		albumName = tempAlbumName;
	}
	
	public void setArtistName(String tempArtistName)
	{
		artistName = tempArtistName;
	}
	
	public void setArtistID(int tempArtistID)
	{
		artistID = tempArtistID;
	}
	
	public void setReleaseYear(int tempReleaseYear)
	{
		releaseYear = tempReleaseYear;
	}
	
	public void setReleaseCountry(String tempReleaseCountry)
	{
		releaseCountry = tempReleaseCountry;
	}
	
	public void setCatilogNum(String tempCatilogNum)
	{
		catilogNum = tempCatilogNum;
	}
	
	/*
	 * The returned string is formatted and orders to submit directly to database
	 */
	public String toSting()
	{
		return "'"+albumName+"','"+artistID+"',"+releaseYear+",'"+releaseCountry+"','"+catilogNum+"'"; 		
	}
	/*
	 * returns all fields in a string array in order of atributes
	 */
	public String[] toArray()
	{
		 String[] out = {albumName,""+artistID,""+releaseYear,""+releaseCountry,catilogNum};
		 return out;
	}
			
	

}
