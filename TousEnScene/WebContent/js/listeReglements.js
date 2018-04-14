$(document).ready(function() {});
	
	function selectionReglement(modReg, Des, DatReg, Mnt, id) {
		
		$('#idMogRegChoisi').val(modReg);
		$('#idDesChoisi').val(Des);
		$('#idDatRegChoisi').val(DatReg);
		$('#idMntChoisi').val(Mnt) ;
		$('#idChoisi').val(id) ;
		$('#idmodeAcces').val('CHG');
		$('#action').val('choixDunReglement');
		$('#form').submit();
	}
