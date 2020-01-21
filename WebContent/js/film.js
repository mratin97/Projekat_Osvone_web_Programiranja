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
					var id = window.location.search.slice(1).split('&')[0].split('=')[1];
					
					var id1=filmovi[film].id;
					if (id1 == id){
						
					$('#nazivInput').text(filmovi[film].naziv);
					$('#reziserInput').text(filmovi[film].reziser);
					$('#glumciInput').text(filmovi[film].glumci);
					$('#zanrInput').text(filmovi[film].zanrovi);
					$('#trajanjeInput').text(filmovi[film].trajanje);
					$('#distributerInput').text(filmovi[film].distributer);
					$('#zemljaInput').text(filmovi[film].zemljaPorekla);
					$('#godinaInput').text(filmovi[film].godina);
					$('#opisInput').text(filmovi[film].opis);
					
				}
			}
			
			}});
		

	
});