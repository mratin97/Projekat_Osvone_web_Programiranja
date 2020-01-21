$(document).ready(function() {


	var nazivInput = $('#nazivInput');
	var reziserInput = $('#reziserInput');
	var glumciInput = $('#glumciInput');
	var zanrInput = $('#zanrInput');
	var trajanjeInput = $('#trajanjeInput');
	var distributerInput = $('#distributerInput');
	var zemljaInput = $('#zemljaInput');
	var godina = $('#godinaInput');
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
		
		
		console.log('naziv: ' + naziv);
		console.log('reziser: ' + reziser);
		console.log('glumci: ' + glumci);
		console.log('zanr: ' + zanr);
		console.log('trajanje: ' + trajanje);
		console.log('distributer: ' + distributer);
		console.log('zemlja: ' + zemlja);
		console.log('godina: ' + godina);
		console.log('opis: ' + opis);

		params = {
			'action': 'add', 
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
		$.post('FilmServlet', params, function(data) {
			console.log(data);

		});
		
		event.preventDefault();
		return false;
	});
});