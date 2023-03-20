package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabaseAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) throws Exception {
		DatabaseAccess db = new DatabaseAccess();
		db.readDatabase();
		db.SaveToDataBase("Login1", "ShortUrl1", "OriginalUrl1", "1299-02-12");
		db.DeleteFromDataBase("ShortUrl1");
	}

	public String test() {
		return "dsdadqdwq";
	}
	
	public Statement connectToDatabase() throws Exception {
		try {
			// load the MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/urldatabase?" + "user=sqluser&password=SQLuserPWD!");
			return connect.createStatement();
		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	public void readDatabase() throws Exception {
		try {

			statement = connectToDatabase();
			resultSet = statement.executeQuery("SELECT * from urldatabase.urlstack");
			System.out.println("writeResultSet \"SELECT * from urldatabase.urlstack\":\n");
			writeResultSet(resultSet);
			System.out.println("\nEND of: \"SELECT * from urldatabase.urlstack\"\n");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public String readUrlFromDatabase(String ShortenedUrl) throws Exception {
		try {

			statement = connectToDatabase();
			System.out.println("ShortenedUrl: " + ShortenedUrl);
			resultSet = statement.executeQuery("SELECT * from urldatabase.urlstack WHERE ShortenedUrl = 'TestEmail'");
			System.out.println("writeResultSet \"SELECT * from urldatabase.urlstack\":\n");
		//	writeResultSet(resultSet);
			System.out.println("\nEND of: \"SELECT * from urldatabase.urlstack\"\n");
			return ShortenedUrl;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	public void SaveToDataBase(String UserLogin, String ShortenedUrl, String OriginalUrl, String ExpirationDate)
			throws Exception {
		try {

			connectToDatabase();
			preparedStatement = connect.prepareStatement("INSERT into  urldatabase.urlstack values (default, ?, ?, ?, ?)");
			// "UserLogin, ShortenedUrl, OriginalUrl, ExpirationDate

			System.out.println("Saving to database: " + UserLogin + "  " + OriginalUrl +  "... \n");

			preparedStatement.setString(1, "Login1");
			preparedStatement.setString(2, "ShortUrl1");
			preparedStatement.setString(3, "OgiginalUrl1");
			preparedStatement.setString(4, "2023-02-24"); //
		
			preparedStatement.executeUpdate();

			System.out.println("Seems to be saved \n");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public void DeleteFromDataBase(String ShortenedUrl)	throws Exception {
		try {
			connectToDatabase();
			System.out.println("Deleting... where ShortenerURl = " + ShortenedUrl + "\n");
			preparedStatement = connect.prepareStatement("DELETE from urldatabase.urlstack where ShortenedUrl= ? ; ");
			preparedStatement.setString(1, ShortenedUrl);
			preparedStatement.executeUpdate();
			System.out.println("Deleted ShortenerURl = " + ShortenedUrl + "\n");
			

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	
	private void writeData(ResultSet resultSet) throws SQLException {
		// write out columns in the table from resultSet
		System.out.println("\nThe columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via the column number
			// "UserLogin, ShortenedUrl, OriginalUrl, ExpirationDate from
			// urldatabase.urlstack");
			String user = resultSet.getString("UserLogin");
			String website = resultSet.getString("ShortenedUrl");
			String summary = resultSet.getString("OriginalUrl");
			Date date = resultSet.getDate("ExpirationDate");
			// String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("summary: " + summary);
			System.out.println("Date: " + date);
			// System.out.println("Comment: " + comment);
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}