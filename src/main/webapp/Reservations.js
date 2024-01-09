const username = localStorage.getItem('username');
document.getElementById("res_text").textContent = username + "'s Reservations:";


function defave() 
{
	console.log("fav:" + current_deat);
	
	
	const fave_button = document.getElementById("favor");
	//check if faved 
	fave_button.textContent = "Add to Favorites";
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.open("POST", "RestaurantSearchServlet", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log("loaded");
		
	}
	xhttp.send("deat="+current_deat);
	
	xhttp = new XMLHttpRequest();
	xhttp.open("POST", "ReservationsServlet", true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log("loaded");
		
	}
	const act = "remove";
	xhttp.send("deat="+current_deat+"&username=" + username +"&action=" + act);	

}

function displaySearch() 
{
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "ReservationsServlet?username="+username, true);
	//xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onload = function() {
		console.log(this.responseText);
		//console.log(JSON.parse(this.responseText).businesses);
		//displaySearchResults(JSON.parse(this.responseText).businesses);
		
		const json = JSON.parse(this.responseText);
		console.log(json);
		displaySearchResults(json.businesses);
		
	}
	xhttp.send();
}

  function toggleDropdown() {
    var dropdown = document.getElementById("myDropdown");
    
    if (dropdown.style.display === "block") {
        dropdown.style.display = "none";
    } else {
        dropdown.style.display = "block";
    }
}
function sortR(criteria) 
{
	if (criteria=="atz") 
	{
		
		const searchResultsContainer = document.getElementById('search_results_hide');

		const searchResults = searchResultsContainer.querySelectorAll('.search-result');

		const searchResultsArray = Array.from(searchResults);
		
		searchResultsArray.sort((a, b) => {
		  const nameA = a.querySelector('.result-content > p:nth-of-type(1)').textContent.toLowerCase();
		  const nameB = b.querySelector('.result-content > p:nth-of-type(1)').textContent.toLowerCase();
		  
		  if (nameA < nameB) return -1;
		  if (nameA > nameB) return 1;
		  return 0;
		});
		
		
		searchResultsContainer.innerHTML = '';
		
		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	if (criteria=="zta") 
	{
		
		const searchResultsContainer = document.getElementById('search_results_hide');
		
		
		const searchResults = searchResultsContainer.querySelectorAll('.search-result');
		
		
		const searchResultsArray = Array.from(searchResults);
		
		
		searchResultsArray.sort((a, b) => {
		  const nameA = a.querySelector('.result-content > p:nth-of-type(1)').textContent.toLowerCase();
		  const nameB = b.querySelector('.result-content > p:nth-of-type(1)').textContent.toLowerCase();
		  console.log(nameA);
		  if (nameA < nameB) return 1;
		  if (nameA > nameB) return -1;
		  return 0;
		});
		
		
		searchResultsContainer.innerHTML = '';
		
		
		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	if (criteria=="hr") 
	{
		
		const searchResultsContainer = document.getElementById('search_results_hide');
		
		
		const searchResults = searchResultsContainer.querySelectorAll('.search-result');
		
		
		const searchResultsArray = Array.from(searchResults);
		
		
		searchResultsArray.sort((a, b) => {
		  const nameA = exRes.find(t=>t.name == a.querySelector('.result-content > p:nth-of-type(1)').textContent).rating;
		  const nameB = exRes.find(t=>t.name == b.querySelector('.result-content > p:nth-of-type(1)').textContent).rating;
		  console.log(nameA);
		  if (nameA < nameB) return 1;
		  if (nameA > nameB) return -1;
		  return 0;
		});
		

		searchResultsContainer.innerHTML = '';
		

		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	if (criteria=="lr") 
	{

		const searchResultsContainer = document.getElementById('search_results_hide');
		

		const searchResults = searchResultsContainer.querySelectorAll('.search-result');

		const searchResultsArray = Array.from(searchResults);
		
		searchResultsArray.sort((a, b) => {
		  const nameA = exRes.find(t=>t.name == a.querySelector('.result-content > p:nth-of-type(1)').textContent).rating;
		  const nameB = exRes.find(t=>t.name == b.querySelector('.result-content > p:nth-of-type(1)').textContent).rating;
		  console.log(nameA);
		  if (nameA < nameB) return -1;
		  if (nameA > nameB) return 1;
		  return 0;
		});
		

		searchResultsContainer.innerHTML = '';


		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	if (criteria=="hre") 
	{

		const searchResultsContainer = document.getElementById('search_results_hide');

		const searchResults = searchResultsContainer.querySelectorAll('.search-result');

		const searchResultsArray = Array.from(searchResults);
		
		searchResultsArray.sort((a, b) => {
		  const nameA = exRes.find(t=>t.name == a.querySelector('.result-content > p:nth-of-type(1)').textContent).id;
		  const nameB = exRes.find(t=>t.name == b.querySelector('.result-content > p:nth-of-type(1)').textContent).id;
		  console.log(nameA);
		  if (nameA < nameB) return 1;
		  if (nameA > nameB) return -1;
		  return 0;
		});
		
		
		searchResultsContainer.innerHTML = '';
		

		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	if (criteria=="lre") 
	{
		
		const searchResultsContainer = document.getElementById('search_results_hide');
		
		
		const searchResults = searchResultsContainer.querySelectorAll('.search-result');
		
		
		const searchResultsArray = Array.from(searchResults);
		
		
		searchResultsArray.sort((a, b) => {
		  const nameA = exRes.find(t=>t.name == a.querySelector('.result-content > p:nth-of-type(1)').textContent).id;
		  const nameB = exRes.find(t=>t.name == b.querySelector('.result-content > p:nth-of-type(1)').textContent).id;
		  console.log(nameA);
		  if (nameA < nameB) return -1;
		  if (nameA > nameB) return 1;
		  return 0;
		});
		
		
		searchResultsContainer.innerHTML = '';
		
		
		searchResultsArray.forEach(result => {
		  searchResultsContainer.appendChild(result);
		});
	}
	
	

}

window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.style.display === "block") {
                openDropdown.style.display = "none";
            }
        }
    }
}
displaySearch();
var exRes = "";

function displaySearchResults(searchResults) {
	console.log(searchResults);
	exRes =searchResults;
    const searchResultsContainer = document.getElementById('search_results_hide');
    const searchDetailsContainer = document.getElementById('searchResults');
    
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
        image.src = result.img_url; 
        image.classList.add('restaurant-image');
        image.addEventListener('click', function() {
		    
		    displayDetails(result.name);
		    
		});
		
		
		const imageContainerDeat = document.createElement('div');
        imageContainerDeat.classList.add('image-container');

        const imageDeat = document.createElement('img');
        imageDeat.src = result.img_url; 
        imageDeat.classList.add('restaurant-image');
        

        const content = document.createElement('div');
        content.classList.add('result-content');

        const name = document.createElement('p');
        name.textContent = result.name;
        name.style.fontSize = "1.9vw";

        const address = document.createElement('p');
        address.textContent = result.address;


		const date_line = document.createElement("p");
		const boldText = document.createElement("span");
		boldText.style.fontWeight = "bold"; 
		boldText.textContent = "Date: ";
		
		const dateText = document.createTextNode(result.date);
		
		
		date_line.appendChild(boldText);
		date_line.appendChild(dateText);
		
		const date_line2 = document.createElement("p");
		const boldText2 = document.createElement("span");
		boldText2.style.fontWeight = "bold"; 
		boldText2.textContent = "Time: ";
		
		const timeText = document.createTextNode(result.time);
		
		
		date_line2.appendChild(boldText2);
		date_line2.appendChild(timeText);
 		
        content.appendChild(name);
        content.appendChild(address);
        content.appendChild(date_line);
        content.appendChild(date_line2);
        
        const contentDeat = document.createElement('div');
        contentDeat.classList.add('deat-content');
        
        const addressDeat = document.createElement('p');
		addressDeat.textContent = "Address: " + result.address;
		contentDeat.appendChild(addressDeat);
		
		const phoneDeat = document.createElement('p');
		phoneDeat.textContent = "Phone No. " + result.phone;
		contentDeat.appendChild(phoneDeat);
		
		const cuisineDeat = document.createElement('p');
		cuisineDeat.textContent = "Cuisine: " + result.cuisine;
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
  
    function displayDetails(name)
	{
		current_deat = name;
		document.getElementById("dropdown").style.display = 'none';
		document.getElementById('search_results_hide').style.display = 'none';
		document.getElementById(name).style.display = "flex";
		document.getElementById('res_text').textContent = name;
		document.getElementById('reserve').style.display = "block";
	document.getElementById('favor').style.display = "block";
	}
}