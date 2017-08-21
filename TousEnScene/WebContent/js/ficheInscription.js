$(document).ready(function() {
//	$('#dateNaisId').css('background','red') ;
	//$('.blurDateNaiss').blur(function() {
		//alert('fsdjfksd');
		//soumettre("Age");
//	});
	
	$('#choixPage').change(function() {
		soumettre("ZoneDeListe");
	});
	
	function soumettre(valeur) { 
		if ($('#form').length > 0) { 
			$('#action').val(valeur);
			$('#form').submit();
			//$.blockUI();
		}
	}	
 	
}) ;