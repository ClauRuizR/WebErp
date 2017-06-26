angular.module("WebErpApp").controller("facturaConsultaController", function($scope,$http) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.numeroDocumento;


    $scope.pageChanged =function(){
        $scope.factura;

        $http.get("/rest/facturacion/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.factura = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.numeroDocumento != ""){
            $scope.numeroDocumento ="";
        }
    }
    $scope.filtrarPorNumeroDocumento=function(){

        $scope.tipoProducto;

        $http.get("/rest/facturacion/filtrar?numeroDocumento="+$scope.numeroDocumento+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.factura = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
