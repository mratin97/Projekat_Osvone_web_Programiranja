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
	var reziserInput = $('#reziserInput');
	var glumciInput = $('#glumciInput');	
	var zanrInput = $('#zanrInput');	
	var trajanjeInput = $('#trajanjeInput');	
	var distributerInput = $('#distributerInput');
	var zemljaInput = $('#zemljaInput');	
	var godinaInput = $('#godinaInput');	
	var opisInput = $('#opisInput');	
	
	
	
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
					nazivInput.val(filmovi[film].naziv);
					reziserInput.val(filmovi[film].reziser);
					glumciInput.val(filmovi[film].glumci);
					zanrInput.val(filmovi[film].zanrovi);
					trajanjeInput.val(filmovi[film].trajanje);
					distributerInput.val(filmovi[film].distributer);
					zemljaInput.val(filmovi[film].zemljaPorekla);
					godinaInput.val(filmovi[film].godina);
					opisInput.val(filmovi[film].opis);
					
				}
			}
				$('#updateSubmit').on('click', function(event) {
					var naziv = nazivInput.val();
					var reziser = reziserInput.val();
					var glumci = glumciInput.val();
					var zanr = zanrInput.val();
					var trajanje = trajanjeInput.val();
					var distributer = distributerInput.val();
					var zemlja = zemljaInput.val();
					var godina = godinaInput.val();
					var opis = opisInput.val();
					var id = window.location.search.slice(1).split('&')[0].split('=')[1];

					params = {
						'action': 'update',
						'id': id, 
						'naziv': naziv, 
						'reziser': reziser,
						'glumci': glumci, 
						'zanr': zanr, 
						'trajanje': trajanje, 
						'distributer': distributer, 
						'zemlja': zemlja, 
						'godina': godina,
						'opis': opis
						
					};
					console.log(params);
					$.post('FilmoviServlet', params, function(data) {
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
					$('#deleteSubmit').on('click', function(event) {
						var id = window.location.search.slice(1).split('&')[0].split('=')[1];
						console.log(id);
						params = {
							'action': 'delete',
							'id': id, 
						};
						console.log(params);
						$.post('FilmoviServlet', params, function(data) {
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