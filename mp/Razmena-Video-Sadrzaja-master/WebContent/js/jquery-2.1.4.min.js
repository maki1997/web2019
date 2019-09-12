$('document').ready(function(e){
    
	var users = $('.row');
    
    $.get('UsersServlet',{},function(data){
    	users.empty();
    	for(i in data.users){
    		users.append('<div class="col-md-12 col-sm-12 col-xs-12" id="user'+data.users[i].username +'"><ul id="info"><li><a href="user.html?username='+data.users[i].username +'" id="picDiv"><img src="./pictures/'+data.users[i].id+'.jpg" alt="Lights" style="width:130px;height: 130px"><h3>'+data.users[i].username+'</h3></a></li><li><p>'+ data.users[i].name +'  '+data.users[i].surname+'</p></li><li><p>'+data.users[i].email+'</p></li><li><p>'+data.users[i].role+'</p></li><li><a id="delete" href="#deleteModal" data-toggle="modal"  data-book-id="'+data.users[i].username+'">Delete user</a></li></ul></div>');
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
    	$('#deleteModal').on('show.bs.modal',function(event){
			 var username = $(event.relatedTarget).data('book-id');
			 document.getElementById("deleteuser").setAttribute("name", username);
			 
		});
    	
    	$('#deleteuser').on('click',function(event){
        	var userName=$(this).attr('name');
        	$.post('UsersServlet',{'userName':userName,'status':"delete",},function(data){
        		console.log(data.loggedUser);
        		if(userName == data.loggedUser.username){
        			$.get('LogOutServlet',{},function(){
						
					});
        		}else{
        			$('#user'+userName).remove();
        		}
        	});
        	$('#deleteModal').modal('toggle');
        	
        });
    });
    $('#order').on('click',function(event){
    	var desc=$('#desc');
    	var asc=$('#asc');
    	var column=$('#orderUsers').val();
		var ascDesc=asc.val();
		if(desc.is(':checked')){
			var ascDesc=desc.val();
		}
		$.post('UsersServlet',{'status':"order",'ascDesc':ascDesc,'column':column},function(data){
			if(data.stat=="success"){
				users.empty();
				for(i in data.users){users.append('<div class="col-md-12 col-sm-12 col-xs-12"><ul id="info"><li><a href="user.html?username='+data.users[i].username +'" id="picDiv"><img src="./pictures/'+data.users[i].id+'.jpg" alt="Lights" style="width:130px;height: 130px"><h3>'+data.users[i].username+'</h3></a></li><li><p>'+ data.users[i].name +'  '+data.users[i].surname+'</p></li><li><p>'+data.users[i].email+'</p></li><li><p>'+data.users[i].role+'</p></li><li><a id="delete" href="#deleteModal" data-toggle="modal" name="'+data.users[i].username+'">Delete user</a></li></ul></div>');}
			}
		});
		event.preventDefault();
		return false;
	});
    $('#filterSubmitUsers').on('click',function(event){
 		var param1 = $('#filterParameters').val().trim();
 		var param = '%'+param1+'%';
 		$.post('UsersServlet',{'status':"filter",'param':param},function(data){
			if(data.stat=="success"){
				users.empty();
				for(i in data.users){users.append('<div class="col-md-12 col-sm-12 col-xs-12"><ul id="info"><li><a href="user.html?username='+data.users[i].username +'" id="picDiv"><img src="./pictures/'+data.users[i].id+'.jpg" alt="Lights" style="width:130px;height: 130px"><h3>'+data.users[i].username+'</h3></a></li><li><p>'+ data.users[i].name +'  '+data.users[i].surname+'</p></li><li><p>'+data.users[i].email+'</p></li><li><p>'+data.users[i].role+'</p></li><li><a id="delete" href="#deleteModal" data-toggle="modal" name="'+data.users[i].username+'">Delete user</a></li></ul></div>');}
			}
		});
 		event.preventDefault();
		return false;
 	});

});

