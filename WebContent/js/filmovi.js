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
	function getFilms() {
		
	var nameFilter ="%"+nameFilterInput.val()+"%";
	console.log('nameFilter: ' + nameFilter);


		var params = {
				'nameFilter': nameFilter, 
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
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}
			}
			
		});
	}

		nameFilterInput.on('keyup', function(event) {
			$('#FilmoviTable').find('tr:gt(1)').remove();
			getFilms();

			event.preventDefault();
			return false;
		});
		$('#sortSubmit').on('click', function(event) {
			 var table, i, x, y; 
             table = document.getElementById("FilmoviTable"); 
             var switching = true; 

             // Run loop until no switching is needed 
             while (switching) { 
                 switching = false; 
                 var rows = table.rows; 

                 // Loop to go through all rows 
                 for (i = 1; i < (rows.length - 1); i++) { 
                     var Switch = false; 

                     // Fetch 2 elements that need to be compared 
                     x = rows[i].getElementsByTagName("TD")[1]; 
                     y = rows[i + 1].getElementsByTagName("TD")[1]; 

                     // Check if 2 rows need to be switched 
                     if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) 
                         { 

                         // If yes, mark Switch as needed and break loop 
                         Switch = true; 
                         break; 
                     } 
                 } 
                 if (Switch) { 
                     // Function to switch rows and mark switch as completed 
                     rows[i].parentNode.insertBefore(rows[i + 1], rows[i]); 
                     switching = true; 
                 } 
             } 
	});
		
});