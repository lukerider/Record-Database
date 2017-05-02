package Models;

import java.sql.*;
import java.util.UUID;

import Helpers.Helper;

/*
 * required fields for this class: albumID, VendorID, VersionNum, Cost, MediaCondition, SleveCondition, 
 * 									DatePurchased, VersionID.
 */
//TODO: have database name be a field and remove all DBName's
public class Versions 
{
	//Fields
	private static Connection c;
	private static PreparedStatement insertStatement;
	private int albumID, vendorID, versionNum, cost;
	private String albumName, mediaCondition, sleeveCondition, datePurchased, vendorName, otherInfo, versionID;	
	private static String prepStatemnt = "insert into Versions(AlbumID"
													+ ",MediaConditon,"
													+ "SleeveCondition,"
													+ "DatePurchased,"
													+ "VendorID,"
													+ "Cost,"
													+ "VersionNum,"
													+ "VersionID,"
													+ "OtherInfo)"
													+ "Values(?,?,?,?,?,?,?,?,?)";
	
	//Constructors 
	//Thought: public Versions(ResultSet rs)
	public Versions()
	{
		
	}//ends default constructor 
	
	/*
	 * this constructor is formated such that it's parameters are ordered the same as the attributes on the 
	 * versions table.
	 */
	public Versions(int tempAlbumID, String tempMediaCondition, String tempSleeveCondition, 
			String tempDatePurchased, int tempVendorID, int tempCost, int tempVersionNum, 
			String tempVersionID, String tempOtherInfo)
	{
		setAlbumID(tempAlbumID);
		setMediaCondition(tempMediaCondition.toUpperCase());
		setSleeveCondition(tempSleeveCondition.toUpperCase());
		setDatePurchased(tempDatePurchased.toLowerCase());
		setVendorID(tempVendorID);
		setCost(tempCost);
		setVersionNum(tempVersionNum);
		setVersionID(tempVersionID);
		setOtherInfo(tempOtherInfo);
	}//ends table constructor
	
	/*
	 * this constructor is formated such that it's parameters are ordered the same as the attributes on the 
	 * versions table with the exception of otherInfo. Other info is not included in the parameters.
	 */
	//********public Versions(ResultSet rs)****************
	public Versions(int tempAlbumID, String tempMediaCondition, String tempSleeveCondition,
			String tempDatePurchased, int tempVendorID, int tempCost, int tempVersionNum, String tempVersionID)
	{
		setAlbumID(tempAlbumID);
		setMediaCondition(tempMediaCondition.toUpperCase());
		setSleeveCondition(tempSleeveCondition.toUpperCase());
		setDatePurchased(tempDatePurchased.toLowerCase());
		setVendorID(tempVendorID);
		setCost(tempCost);
		setVersionNum(tempVersionNum);
		setVersionID(tempVersionID);
	}//ends	varied table constructor
	
	/*
	 * this constructor is formated such that the parameters would match the input from the NewAlbum panel.
	 */
	public Versions(String tempAlbumName, String tempMediaCondition, String tempSleeveCondition, 
			String tempDatePurchased, String tempVendorName, int tempCost, String tempOtherInfo)
	{
		try
		{
			setAlbumName(tempAlbumName.toLowerCase());
			setAlbumID(findAlbumID("Record.db"));
			setMediaCondition(tempMediaCondition.toUpperCase());
			setSleeveCondition(tempSleeveCondition.toUpperCase());
			setDatePurchased(tempDatePurchased.toLowerCase());
			setVendorName(tempVendorName.toLowerCase());
			setVendorID(findVendorID("Record.db"));
			setCost(tempCost);
			setVersionNum(findVersionNum());
			setVersionID(""+UUID.randomUUID());
			setOtherInfo(tempOtherInfo);
		}
		catch(Exception e)
		{
			System.err.println("Couldn't Create Version Object");
		}
	}//ends original constructor
	
	/*
	 * this constructor is formated such that the parameters would match the input from the NewAlbum panel except 
	 * otherInfo. Other info is not included in parameters. 
	 */
	public Versions(String tempAlbumName, String tempMediaCondition, String tempSleeveCondition, 
			String tempDatePurchased, String tempVendorName, int tempCost)
	{
		try
		{
			setAlbumName(tempAlbumName.toLowerCase());
			setAlbumID(findAlbumID("Record.db"));
			setMediaCondition(tempMediaCondition.toUpperCase());
			setSleeveCondition(tempSleeveCondition.toUpperCase());
			setDatePurchased(tempDatePurchased.toLowerCase());
			setVendorName(tempVendorName.toLowerCase());
			setVendorID(findVendorID("Record.db"));
			setVersionID(""+UUID.randomUUID());
			setVersionNum(findVersionNum());
			setCost(tempCost);
		}
		catch(Exception e)
		{
			System.err.println("Couldn't Create Version Object");
		}
	}//ends original constructor
	
	
	//Other Methods
	public void commitToDatabase(Connection c)throws SQLException
	{
		Helper.initialize(c, insertStatement, prepStatemnt);
		if(areRequiredsFilled())
		{
			insertStatement.setInt(1, albumID);
			insertStatement.setString(2, mediaCondition);
			insertStatement.setString(3, sleeveCondition);
			insertStatement.setString(4, datePurchased);
			insertStatement.setInt(5, vendorID);
			insertStatement.setString(6, otherInfo);
		}
		else
		{
			System.err.println("Not all the required fields are filled");
		}
	}
	
