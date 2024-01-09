package sdtran_CSCI201_Assignment4;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PrintWriter out = response.getWriter();
		System.out.println("received0");
		JDBCConnector connector = new JDBCConnector();
		
		System.out.println("received");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if(connector.doesEmailExist(email)) 
		{
			out.write("Email in use");
			out.flush();
		} else if(connector.doesUsernameExist(username)) 
		{
			out.write("Username in use");
			out.flush();
		} else 
		{
			try {
				connector.insertNewUser(username, password, email);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			out.write("");
			out.flush();
			
		} 
		out.close();
		
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();;
		//JsonArray array = new JsonArray();
		/*
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("email", email);
		jsonObject.addProperty("username", username);
		jsonObject.addProperty("password", password);
		
		System.out.println(jsonObject);
		*/
	}

}

