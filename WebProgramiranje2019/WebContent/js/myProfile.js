$('document').ready(function(e){
	
	var username=$('#username');
	var password=$('#password');
	var userName;
	var role;
	var userName =$('#loggeduser').val();
	

	
	
	$.get('UserServlet',{'userName':userName},function(data){
		if(data.user != null){
			var y = document.getElementById("navBarNotLogIn");
		    y.style.display = "none";
		    var y = document.getElementById("navBarLogIn");
		    y.style.display = "block";
		    //var userPage =document.getElementById("userPage");
		    //userPage.setAttribute('href','user.html?username='+ data.user.username);
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
		if(data.status == 'blocked'){
			$('.mainDiv').hide();
		}
		username.val(data.user.username);
		userName=(data.user.username);
		password.val(data.user.password);
		role=(data.user.role);
		console.log("rola:"+role);
		
	});
	
	$('#update').on('click',function(event){
    	var username1=$('#username').val().trim();
    	var password1=$('#password').val().trim();
    	console.log(username1);
    	var params={
				'password':password1,
				'role':role,
				'status':'edit',
				'userName':username1,
				'un':userName
			};
    	console.log(params);
    	$.post('UserServlet',params,function(data){
			if(data.status=="success"){
				
				$("#successfulEdit").modal("toggle");
				//logout();
				
			}
			
		});
    	
		event.preventDefault();
		return false;
    });
	
	
});

function logout(){
	console.log('logout');
	$.get('LogOutServlet',{},function(){
		
	});
}