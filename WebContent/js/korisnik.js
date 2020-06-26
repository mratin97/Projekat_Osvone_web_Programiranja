$(document).ready(function() {
	$('#logoutLink').on('click', function(event) {
		$.get('LogoutServlet', function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
		});
	
		event.preventDefault();
		return false;
	});
	
	
	
	
	var id1 = window.location.search.slice(1).split('&')[0].split('=')[1];
	var nazivInput = $('#idInput');
	var reziserInput = $('#passInput');
	var glumciInput = $('#roleInput');	
	var zanrInput = $('#dateInput');	
	
	
	
	
		var params = {
			
		};
		$.post('UserServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if (data.status == 'success') {
				if (data.loggedInUserRole == 'ADMIN') {
					document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
					document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
					document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
			
			}
				var korisnici = data.korisnici;
				for (user in korisnici) {
					var id = window.location.search.slice(1).split('&')[0].split('=')[1];
					
					var id1=korisnici[user].id;
					if (id1 == id){	
						$('#idInput').text(korisnici[user].id);
						$('#passInput').text(korisnici[user].pass);
						$('#roleInput').text(korisnici[user].role);
						$('#dateInput').text(korisnici[user].datum);
						
					
				}
					
			}
				$('#Upd').append('<a href="UserUpd.html?id='+id+' ">Korisnik</a>');
			}});
		

	
});