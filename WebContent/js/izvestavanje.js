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
	var nazivSort =$('#nazivSort');
	var trajanjeSort =$('#trajanjeSort');
	var sortInput=$('#sortInput');
	var sortCheck = sortInput.val();
	
	function getFilms() {	
	var nameFilter =nameFilterInput.val();
	console.log('nameFilter: ' + nameFilter);
	var sortCheck = sortInput.val();
	var nazivSort =$('#nazivSort');

		var params = {
				'nameFilter': nameFilter, 
		};
		
		$.get('IzvestavanjeServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if (data.status == 'success') {
				console.log(sortCheck);
				var filmovi = data.filmovi;
				if(sortCheck=="Naziv"){
					filmovi= filmovi.sort((a,b) =>(a.naziv > b.naziv) ? 1 : -1)
					
				}

				if(sortCheck=="Trajanje"){
					filmovi= filmovi.sort((a,b) =>(a.trajanje > b.trajanje) ? 1 : -1)
					
				}
				if(sortCheck=="Zanrovi"){
					filmovi= filmovi.sort((a,b) =>(a.zanrovi > b.zanrovi) ? 1 : -1)
					
				}
				if(sortCheck=="Glumci"){
					filmovi= filmovi.sort((a,b) =>(a.glumci > b.glumci) ? 1 : -1)
					
				}
				if(sortCheck=="Distributer"){
					filmovi= filmovi.sort((a,b) =>(a.distribuer > b.distribuer) ? 1 : -1)
					
				}
				if(sortCheck=="Reziser"){
					filmovi= filmovi.sort((a,b) =>(a.reziser > b.reziser) ? 1 : -1)
					
				}
				if(nazivSort.prop('checked')){
					if(sortCheck=="Naziv"){
						filmovi= filmovi.sort((a,b) =>(a.naziv < b.naziv) ? 1 : -1)
						
					}

					if(sortCheck=="Trajanje"){
						filmovi= filmovi.sort((a,b) =>(a.trajanje < b.trajanje) ? 1 : -1)
						
					}
					if(sortCheck=="Zanrovi"){
						filmovi= filmovi.sort((a,b) =>(a.zanrovi < b.zanrovi) ? 1 : -1)
						
					}
					if(sortCheck=="Glumci"){
						filmovi= filmovi.sort((a,b) =>(a.glumci < b.glumci) ? 1 : -1)
						
					}
					if(sortCheck=="Reziser"){
						filmovi= filmovi.sort((a,b) =>(a.reziser < b.reziser) ? 1 : -1)
						
					}
					if(sortCheck=="Distributer"){
						filmovi= filmovi.sort((a,b) =>(a.distribuer < b.distribuer) ? 1 : -1)
						
					}
				}
				
				
				for (film in filmovi) {
					const table = document.getElementById('FilmoviTable');
					
					var rows = 
						'<tr>' + 
						'<td><a href="Film.html?id=' + filmovi[film].id + '">' + filmovi[film].naziv + '</a></td>' + 
						'<td>' + filmovi[film].reziser  +'</td>' +
						'<td>' + filmovi[film].glumci + '</td>' + 
						'<td>' + filmovi[film].zanrovi + '</td>' + 
						'<td>' + filmovi[film].trajanje + '</td>' + 
						'<td>' + filmovi[film].distribuer + '</td>' + 
						'<td>' + filmovi[film].opis + '</td>' + 
						'<td><a href="Karta.html?id=' + filmovi[film].naziv + '">' + "kupi kartu"+ '</a></td>' + 
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}
				
				console.log(data.loggedInUserName);
				if (data.loggedInUserRole == 'ADMIN') {
					document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
					document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
					document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnik').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
			
			}
			} 
			
		});
	}
	getFilms();
		nameFilterInput.on('keyup', function(event) {
			$('#FilmoviTable').find('tr:gt(1)').remove();
		
			
			getFilms();

			event.preventDefault();
			return false;
		});
		$('#sortSubmit').on('click', function(event) {
			
			
			$('#FilmoviTable').find('tr:gt(1)').remove();
			$('#FilmoviTable').find('tr:gt(1)').remove();
			getFilms();
			 
	});
		
});