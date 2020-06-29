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
	var brojac=0;
	function getProjekcije() {
		
	var nameFilter =nameFilterInput.val();
	var tipCheck = tipInput.val();
	var vremeCheck = vremeInput.val();
	var sortInput=$('#sortInput');
	var sortCheck = sortInput.val();
	var nazivSort =$('#nazivSort');

	console.log('nameFilter: ' + nameFilter);
	

		var params = {
				'nameFilter': nameFilter, 
				'tip': tipCheck,
				'vreme' : vremeCheck
		};
		
		$.get('ProjekcijeServlet', params, function(data) {
			console.log(data);
			
			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if (data.status == 'success') {
				var sortCheck = sortInput.val();
				if (data.loggedInUserRole == 'ADMIN') {
					document.getElementById('addFilm').innerHTML = '<a href="addFilm.html" >Add Film</a>';
					document.getElementById('Korisnici').innerHTML = '<a href="korisnici.html" >Korisnici</a>';
					document.getElementById('addProjekcije').innerHTML = '<a href="addProjekcija.html" >Add Projekcije</a>';
					document.getElementById('Izvestaj').innerHTML = '<a href="Izvestavanje.html" >Izvestaj</a>';
				
					
				
			}else if (data.loggedInUserRole == 'USER') {
				document.getElementById('Korisnici').innerHTML = '<a href="korisnik.html?id='+data.loggedInUserName+' ">Korisnik</a>';
			
			}
				var role=data.loggedInUserRole;
				if (role=="ADMIN"){var projekcije=data.projekcije}
				else if(role=="USER"){var projekcije = data.projekcijedanas;}
				
				var filmovi = data.filmovi;
				if(sortCheck=="Film"){
					projekcije= projekcije.sort((a,b) =>(a.idFilma > b.idFilma) ? 1 : -1)
					
				}

				if(sortCheck=="Cena"){
					projekcije= projekcije.sort((a,b) =>(a.cena > b.cena) ? 1 : -1)
					
				}
				if(sortCheck=="Sala"){
					projekcije= projekcije.sort((a,b) =>(a.sala > b.sala) ? 1 : -1)
					
				}
				if(nazivSort.prop('checked')){if(sortCheck=="Film"){
					projekcije= projekcije.sort((a,b) =>(a.idFilma < b.idFilma) ? 1 : -1)
					
				}

				if(sortCheck=="Cena"){
					projekcije= projekcije.sort((a,b) =>(a.cena < b.cena) ? 1 : -1)
					
				}
				if(sortCheck=="Sala"){
					projekcije= projekcije.sort((a,b) =>(a.sala < b.sala) ? 1 : -1)
					
				}}
				
				for (projekcija in projekcije) {
					const table = document.getElementById('ProjekcijeTable');
					for (film in filmovi){ if (filmovi[film].naziv== projekcije[projekcija].idFilma){
					if(tipCheck==projekcije[projekcija].tip /* vremeCheck==projekcije[projekcija].vreme*/){
					var rows = 
						'<tr>' + 
						'<td><a href="Film.html?naziv=' + filmovi[film].id + '">' + projekcije[projekcija].idFilma + '</a></td>' + 
						'<td>' + projekcije[projekcija].datum  +'</td>' +
						'<td><a href="Projekcija.html?id=' + projekcije[projekcija].id + '">'+ projekcije[projekcija].vreme  +'</td>' +
						'<td>' + projekcije[projekcija].tip + '</td>' + 
						'<td>' + projekcije[projekcija].sala + '</td>' + 
						'<td>' + projekcije[projekcija].cena + '</td>' +
						'<td><a href="KupiKartu.html?naziv=' + projekcije[projekcija].id+'">'+'Kupi Kartu </td>' +
			
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}else if((tipCheck==" " && vremeCheck==" " ) ){
					var rows = 
						'<tr>' +
						'<td><a href="Film.html?naziv=' + filmovi[film].id + '">' + projekcije[projekcija].idFilma + '</a></td>' + 
						'<td>' + projekcije[projekcija].datum  +'</td>' +
						'<td><a href="Projekcija.html?id=' + projekcije[projekcija].id + '">'+ projekcije[projekcija].vreme  +'</td>' +
						'<td>' + projekcije[projekcija].tip + '</td>' + 
						'<td>' + projekcije[projekcija].sala + '</td>' + 
						'<td>' + projekcije[projekcija].cena + '</td>' + 
						'<td><a href="KupiKartu.html?naziv=' + projekcije[projekcija].id+'">'+'Kupi Kartu </td>' +
			
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
			
			
			$('#ProjekcijeTable').find('tr:gt(1)').remove();
			getProjekcije();
			 
	});
		
});