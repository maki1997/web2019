$(document).ready(function(e) {
	var userName= "";
	var content=$('#airportsBody');
 	$.get('AirportsServlet',{},function(data){
 		for (a in data.airports) {
 			console.log(data.airports[a].name);
 			if(data.user != null){
 				userName = data.user.username;
 			}
 			content.append('<tr><td>' + data.airports[a].name + '</td></tr>');

 			
 		}
 		if(data.user != null){
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "none";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "block";
 		    //var userPage =document.getElementById("userPage");
 		    //userPage.setAttribute('href','user.html?username='+ data.user.username);
 		    //var adminPage =$("#adminPage");
 		    //if(data.user.role != "ADMIN"){
 		    	//adminPage.hide();
 		    //}
 		    $("#loggeduser").text(data.user.username)
 		    
 		}else{
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "block";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "none";
 		}
 		
 		

 	});
});