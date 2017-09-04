$(document).ready(function() {
	
	$('#choixPage').change(function() {
		soumettre("ZoneDeListe");
	});
	
	$(':radio').attr('checked', false);
	
}) ;
	
	function selectionAdherent(valeur) {
		$('#rangId').val(valeur);
		$('#action').val('ouvrirFichAdherent');
		$('#form').submit();
	}
	
	function soumettre(valeur) { 
		if ($('#form').length > 0) { 
			//alert('soumettre') ;
			$('#action').val(valeur);
			$('#form').submit();
			//$.blockUI();
		}
	}	
	
