$(document).ready(function(e) {
	var userName= "";
	var content=$('#resBody');
 	$.get('ReservationsServlet',{},function(data){
 		for (r in data.reservations) {
 			console.log(data.reservations[r]);
 			if(data.user != null){
 				userName = data.user.username;
 			}
 			content.append('<tr><td>' +'Flight number: '+ data.reservations[r].startFlight.flightNumber + '</td><td>' +'Flight number: '+ data.reservations[r].endFlight.flightNumber + '</td><td>' + data.reservations[r].startFlightSeat + '</td><td>' + data.reservations[r].endFlightSeat + '</td><td>' + format(data.reservations[r].reservationDate) + '</td><td>' + format(data.reservations[r].ticketSaleDate) + '</td><td>' + data.reservations[r].passenger.username + '</td><td>' + data.reservations[r].passengerFirstname + '</td><td>' + data.reservations[r].passengerLastname + '</td></tr>');
 			
 		}
 		if(data.user != null){
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "none";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "block";
 		    var adminPage =$("#adminPage");
 		    if(data.user.role != "ADMIN"){
 		    	$('#addFButton').hide();
 		    	
 		    }
 		    $("#loggeduser").text(data.user.username)
 		    
 		}else{
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "block";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "none";

	 		   $('#addFButton').hide();
 		}
 		
 		

 	});
 	
 	function format(tempDate) {
 		var date = new Date(tempDate);
 		var day = date.getDate();
 		var monthIndex = date.getMonth();
 		var year = date.getFullYear();
 		var monthNames = [
 			  "January", "February", "March",
 			  "April", "May", "June", "July",
 			  "August", "September", "October",
 			  "November", "December"
 			];
 		
 		return day + '. ' + monthNames[monthIndex] + ' ' + year+'.';
 	}
})