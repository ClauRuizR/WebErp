

function aprobarOrdenCompra(id) {
	var url = "/ordencompras/aprobar/" + id;
	$.ajax({
		type : "POST",
		url : url,
		data : $("#formOrdenCompra").serialize(),
		success : function(data) {
			notificarQue('Aprobada! ', 'success');
		},
		error : function(e) {
			notificarQue('Error aprobando orden compra', 'danger');
		}
	});

}

function pagarOrdenCompra(id) {
	var url = "/ordencompras/pagar/" + id;
	$.ajax({
		type : "POST",
		url : url,
		data : $("#formOrdenCompra").serialize(),
		success : function(data) {
			notificarQue('Pagada! ', 'success');
		},
		error : function(e) {
			notificarQue('Error pagando orden compra', 'danger');
		}
	});

}
