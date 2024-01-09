
document.getElementById("showMapButton").addEventListener("click", function() {
            document.getElementById("map-overlay").style.display = "block";
            console.log("here")
            initMap();
        });
        
document.addEventListener('click', function(event) {
            if ((event.target.id === 'map-overlay')) {
                document.getElementById('map-overlay').style.display = 'none';
               
            }
        });
        

const username = localStorage.getItem('username');
const SearchForm = document.getElementById("SearchForm");
const ReserveForm = document.getElementById("reserveForm");

ReserveForm.addEventListener('submit', function(event) {
	
	event.preventDefault();
	reserve();

});



let map;
let marker;
async function initMap() {

  const position = { lat: 34.02116, lng: -118.2871 };
  
  map = new google.maps.Map(document.getElementById('map'), {
                center: position,
                zoom: 6 
                
            });


  marker = new google.maps.Marker({
    map: map,
    position: position,
    draggable: true,
    title: "Your Location"
  });
  
  
  
   google.maps.event.addListener(map, 'click', function(event){
     document.getElementById("latitude").value = event.latLng.lat();
     document.getElementById("longitude").value = event.latLng.lng();
     document.getElementById('map-overlay').style.display = 'none';
     });
}



function search() 
{
	console.log("searched:");
	const search_terms = document.getElementById("restaurantName").value;
	const sortby = document.searchForm.searchOption.value;
	console.log(sortby);
	const lat = document.getElementById("latitude").value;
	const lng = document.getElementById("longitude").value;
	
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.open("GET", "RestaurantSearchServlet?terms="+search_terms+"&sortby=" + sortby +"&lat=" + lat +"&lng=" + lng, true);
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log(JSON.parse(this.responseText).businesses);
		const searchDetailsContainer = document.getElementById('searchResults');
		searchDetailsContainer.innerHTML = '';
		const search_hid = document.createElement('div');
		search_hid.id = 'search_results_hide';
		document.getElementById('res_text').textContent = 'Results for "' + search_terms+'"';
		document.getElementById('reserve').style.display = "none";
	document.getElementById('favor').style.display = "none";
		searchDetailsContainer.appendChild(search_hid);
		displaySearchResults(JSON.parse(this.responseText).businesses);
		
		
	}
	xhttp.send();


}
var current_deat = "";

function reserve() 
{
	console.log("res:" + current_deat);
	
	
	const res_button = document.getElementById("reserve");
	//check if faved 
	res_button.textContent = "Remove Reservation";
	
	var xhttp = new XMLHttpRequest();
	const date = document.getElementById("reservationDate").value;
	const time = document.getElementById("reservationTime").value;
	const desc = document.getElementById("reservationDesc").value;
	
	xhttp.open("POST", "RestaurantSearchServlet", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log("loaded");
		
		var xhttp2 = new XMLHttpRequest();
		xhttp2.open("POST", "ReservationsServlet", true);
		xhttp2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhttp2.onload = function() {
			console.log("loaded");
			closeReservations();
		}
		const act = "put";
		xhttp2.send("deat="+current_deat+"&username=" + username +"&action=" + act + "&date="+date +"&time="+time+"&desc="+desc);	
	}
	xhttp.send("deat="+current_deat);
	

}

function fave() 
{
	console.log("fav:" + current_deat);
	
	
	const fave_button = document.getElementById("favor");
	//check if faved 
	fave_button.textContent = "Remove from Favorites";
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.open("POST", "RestaurantSearchServlet", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log("loaded");
		
		var xhttp2 = new XMLHttpRequest();
		xhttp2.open("POST", "FavoritesServlet", true);
		xhttp2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhttp2.onload = function() {
			console.log("loaded");
			
		}
		const act = "put";
		xhttp2.send("deat="+current_deat+"&username=" + username +"&action=" + act);	
	}
	xhttp.send("deat="+current_deat);
	

}

