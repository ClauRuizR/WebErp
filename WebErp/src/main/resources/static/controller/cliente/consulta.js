angular.module("WebErpApp").controller("clienteConsultaController", function($scope,$http) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.busqueda;


    $scope.pageChanged =function(){
        $scope.cliente;

        $http.get("/rest/clientes/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaCliente = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.busqueda != ""){
            $scope.busqueda ="";
        }
    }
    $scope.filtrarPorBusqueda=function(){

        $scope.cliente;

        $http.get("/rest/clientes/filtrar?busqueda="+$scope.busqueda+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaCliente = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
