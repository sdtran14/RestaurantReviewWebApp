// script.js
const SignUpForm = document.getElementById("SignUpForm");
const LoginForm = document.getElementById("LoginForm");

function logout() 
{
	localStorage.setItem('loggedIn', 'false');
	localStorage.setItem('username', 'NULL');
}

logout();
function validateLogin(event) 
{
	const password = document.getElementById("login_password").value;
	const username = document.getElementById("login_username").value;
	
	var xhttp = new XMLHttpRequest();
xhttp.open("POST", "LoginServlet", true);
xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
xhttp.onload = function() {
	console.log("here")
	
	
	if(this.responseText.length <= 1) 
	{
		
    	
	    localStorage.setItem('loggedIn', 'true');
	    localStorage.setItem('username', username);
	    

		 window.location.href = "Home.html";
	}
	
	document.getElementById("login_msg").innerHTML = "Invalid username/password";
	
	

}

xhttp.send("password="+password+"&username=" + username);


}


function validateSignUp()
{
	 const password = document.getElementById("password").value;
	const username = document.getElementById("username").value;
		const email = document.getElementById("email").value;
		var xhttp = new XMLHttpRequest();
		xhttp.open("POST", "SignUpServlet", true);
		xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhttp.onload = function() {
		console.log()
		
		document.getElementById("signup_msg").innerHTML = this.responseText;
		if(this.responseText.length <= 1) 
		{
			
	    	
		    localStorage.setItem('loggedIn', 'true');
		    localStorage.setItem('username', username);
		    
	
			 window.location.href = "Home.html";
		}
		
	}
	xhttp.send("password="+password+"&username=" + username + "&email="+email);
}


SignUpForm.addEventListener('submit', function(event) {
	event.preventDefault();
	 const password = document.getElementById("password").value;
   	 const verifyPassword = document.getElementById("verifyPassword").value;
	
    if (password !== verifyPassword) {
        
        document.getElementById("signup_msg").innerHTML="Passwords must match";
    } else 
    {
		console.log("her!e")
		validateSignUp();
	}
    
});


LoginForm.addEventListener('submit', function(event) {
	
	validateLogin(event);

	event.preventDefault();
		
	
});
