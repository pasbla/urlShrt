package database;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class DatabaseAccessServlet
 */
public class DatabaseAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null; 
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
	public static void main(String[] args) throws Exception {
		DatabaseAccessServlet db = new DatabaseAccessServlet();
		System.out.println("DatabaseAccessServlet:main()");
		db.connectToDatabase();
		db.readDatabase();
		db.saveUrlToDataBase("Login1", "ShortUrl1", "OriginalUrl1", "1299-02-12");
//		db.DeleteFromDataBase("ShortUrl1");
	}
	
	

	@SuppressWarnings("finally")
	public String test() throws Exception {
		
		String testOut = "test error";
		try {
			statement = connectToDatabase();
			testOut = "connected";
			resultSet = statement.executeQuery("SELECT * from urldatabase.urlstack");
			writeResultSet(resultSet);

		} catch (Exception e) {
			testOut = e.toString();
			//throw e;
		} finally {
			close();
			return testOut;
		}

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
	

	public ResultSet readUserHistory(String email) throws Exception {
		try {

			statement = connectToDatabase();
			resultSet = statement.executeQuery("SELECT * from urldatabase.urlstack WHERE UserMail = '" + email + "' ORDER BY ExpirationDate");
			System.out.println("*********writeResultSet \"SELECT * from urldatabase.urlstack\":\n");
			
	//		writeResultSet(resultSet);
			System.out.println("***********\nEND of: \"SELECT * from urldatabase.urlstack\"\n");
			return resultSet;

		} catch (Exception e) {
			throw e;
		} finally {
//			close();
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


	public String readUrlFromDatabase(String ShortenedUrl) throws Exception {
		try {
			String originalUrl = null;
			statement = connectToDatabase();
//			System.out.println("ShortenedUrl: " + ShortenedUrl);
			String queryString = "SELECT * FROM urldatabase.urlstack WHERE ShortenedUrl = '" + ShortenedUrl + "'";
			resultSet = statement.executeQuery(queryString);
//			System.out.println("writeResultSet \"SELECT * from urldatabase.urlstack\":\n");
		//	writeResultSet(resultSet);
//			System.out.println("\nEND of: \"SELECT * from urldatabase.urlstack\"\n");
			resultSet.next();
			
			originalUrl = resultSet.getString("OriginalUrl");
			Date expirationDate = resultSet.getDate("ExpirationDate");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			String dateString = dateFormat.format(date); //set current date to localDate
			Date localDate = dateFormat.parse(dateString);
			//int compareTo2 = expirationDate.compareTo(date); 
			int compareTo = expirationDate.compareTo(localDate);
			if (compareTo < 0) {
				return null;
				
			} else {
				return originalUrl;
			}
//			return "expirationDate: "+expirationDate
//					+";;; loacalDate: "+localDate
//					+";;;  expDate.compareTo(date): "+String.valueOf(compareTo)
//					+";;; date2: "
//					+";;; expirationDate.equals(localDate)\r\n "+expirationDate.equals(localDate)
//					+";;; expirationDate.compareTo(date2)\r\n: "+String.valueOf(compareTo2);
	//		return localDate;
	//		return ;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}
	
	public Date readDateFromDatabase(String ShortenedUrl) throws Exception {
		try {

			statement = connectToDatabase();
			String queryString = "SELECT * from urldatabase.urlstack WHERE ShortenedUrl = '" + ShortenedUrl + "'";
			resultSet = statement.executeQuery(queryString);
//			System.out.println("writeResultSet \"SELECT * from urldatabase.urlstack\":\n");
		//	writeResultSet(resultSet);
//			System.out.println("\nEND of: \"SELECT * from urldatabase.urlstack\"\n");
			resultSet.next();
			Date date2 = resultSet.getDate("ExpirationDate");
			return date2;
//			return "return";
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	
	public void saveUrlToDataBase(String UserMail, String ShortenedUrl, String OriginalUrl, String ExpirationDate)
			throws Exception {
		try {

			connectToDatabase();
			preparedStatement = connect.prepareStatement("INSERT into  urldatabase.urlstack values (default, ?, ?, ?, ?)");
			// "UserLogin, ShortenedUrl, OriginalUrl, ExpirationDate

//			System.out.println("Saving to database: " + UserLogin + "  " + OriginalUrl +  "... \n");

			preparedStatement.setString(1, UserMail);
			preparedStatement.setString(2, ShortenedUrl);
			preparedStatement.setString(3, OriginalUrl);
			preparedStatement.setString(4, ExpirationDate); //
		
			preparedStatement.executeUpdate();

//			System.out.println("Seems to be saved \n");

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	public Boolean saveNewUserToDatabase(String userName, String email, String password)
			throws Exception {
		Boolean ans = false;
		try {
			connectToDatabase();
				
			preparedStatement = connect.prepareStatement("INSERT into  urldatabase.userdb values (?, ?, ?)");
			// "userName, email, password
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.executeUpdate();
			ans = true;
		} catch (Exception e) {
			// throw e;
			return ans;
		} finally {
			close();
		}
		return ans;
	}
	
	public Boolean isUserExisting (String email) throws Exception {
		Boolean ans = false;
		try {
			statement = connectToDatabase();
			String queryString = "SELECT * FROM urldatabase.userdb WHERE UserMail = '" + email + "'";
			resultSet = statement.executeQuery(queryString);
			if (resultSet.next()) {
					ans = true;
			}
		} catch (Exception e) {
//			throw e;
		} finally {
			close();
		}
		return ans;
	}
	
	
	public String isUserValid(String email, String password)
			throws Exception {
		String ans = null;
		try {
			statement = connectToDatabase();
			String queryString = "SELECT * FROM urldatabase.userdb WHERE UserMail = '" + email + "'";
			resultSet = statement.executeQuery(queryString);
			if(resultSet.next()){
				if(password.equals(resultSet.getString("UserPasswd"))){
					return resultSet.getString("UserLogin");
				};
			};
			
		} catch (Exception e) {
//			throw e;
		} finally {
			close();
		}

		return ans;
	}
	
	
	
	public void DeleteFromDatabase(String ShortenedUrl)	throws Exception {
		try {
			connectToDatabase();
			preparedStatement = connect.prepareStatement("DELETE from urldatabase.urlstack where ShortenedUrl= ? ; ");
			preparedStatement.setString(1, ShortenedUrl);
			preparedStatement.executeUpdate();
//			System.out.println("Deleted ShortenerURl = " + ShortenedUrl + "\n");
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	
    public DatabaseAccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
