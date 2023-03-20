package wtpTutorialPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Random;

//import org.apache.commons.lang3.RandomStringUtils;

//import org.apache.tomcat.util.buf.StringUtils;

/**
 * Servlet implementation class WtpServlet
 */
public class WtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WtpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	 RequestDispatcher dispatcher = getServletContext()
		    .getRequestDispatcher("/forwarded");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		   String original_URL = request.getParameter("original_URL");
	        String expiration_time = request.getParameter("expiration_time");
	         
	        System.out.println("username: " + original_URL);
	        System.out.println("password: " + expiration_time);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>Your original URL: " + original_URL + "<br/>";      
	        htmlRespone += "Set expiration time: " + expiration_time + "</h2>";    
	        
	        
	        String url = request.getRequestURL().toString();
	        htmlRespone += "get.RequestURL: " + url + "<br/>";
	        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";	         
	       
	        String contextPath = request.getContextPath().toString();
	    
	        htmlRespone += "url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + \"/\";	  "
	        		+"<br/>" + "getContextPath(): " + contextPath +"<br/>" + baseURL + "<br/>";
	        
	        String serverURL = request.getRequestURI().toString(); 
	        
	        int leftLimit = 71; // letter 'a'
	        int rightLimit = 122; // letter 'z'
	        int targetStringLength = 10;
	        Random random = new Random();

	        String generatedString = random.ints(leftLimit, rightLimit + 1)
	          .limit(targetStringLength)
	          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	          .toString();

	        System.out.println(generatedString);
//	        
	        htmlRespone += "<br/> "
	        		+ "generatredstring: " + generatedString + "<br/>";
	        
	        htmlRespone += "<br/> "
	        		+ "getRequestURI(): " + serverURL + "<br/>";
	        
	        URL aURL = new URL(request.getRequestURL().toString()); 
	       
	        htmlRespone += "<br/> "
	        		+ "aURL: " + aURL.getAuthority()+aURL.getPath() + "<br/>";
	  
	        htmlRespone += "</html>";
	        // return response
	        
	        writer.println(htmlRespone);
	}

}