/*
function displaySearchResults(searchResults) {
    // Get the container to display search results
    const searchResultsContainer = document.getElementById('searchResults');

    // Clear previous results
    searchResultsContainer.innerHTML = '';

    // Create and append list elements for each search result
    searchResults.forEach(result => {
        const listItem = document.createElement('div');
        listItem.classList.add('search-result');

		const image = document.createElement('img');
        image.src = result.image_url; // Replace with your image URL
        image.classList.add('restaurant-image');
        
         const content = document.createElement('div');
        content.classList.add('result-content');
        
        const name = document.createElement('h3');
        name.textContent = result.name;

        const address = document.createElement('p');
        address.textContent = result.location.display_address.join(' ');;

        content.appendChild(name);
        content.appendChild(address);
        
         listItem.appendChild(image);
        listItem.appendChild(content);
        
        searchResultsContainer.appendChild(listItem);
        
        const line = document.createElement('hr');
        listItem.appendChild(line);
    });
}*/
function displaySearchResults(searchResults) {
    const searchResultsContainer = document.getElementById('search_results_hide');
    const searchDetailsContainer = document.getElementById('searchResults');
    closeReservations();
    document.getElementById("banner").style.display = 'none';
    searchResultsContainer.innerHTML = '';

    searchResults.forEach(result => {
		
		const deatItem = document.createElement('div');
		deatItem.id = result.name;
		deatItem.classList.add('search-result');
		 
        const listItem = document.createElement('div');
        listItem.classList.add('search-result');
        
        const line = document.createElement('hr');
        listItem.appendChild(line.cloneNode());
        deatItem.appendChild(line.cloneNode());

        const imageContainer = document.createElement('div');
        imageContainer.classList.add('image-container');

        const image = document.createElement('img');
        image.src = result.image_url; 
        image.classList.add('restaurant-image');
        image.addEventListener('click', function() {
	
		    displayDetails(result.name);

		});
		
		
		const imageContainerDeat = document.createElement('div');
        imageContainerDeat.classList.add('image-container');

        const imageDeat = document.createElement('img');
        imageDeat.src = result.image_url; 
        imageDeat.classList.add('restaurant-image');
        

        const content = document.createElement('div');
        content.classList.add('result-content');

        const name = document.createElement('p');
        name.textContent = result.name;
        name.style.fontSize = "1.9vw";

        const address = document.createElement('p');
        address.textContent = result.location.display_address.join(' ');


 		const link = document.createElement('a');
 		link.textContent = "https://www.yelp.com/biz/" + result.alias;
 		link.href = "https://www.yelp.com/biz/" + result.alias;
 		link.classList.add("link");
 		link.style.textDecoration = "none";
 		link.style.color = "dimgrey";
 		
        content.appendChild(name);
        content.appendChild(address);
        content.appendChild(link);
        
        const contentDeat = document.createElement('div');
        contentDeat.classList.add('deat-content');
        
        const addressDeat = document.createElement('p');
		addressDeat.textContent = "Address: " + result.location.display_address.join(' ');
		contentDeat.appendChild(addressDeat);
		
		const phoneDeat = document.createElement('p');
		phoneDeat.textContent = "Phone No. " + result.display_phone;
		contentDeat.appendChild(phoneDeat);
		
		const cuisineDeat = document.createElement('p');
		cuisineDeat.textContent = "Cuisine: " + result.categories[0].title;
		contentDeat.appendChild(cuisineDeat);
		
		const priceDeat = document.createElement('p');
		priceDeat.textContent = "Price: " + result.price;
		contentDeat.appendChild(priceDeat);
		
		const ratingDeat = document.createElement('p');
		const rating = result.rating
		let starsHTML = '';
  
	 
	  for (let i = 1; i <= 5; i++) {
	    if (rating >= i) {
	      starsHTML += '<i class="fa fa-star"></i>';
	    } else if (rating >= i - 0.5) {
	      starsHTML += '<i class="fa fa-star-half"></i>';
	    } else {
	     
	    }
	  }
		ratingDeat.innerHTML = "Rating: "+ starsHTML ;
		contentDeat.appendChild(ratingDeat);
		
		
		
        
        
        imageContainer.appendChild(image);
        imageContainerDeat.appendChild(imageDeat);
        listItem.appendChild(imageContainer);
       	deatItem.appendChild(imageContainerDeat);
       	
        listItem.appendChild(content);
        deatItem.appendChild(contentDeat);
        searchResultsContainer.appendChild(listItem);
        searchDetailsContainer.appendChild(deatItem);
        
        deatItem.style.display = 'none';

        
    });
}



