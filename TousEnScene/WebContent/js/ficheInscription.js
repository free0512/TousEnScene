$(document).ready(function() {
	
	$('#choixPage').change(function() {
		//$("#popupListAdherent").dialog("open");
		soumettre("ZoneDeListe");
	});
	
	$('#imprimer').click(function() {
		var divToPrint=document.getElementById('pageInscription');
		  var newWin=window.open('','Print-Window');
		  newWin.document.open();
		  newWin.document.write('<html><body onload="window.print()">'+divToPrint.innerHTML+'</body></html>');
		  newWin.document.close();
		  setTimeout(function(){newWin.close();},10);
	} );
	
	$(':radio.selRadioOpt').attr('checked', false);
	
}) ;
	
	function selectionAdherent(valeur) {
		$('#rangId').val(valeur);
		//$('#action').val('ouvrirFichAdherent');
		//$('#form').submit();
	}
	
	function soumettre(valeur) { 
		if ($('#form').length > 0) { 
			//alert('soumettre') ;
			$('#action').val(valeur);
			$('#form').submit();
			//$.blockUI();
		}
	}	
