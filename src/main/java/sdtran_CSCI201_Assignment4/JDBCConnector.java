package sdtran_CSCI201_Assignment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JDBCConnector {
	Connection conn  = null;
	PreparedStatement st = null;
	
	public boolean doesEmailExist(String email) {
	    boolean emailExists = false;
	    ResultSet rs = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("SELECT * FROM Users WHERE Email = ?");
	        st.setString(1, email);
	        rs = st.executeQuery();

	        if (rs.next()) {
	          
	            emailExists = true;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("MYSQL not found!");
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    return emailExists;
	}
	
	public boolean doesUsernameExist(String username) {
	    boolean userExists = false;
	    ResultSet rs = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("SELECT * FROM Users WHERE Username = ?");
	        st.setString(1, username);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            
	            userExists = true;
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("MYSQL not found!");
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    return userExists;
	}

	public void insertNewFavorite(String username, String r_name) throws ClassNotFoundException {
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("INSERT INTO Favorites (User_ID, Restaurant_ID) " +
	                   "SELECT Users.User_ID, Restaurants.Restaurant_ID " +
	                   "FROM Users " +
	                   "JOIN Restaurants ON Restaurants.Name = ? " +
	                   "WHERE Users.Username = ?;");
	        st.setString(1, r_name);
	        st.setString(2, username);
	        st.executeUpdate();
	        System.out.println(r_name);
	    } catch (SQLException e) {
	        
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	       
	        System.out.println("MYSQL not found!");
	    } finally {
	        
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	    }
	    
	}
	
public void removeNewReservation(String username, String r_name) throws ClassNotFoundException {
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");
	        String uid = getUserID(username);
	        String rid = getRestaurantID(r_name);
	        st = conn.prepareStatement("DELETE FROM Reservations WHERE User_ID = ? AND Restaurant_ID = ?;");
	        
	        st.setString(2, rid);
	        st.setString(1, uid);
	        st.executeUpdate();
	    } catch (SQLException e) {
	        
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	       
	        System.out.println("MYSQL not found!");
	    } finally {
	        
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	    }
	    
	}

public void insertNewReservation(String username, String r_name, String date, String time, String desc) throws ClassNotFoundException {
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");
	        String uid = getUserID(username);
	        String rid = getRestaurantID(r_name);
	        st = conn.prepareStatement("INSERT INTO Reservations (User_ID, Restaurant_ID, Time, Date, Description) VALUES (?, ?, ?, ?, ?);");
	        st.setString(2, rid);
	        System.out.println(rid + ", " + uid);
	        st.setString(1, uid); 
	        st.setString(3, time);
	        st.setString(4, date);
	        st.setString(5, desc);
	        System.out.println(st.toString());
	        st.executeUpdate();
	        System.out.println(r_name);
	    } catch (SQLException e) {
	        
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        
	        System.out.println("MYSQL not found!");
	    } finally {
	
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	    }
	    
	}
	
