$(document).ready(function() {
	var userNameInput = $('#userNameInput');
	var passwordInput = $('#passwordInput');
	var repeatedPasswordInput = $('#repeatedPasswordInput');

	var messageParagraph = $('#messageParagraph');

	$('#registrationSubmit').on('click', function(event) {
		var userName = userNameInput.val();
		var password = passwordInput.val();
		var repeatedPassword = repeatedPasswordInput.val();
		console.log('userName: ' + userName);
		console.log('password: ' + password);
		console.log('repeated: ' + repeatedPassword);

		if (password != repeatedPassword) {
			messageParagraph.text('Lozinke se ne podudaraju!');

			event.preventDefault();
			return false;
		}
		
		var params = {
			'userName': userName, 
			'password': password
		}
		$.post('RegisterServlet', params, function(data) {
			console.log(data);

			if (data.status == 'failure') {
				messageParagraph.text(data.message);
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

		event.preventDefault();
		return false;
	});
});