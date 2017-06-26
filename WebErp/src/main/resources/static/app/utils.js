function editarContacto(formId, formAction) {
	document.getElementById(formId).action = formAction;
	document.getElementById(formId).submit();
}


function editarDetalleAlmacen(formId, formAction) {
	document.getElementById(formId).action = formAction;
	document.getElementById(formId).submit();
}

function editarDetalleOrdenCompra(formId, formAction) {
	document.getElementById(formId).action = formAction;
	document.getElementById(formId).submit();
}

function notificarQue(mensaje, tipoMensaje) {
	$('.top-rigth').notify({
		message : {
			text : mensaje
		},
		type : tipoMensaje
	}).show();
}
function goBack() {
	window.history.back();
}
$("#formCliente")
		.submit(
				function(e) {
					var url = "/clientes/guardar";
					$.ajax({
					type : "POST",
					url : url,
					data : $("#formCliente").serialize(),
					success : function(data) {
							notificarQue('Cliente fue guardado con exito! ','success');
								},
								error : function(e) {
									notificarQue('hubo un error intentando guardar el cliente! ','danger');
								}
							});
					e.preventDefault(); // avoid to execute the actual submit of
					// the form.
				});

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
function getPrecio(rowIndex, id) {

	var text = $("#chkFacturaProducto option[value=" + id + "]").text()

	var res = text.split("-");

	var table = $('#detalleFactura tr')[rowIndex];

	$(table).find('#txtFacturaPrecio').val(res[1]);

}

function popup() {
	window.open("../clientes/crear", 'window', 'width=200,height=100');
}

function getPrecioOrdenCompra(rowIndex, id) {

	var text = $("#chkOrdenProducto option[value=" + id + "]").text()

	var res = text.split("-");

	var table = $('#detalleOrdenCompra tr')[rowIndex];

	$(table).find('#txtOrdenCompraPrecio').val(res[1]);

}

function ocultarComprobanteFiscal(tipoFactura) {
	if (tipoFactura == "FACT") {
		document.getElementById('divComprobanteFiscal').style.display = "initial";
	}

	if (tipoFactura == "COT") {
		document.getElementById('divComprobanteFiscal').style.display = "none";
	}

}

function cambiarContrasena() {
	var password = $('#user-settings-password').val();
	var repassword = $('#user-settings-repassword').val();
	if (password != repassword) {
		alert('Ambas contraseñas deben de coincidir');
		return;
	}

	var usuario = {
		usuario : document.getElementById("usuario").value,
		clave : document.getElementById("user-settings-password").value
	};

	var url = "/usuarios/cambiarContrasena";
	$.ajax({
		type : "POST",
		url : url,
		data : usuario,
		success : function(data) {

			if (data == "OK") {
				$('#user-settings-password').val('');
				$('#user-settings-repassword').val('');

				$("#btnCerrarCambioContrasena").click()

				alert('Contraseña cambiada');
			}
		},
		error : function(e) {
			if (data == "false") {
				alert('Error cambiando contraseña');
			}
		}
	});

}
