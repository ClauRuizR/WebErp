angular.module("WebErpApp").controller("localidadConsultaController", function($scope,$http) {


    $scope.localidad;
    $scope.init=function(){

        $scope.pageChanged();

    }



        $scope.totalItems = 1;
        $scope.page= 1;
        $scope.itemsPerPage =10;
        $scope.nombre;
		
	
		$scope.pageChanged =function(){
            $scope.localidad;

            $http.get("/rest/localidad/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.listaLocalidades = result.data.content;

               $scope.totalItems = result.data.totalElements;

            })

            if($scope.nombre != ""){
                $scope.nombre ="";
            }
		}
        $scope.filtrarPorNombre=function(){

            $scope.localidad;

            $http.get("/rest/localidad/filtrar?nombre="+$scope.nombre+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.listaLocalidades = result.data.content;

                $scope.totalItems = result.data.totalElements;

            })


        }
	
	

})
