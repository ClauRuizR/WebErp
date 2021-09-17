
angular.module("WebErpApp").controller("usuariosConsultaController", function($scope,$http) {
    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.usuario;


    $scope.pageChanged =function(){
        $scope.usuarios;

        $http.get("/rest/usuarios/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaUsuarios = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.usuario != ""){
            $scope.usuario ="";
        }
    }
    $scope.filtrarPorUsuario=function(){

        $scope.empresa;

        $http.get("/rest/usuarios/filtrar?usuario="+$scope.usuario+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaUsuarios = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }



})