var exRes = {"businesses": [{"id": "iSZpZgVnASwEmlq0DORY2A", "alias": "daikokuya-little-tokyo-los-angeles", "name": "Daikokuya Little Tokyo", "image_url": "https://s3-media3.fl.yelpcdn.com/bphoto/I48pvsYsOvZdG6Mr3364Kw/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/daikokuya-little-tokyo-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 9499, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "noodles", "title": "Noodles"}], "rating": 4.0, "coordinates": {"latitude": 34.04997674306073, "longitude": -118.24009370981828}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "327 E 1st St", "address2": "", "address3": "", "city": "Los Angeles", "zip_code": "90012", "country": "US", "state": "CA", "display_address": ["327 E 1st St", "Los Angeles, CA 90012"]}, "phone": "+12136261680", "display_phone": "(213) 626-1680", "distance": 3948.2220406518754}, {"id": "mExUrNAYg8FSI8s_whNJ4A", "alias": "momota-ramen-house-los-angeles-8", "name": "Momota Ramen House", "image_url": "https://s3-media3.fl.yelpcdn.com/bphoto/TCzdtTAP-1t9hdIz1P7Pqg/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/momota-ramen-house-los-angeles-8?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 336, "categories": [{"alias": "ramen", "title": "Ramen"}], "rating": 4.0, "coordinates": {"latitude": 34.0247683, "longitude": -118.2783463}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "3019 S Figueroa St", "address2": null, "address3": "", "city": "Los Angeles", "zip_code": "90007", "country": "US", "state": "CA", "display_address": ["3019 S Figueroa St", "Los Angeles, CA 90007"]}, "phone": "+12139735488", "display_phone": "(213) 973-5488", "distance": 1303.0835233829766}, {"id": "MlmcOkwaNnxl3Zuk6HsPCQ", "alias": "slurpin-ramen-bar-los-angeles-los-angeles", "name": "Slurpin' Ramen Bar - Los Angeles", "image_url": "https://s3-media2.fl.yelpcdn.com/bphoto/axO_FH4VwDYcPQOuabFi6g/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/slurpin-ramen-bar-los-angeles-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 5647, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "noodles", "title": "Noodles"}], "rating": 4.5, "coordinates": {"latitude": 34.0573614429986, "longitude": -118.306769744705}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "3500 W 8th St", "address2": null, "address3": "", "city": "Los Angeles", "zip_code": "90005", "country": "US", "state": "CA", "display_address": ["3500 W 8th St", "Los Angeles, CA 90005"]}, "phone": "+12133888607", "display_phone": "(213) 388-8607", "distance": 5561.171954021303}, {"id": "2GoGnKxRmKeL_D49pHlGKA", "alias": "jinya-ramen-bar-los-angeles-los-angeles", "name": "JINYA Ramen Bar - Los Angeles", "image_url": "https://s3-media2.fl.yelpcdn.com/bphoto/CYsmH8rZcx72i-BeSaAltw/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/jinya-ramen-bar-los-angeles-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 610, "categories": [{"alias": "ramen", "title": "Ramen"}], "rating": 4.0, "coordinates": {"latitude": 34.04868916422349, "longitude": -118.25741887140612}, "transactions": ["delivery"], "price": "$$", "location": {"address1": "700 Wilshire Blvd", "address2": "Ste B", "address3": null, "city": "Los Angeles", "zip_code": "90017", "country": "US", "state": "CA", "display_address": ["700 Wilshire Blvd", "Ste B", "Los Angeles, CA 90017"]}, "phone": "+12136283736", "display_phone": "(213) 628-3736", "distance": 3142.431298101098}, {"id": "STe3KancAkR7kqEL7wydow", "alias": "dtla-ramen-los-angeles", "name": "DTLA RAMEN", "image_url": "https://s3-media3.fl.yelpcdn.com/bphoto/rojPiLChPn1uf-r2Wk0Nqg/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/dtla-ramen-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 723, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "beerbar", "title": "Beer Bar"}, {"alias": "noodles", "title": "Noodles"}], "rating": 4.0, "coordinates": {"latitude": 34.04098, "longitude": -118.25696}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "952 S Broadway St", "address2": null, "address3": "", "city": "Los Angeles", "zip_code": "90015", "country": "US", "state": "CA", "display_address": ["952 S Broadway St", "Los Angeles, CA 90015"]}, "phone": "+12132657641", "display_phone": "(213) 265-7641", "distance": 2327.355592860925}, {"id": "CWM5nseG5en-lCW5ac-Vfw", "alias": "afuri-ramen-dumpling-los-angeles-5", "name": "Afuri Ramen Dumpling", "image_url": "https://s3-media4.fl.yelpcdn.com/bphoto/8kN6ldQeeJcP3E7uSZ38cQ/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/afuri-ramen-dumpling-los-angeles-5?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 499, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "noodles", "title": "Noodles"}], "rating": 4.0, "coordinates": {"latitude": 34.03517, "longitude": -118.23218}, "transactions": ["delivery", "restaurant_reservation", "pickup"], "price": "$$", "location": {"address1": "688 Mateo St", "address2": "", "address3": null, "city": "Los Angeles", "zip_code": "90021", "country": "US", "state": "CA", "display_address": ["688 Mateo St", "Los Angeles, CA 90021"]}, "phone": "+12132217206", "display_phone": "(213) 221-7206", "distance": 3413.446548822082}, {"id": "-ZXiF1DfjUt2_e-OkjM_Tg", "alias": "ramen-kenjo-los-angeles-2", "name": "Ramen Kenjo", "image_url": "https://s3-media1.fl.yelpcdn.com/bphoto/gi61UpIxhU_BUjXSG1pQZA/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/ramen-kenjo-los-angeles-2?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 137, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "noodles", "title": "Noodles"}, {"alias": "soup", "title": "Soup"}], "rating": 4.0, "coordinates": {"latitude": 34.024655035740786, "longitude": -118.28567036322497}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "929 W Jefferson Blvd", "address2": "Ste 1630", "address3": null, "city": "Los Angeles", "zip_code": "90089", "country": "US", "state": "CA", "display_address": ["929 W Jefferson Blvd", "Ste 1630", "Los Angeles, CA 90089"]}, "phone": "+12135365922", "display_phone": "(213) 536-5922", "distance": 1932.7165200060736}, {"id": "fxeuGYnoRWwm5aGDg1FRJA", "alias": "marugame-monzo-los-angeles-5", "name": "Marugame Monzo", "image_url": "https://s3-media1.fl.yelpcdn.com/bphoto/De7_sqX_0bvCqvuFv2WadQ/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/marugame-monzo-los-angeles-5?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 4624, "categories": [{"alias": "japanese", "title": "Japanese"}, {"alias": "noodles", "title": "Noodles"}, {"alias": "asianfusion", "title": "Asian Fusion"}], "rating": 4.5, "coordinates": {"latitude": 34.04994760027338, "longitude": -118.24004464723886}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "329 E 1st St", "address2": "", "address3": "", "city": "Los Angeles", "zip_code": "90012", "country": "US", "state": "CA", "display_address": ["329 E 1st St", "Los Angeles, CA 90012"]}, "phone": "+12133469762", "display_phone": "(213) 346-9762", "distance": 3948.2376172652125}, {"id": "3IoIViOW1W38eQOPWm0_DA", "alias": "ebaes-los-angeles", "name": "Ebaes", "image_url": "https://s3-media4.fl.yelpcdn.com/bphoto/WbAcFa_4k4R2QYbOvxeutQ/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/ebaes-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 980, "categories": [{"alias": "tapasmallplates", "title": "Tapas/Small Plates"}, {"alias": "ramen", "title": "Ramen"}, {"alias": "sushi", "title": "Sushi Bars"}], "rating": 4.0, "coordinates": {"latitude": 34.0344536304474, "longitude": -118.283669427037}, "transactions": ["delivery", "pickup"], "price": "$$", "location": {"address1": "2314 S Union Ave", "address2": null, "address3": "", "city": "Los Angeles", "zip_code": "90007", "country": "US", "state": "CA", "display_address": ["2314 S Union Ave", "Los Angeles, CA 90007"]}, "phone": "+12137476888", "display_phone": "(213) 747-6888", "distance": 2254.4431579761354}, {"id": "gSjHUDuEsamNwUOxE7iz8A", "alias": "shin-sen-gumi-hakata-ramen-express-downtown-la-los-angeles", "name": "Shin-Sen-Gumi Hakata Ramen Express - Downtown LA", "image_url": "https://s3-media2.fl.yelpcdn.com/bphoto/waicEtykLbv9dqTqBy7DWg/o.jpg", "is_closed": false, "url": "https://www.yelp.com/biz/shin-sen-gumi-hakata-ramen-express-downtown-la-los-angeles?adjust_creative=vGeZ2PG6SZoFvo7dHxOatw&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=vGeZ2PG6SZoFvo7dHxOatw", "review_count": 10, "categories": [{"alias": "ramen", "title": "Ramen"}, {"alias": "japacurry", "title": "Japanese Curry"}], "rating": 3.5, "coordinates": {"latitude": 34.0148676008897, "longitude": -118.278790867221}, "transactions": ["delivery", "pickup"], "location": {"address1": "358 W 38th St", "address2": null, "address3": "", "city": "Los Angeles", "zip_code": "90037", "country": "US", "state": "CA", "display_address": ["358 W 38th St", "Los Angeles, CA 90037"]}, "phone": "+12133444872", "display_phone": "(213) 344-4872", "distance": 1446.2177873294029}], "total": 670, "region": {"center": {"longitude": -118.26512734375, "latitude": 34.02116}}}

//displaySearchResults(exRes.businesses);
SearchForm.addEventListener('submit', function(event) {
	
	event.preventDefault();
	search();

});

function checkLoginStatus() {
    return localStorage.getItem('loggedIn') === 'true';
}

function openReservations() 
{
	document.getElementById('reservationDate').style.display = "block";
	document.getElementById('reservationTime').style.display = "block";
	document.getElementById('reservationSub').style.display = "block";
	document.getElementById('reservationDesc').style.display = "block";
}
function closeReservations() 
{
	document.getElementById('reservationDate').style.display = "none";
	document.getElementById('reservationTime').style.display = "none";
	document.getElementById('reservationSub').style.display = "none";
	document.getElementById('reservationDesc').style.display = "none";
}
function displayOptions() {
    
    if (checkLoginStatus()) {
        document.getElementById('options_guest').style.display='none';
    } else {
        document.getElementById('options_signin').style.display='none';
    }
}

function displayDetails(name)
{
	current_deat = name;
	document.getElementById('search_results_hide').style.display = 'none';
	document.getElementById(name).style.display = "flex";
	document.getElementById('res_text').textContent = name;
	console.log("hereDeat");
	document.getElementById('reserve').style.display = "block";
	document.getElementById('reserve').textContent = "Add Reservation";
	document.getElementById('favor').textContent = "Add to Favorites";
	document.getElementById("reservationDate").value = "";
	document.getElementById("reservationTime").value = "";
	document.getElementById("reservationDesc").value = "";
	document.getElementById('favor').style.display = "block";
	
}
displayOptions();

//window.onload = displayOptions;

/*var map;

  function initialize() {
console.log("here");
  var myLatlng = new google.maps.LatLng(40.713956,-74.006653);

  map = new google.maps.Map(document.getElementById("map_canvas"), myOptions); 

var myOptions = {
     zoom: 3,
     center: position,
     mapTypeId: google.maps.MapTypeId.ROADMAP
     }
  var marker = new google.maps.Marker({
  draggable: true,
  position: myLatlng, 
  map: map,
  title: "Your location"
  });

 google.maps.event.addListener(marker, 'drag', function(event){
     document.getElementById("latitude").value = event.latLng.lat();
     document.getElementById("longitude").value = event.latLng.lng();
     });

}
function google_maps_request() 
{
	console.log("here0");
	initialize();
}
*/
console.log("here0");

 