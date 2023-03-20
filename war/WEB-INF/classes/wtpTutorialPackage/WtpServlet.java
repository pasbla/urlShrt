package wtpTutorialPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		   String username = request.getParameter("original_URL");
	        String password = request.getParameter("expiration_time");
	         
	        System.out.println("username: " + username);
	        System.out.println("password: " + password);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>Your username is: " + username + "<br/>";      
	        htmlRespone += "Your password is: " + password + "</h2>";    
	        
	        
	        String url = request.getRequestURL().toString();
	        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";	         
	        htmlRespone += baseURL + "<br/>";
	        
	        htmlRespone += "</html>";
	        // return response
	        
	        writer.println(htmlRespone);
	}

}
