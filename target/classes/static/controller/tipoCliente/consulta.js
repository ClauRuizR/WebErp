angular.module("WebErpApp").controller("tipoClienteConsultaController", function($scope,$http) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.busqueda;


    $scope.pageChanged =function(){
        $scope.tipoCliente;

        $http.get("/rest/tipoCliente/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.tipoClienteList = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.busqueda != ""){
            $scope.busqueda ="";
        }
    }
    $scope.filtrarPorBusqueda=function(){

        $scope.tipoCliente;

        $http.get("/rest/tipoCliente/filtrar?busqueda="+$scope.busqueda+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.tipoClienteList = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
