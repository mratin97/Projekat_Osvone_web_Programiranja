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

	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');
	var glumciInput = $('#glumciInput');	
	var zanrInput = $('#zanrInput');	
	var trajanjeInput = $('#trajanjeInput');	
	var distributerInput = $('#distributerInput');
	var zemljaInput = $('#zemljaInput');	
	var godinaInput = $('#godinaInput');	
	var opisInput = $('#opisInput');	

	$('#filmSubmit').on('click', function(event) {
		var naziv = nazivInput.val();
		var reziser = reziserInput.val();
		var glumci = glumciInput.val();
		var zanr = zanrInput.val();
		var trajanje = trajanjeInput.val();
		var distributer = distributerInput.val();
		var zemlja = zemljaInput.val();
		var godina = godinaInput.val();
		var opis = opisInput.val();
		var id = String(Math.floor(Math.random() * 1001));

		params = {
				'action': 'add', 
				'id' : id,
				'naziv': naziv, 
				'reziser': reziser,
				'glumci': glumci,
				'zanr': zanr,
				'trajanje': trajanje,
				'distributer': distributer,
				'zemlja': zemlja,
				'godina': godina,
				'opis': opis
		};
		$.post('FilmoviServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}

			if (data.status == 'success') {
				window.location.replace('filmovi.html');
			}
		});
		
		event.preventDefault();
		return false;
	});
});