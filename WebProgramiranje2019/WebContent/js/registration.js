$(document).ready(function(e) {
	$('#submit').on('click',function(event){
		var username=document.getElementById("username").value.trim();
		var password=document.getElementById("password").value.trim();
		console.log("username i pass" + username + " " + password);
 
		var data=new FormData();
		data.append('username',username);
		data.append('password',password);


		if(username == "" || password == ""){
				$("#registerErrorMessage").text("Fields can not be empty!");
               	$("#badRegisterModal").modal();
			}
		$.post('RegisterServlet',{'username':username,'password':password},function(data){

			if(data.status == "success"){
				
				btnCancel();
			}
			else{
				$("#registerErrorMessage").text("User with username exists!");
               	$("#badRegisterModal").modal();
			}
		});
		event.preventDefault();
		return false;
	});

});
function btnCancel() {
	window.location.replace('index.html');
}
