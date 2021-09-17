
angular.module("WebErpApp").controller("rolConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.rol;


    $scope.pageChanged =function(){
        $scope.rol;

        $http.get("/rest/rol/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaRol = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.rol != ""){
            $scope.rol ="";
        }
    }
    $scope.filtrarPorRol=function(){

        $scope.rol;

        $http.get("/rest/rol/filtrar?rol="+$scope.rol+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaRol = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
