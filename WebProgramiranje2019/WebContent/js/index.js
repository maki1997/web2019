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
 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="edit" href="#editModal" type="button" data-toggle="modal"  data-f-id="'+data.flights[f].id+'">Edit flight</a></td><td><a id="reservation" href="#resModal" type="button" data-toggle="modal"  data-fr-id="'+data.flights[f].id+'">Reservations</a></td></tr>');
 			
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
				for(f in data.flights){content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="edit" href="#editModal" type="button" data-toggle="modal"  data-f-id="'+data.flights[f].id+'">Edit flight</a></td></tr>');}
			}
		});
		event.preventDefault();
		return false;
	});
	
	$('#makeReservation').on('click',function(event){
		$('#makeResModal').modal();
	});
	$('#editModal').on('show.bs.modal',function(event){
		 var idFlight = $(event.relatedTarget).data('f-id');
		 console.log(idFlight);
		 document.getElementById("ef").setAttribute("idFlight", idFlight);
		 $.get('GetFlight',{'idF':idFlight},function(data){
			 
			 $('#eaadd').val(data.flight.endAirport.name);
			 $('#edadd').val(formatForInputInDatepicker(data.flight.endDate));
			 console.log(formatForInputInDatepicker(data.flight.endDate));
			 $('#nosadd').val(data.flight.numberOfSeats);
			 $('#priceadd').val(data.flight.ticketPrice);
		 });
		 
	});
	
	$('#deleteModal').on('show.bs.modal',function(event){
		 var idFlight = $(event.relatedTarget).data('book-id');
		 console.log(idFlight);
		 document.getElementById("deleteflight").setAttribute("idFlight", idFlight);
		 
	});
	
	$('#resModal').on('show.bs.modal',function(event){
		 var idFlightRes = $(event.relatedTarget).data('fr-id');
		 
		 var c = $('#resBody');
		 localStorage.setItem("flight",idFlightRes);
		 c.empty();
		 console.log('id leta:'+idFlightRes);
		 $.get('ReservationsServlet',{'reservationId':idFlightRes},function(data){
			 for(r in data.reservationsByFlight){
		 		c.append('<tr><td>' +'Flight number: '+ data.reservationsByFlight[r].startFlight.flightNumber + '</td><td>' +'Flight number: '+ data.reservationsByFlight[r].endFlight.flightNumber + '</td><td>' + data.reservationsByFlight[r].startFlightSeat + '</td><td>' + data.reservationsByFlight[r].endFlightSeat + '</td><td>' + format(data.reservationsByFlight[r].reservationDate) + '</td><td>' + format(data.reservationsByFlight[r].ticketSaleDate) + '</td><td>' + data.reservationsByFlight[r].passenger.username + '</td><td>' + data.reservationsByFlight[r].passengerFirstname + '</td><td>' + data.reservationsByFlight[r].passengerLastname + '</td></tr>');
			 }
	});
	});
	
	$('#makeResModal').on('show.bs.modal',function(event){
		 var endFlightSelect = $('#endFlight');
		 var f =localStorage.getItem("flight");
		 console.log(f);
		 $.get('FlightsServlet',{},function(data){
			 endFlightSelect.empty();
			 for (f in data.flights){
				 endFlightSelect.append('<option value='+data.flights[f].id+' selected>'+data.flights[f].flightNumber+'</option>')
			 }
		 		
		 
	});
	});
	
	$('#finishReservation').on('click',function(event){
		var startFid=localStorage.getItem("flight");
		var endFid=$('#endFlight').val();
		var startFSeat = $('#startFlightSeat').val();
		var endFSeat = $('#endFlightSeat').val();
		var name = $('#pfirstname').val();
		var surname = $('#plastname').val();
		console.log("sid"+startFid+"end"+endFid);
		$.post('ReservationsServlet',{'startFlight':startFid,'endFlight':endFid,'startFlightSeat':startFSeat,'endFlightSeat':endFSeat,'firstname':name,'lastname':surname,'status':"add"},function(data){
			
		})
		$('#makeResModal').modal('toggle');
		
		
	});
	
	$('#ef').on('click',function(event){
	   	 var id=$(this).attr('idFlight');
	   	 console.log(id);
	   	 var endAirport =$('#eaadd').val();
		 var endDate = $('#edadd').val();
		 var numberos =$('#nosadd').val();
		 var pricee = $('#priceadd').val();
	   	var params = {'idF':id,'endA':endAirport,'endD':endDate,'numofs':numberos,'p':pricee}
	   	console.log("params:"+id);
	   	$.post('FlightsServlet',{'idF':id,'endA':endAirport,'endD':endDate,'numofs':numberos,'p':pricee,'status':"edit",},function(data){
	   		content.empty();
		   	$.get('FlightsServlet',{},function(data){
		 		for (f in data.flights) {
		 			console.log(data.flights[f]);
		 			if(data.user != null){
		 				userName = data.user.username;
		 			}
		 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="edit" href="#editModal" type="button" data-toggle="modal"  data-f-id="'+data.flights[f].id+'">Edit flight</a></td></tr>');

		 			
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
	   	
	   	
	   	$('#editModal').modal('toggle');
	   	
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
	 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="edit" href="#editModal" type="button" data-toggle="modal"  data-f-id="'+data.flights[f].id+'">Edit flight</a></td></tr>');

	 			
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
		 			content.append('<tr><td>' + data.flights[f].flightNumber + '</td><td>' + data.flights[f].startAirport.name + '</td><td>' + data.flights[f].endAirport.name + '</td><td>' + format(data.flights[f].startDate) + '</td><td>' + format(data.flights[f].endDate) + '</td><td>' + data.flights[f].numberOfSeats + '</td><td>' + data.flights[f].ticketPrice + '</td><td><a id="delete" href="#deleteModal" type="button" data-toggle="modal"  data-book-id="'+data.flights[f].id+'">Delete flight</a></td><td><a id="edit" href="#editModal" type="button" data-toggle="modal"  data-f-id="'+data.flights[f].id+'">Edit flight</a></td></tr>');

		 			
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
 	
 	function formatForInputInDatepicker(tempDate) {
		var date = new Date(tempDate);
		var day = day_of_the_month(date);
		
		var monthIndex = date.getMonth();
		var year = date.getFullYear();
		var monthNames = [
			  "01", "02", "03",
			  "04", "05", "06", "07",
			  "08", "09", "10",
			  "11", "12"
			];
		
		return year + '-' + monthNames[monthIndex] + '-' + day;
	}
 	
 	function day_of_the_month(d)
 	{ 
 	  return (d.getDate() < 10 ? '0' : '') + d.getDate();
 	}
});


