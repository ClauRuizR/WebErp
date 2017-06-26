angular.module("WebErpApp").controller("almacenConsultaController", function($scope,$http) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.codigo;


    $scope.pageChanged =function(){
        $scope.almacen;

        $http.get("/rest/almacen/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.almacen = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.codigo != ""){
            $scope.codigo ="";
        }
    }
    $scope.filtrarPorCodigo=function(){

        $scope.almacen;

        $http.get("/rest/almacen/filtrar?codigo="+$scope.codigo+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.almacen = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
