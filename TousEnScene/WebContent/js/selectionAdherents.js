$(document).ready(function() {
	
	$("#dialog").dialog({
		// add the no-close(see ficheInscription.css) class in order to hide its close button.
		dialogClass: "no-close",
		modal : true,
		show: {effect: 'blind', duration: 2000}, 
	    height: 600,
	    width: 1100,
	    buttons: {
	    	
	    }
	}) ;
	
	$('#choixPage').change(function() {
	//	$("#popupListAdherent").dialog("open");
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
	
	function selectionAdherent(num, nom, prenom, age, atelier, regl) {
		
		$('#rangId').val(num);
		$('#idNomEleveChoisi').val(nom);
		$('#idPrenomEleveChoisi').val(prenom);
		$('#idageEleve').val(age) ;
		$('#action').val('choixDunAdherent');
		$('#idatelierEleve').val(atelier) ;
		$('#idreglementEleve').val(regl) ;
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
	
