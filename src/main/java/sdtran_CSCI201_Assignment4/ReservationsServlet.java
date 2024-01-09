package sdtran_CSCI201_Assignment4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/ReservationsServlet")
public class ReservationsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String r_name = req.getParameter("deat");
		String username = req.getParameter("username");
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		String desc = req.getParameter("desc");
		
		String action = req.getParameter("action");
		JDBCConnector connector = new JDBCConnector();
		try {
			if(action.equals("put")) 
			{
				
				connector.insertNewReservation(username, r_name, date, time, desc);;
			} else 
			{
				connector.removeNewReservation(username, r_name);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("username");
		JDBCConnector connector = new JDBCConnector();
		PrintWriter out = resp.getWriter();
		try 
		{
			out.write(connector.getReservationRestaurants(connector.getUserID(user)));
			out.flush();
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		out.close();
		
	}
}