public void removeNewFavorite(String username, String r_name) throws ClassNotFoundException {
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("DELETE FROM Favorites " +
	                   "WHERE User_ID = (SELECT User_ID FROM Users WHERE Username = ?) " +
	                   "AND Restaurant_ID = (SELECT Restaurant_ID FROM Restaurants WHERE Name = ?);");

	        st.setString(2, r_name);
	        st.setString(1, username);
	        st.executeUpdate();
	    } catch (SQLException e) {
	        
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        
	        System.out.println("MYSQL not found!");
	    } finally {
	        
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	    }
	    
	}

	public void insertNewRestaurant(Restaurant r) throws ClassNotFoundException {
	    try {
	    	ResultSet rs = null;
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("SELECT * FROM Restaurants WHERE Name = ?");
	        st.setString(1, r.name);
	        rs = st.executeQuery();

	        if (rs.next()) {
	           return; //restaurnt exists
	        } 
	        st = conn.prepareStatement("INSERT INTO Restaurants (Url, Img_url, Name, Address, Rating, Phone, Cuisine, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	        st.setString(1, r.url);
	        st.setString(2, r.img_url);
	        st.setString(3, r.name);
	        st.setString(4, r.location);
	        st.setDouble(5, r.rating);
	        st.setString(6, r.phone);
	        st.setString(7, r.cuisine);
	        st.setString(8, r.price);
	        st.executeUpdate();
	    } catch (SQLException e) {
	       
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	       
	        System.out.println("MYSQL not found!");
	    } finally {
	     
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            
	            System.out.println(e.getMessage());
	        }
	    }
	}

  public void insertNewUser(String username, String password, String email) throws ClassNotFoundException  
  {
	  
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");
		
		st = conn.prepareStatement("INSERT INTO Users (Username, UserPassword, Email) VALUES (?, ?, ?);");
		st.setString(1, username);
		st.setString(2, password);
		st.setString(3, email);
		st.executeUpdate();
	} catch (SQLException e) {
		
		System.out.println(e.getMessage());		
	} catch (ClassNotFoundException e) {
		System.out.println("MYSQL not found!");		
	}
	  finally 
	  {
		  try 
		  {
			  if(st!=null) 
			  {
				  
				  st.close();
			  }
			  if(conn!=null) 
			  {
				  conn.close();
			  }
		  } catch (SQLException e)
		  {
			  System.out.println(e.getMessage());	
		  }
	  }
  }
  public String getUserID(String username) throws ClassNotFoundException {
      
	  
      String userID = "-1"; 

      try {
    	  Class.forName("com.mysql.cj.jdbc.Driver");
    	  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");
          String sql = "SELECT User_ID FROM Users WHERE Username = ?";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setString(1, username);

          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
              userID = resultSet.getString("User_ID");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      System.out.println(userID);
      return userID;
  }
  
public String getRestaurantID(String rname) throws ClassNotFoundException {
      
	  
      String rID = "-1"; 

      try {
    	  Class.forName("com.mysql.cj.jdbc.Driver");
    	  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");
          String sql = "SELECT Restaurant_ID FROM Restaurants WHERE Name = ?";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setString(1, rname);

          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
              rID = resultSet.getString("Restaurant_ID");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      System.out.println(rID);
      return rID;
  }
public String getReservationRestaurants(String userID) 
{
	  String jsonString = "";
	  ResultSet resultSet = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("SELECT r.Restaurant_ID, r.time, r.date, r.description, res.* FROM reservations r JOIN Restaurants res ON r.Restaurant_ID = res.Restaurant_ID WHERE r.User_ID = ?;");
	        st.setString(1, userID);
	        resultSet = st.executeQuery();
	        
	        List<Map<String, Object>> restaurantsList = new ArrayList<>();
	        
          while (resultSet.next()) {
          	//System.out.println("had_next");
          	Map<String, Object> restaurantMap = new HashMap<>();
          	
              restaurantMap.put("id", resultSet.getString("Restaurant_ID"));
              restaurantMap.put("name", resultSet.getString("Name"));
              restaurantMap.put("address", resultSet.getString("Address"));
              restaurantMap.put("img_url", resultSet.getString("Img_url"));
              restaurantMap.put("url", resultSet.getString("Url"));
              restaurantMap.put("cuisine", resultSet.getString("Cuisine"));
              restaurantMap.put("phone", resultSet.getString("Phone"));
              restaurantMap.put("rating", resultSet.getDouble("Rating"));
              restaurantMap.put("price", resultSet.getString("Price"));
              restaurantMap.put("time", resultSet.getString("Time"));
              System.out.println((resultSet.getString("Time")));
              restaurantMap.put("date", resultSet.getString("Date"));
              restaurantMap.put("desc", resultSet.getString("Description"));
              restaurantsList.add(restaurantMap);
          }
          Map<String, Object> businesses = new HashMap<>();
          businesses.put("businesses", restaurantsList);

          /*
          jsonString = businesses.toString();
          String input = jsonString;
          input = input.replace("{businesses=", "[");
          input = input.substring(0, input.length() - 1) + "]";
          System.out.println(input);
          */
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          Object jsonObject = gson.toJson(businesses);

          jsonString = jsonObject.toString();
          
          
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("MYSQL not found!");
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    return jsonString;
	  
}
  public String getFavoriteRestaurants(String userID) 
  {
	  String jsonString = "";
	  ResultSet resultSet = null;
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

	        st = conn.prepareStatement("SELECT r.* FROM Restaurants r INNER JOIN Favorites f ON r.Restaurant_ID = f.Restaurant_ID WHERE f.User_ID = ?;");
	        st.setString(1, userID);
	        resultSet = st.executeQuery();
	        
	        List<Map<String, Object>> restaurantsList = new ArrayList<>();
	        
            while (resultSet.next()) {
            	System.out.println("had_next");
            	Map<String, Object> restaurantMap = new HashMap<>();
            	
                restaurantMap.put("id", resultSet.getString("Restaurant_ID"));
                restaurantMap.put("name", resultSet.getString("Name"));
                restaurantMap.put("address", resultSet.getString("Address"));
                restaurantMap.put("img_url", resultSet.getString("Img_url"));
                restaurantMap.put("url", resultSet.getString("Url"));
                restaurantMap.put("cuisine", resultSet.getString("Cuisine"));
                restaurantMap.put("phone", resultSet.getString("Phone"));
                restaurantMap.put("rating", resultSet.getDouble("Rating"));
                restaurantMap.put("price", resultSet.getString("Price"));

                restaurantsList.add(restaurantMap);
            }
            Map<String, Object> businesses = new HashMap<>();
            businesses.put("businesses", restaurantsList);

            /*
            jsonString = businesses.toString();
            String input = jsonString;
            input = input.replace("{businesses=", "[");
            input = input.substring(0, input.length() - 1) + "]";
            System.out.println(input);
            */
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object jsonObject = gson.toJson(businesses);

            jsonString = jsonObject.toString();
            
            
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.out.println("MYSQL not found!");
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    return jsonString;
	  
  }
public boolean validateUser(String username, String password) {
	boolean valid = false;
    ResultSet rs = null;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/JoesTable?user=root&password=conthoSQL123");

        st = conn.prepareStatement("SELECT Username, UserPassword FROM JoesTable.Users WHERE Username = ? AND UserPassword = ?");
        st.setString(1, username);
        st.setString(2, password);
        rs = st.executeQuery();

        if (rs.next()) {
           
            valid = true;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("MYSQL not found!");
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    return valid;
}
}
