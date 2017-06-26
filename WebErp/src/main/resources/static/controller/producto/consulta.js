angular.module("WebErpApp").controller("productoConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



        $scope.totalItems = 1;
        $scope.page= 1;
        $scope.itemsPerPage =10;
        $scope.codigoAlfaNumerico;
		
	
		$scope.pageChanged =function(){
            $scope.listaProductos;

            $http.get("/rest/producto/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.listaProductos = result.data.content;

               $scope.totalItems = result.data.totalElements;

            })

            if($scope.codigoAlfaNumerico != ""){
                $scope.codigoAlfaNumerico ="";
            }
		}
        $scope.filtrarPorCodigoAlfaNumerico=function(){

            $scope.producto;

            $http.get("/rest/producto/filtrar?codigoAlfaNumerico="+$scope.codigoAlfaNumerico+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.listaProductos = result.data.content;

                $scope.totalItems = result.data.totalElements;

            })


        }
	
	

})
