angular.module("WebErpApp").controller("empresaConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.nombre;


    $scope.pageChanged =function(){
        $scope.empresa;

        $http.get("/rest/empresa/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.empresa = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.nombre != ""){
            $scope.nombre ="";
        }
    }
    $scope.filtrarPorNombre=function(){

        $scope.empresa;

        $http.get("/rest/empresa/filtrar?nombre="+$scope.nombre+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.empresa = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
