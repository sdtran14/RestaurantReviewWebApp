package sdtran_CSCI201_Assignment4;

import java.util.List;

public class Restaurant {
    public String name;
    public String location;
    public double rating;
    public String phone;
    public String cuisine;
    public String price;
    public String img_url;
    public String url;

    public Restaurant(String u, String iu, String name, String address, double rating, String phoneNumber, String cuisine, String price) {
        this.name = name;
        this.location = address;
        this.rating = rating;
        this.phone = phoneNumber;
        this.cuisine = cuisine;
        this.price = price;
        img_url = iu;
        url = u;
    }

}