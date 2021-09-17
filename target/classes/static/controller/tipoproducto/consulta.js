angular.module("WebErpApp").controller("tipoProductoConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



        $scope.totalItems = 1;
        $scope.page= 1;
        $scope.itemsPerPage =10;
        $scope.nombre;
		
	
		$scope.pageChanged =function(){
            $scope.tipoProducto;

            $http.get("/rest/tipoProducto/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.tipoProducto = result.data.content;

               $scope.totalItems = result.data.totalElements;

            })

            if($scope.nombre != ""){
                $scope.nombre ="";
            }
		}
        $scope.filtrarPorNombre=function(){

            $scope.tipoProducto;

            $http.get("/rest/tipoProducto/filtrar?nombre="+$scope.nombre+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

                $scope.tipoProducto = result.data.content;

                $scope.totalItems = result.data.totalElements;

            })


        }
	
	

})
