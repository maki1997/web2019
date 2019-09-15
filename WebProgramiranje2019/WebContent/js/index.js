$(document).ready(function(e) {
	var userName= "";
	var content=$('#flightsBody');
	$('#addFButton').on('click',function(event){$('#flightModal').modal('toggle');});
 	$.get('FlightsServlet',{},function(data){
 		for (f in data.flights) {
 			console.log(data.flights[f]);
 			if(data.user != null){
 				userName = data.user.username;
 			}
 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-flight-id="'+data.flights[f].id+'">Edit flight</a></td></tr>');

 			
 		}
 		if(data.user != null){
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "none";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "block";
 		    //var userPage =document.getElementById("userPage");
 		    //userPage.setAttribute('href','user.html?username='+ data.user.username);
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
 	
	$('#filterSubmitFlights').on('click',function(event){
 		var param1 = $('#filterParameters').val().trim();
 		var param = '%'+param1+'%';
 		$.post('FlightsServlet',{'status':"filter",'param':param},function(data){
			if(data.stat=="success"){
				content.empty();
				for(f in data.flights){content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td></tr>');}
			}
		});
 		event.preventDefault();
		return false;
 	});
	
	$('#order').on('click',function(event){
    	var desc=$('#desc');
    	var asc=$('#asc');
    	var column=$('#orderUsers').val();
		var ascDesc=asc.val();
		if(desc.is(':checked')){
			var ascDesc=desc.val();
		}
		$.post('FlightsServlet',{'status':"order",'ascDesc':ascDesc,'column':column},function(data){
			if(data.stat=="success"){
				content.empty();
				for(f in data.flights){content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td></tr>');}
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#deleteModal').on('show.bs.modal',function(event){
		 var idFlight = $(event.relatedTarget).data('book-id');
		 console.log(idFlight);
		 document.getElementById("deleteflight").setAttribute("idFlight", idFlight);
		 
	});
	
	$('#editModal').on('show.bs.modal',function(event){
		 var idFlight = $(event.relatedTarget).data('flight-id');
		 console.log(idFlight);
		 document.getElementById("editFlight").setAttribute("idFlight", idFlight);
		 
	});
	
	$('#deleteflight').on('click',function(event){
	   	var id=$(this).attr('idFlight');
	   	$.post('FlightsServlet',{'idF':id,'status':"delete",},function(data){
	   		console.log(data.loggedUser);
	   		
	   	});
	   	$('#deleteModal').modal('toggle');
	   	content.empty();
	   	$.get('FlightsServlet',{},function(data){
	 		for (f in data.flights) {
	 			console.log(data.flights[f]);
	 			if(data.user != null){
	 				userName = data.user.username;
	 			}
	 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td></tr>');

	 			
	 		}
	 		if(data.user != null){
	 			var y = document.getElementById("navBarNotLogIn");
	 		    y.style.display = "none";
	 		    var y = document.getElementById("navBarLogIn");
	 		    y.style.display = "block";
	 		    //var userPage =document.getElementById("userPage");
	 		    //userPage.setAttribute('href','user.html?username='+ data.user.username);
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
	   
	   });
	
	
 	
 	$('#addFlightSubmit').on('click',function(event){
 		
 		var fn =$('#flight_number').val();
 		
 		var sa =$('#sa').val();
 		var la =$('#ea').val();
 		var sd =$('#sd').val();
 		var ed =$('#ed').val();
 		var nos =$('#nos').val();
 		var price =$('#price').val();
 		
 		var params = {'flightNumber':fn,
 				'sAirport':sa,
 				'eAirport':la,
 				'sDate':sd,
 				'eDate':ed,
 				'numberOfSeats':nos,
 				'price':price,
 				'status':"add"}
 		console.log(params);
	   	$.post('FlightsServlet',params,function(data){
	   		if(data.status=="success"){$('#flightModal').modal('toggle');
	   		content.empty();
		   	$.get('FlightsServlet',{},function(data){
		 		for (f in data.flights) {
		 			console.log(data.flights[f].startAirport.name);
		 			if(data.user != null){
		 				userName = data.user.username;
		 			}
		 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td></tr>');

		 			
		 		}
		 		if(data.user != null){
		 			var y = document.getElementById("navBarNotLogIn");
		 		    y.style.display = "none";
		 		    var y = document.getElementById("navBarLogIn");
		 		    y.style.display = "block";
		 		    //var userPage =document.getElementById("userPage");
		 		    //userPage.setAttribute('href','user.html?username='+ data.user.username);
		 		    var adminPage =$("#adminPage");
		 		    if(data.user.role != "ADMIN"){
		 		    	alert("asdasd");
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
		 		
		 		

		 	});}
	   	});
	   
	   	
	   	
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
});