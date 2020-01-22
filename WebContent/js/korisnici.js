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

		nameFilterInput.on('keyup', function(event) {
			$('#userTable').find('tr:gt(1)').remove();
			getUsers();

			event.preventDefault();
			return false;
		});
});