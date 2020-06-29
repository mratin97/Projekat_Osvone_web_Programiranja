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
	var tipInput = $('#tipInput');
	var salaInput = $('#salaInput');	
	var datumInput = $('#datumInput');	
	var vremeInput = $('#vremeInput');	
	var cenaInput = $('#cenaInput');
	
	
	
		var params = {
			
		};
		$.get('ProjekcijeServlet', params, function(data) {
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
					document.getElementById('Izvestaj').innerHTML = '<a href="Izvestavanje.html" >Izvestaj</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
				document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
			}
				
				$('#addProjekcija').on('click', function(event) {
					var nazivInput = $('#nazivInput');
					var tipInput = $('#tipInput');
					var salaInput = $('#salaInput');	
					var datumInput = $('#datumInput');	
					var vremeInput = $('#vremeInput');	
					var cenaInput = $('#cenaInput');
					
					var naziv = nazivInput.val();
					var tip = tipInput.val();
					var sala = salaInput.val();
					var datum = datumInput.val();
					var vreme = vremeInput.val();
					var cena = cenaInput.val();
					var id = Math.floor(Math.random() * 1000); 
					var admin=data.loggedInUser;
					
					params = {
						'action': 'add',
						'id': id, 
						'naziv': naziv, 
						'tip': tip,
						'sala': sala, 
						'datum': datum, 
						'vreme': vreme, 
						'cena': cena,
						'admin':admin
						
						
					};
					console.log(params);
					$.post('ProjekcijeServlet', params, function(data) {
						if (data.status == 'unauthenticated') {
							window.location.replace('Login.html');
							return;
						}

						if (data.status == 'success') {
							window.location.replace('projekcije.html');
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
								window.location.replace('projekcije.html');
								return;
							}
						});

						

					event.preventDefault();
					return false;
				});
			}});
		

	
});