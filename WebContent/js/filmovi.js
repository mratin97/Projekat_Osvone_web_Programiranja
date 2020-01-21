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

		

		var params = {
				
		};
		$.get('FilmoviServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if (data.status == 'success') {

				var filmovi = data.filmovi;
				for (film in filmovi) {
					var rows = 
						'<tr>' + 
						'<td><a href="Film.html?id=' + filmovi[film].id + '">' + filmovi[film].naziv + '</a></td>' + 
						'<td>' + filmovi[film].reziser  +'</td>' +
						'<td>' + filmovi[film].glumci + '</td>' + 
						'<td>' + filmovi[film].zanrovi + '</td>' + 
						'<td>' + filmovi[film].trajanje + '</td>' + 
						'<td>' + filmovi[film].distribuer + '</td>' + 
						'<td>' + filmovi[film].opis + '</td>' + 
					'</tr>'
					;

						const table = document.getElementById('FilmoviTable');
						table.insertAdjacentHTML('beforeend', rows);
					
				}
			}
			
		});
		

	
});