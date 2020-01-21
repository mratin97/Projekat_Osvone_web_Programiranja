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

	var id = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log(id);
	
	function getFilm() {
		$.get('FilmServlet', {'id': id}, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
				var film = data.film;
					
					$('#nazivInput').text(film.naziv);
					$('#reziserInput').text(film.reziser);
					$('#glumciInput').text(film.glumci);
					$('#zanrInput').text(film.zanrovi);
					$('#trajanjeInput').text(film.trajanje);
					$('#distributerInput').text(film.distributer);
					$('#zemljaInput').text(film.zemljaPorekla);
					$('#godinaInput').text(film.godina);
					$('#opisInput').text(film.opis);
						event.preventDefault();
						return false;
					});
				}
			

	getFilm();
});