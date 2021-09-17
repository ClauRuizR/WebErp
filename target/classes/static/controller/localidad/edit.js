angular.module("WebErpApp").controller("localidadEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.localidad={};
	$scope.init=function(){
		$scope.localidad;
		$scope.loadEmpresa();
		if($scope.id >0){
				$http.get("/rest/localidad/"+$scope.id).then(function(result){

					$scope.localidad = result.data;

				})
		}
		$scope.localidad.estado=1;

	}


		$scope.loadEmpresa=function(){


			$http.get("/rest/empresa/").then(function(result){
				$scope.listaEmpresa = result.data;

			})
		}
		$scope.saveLocalidad= function(localidad){
		
		//guardar
		$http.post("/rest/localidad/",$scope.localidad).then(function(result){



            notificarQue('Localidad fue guardada con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardando la localidad! { '+error.data.message+' }','danger');



        })
	}
	$scope.change=function(){
		alert(typeof($scope.localidad.estado));
	}

	

})