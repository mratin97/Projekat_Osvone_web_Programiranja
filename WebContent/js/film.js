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
	
	var naziv = $('#naziv');
	var reziser = $('#reziser');
	var glumci =$('#glumci');
	var zanr = $('#zanr');	
	var trajanje = $('#trajanje');	
	var distributer = $('#distributer');
	var zemlja =  $('#zemlja');
	var godina =  $('#godina');
	var opis =  $('#opis');
	

	
		var params = {
			
		};
		$.get('FilmoviServlet', params, function(data) {
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
				var filmovi = data.filmovi;
				for (film in filmovi) {
					console.log(data);
					var id = window.location.search.slice(1).split('&')[0].split('=')[1];
					if (data.loggedInUserRole == 'ADMIN'){
					$('#adminTable').show();
					$('#userTable').hide();
					var id1=filmovi[film].id;
					if (id1 == id){	
					nazivInput.val(filmovi[film].naziv);
					reziserInput.val(filmovi[film].reziser);
					glumciInput.val(filmovi[film].glumci);
					zanrInput.val(filmovi[film].zanrovi);
					trajanjeInput.val(filmovi[film].trajanje);
					distributerInput.val(filmovi[film].distribuer);
					zemljaInput.val(filmovi[film].zemljaPorekla);
					godinaInput.val(filmovi[film].godina);
					opisInput.val(filmovi[film].opis);
					
					}
					
				}
					else if (data.loggedInUserRole == 'USER'){
						console.log(data);
						$('#adminTable').hide();
						$('#userTable').show();
						$('#naziv').text(filmovi[film].naziv);
						$('#reziser').text(filmovi[film].reziser);
						$('#glumci').text(filmovi[film].glumci);
						$('#zanr').text(filmovi[film].zanrovi);
						$('#trajanje').text(filmovi[film].trajanje);
						$('#distributer').text(filmovi[film].distribuer);
						$('#zemlja').text(filmovi[film].zemljaPorekla);
						$('#godina').text(filmovi[film].godina);
						$('#opis').text(filmovi[film].opis);
						document.getElementById("updateSubmit").remove();
						document.getElementById("deleteSubmit").remove();
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