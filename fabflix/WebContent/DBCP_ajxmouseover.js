/**
 * var mvd = document.getElementById('myForm').mvId.value;
 */

//Browser Support Code
function ajaxFunction(mvID){
	var ajaxRequest;  // The variable that makes Ajax possible
	try{
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e){
		// Internet Explorer Browsers
		try{
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try{
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e){
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
	}
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function(){
		if(ajaxRequest.readyState == 4&& ajaxRequest.status == 200){
			var ajaxDisplay = document.getElementById('ajaxDiv');
			ajaxDisplay.innerHTML = ajaxRequest.responseText;
		}
	}
	
	var queryString = "?movieId=" + mvID;
	
	ajaxRequest.open("GET", "DBCPAjaxPopUpMovieInfoServlet" + queryString, true);
	ajaxRequest.send(null); 
	
}
