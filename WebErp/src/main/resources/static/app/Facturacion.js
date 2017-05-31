$(document).ready(function() {
	$('#chkconComprobanteFiscal').click(function() {
		if ($(this).is(':checked'))
			obtenerComprobanteFiscalValue();

		else
			$('#txtComprobanteFiscal').val();
		$('#txtComprobanteFiscal').hide();
	});

});

function obtenerComprobanteFiscalValue() {
	$.ajax("/comprobantefiscal/obtenerComprobanteFiscal", {
		success : function(data) {

			$('#txtComprobanteFiscal').val(data);
			$('#txtComprobanteFiscal').show();
		},
		error : function() {
			alert('Error Obteniendo Comprobante Fiscal!');
		}

	});
}




function pagarFactura(id) {
	var url = "/facturacion/pagar/" + id;
	$.ajax({
		type : "get",
		url : url,
		data : id,
		success : function(data) {
			if(data==true){
				$('#estatus').val('X');
			}
		}
	});

}


function aprobarFactura(id) {
	var url = "/facturacion/aprobar/" + id;
	$.ajax({
		type : "get",
		url : url,
		data : id,
		
		success : function(data) {
			if(data==true){
				$('#estatus').val('A');
			}
		}
		
	});
	

}
