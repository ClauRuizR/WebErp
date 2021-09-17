angular.module("WebErpApp").controller("servicioConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.nombre;


    $scope.pageChanged =function(){
        $scope.servicio;

        $http.get("/rest/servicio/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.servicio = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.nombre != ""){
            $scope.nombre ="";
        }
    }
    $scope.filtrarPorNombre=function(){

        $scope.servicio;

        $http.get("/rest/servicio/filtrar?nombre="+$scope.nombre+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.servicio = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
