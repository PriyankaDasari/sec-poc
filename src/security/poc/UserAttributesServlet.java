package security.poc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sap.security.um.service.UserManagementAccessor;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

/**
 * Servlet implementation class UserAttributesServlet
 */
@WebServlet("/UserAttributes")
public class UserAttributesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAttributesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// Check for a logged in user
		if (request.getUserPrincipal() != null) {
		  try {
		    // UserProvider provides access to the user storage
		    UserProvider users = UserManagementAccessor.getUserProvider();

		    // Read the currently logged in user from the user storage
		    User user = users.getUser(request.getUserPrincipal().getName());
		    
		    
		    HttpSession session=request.getSession();  
		    //Set User Id, First Name,Last Name,Email to session object
	        session.setAttribute("userId",request.getUserPrincipal().getName());  

	        session.setAttribute("firstName",user.getAttribute("firstname")); 
	        session.setAttribute("lastName",user.getAttribute("lastname")); 
	        session.setAttribute("email",user.getAttribute("email")); 
	        
	        
	        Map<String, String> userData = new HashMap<>();
	        userData.put("userId", request.getUserPrincipal().getName());
	        userData.put("firstName", user.getAttribute("firstname"));
	        userData.put("lastName", user.getAttribute("lastname"));
	        userData.put("email", user.getAttribute("email"));
	        
	       // Set<String> roles = user.getRoles();
	        user.getRoles().remove("Everyone");
	        userData.put("roles", user.getRoles().toString());
	        
	        //Arrays.toString(user.getAttributeValues("roles"));
	        
		    String json = new Gson().toJson(userData);

		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		    
		  } catch (Exception e) {
		    out.println("Error ocuured "+e.getMessage());
		  }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
