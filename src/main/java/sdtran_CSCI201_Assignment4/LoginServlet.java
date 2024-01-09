package sdtran_CSCI201_Assignment4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("received0");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        
        JDBCConnector connector = new JDBCConnector();

        if (connector.validateUser(username, password)) {
        	//out.println("Valid Username/Password");
           
        	out.println();
        	out.flush();
        } else {
            
            out.println("Invalid Username/Password");
            out.flush();
        }
        out.close();
        
    }
}