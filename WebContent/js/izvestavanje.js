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
				if (data.loggedInUserRole == 'ADMIN') {
					document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
					document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
					document.getElementById('Projekcije').innerHTML = '<a href="projekcije.html" >projekcije</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
			
			}
				var izvestaji = data.izvestaji;
				
				if(sortCheck=="Ukupna zarada"){
					izvestaji= izvestaji.sort((a,b) =>(a.cena > b.cena) ? 1 : -1)
					
				}

				if(sortCheck=="Broj Projekcija"){
					izvestaji= izvestaji.sort((a,b) =>(a.brojP > b.brojP) ? 1 : -1)
					
				}
				if(sortCheck=="Broj Karata"){
					izvestaji= izvestaji.sort((a,b) =>(a.brojK > b.brojK) ? 1 : -1)
					
				}
				
				if(nazivSort.prop('checked')){
					if(sortCheck=="Ukupna zarada"){
						izvestaji= izvestaji.sort((a,b) =>(a.cena < b.cena) ? 1 : -1)
						
					}

					if(sortCheck=="Broj Projekcija"){
						izvestaji= izvestaji.sort((a,b) =>(a.brojP < b.brojP) ? 1 : -1)
						
					}
					if(sortCheck=="Broj Karata"){
						izvestaji= izvestaji.sort((a,b) =>(a.brojK < b.brojK) ? 1 : -1)
						
					}
					
				}
				
				
				
				for (izvestaj in izvestaji) {
					const table = document.getElementById('FilmoviTable');
					
					var rows = 
						'<tr>' + 
						'<td><a href="Film.html?id=' + izvestaji[izvestaj].naziv + '">' + izvestaji[izvestaj].naziv + '</a></td>' + 
						'<td>' + izvestaji[izvestaj].brojP  +'</td>' +
						'<td>' + izvestaji[izvestaj].brojK + '</td>' + 
						'<td>' + izvestaji[izvestaj].cena + '</td>' + 
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