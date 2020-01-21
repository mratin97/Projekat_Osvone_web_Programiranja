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

		var naziv =$('#naziv');
		var filmovi = lowPriceFilterInput.val();
		var zanr = highPriceFilterInput.val();
		console.log('nameFilter: ' + nameFilter);
		console.log('lowPriceFilter: ' + lowPriceFilter);
		console.log('highPriceFilter: ' + highPriceFilter);

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
					FilmoviTable.append(
						'<tr>' + 
							'<td><a href="Film.html?id=' + filmovi[film].id + '">' + filmov[film].naziv + '</a></td>' + 
							'<td>' + filteredProducts[it].price + '</td>' + 
							'<td>' + +'</td>' + 
							//TREBA DA SE UBACE PARAMETRI DA ISPISE STAVKE U TABELU
						'</tr>'
					)
				}
			}
		});
	

	
});