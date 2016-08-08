package Helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * general helper class
 */
public class Helper {
	public static boolean empty(final String s)
	{
		return s == null|| s.trim().isEmpty();
	}
	
	public static String makeAtributeString(String[] array)
	{
		String out = "";
		for(String temp: array)
		{
			out= out +temp +",";
		}
		return out;
	}
	
	public static void initialize(Connection connection, PreparedStatement ps, String statement) throws SQLException
	{
		ps = connection.prepareStatement(statement);
		
	}//ends initialize
	
	
}