	//Helper methods
	
	
	public boolean areRequiredsFilled()
	{
		try
		{
			//DONE: Finish this method
			if((!Helper.empty(""+albumID))&&
				(!Helper.empty(mediaCondition))&&
				(!Helper.empty(sleeveCondition))&&
				(!Helper.empty(datePurchased))&&
				(!Helper.empty(""+vendorID))&&
				(!Helper.empty(""+cost))&&
				(!Helper.empty(""+versionNum))&&
				(!Helper.empty(versionID)))//end
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(NullPointerException npe)
		{
			return false;
		}
		catch(Exception e)
		{
			System.out.println("Other error occoured");
			return false;
		}
	}
	
	
	/*
	 * @precondition: Album must already be created;
	 * @precondition: must call initialize() prior to call of this method
	 */
	private int findAlbumID(String DBName) throws SQLException
	{
		Statement stmt = null;
		int output = -1; 
		try
		{
			//Setup
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+DBName);
			c.setAutoCommit(false);
			System.out.println("Opened "+ DBName+ "connection successfully");//REMOVE END
			stmt = c.createStatement();
			//getting AlbumID
			ResultSet rs = stmt.executeQuery("select ArtistID from Artists where ArtistName = '"+ albumName+"'");
			output = rs.getInt(1);
			//Closing out
			rs.close();
			stmt.close();
		
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );//REMOVE END			
		}
		finally 
		{
			//rs.close();
			stmt.close();
			c.close();
		}
		
		return output;
	}//ends findAlbumID()
	/*
	 * @precondition: vendor already instantiated.
	 */
	private int findVendorID(String DBName)
	{
		Connection c = null;
		Statement stmt = null;
		int out = -1;
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("");
			c.setAutoCommit(false);
			System.out.println("Opened "+ DBName+ "connection successfully");//REMOVE END
			stmt = c.createStatement();
			//Getting VendorID
			ResultSet rs = stmt.executeQuery("select VendorID from Vendors where VendorName = '"+vendorName+"'");
			out = rs.getInt(1);
			//Closing out
			rs.close();
			stmt.close();
			c.close();		
		}
		catch(Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );//REMOVE END
			System.exit(0);
		}
		return out;
	}//ends finVendorID()
	
	/*
	 * @precondition: all other required fields are filled 
	 */
	private int findVersionNum()
	{
		//TODO: Write findVersionNum helper Method
		return 0;
	}//ends findVersionNum()
	
	public String generateVersionID()
	{
		//TODO: Write generateVersionID helper Method
		return "";
	}
	
	
	
	
	
	
	
	
	//Getter Methods
	public int getAlbumID() 
	{
		return albumID;
	}
	public int getVendorID() 
	{
		return vendorID;
	}
	public int getVersionNum()
	{
		return versionNum;
	}
	public int getCost() 
{
		return cost;
	}
	public String getSleeveCondition() 
	{
		return sleeveCondition;
	}
	public String getAlbumName() 
	{
		return albumName;
	}
	public String getMediaCondition() 
	{
		return mediaCondition;
	}
	public String getDatePurchased() 
	{
		return datePurchased;
	}
	public String getVendorName() 
	{
		return vendorName;
	}
	public String getOtherInfo() 
	{
		return otherInfo;
	}
	public String getVersionID() 
	{
		return versionID;
	}
	
	
	
	
	
	
	
	
	
	
	//Setter Methods
	public void setAlbumID(int albumID) 
	{
		this.albumID = albumID;
	}
	public void setVendorID(int vendorID) 
	{
		this.vendorID = vendorID;
	}
	public void setVersionNum(int versionNum) 
	{
		this.versionNum = versionNum;
	}
	public void setSleeveCondition(String sleeveCondition) 
{
		this.sleeveCondition = sleeveCondition;
	}
	public void setCost(int cost) 
{
		this.cost = cost;
	}
	public void setAlbumName(String albumName) 
	{
		this.albumName = albumName;
	}
	public void setMediaCondition(String mediaCondition) 
	{
		this.mediaCondition = mediaCondition;
	}
	public void setDatePurchased(String datePurchased) 
	{
		this.datePurchased = datePurchased;
	}
	public void setVendorName(String vendorName) 
	{
		this.vendorName = vendorName;
	}
	public void setOtherInfo(String otherInfo) 
	{
		this.otherInfo = otherInfo;
	}
	public void setVersionID(String versionID) 
	{
		this.versionID = versionID;
	}

	
	
}

