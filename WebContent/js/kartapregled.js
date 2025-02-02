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
	var nameFilterInput = $('#nameFilterInput');
	var tipInput = $('#tipInput');
	var vremeInput = $('#vremeInput');
	function getProjekcije() {
		
	var nameFilter =nameFilterInput.val();
	var tipCheck = tipInput.val();
	var vremeCheck = vremeInput.val();
	console.log('nameFilter: ' + nameFilter);
	

		var params = {
				'nameFilter': nameFilter, 
				'tip': tipCheck,
				'vreme' : vremeCheck
		};

		$.get('KartaPregledServlet', params, function(data) {
			console.log(data);

			
			if (data.loggedInUserRole == 'ADMIN') {
				document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
				document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
				document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
				document.getElementById('Izvestaj').innerHTML = '<a href="Izvestavanje.html" >Izvestaj</a>';
			
				
			
		}else if (data.loggedInUserRole == 'USER') {
			document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
		
		}
				var karte = data.karte;
				var karte2=data.karte2;
				if (data.loggedInUserRole == 'USER') {
				for (karta in karte) {
					const table = document.getElementById('ProjekcijeTable');
					
					
					var rows = 
						'<tr>' +
						'<td> '+ karte[karta].id+' </a></td>' + 
						'<td>' + karte[karta].projekcija  +'</td>' +
						'<td>'+ karte[karta].sediste  +'</td>' +
						'<td>' + karte[karta].datum + '</td>' + 
						'<td>' + karte[karta].korisnik + '</td>' + 
						
			
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				
			
			}}else if (data.loggedInUserRole == 'ADMIN') {for (karta in karte2) {
				const table = document.getElementById('ProjekcijeTable');
				
				
				var rows = 
					'<tr>' +
					'<td> '+ karte[karta].id+' </a></td>' + 
					'<td>' + karte[karta].projekcija  +'</td>' +
					'<td>'+ karte[karta].sediste  +'</td>' +
					'<td>' + karte[karta].datum + '</td>' + 
					'<td>' + karte[karta].korisnik + '</td>' + 
					
		
				'</tr>'
				;

					
					table.insertAdjacentHTML('beforeend', rows);
				
			
		
		}}
				
		});
		
	}
	
	getProjekcije();
	
		
		
		
	
});