$('document').ready(function(e){

	$('#loginSubmit').on('click',function(event){
		var username=$("#username").val().trim();
		var password=$("#password").val();
		if(username == '' || password == '')
			$("#login-modal").modal('toggle');

		$.post('LogInServlet', {'username': username, 'password': password}, function(data) { 
			if (data.status == 'success') {
				window.location.replace('index.html');
			}
			if (data.status == 'failure') {
				$("#loginErrorMessage").text("Wrong username or password!");
               	$("#badLoginModal").modal();
			}
			event.preventDefault();
			return false;
		});
		event.preventDefault();
		return false;
	});
	
	

});
