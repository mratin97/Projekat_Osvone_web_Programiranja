$(document).ready(function() { 
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');

	$('#loginSubmit').on('click', function(event) {
		var userName = userNameInput.val();
		var password = passwordInput.val();
		console.log('userName: ' + userName);
		console.log('passwrod: ' + password);		

		var params = {
			'userName': userName, 
			'password': password
		}
		
		$.post('LoginServlet', params, function(data) { 
			console.log('stigao odgovor!')
			console.log(data);

			if (data.status == 'failure') {
				userNameInput.val('');
				passwordInput.val('');

				return;
			}
			if (data.status == 'success') {
				if (data.loggedInUserRole == 'ADMIN') {
					document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
					document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
					document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
					document.getElementById('Izvestaj').innerHTML = '<a href="Izvestavanje.html" >Izvestaj</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
			
			}
				window.location.replace('filmovi.html');
			}
		});
		
		console.log('poslat zahtev!')

	
		event.preventDefault();
		return false;
	});
});