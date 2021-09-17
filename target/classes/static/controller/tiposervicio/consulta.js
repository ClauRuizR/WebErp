angular.module("WebErpApp").controller("tipoServicioConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.nombre;


    $scope.pageChanged =function(){
        $scope.tipoServicio;

        $http.get("/rest/tipoServicio/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.tipoServicio = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.nombre != ""){
            $scope.nombre ="";
        }
    }
    $scope.filtrarPorNombre=function(){

        $scope.tipoServicio;

        $http.get("/rest/tipoServicio/filtrar?nombre="+$scope.nombre+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.tipoServicio = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
