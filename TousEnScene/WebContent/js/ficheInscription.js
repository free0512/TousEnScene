$(document).ready(function() {
	
	$('#choixPage').change(function() {
		$("#popupListAdherent").dialog("open");
		soumettre("ZoneDeListe");
	});
 //   $('#addAdhrent').click(function(){    
//        $( "#popupListeClient" ).dialog( "open" );
//    	soumettre("Validation") ;
//        $('#dispatchClt').val('selectionAjax');
//        $.unblockUI();
//  }); 

	
	$(':radio.selRadioOpt').attr('checked', false);
	
//	$( "#popupListAdherent" ).dialog({
//        autoOpen: false,
//        height: 600,
//        width: 1000,
//        modal: true,
//        close: function() {
//              resetForm('formLstClt');
//        // On remet la fenetre de devant
//       if( $('#contentPopupLstClient .flip-container').hasClass('flip')){
//          $('#contentPopupLstClient .flip-container').removeClass('flip');
//        }
//     }
// });
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
	
