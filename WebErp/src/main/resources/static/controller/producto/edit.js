angular.module("WebErpApp").controller("productoEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.producto={};

	$scope.init=function(){
		$scope.producto;
        $scope.loadTipoProducto();
        $scope.producto.estado=1;
		if($scope.id >0){
				$http.get("/rest/producto/"+$scope.id).then(function(result){

					$scope.producto = result.data;

				})

			$scope.getDetalleAlmacen();
		}


	}
		$scope.saveProducto= function(producto){
		
		//guardar
		$http.post("/rest/producto/",$scope.producto).then(function(result){




            notificarQue('Producto fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el producto! { '+error.data.message+' }','danger');


        })
	}
	$scope.change=function(){
		alert(typeof($scope.producto.estado));
	}


	$scope.getDetalleAlmacen=function () {
        $http.get("/rest/producto/getDetalleAlmacen/"+$scope.id).then(function(result){

            $scope.listaDetalleAlmacen = result.data;

        })
		
    }

    $scope.loadTipoProducto = function () {

        $http.get("/rest/tipoProducto/").then(function(result){

            $scope.listaTipoProductos = result.data;

        })

    }
	

})