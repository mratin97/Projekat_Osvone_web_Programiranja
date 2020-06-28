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
	function getUsers() {
		
	var nameFilter ="%"+nameFilterInput.val()+"%";
	console.log('nameFilter: ' + nameFilter);


		var params = {
				'nameFilter': nameFilter, 
		};
		
		$.post('UserServlet', params, function(data) {
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
			
			}
				var korisnici = data.korisnici;
				for (user in korisnici) {
					const table = document.getElementById('userTable');
					
					var rows = 
						'<tr>' + 
						'<td><a href="korisnik.html?id=' + korisnici[user].id + '">' + korisnici[user].id + '</a></td>' + 
						'<td>' + korisnici[user].pass  +'</td>' +
						'<td>' + korisnici[user].role + '</td>' + 
						'<td>' + korisnici[user].datum + '</td>' + 
						
					'</tr>'
					;

						
						table.insertAdjacentHTML('beforeend', rows);
					
				}
			}
			
		});
	}
	getUsers();
		nameFilterInput.on('keyup', function(event) {
			$('#userTable').find('tr:gt(1)').remove();
			getUsers();

			event.preventDefault();
			return false;
		});
		$('#sortSubmit').on('click', function(event) {
			 var table, i, x, y; 
            table = document.getElementById("userTable"); 
            var switching = true; 

            
            while (switching) { 
                switching = false; 
                var rows = table.rows; 

                 
                for (i = 1; i < (rows.length - 1); i++) { 
                    var Switch = false; 

                    
                    x = rows[i].getElementsByTagName("TD")[0]; 
                    y = rows[i + 1].getElementsByTagName("TD")[0]; 

                 
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