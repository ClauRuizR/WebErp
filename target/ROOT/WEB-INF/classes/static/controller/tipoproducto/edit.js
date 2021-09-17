angular.module("WebErpApp").controller("tipoProductoEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tipoProducto={};
	$scope.init=function(){
		$scope.tipoProducto;

		if($scope.id >0){
				$http.get("/rest/tipoProducto/"+$scope.id).then(function(result){

					$scope.tipoProducto = result.data;

				})
		}
$scope.tipoProducto.estado=1;

	}
		$scope.saveTipoProducto= function(tipoProducto){
		
		//guardar
		$http.post("/rest/tipoProducto/",$scope.tipoProducto).then(function(result){


            $('#myModalTipoProducto').hide();
            $('.modal-backdrop').hide();

            notificarQue('Tipo Producto fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el tipo de producto! { '+error.data.message+' }','danger');



        })
	}
	$scope.change=function(){
		alert(typeof($scope.tipoProducto.estado));
	}

	

})