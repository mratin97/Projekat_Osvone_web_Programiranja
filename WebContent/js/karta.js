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
	var naziv = window.location.search.slice(1).split('&')[0].split('=')[1];
	var nameFilterInput = $('#nameFilterInput');
	var tipInput = $('#tipInput');
	var vremeInput = $('#vremeInput');
	function getProjekcije() {
		
	var nameFilter =nameFilterInput.val();
	var tipCheck = tipInput.val();
	var vremeCheck = vremeInput.val();
	console.log('nameFilter: ' + nameFilter);
	

		var params = {
				'nameFilter': naziv, 
				'tip': tipCheck,
				'vreme' : vremeCheck
		};
		
		$.get('ProjekcijeServlet', params, function(data) {
			console.log(data);
			console.log(naziv);

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
			
			}
				var projekcije = data.projekcije;
				var filmovi = data.filmovi;
				for (projekcija in projekcije) {
					const table = document.getElementById('ProjekcijeTable');
					for (film in filmovi){ if (filmovi[film].naziv== projekcije[projekcija].idFilma){
					if(tipCheck==projekcije[projekcija].tip && projekcije[projekcija].idFilma==naziv /* vremeCheck==projekcije[projekcija].vreme*/){
					var rows = 
						'<tr>' + 
						'<td><a href="KupiKartu.html?naziv=' + projekcije[projekcija].id + '">' + projekcije[projekcija].idFilma + '</a></td>' + 
						'<td>' + projekcije[projekcija].datum  +'</td>' +
						'<td><a href="Projekcija.html?id=' + projekcije[projekcija].id + '">'+ projekcije[projekcija].vreme  +'</td>' +
						'<td>' + projekcije[projekcija].tip + '</td>' + 
						'<td>' + projekcije[projekcija].sala + '</td>' + 
						'<td>' + projekcije[projekcija].cena + '</td>' + 
			
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}else if((tipCheck==" " && vremeCheck==" " ) ){
					var rows = 
						'<tr>' + 
						'<td><a href="KupiKartu.html?naziv=' + projekcije[projekcija].id + '">' + projekcije[projekcija].idFilma + '</a></td>' + 
						'<td>' + projekcije[projekcija].datum  +'</td>' +
						'<td>' + projekcije[projekcija].vreme  +'</td>' +
						'<td>' + projekcije[projekcija].tip + '</td>' + 
						'<td>' + projekcije[projekcija].sala + '</td>' + 
						'<td>' + projekcije[projekcija].cena + '</td>' + 
			
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}
			}}
			}}
		});
	}
	getProjekcije();
		nameFilterInput.on('keyup', function(event) {
			$('#ProjekcijeTable').find('tr:gt(1)').remove();
			getProjekcije();
	
			event.preventDefault();
			return false;
		});
		
		tipInput.on('change', function(event) {
			$('#ProjekcijeTable').find('tr:gt(1)').remove();
			getProjekcije();

			event.preventDefault();
			return false;
		});
		vremeInput.on('change', function(event) {
			$('#ProjekcijeTable').find('tr:gt(1)').remove();
			getProjekcije();

			event.preventDefault();
			return false;
		});
		
		$('#sortSubmit').on('click', function(event) {
			 var table, i, x, y; 
             table = document.getElementById("ProjekcijeTable"); 
             var switching = true; 

             
             while (switching) { 
                 switching = false; 
                 var rows = table.rows; 

                  
                 for (i = 1; i < (rows.length - 1); i++) { 
                     var Switch = false; 

                     
                     x = rows[i].getElementsByTagName("TD")[1]; 
                     y = rows[i + 1].getElementsByTagName("TD")[1]; 

                  
                     if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) 
                         { 

                          
                         Switch = true; 
                         break; 
                     } 
                 } 
                 if (Switch) { 
                     
                     rows[i].parentNode.insertBefore(rows[i + 1], rows[i]); 
                     switching = true; 
                 } 
             } 
	});
		
});