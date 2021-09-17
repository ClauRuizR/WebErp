
function notificarQue(mensaje,tipoMensaje){
	$('.top-rigth').notify({
		message : {text : mensaje},
		type: tipoMensaje
	}).show();
}

function guardar(){
	$("#formCliente").submit(function(e) {
		var url = "/pacientes/guardar";
		$.ajax({
			type : "POST",
			url : url,
			data : $("#formPaciente").serialize(),
			success : function(data) {
				notificarQue('Paciente fue guardado con exito! ','success');
			},
			error: function(e){
			    notificarQue('hubo un error intentando guardar el paciente! ','danger');
			}
		});
		e.preventDefault(); // avoid to execute the actual submit of the form.
	});
}




