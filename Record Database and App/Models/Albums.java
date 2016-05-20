package Models;
/*
 * All Fields are required for this class
 */
import java.sql.*;
public class Albums {
	
	//Fields
	private String albumName,artistName,catilogNum,releaseCountry; 
	private int releaseYear,artistID, albumID;
	private String[] atrubuteArray = {"AlbumName", "ArtistID", "ReleaseYear", "ReleaseCountry","CatilogNum"};

	
	//Constructors
	public Albums()
	{
			
	}//end default constructor
	/*
	 * 
	 */
	public Albums(String tempAlbumName, String tempArtistName, int tempReleaseYear, 
			String tempReleaseCountry, String tempCatilogNum)
	{
		albumName= tempAlbumName.toLowerCase();
		artistName = tempArtistName.toLowerCase();
		artistID = getArtistID(artistName, "Recrods.db");
		releaseYear = tempReleaseYear;
		releaseCountry = tempReleaseCountry.toLowerCase();
		catilogNum = tempCatilogNum;
	}//end constructor
	
	/*
	 * meant to create Album Object when outputStream from SQL select statement no albumID
	 */
	public Albums(String tempAlbumName, int tempArtistID, int tempReleaseYear, 
			String tempReleaseCountry, String tempCatilogNum)
	{
		albumName= tempAlbumName.toLowerCase();
		artistID = tempArtistID;
		releaseYear = tempReleaseYear;
		releaseCountry = tempReleaseCountry.toLowerCase();
		catilogNum = tempCatilogNum;
		
	}//end constructor
	
	public Albums(int tempAlbumID, String tempAlbumName, int tempArtistID, int tempReleaseYear, 
			String tempReleaseCountry, String tempCatilogNum)
	{
		albumID = tempAlbumID;
		albumName= tempAlbumName.toLowerCase();
		artistID = tempArtistID;
		releaseYear = tempReleaseYear;
		releaseCountry = tempReleaseCountry.toLowerCase();
		catilogNum = tempCatilogNum;
		
	}//end constructor
	
	//Helper Methods
	private String atributeNames()
	{
		String out = "";
		for(String temp: atrubuteArray)
		{
			out= out +temp +",";
		}
		return out;
	}
	
	/*
	 *@param dbPath: Path from Working Director
	 *@param artistName: name of the artist you are looking for
	 * if the artist is not created method will return null
	 */
	public int getArtistID(String artistName, String dbPath)
	{
		
		int id = -1;
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
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );//REMOVE END
		}
		
		return id;
	}//ends getArtistID
	
	/*
	 * Checks to see if all required fields are filled. If all required fields are filled method returns true
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
	 * @precondition: no parameters can be null;
	 */
	public void commitToDatabase(String DBName)
	{
		if(areAllRequiredsFilled())
		{
			try
			{
				//Setup
				Connection c = null;
				Statement stmt = null;
				String sql = null;
				//Connecting & Further setup
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdvc:sqlite:"+ DBName);
				c.setAutoCommit(false);
				System.out.println("connection to "+DBName+" susessful");//REMOVE END
				stmt = c.createStatement();
				//Submitting
				sql = "insert into Albums ("+atributeNames()+")"
						+ "values("+ toString()+")";
				stmt.executeUpdate(sql);
				//Closing Connection
				stmt.close();
				c.commit();
				c.close();
			}
			
			catch(Exception e)
			{
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );//REMOVE END
				return;
			}
		}
		else
		{
			System.out.println("Not all required fields are met. Nothig committed to Database.");//REMOVE END
			
		}
		
	}//end commitToDatabase
	
	/*
	 * Updates Album In Database by deleting the old record and creating a new instance in java and commits it to
	 * the database everything should be the same except the changed arguments.(Maintains AlbumID's Integrity)(When pulling
	 * old record the albumID is used)
	 * @precondition albumID is instantiated.
	 * @param DBName- must be a created Database that is structured for this object
	 */
	public void updateToDatabase(String DBName, Albums updated)
	{
		
		if(areAllRequiredsFilled())
		{
			try
			{
				
				//Setup
				Connection c = null;
				Statement stmt = null;
				ResultSet rs = null;
				String sql = null;
				//Connecting & Further setup
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdvc:sqlite:"+ DBName);
				c.setAutoCommit(false);
				System.out.println("Connection to "+DBName+" susessful");
				stmt = c.createStatement();
				//Pulling old Record
				rs = stmt.executeQuery("select from Albums where AlbumName="+ albumName);
				albumID = rs.getInt("AlbumID");
				updated.setAlbumID(albumID);
				//Removes old record
				sql = "delet from Albums where AlbumID = "+ albumID;
				stmt.executeUpdate(sql);
				//Adds new one
				sql = "insert into Albums (AlbumID,"+atributeNames()+")+"
						+ "values("+ updated.getAlbumID()+","+updated.toString()+")";
				stmt.executeUpdate(sql);
				//Closing out
				rs.close();
				stmt.close();
				c.commit();
				c.close();
				
			}
			catch(Exception e)
			{
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );//REMOVE END
				return;
			}
		}
		else
		{
			System.out.println("Not all required fields are met. Nothig updated in Database.");//REMOVE END
		}
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
	public String[] getAtrubuteArray() {
		return atrubuteArray;
	}
	public int getAlbumID() 
	{
		return albumID;
	}
	///////////////////////////////////////////////Setter Methods////////////////////////////////////////////////////
	
	public void setAlbumID(int albumID) {
		this.albumID = albumID;
	}
	public void setAtrubuteArray(String[] atrubuteArray) {
		this.atrubuteArray = atrubuteArray;
	}
	public void setAlbumName(String tempAlbumName)
	{
		albumName = tempAlbumName.toLowerCase();
	}
	public void setArtistName(String tempArtistName)
	{
		artistName = tempArtistName.toLowerCase();
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
		releaseCountry = tempReleaseCountry.toLowerCase();
	}
	public void setCatilogNum(String tempCatilogNum)
	{
		catilogNum = tempCatilogNum;
	}

	//Other 
	/*
	 * Returns true if the passed album is  equal to the latter. This comparison is made between 
	 * the two Catalog Numbers of the two Albums.
	 * 
	 * @precondition: all required fields are met
	 */
	public boolean equals(Albums tempAlbum)
	{
		if(this.getCatilogNum().equals(tempAlbum.getCatilogNum()))
			return true;
		else
			return false;
	}
	
	/*
	 * The returned string is formatted and orders to submit directly to database
	 */
	public String toSting()
	{
		return "'"+albumName+"','"+artistID+"',"+releaseYear+",'"+releaseCountry+"','"+catilogNum+"'"; 		
	}
	/*
	 * returns all fields in a string array in order of attributes
	 */
	public String[] toArray()
	{
		 String[] out = {albumName,""+artistID,""+releaseYear,""+releaseCountry,catilogNum};
		 return out;
	}
			
	

}
