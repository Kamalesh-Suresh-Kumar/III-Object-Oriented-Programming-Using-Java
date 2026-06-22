package bank.management.system;
import java.sql.*;
public class Database 
{
	private static final String URL = "jdbc:mysql://localhost:3306/bank";
	private static final String USER = "root";
	private static final String PASSWORD = "NewStrongPassword@123";
	private static Connection connection;
	public static Connection connect() 
	{
		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Connected to the database!");
		} 
		catch (ClassNotFoundException | SQLException e)
		{
		System.out.println("Failed to connect to the database!");
		e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) 
	{
		connect ();
	}
}