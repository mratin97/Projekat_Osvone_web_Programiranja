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
	var nazivInput = $('#nazivInput');
	var passInput = $('#passInput');
	var roleInput = $('#roleInput');	
	var dateInput = $('#dateInput');	
	
	
	
	
		var params = {
				'action': 'nista',
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
						
						nazivInput.val(korisnici[user].id);
						passInput.val(korisnici[user].pass);
						roleInput.val(korisnici[user].role);
						dateInput.val(korisnici[user].datum);
						
					
				}
			}
				$('#updateSubmit').on('click', function(event) {
					var id = nazivInput.val();
					var pass = passInput.val();
					var role = roleInput.val();
					var datum = dateInput.val();
					var id2 = window.location.search.slice(1).split('&')[0].split('=')[1];
					params = {
						'action': 'update',
						'id': id, 
						'id2': id2,
						'pass': pass, 
						'role': role,
						'datum': datum
						
					};
					console.log(params);
					$.post('UserUpdServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('filmovi.html');
							return;
						}
					});
					event.preventDefault();
					return false;
				});
			
			}});
		

		
});