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
	
	
	
	
	var id1 = window.location.search.slice(1).split('&')[0].split('=')[1];
	var nazivInput = $('#nazivInput');
	var DatumInput = $('#DatumInput');
	var VremeInput =$('#VremeInput');
	var TipInput = $('#TipInput');	
	var SalaInput = $('#SalaInput');	
	var CenaInput = $('#CenaInput');
	var Sediste =  $('#Sediste');
	var BrKarataInput =  $('#BrKarataInput');
	
	
	
	
		var params = {
			
		};
		$.get('KartaServlet', params, function(data) {
			console.log(data);

			if (data.status == 'unauthenticated') {
				window.location.replace('Login.html');
				return;
			}
			
			if (data.status == 'success') {
				
				var filmovi = data.filmovi;
				for (projekcija in filmovi) {
					var id = window.location.search.slice(1).split('&')[0].split('=')[1];
					console.log(id);
					var id1=filmovi[projekcija].id;
					if (id1 == id){	
					$('#nazivInput').text(filmovi[projekcija].idFilma);
					//nazivInput.val(filmovi[projekcija].idFilma);
					$('#DatumInput').text(filmovi[projekcija].datum);
					$('#VremeInput').text(filmovi[projekcija].vreme);
					$('#TipInput').text(filmovi[projekcija].tip);
					$('#SalaInput').text(filmovi[projekcija].sala);
					$('#CenaInput').text(filmovi[projekcija].cena);
					
					
					
				}
			}
				
			}});

		$('#addSubmit').on('click', function(event) {
			var datum = DatumInput.text();
			var id = String(Math.floor(Math.random() * 1001));
			var projekcija = window.location.search.slice(1).split('&')[0].split('=')[1];
			var sediste=Sediste.val();
			var BrKarata=BrKarataInput.val();
			params1 = {
					'action': 'add', 
					'id' : id,
					'datum' : datum,
					'projekcija' : projekcija,
					'sediste' : sediste,
					'BrKarata' : BrKarata,
					
					
			};
			$.post('KartaServlet', params1, function(data) {
				console.log(params1);
		
				if (data.status == 'unauthenticated') {
					window.location.replace('Login.html');
					return;
				}
				if (data.status == 'failure') {
					window.location.replace('Error.html');
					
				}
				
				if (data.status == 'success') {
					window.location.replace('filmovi.html');
				}
			});
			
			event.preventDefault();
			return false;
		});

	
});