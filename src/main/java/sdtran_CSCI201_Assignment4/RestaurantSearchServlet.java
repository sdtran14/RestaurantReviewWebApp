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


@WebServlet("/RestaurantSearchServlet")
public class RestaurantSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String, Restaurant> searchMap;
	public static void main(String[] args) 
	{
		String jsonstring = "{\"businesses\": [{\"id\": \"MlmcOkwaNnxl3Zuk6HsPCQ\", \"alias\": \"slurpin-ramen-bar-los-angeles-los-angeles\", \"name\": \"Slurpin' Ramen Bar - Los Angeles\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/axO_FH4VwDYcPQOuabFi6g/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/slurpin-ramen-bar-los-angeles-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 5647, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.0573614429986, \"longitude\": -118.306769744705}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3500 W 8th St\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90005\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3500 W 8th St\", \"Los Angeles, CA 90005\"]}, \"phone\": \"+12133888607\", \"display_phone\": \"(213) 388-8607\", \"distance\": 4031.0012603677787}, {\"id\": \"E4W2T89vm4hmBwk39EoUuw\", \"alias\": \"tatsu-ramen-los-angeles-4\", \"name\": \"Tatsu Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/ocrE0sRjOB-ZuMDsJ7YhKw/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/tatsu-ramen-los-angeles-4?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 4133, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0836332933004, \"longitude\": -118.34466214165636}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"7111 Melrose Ave\", \"address2\": \"\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90046\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"7111 Melrose Ave\", \"Los Angeles, CA 90046\"]}, \"phone\": \"+13238799332\", \"display_phone\": \"(323) 879-9332\", \"distance\": 7681.608751274401}, {\"id\": \"0kXAJeO1rL4zxa8sa7zL3g\", \"alias\": \"iki-ramen-los-angeles-5\", \"name\": \"Iki Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/g7cwgu-fRZyJTdlfpsWhDg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/iki-ramen-los-angeles-5?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 744, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"izakaya\", \"title\": \"Izakaya\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.05844, \"longitude\": -118.3087}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"740 S Western Ave\", \"address2\": \"Ste 116\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90005\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"740 S Western Ave\", \"Ste 116\", \"Los Angeles, CA 90005\"]}, \"phone\": \"+14243357749\", \"display_phone\": \"(424) 335-7749\", \"distance\": 4155.302520867381}, {\"id\": \"iSZpZgVnASwEmlq0DORY2A\", \"alias\": \"daikokuya-little-tokyo-los-angeles\", \"name\": \"Daikokuya Little Tokyo\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/I48pvsYsOvZdG6Mr3364Kw/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/daikokuya-little-tokyo-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 9499, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.04997674306073, \"longitude\": -118.24009370981828}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"327 E 1st St\", \"address2\": \"\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90012\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"327 E 1st St\", \"Los Angeles, CA 90012\"]}, \"phone\": \"+12136261680\", \"display_phone\": \"(213) 626-1680\", \"distance\": 7118.134659798502}, {\"id\": \"mExUrNAYg8FSI8s_whNJ4A\", \"alias\": \"momota-ramen-house-los-angeles-8\", \"name\": \"Momota Ramen House\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/TCzdtTAP-1t9hdIz1P7Pqg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/momota-ramen-house-los-angeles-8?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 336, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0247683, \"longitude\": -118.2783463}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3019 S Figueroa St\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3019 S Figueroa St\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12139735488\", \"display_phone\": \"(213) 973-5488\", \"distance\": 2842.495302306501}, {\"id\": \"-ZXiF1DfjUt2_e-OkjM_Tg\", \"alias\": \"ramen-kenjo-los-angeles-2\", \"name\": \"Ramen Kenjo\", \"image_url\": \"https://s3-media1.fl.yelpcdn.com/bphoto/gi61UpIxhU_BUjXSG1pQZA/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ramen-kenjo-los-angeles-2?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 137, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}, {\"alias\": \"soup\", \"title\": \"Soup\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.024655035740786, \"longitude\": -118.28567036322497}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"929 W Jefferson Blvd\", \"address2\": \"Ste 1630\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90089\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"929 W Jefferson Blvd\", \"Ste 1630\", \"Los Angeles, CA 90089\"]}, \"phone\": \"+12135365922\", \"display_phone\": \"(213) 536-5922\", \"distance\": 2191.4850435363333}, {\"id\": \"lYbzuO5xHStf_elUKAudvQ\", \"alias\": \"ramen-melrose-los-angeles\", \"name\": \"Ramen Melrose\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/Lt-JJkpOy_3H1Cl9fF3b3g/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ramen-melrose-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 331, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.08316, \"longitude\": -118.32627}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"5784 Melrose Ave\", \"address2\": \"\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90038\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"5784 Melrose Ave\", \"Los Angeles, CA 90038\"]}, \"phone\": \"+13236457766\", \"display_phone\": \"(323) 645-7766\", \"distance\": 7079.375274433523}, {\"id\": \"3IoIViOW1W38eQOPWm0_DA\", \"alias\": \"ebaes-los-angeles\", \"name\": \"Ebaes\", \"image_url\": \"https://s3-media4.fl.yelpcdn.com/bphoto/WbAcFa_4k4R2QYbOvxeutQ/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ebaes-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 980, \"categories\": [{\"alias\": \"tapasmallplates\", \"title\": \"Tapas/Small Plates\"}, {\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0344536304474, \"longitude\": -118.283669427037}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"2314 S Union Ave\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"2314 S Union Ave\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12137476888\", \"display_phone\": \"(213) 747-6888\", \"distance\": 2767.010908955176}, {\"id\": \"ifDisux3aAxV5LJOdWfZew\", \"alias\": \"cafe-mukbang-los-angeles\", \"name\": \"Cafe Mukbang\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/dFV3epzh6Du2FqkBNuf3OQ/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/cafe-mukbang-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 72, \"categories\": [{\"alias\": \"korean\", \"title\": \"Korean\"}, {\"alias\": \"asianfusion\", \"title\": \"Asian Fusion\"}, {\"alias\": \"japanese\", \"title\": \"Japanese\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.02242, \"longitude\": -118.2917}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3619 S Vermont Ave\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3619 S Vermont Ave\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12132058961\", \"display_phone\": \"(213) 205-8961\", \"distance\": 1607.03272273795}, {\"id\": \"s6xL22TfNCO_SJYNARDsNg\", \"alias\": \"ten-ramen-los-angeles\", \"name\": \"Ten Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/KL-kLr60BkWp_sosUcrUNg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ten-ramen-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 813, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 3.5, \"coordinates\": {\"latitude\": 34.06345, \"longitude\": -118.29487}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3324 W 6th St\", \"address2\": \"Ste A\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90020\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3324 W 6th St\", \"Ste A\", \"Los Angeles, CA 90020\"]}, \"phone\": \"+12139085823\", \"display_phone\": \"(213) 908-5823\", \"distance\": 4873.962412487068}], \"total\": 739, \"region\": {\"center\": {\"longitude\": -118.30907265625, \"latitude\": 34.02116}}}";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//JsonObject js = gson.fromJson(response.toString(), JsonObject.class);
		JsonObject jobj = gson.fromJson(jsonstring, JsonObject.class);
		JsonArray jarr = jobj.getAsJsonArray("businesses");
		System.out.println(jarr.size());
		for(JsonElement x : jarr) 
		{
			JsonObject js = x.getAsJsonObject();
			String name = js.get("name").getAsString();
			String img_url = js.get("image_url").getAsString();
			String url = "https://www.yelp.com/biz/" + js.get("alias").getAsString();
			String phoneNumber = js.get("display_phone").getAsString();
			Double rating = js.get("rating").getAsDouble();
			String price = js.get("price").getAsString();
			
			JsonArray locationarr = js.get("location").getAsJsonObject().get("display_address").getAsJsonArray();
			String address = "";
			for(JsonElement i : locationarr) 
			{
				String cl = i.toString();
				address += cl.substring(1, cl.length()-1) + " ";
			}
			address = address.substring(0, address.length()-1);
			System.out.println(address);
			String cusines = js.get("categories").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsString();
			
			
			
			Restaurant curr = new Restaurant(url, img_url, name, address, rating, phoneNumber, cusines, price);
			//price location cuisine
			System.out.println();
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String r_name = request.getParameter("deat");
		PrintWriter out = response.getWriter();
		Restaurant rest = searchMap.get(r_name);
		System.out.println("post size" + searchMap.size());
		System.out.println(r_name);
		JDBCConnector connector = new JDBCConnector();
		try {
			connector.insertNewRestaurant(rest);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		out.write("sucess");
		out.flush();
		out.close();
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

        String API_KEY = "APIKEY";
		PrintWriter out = resp.getWriter();
		String ulat = req.getParameter("lat");
		String ulon = req.getParameter("lng");
		String sortby = req.getParameter("sortby");
		
		String rname = req.getParameter("terms").replaceAll(" ", "%20");
		
		String requestURL = "https://api.yelp.com/v3/businesses/search?"+
							"latitude=" + ulat +
							"&longitude=" +ulon + 
							"&term="+ rname + 
							"&sort_by="+ sortby + 
							"&limit=10";
		
		URL apiUrl;
		searchMap = new HashMap<String, Restaurant>();
		try {
			apiUrl = new URL(requestURL);
			HttpURLConnection httpApi;
			try {
				
				httpApi = (HttpURLConnection) apiUrl.openConnection();
				httpApi.setRequestMethod("GET");
				httpApi.setRequestProperty("accept", "application/json");
				httpApi.setRequestProperty("Authorization", "Bearer " + API_KEY);
				
				int responseCode = httpApi.getResponseCode();
				System.out.println("GET Response Code :: " + responseCode);
				
				if (responseCode == HttpURLConnection.HTTP_OK) { // success
				
					BufferedReader in = new BufferedReader(new InputStreamReader(httpApi.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					// print result
					System.out.println(response.toString());
					
					// response = "{\"businesses\": [{\"id\": \"MlmcOkwaNnxl3Zuk6HsPCQ\", \"alias\": \"slurpin-ramen-bar-los-angeles-los-angeles\", \"name\": \"Slurpin' Ramen Bar - Los Angeles\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/axO_FH4VwDYcPQOuabFi6g/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/slurpin-ramen-bar-los-angeles-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 5647, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.0573614429986, \"longitude\": -118.306769744705}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3500 W 8th St\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90005\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3500 W 8th St\", \"Los Angeles, CA 90005\"]}, \"phone\": \"+12133888607\", \"display_phone\": \"(213) 388-8607\", \"distance\": 4031.0012603677787}, {\"id\": \"E4W2T89vm4hmBwk39EoUuw\", \"alias\": \"tatsu-ramen-los-angeles-4\", \"name\": \"Tatsu Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/ocrE0sRjOB-ZuMDsJ7YhKw/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/tatsu-ramen-los-angeles-4?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 4133, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0836332933004, \"longitude\": -118.34466214165636}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"7111 Melrose Ave\", \"address2\": \"\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90046\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"7111 Melrose Ave\", \"Los Angeles, CA 90046\"]}, \"phone\": \"+13238799332\", \"display_phone\": \"(323) 879-9332\", \"distance\": 7681.608751274401}, {\"id\": \"0kXAJeO1rL4zxa8sa7zL3g\", \"alias\": \"iki-ramen-los-angeles-5\", \"name\": \"Iki Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/g7cwgu-fRZyJTdlfpsWhDg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/iki-ramen-los-angeles-5?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 744, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"izakaya\", \"title\": \"Izakaya\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.05844, \"longitude\": -118.3087}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"740 S Western Ave\", \"address2\": \"Ste 116\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90005\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"740 S Western Ave\", \"Ste 116\", \"Los Angeles, CA 90005\"]}, \"phone\": \"+14243357749\", \"display_phone\": \"(424) 335-7749\", \"distance\": 4155.302520867381}, {\"id\": \"iSZpZgVnASwEmlq0DORY2A\", \"alias\": \"daikokuya-little-tokyo-los-angeles\", \"name\": \"Daikokuya Little Tokyo\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/I48pvsYsOvZdG6Mr3364Kw/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/daikokuya-little-tokyo-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 9499, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.04997674306073, \"longitude\": -118.24009370981828}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"327 E 1st St\", \"address2\": \"\", \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90012\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"327 E 1st St\", \"Los Angeles, CA 90012\"]}, \"phone\": \"+12136261680\", \"display_phone\": \"(213) 626-1680\", \"distance\": 7118.134659798502}, {\"id\": \"mExUrNAYg8FSI8s_whNJ4A\", \"alias\": \"momota-ramen-house-los-angeles-8\", \"name\": \"Momota Ramen House\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/TCzdtTAP-1t9hdIz1P7Pqg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/momota-ramen-house-los-angeles-8?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 336, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0247683, \"longitude\": -118.2783463}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3019 S Figueroa St\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3019 S Figueroa St\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12139735488\", \"display_phone\": \"(213) 973-5488\", \"distance\": 2842.495302306501}, {\"id\": \"-ZXiF1DfjUt2_e-OkjM_Tg\", \"alias\": \"ramen-kenjo-los-angeles-2\", \"name\": \"Ramen Kenjo\", \"image_url\": \"https://s3-media1.fl.yelpcdn.com/bphoto/gi61UpIxhU_BUjXSG1pQZA/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ramen-kenjo-los-angeles-2?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 137, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}, {\"alias\": \"soup\", \"title\": \"Soup\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.024655035740786, \"longitude\": -118.28567036322497}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"929 W Jefferson Blvd\", \"address2\": \"Ste 1630\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90089\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"929 W Jefferson Blvd\", \"Ste 1630\", \"Los Angeles, CA 90089\"]}, \"phone\": \"+12135365922\", \"display_phone\": \"(213) 536-5922\", \"distance\": 2191.4850435363333}, {\"id\": \"lYbzuO5xHStf_elUKAudvQ\", \"alias\": \"ramen-melrose-los-angeles\", \"name\": \"Ramen Melrose\", \"image_url\": \"https://s3-media3.fl.yelpcdn.com/bphoto/Lt-JJkpOy_3H1Cl9fF3b3g/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ramen-melrose-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 331, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"noodles\", \"title\": \"Noodles\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.08316, \"longitude\": -118.32627}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"5784 Melrose Ave\", \"address2\": \"\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90038\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"5784 Melrose Ave\", \"Los Angeles, CA 90038\"]}, \"phone\": \"+13236457766\", \"display_phone\": \"(323) 645-7766\", \"distance\": 7079.375274433523}, {\"id\": \"3IoIViOW1W38eQOPWm0_DA\", \"alias\": \"ebaes-los-angeles\", \"name\": \"Ebaes\", \"image_url\": \"https://s3-media4.fl.yelpcdn.com/bphoto/WbAcFa_4k4R2QYbOvxeutQ/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ebaes-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 980, \"categories\": [{\"alias\": \"tapasmallplates\", \"title\": \"Tapas/Small Plates\"}, {\"alias\": \"ramen\", \"title\": \"Ramen\"}, {\"alias\": \"sushi\", \"title\": \"Sushi Bars\"}], \"rating\": 4.0, \"coordinates\": {\"latitude\": 34.0344536304474, \"longitude\": -118.283669427037}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"2314 S Union Ave\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"2314 S Union Ave\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12137476888\", \"display_phone\": \"(213) 747-6888\", \"distance\": 2767.010908955176}, {\"id\": \"ifDisux3aAxV5LJOdWfZew\", \"alias\": \"cafe-mukbang-los-angeles\", \"name\": \"Cafe Mukbang\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/dFV3epzh6Du2FqkBNuf3OQ/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/cafe-mukbang-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 72, \"categories\": [{\"alias\": \"korean\", \"title\": \"Korean\"}, {\"alias\": \"asianfusion\", \"title\": \"Asian Fusion\"}, {\"alias\": \"japanese\", \"title\": \"Japanese\"}], \"rating\": 4.5, \"coordinates\": {\"latitude\": 34.02242, \"longitude\": -118.2917}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3619 S Vermont Ave\", \"address2\": null, \"address3\": \"\", \"city\": \"Los Angeles\", \"zip_code\": \"90007\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3619 S Vermont Ave\", \"Los Angeles, CA 90007\"]}, \"phone\": \"+12132058961\", \"display_phone\": \"(213) 205-8961\", \"distance\": 1607.03272273795}, {\"id\": \"s6xL22TfNCO_SJYNARDsNg\", \"alias\": \"ten-ramen-los-angeles\", \"name\": \"Ten Ramen\", \"image_url\": \"https://s3-media2.fl.yelpcdn.com/bphoto/KL-kLr60BkWp_sosUcrUNg/o.jpg\", \"is_closed\": false, \"url\": \"https://www.yelp.com/biz/ten-ramen-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw\", \"review_count\": 813, \"categories\": [{\"alias\": \"ramen\", \"title\": \"Ramen\"}], \"rating\": 3.5, \"coordinates\": {\"latitude\": 34.06345, \"longitude\": -118.29487}, \"transactions\": [\"pickup\", \"delivery\"], \"price\": \"$$\", \"location\": {\"address1\": \"3324 W 6th St\", \"address2\": \"Ste A\", \"address3\": null, \"city\": \"Los Angeles\", \"zip_code\": \"90020\", \"country\": \"US\", \"state\": \"CA\", \"display_address\": [\"3324 W 6th St\", \"Ste A\", \"Los Angeles, CA 90020\"]}, \"phone\": \"+12139085823\", \"display_phone\": \"(213) 908-5823\", \"distance\": 4873.962412487068}], \"total\": 739, \"region\": {\"center\": {\"longitude\": -118.30907265625, \"latitude\": 34.02116}}}";
					
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					JsonObject jobj = gson.fromJson(response.toString(), JsonObject.class);
					JsonArray jarr = jobj.getAsJsonArray("businesses");
					System.out.println(jarr.size());
					for(JsonElement x : jarr) 
					{
						JsonObject js = x.getAsJsonObject();
						String name = js.get("name").getAsString();
						String img_url = js.get("image_url").getAsString();
						String url = "https://www.yelp.com/biz/" + js.get("alias").getAsString();
						String phoneNumber = js.get("display_phone").getAsString();
						Double rating = 0.0;
						String price = "N/A";
						
						try 
						{
							rating =js.get("rating").getAsDouble();
							price = js.get("price").getAsString();
						} catch(Exception e)
						{
							
						}
						
						
						JsonArray locationarr = js.get("location").getAsJsonObject().get("display_address").getAsJsonArray();
						String address = "";
						for(JsonElement i : locationarr) 
						{
							String cl = i.toString();
							address += cl.substring(1, cl.length()-1) + " ";
						}
						address = address.substring(0, address.length()-1);
						//System.out.println(address);
						String cusines = js.get("categories").getAsJsonArray().get(0).getAsJsonObject().get("title").getAsString();
						
						
						Restaurant curr = new Restaurant(url, img_url, name, address, rating, phoneNumber, cusines, price);
						searchMap.put(name, curr);
						//price location cuisine
						System.out.println();
					}
					System.out.println("get size" + searchMap.size());
					out.print(response.toString());
					out.flush();
					
				} else {
					BufferedReader in = new BufferedReader(new InputStreamReader(httpApi.getErrorStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					// print result
					System.out.println(response.toString());
					
					System.out.println("GET request did not work.");
				} 
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		out.close();
		
    }
}
