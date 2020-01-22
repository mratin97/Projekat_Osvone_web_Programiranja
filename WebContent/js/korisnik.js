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
			
			}});
		

	
});