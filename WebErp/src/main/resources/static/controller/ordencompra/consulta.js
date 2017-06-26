angular.module("WebErpApp").controller("ordenCompraConsultaController", function($scope,$http) {


    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.numeroOrdenCompra;


    $scope.pageChanged =function(){
        $scope.ordencompra;

        $http.get("/rest/ordencompra/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.ordencompra = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.numeroOrdenCompra != ""){
            $scope.numeroOrdenCompra ="";
        }
    }
    $scope.filtrarPorNumeroOrden=function(){

        $scope.ordencompra;

        $http.get("/rest/ordencompra/filtrar?numeroOrdenCompra="+$scope.numeroOrdenCompra+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.ordencompra = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
