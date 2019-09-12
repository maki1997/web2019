$(document).ready(function(e) {
	var userName= "";
	var content=$('.row');
	
 	$.get('VideosServlet',{},function(data){
 		for (v in data.videos) {
 			console.log(data.videos);
 			if(data.user != null){
 				userName = data.user.username;
 			}
 			content.append('<div class="col-md-4"><a href="video.html?id='+ data.videos[v].id +'" target="_self" ><img id="videoImage" src="./pictures/'+ data.videos[v].thumbnail +'.jpg" alt="video" style="width:40% "><div class="caption"><p id="titleBar">'+ data.videos[v].name +'</p></div><p id="videoInfo"><a href="user.html?username='+ data.videos[v].owner.username +'" id="userName">'+ data.videos[v].owner.username  + '</a><br>'+'Previews:'+ data.videos[v].previews +'<br>'+ 'Date:'+ format(data.videos[v].date) +'</p></a></div>');

 			
 		}
 		if(data.user != null){
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "none";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "block";
 		    var userPage =document.getElementById("userPage");
 		    userPage.setAttribute('href','user.html?username='+ data.user.username);
 		    var adminPage =$("#adminPage");
 		    if(data.user.role != "ADMIN"){
 		    	adminPage.hide();
 		    }
 		    $("#loggeduser").text(data.user.username)
 		    
 		}else{
 			var y = document.getElementById("navBarNotLogIn");
 		    y.style.display = "block";
 		    var y = document.getElementById("navBarLogIn");
 		    y.style.display = "none";
 		}
 		
 		

 	});
 	$('#order').on('click',function(event){
    	var desc=$('#desc');
    	var asc=$('#asc');
    	var column=$('#orderVideos').val();
		var ascDesc=asc.val();
		if(desc.is(':checked')){
			var ascDesc=desc.val();
		}
		
		$.post('VideosServlet',{'status':"order",'column':column,'ascDesc':ascDesc},function(data){
			if(data.stat == "success"){
				content.empty();
				for(v in data.videos){
					content.append('<div class="col-md-4"><a href="video.html?id='+ data.videos[v].id +'" target="_self" ><img id="videoImage" src="./pictures/'+ data.videos[v].thumbnail +'.jpg" alt="video" style="width:40% "><div class="caption"><p id="titleBar">'+ data.videos[v].name +'</p></div><p id="videoInfo"><a href="user.html?username='+ data.videos[v].owner.username +'" id="userName">'+ data.videos[v].owner.username  + '</a><br>'+'Previews:'+ data.videos[v].previews +'<br>'+ 'Date:'+ format(data.videos[v].date) +'</p></a></div>');
	
				}
			}
		});
		event.preventDefault();
		return false;
    	
    });
 	$('#filterSubmitVideos').on('click',function(event){
 		var param1 = $('#filterParameters').val().trim();
 		var param = '%'+param1+'%'; 
 		$.post('VideosServlet',{'status':"filter",'param':param},function(data){
			if(data.stat == "success"){
				content.empty();
				for(v in data.videos){
					content.append('<div class="col-md-4"><a href="video.html?id='+ data.videos[v].id +'" target="_self" ><img id="videoImage" src="./pictures/'+ data.videos[v].thumbnail +'.jpg" alt="video" style="width:40% "><div class="caption"><p id="titleBar">'+ data.videos[v].name +'</p></div><p id="videoInfo"><a href="user.html?username='+ data.videos[v].owner.username +'" id="userName">'+ data.videos[v].owner.username  + '</a><br>'+'Previews:'+ data.videos[v].previews +'<br>'+ 'Date:'+ format(data.videos[v].date) +'</p></a></div>');
	
				}
			}
		});
		event.preventDefault();
		return false;
 		
 	});
 	$('#searchSubmitVideos').on('click',function(event){
 		var param = $('#searchParameters').val().trim();
 		var param1 = "";
 		var param2= "";
 		var param3 = "";
 		if(param == ""){alert("Fields can not be emprty!");}
 		if($('#check1').prop('checked')){
 			param1 = '%'+param+'%';
 		}
		if($('#check2').prop('checked')){
			param2 = '%'+param+'%';		
		 		}
		if($('#check3').prop('checked')){
			param3 = '%'+param+'%';	
			}
		$.post('VideosServlet',{'status':"search",'param1':param1,'param2':param2,'param3':param3},function(data){
			if(data.stat == "success"){
				content.empty();
				for(v in data.videos){
					content.append('<div class="col-md-4"><a href="video.html?id='+ data.videos[v].id +'" target="_self" ><img id="videoImage" src="./pictures/'+ data.videos[v].thumbnail +'.jpg" alt="video" style="width:40% "><div class="caption"><p id="titleBar">'+ data.videos[v].name +'</p></div><p id="videoInfo"><a href="user.html?username='+ data.videos[v].owner.username +'" id="userName">'+ data.videos[v].owner.username  + '</a><br>'+'Previews:'+ data.videos[v].previews +'<br>'+ 'Date:'+ format(data.videos[v].date) +'</p></a></div>');
				}
			}
		});
		event.preventDefault();
		return false;
 		
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
