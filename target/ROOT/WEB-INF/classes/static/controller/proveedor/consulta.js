angular.module("WebErpApp").controller("proveedorConsultaController", function($scope,$http) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.busqueda;


    $scope.pageChanged =function(){
        $scope.proveedor;

        $http.get("/rest/proveedor/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaProveedor = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })

        if($scope.busqueda != ""){
            $scope.busqueda ="";
        }
    }
    $scope.filtrarPorBusqueda=function(){

        $scope.proveedor;

        $http.get("/rest/proveedor/filtrar?busqueda="+$scope.busqueda+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.listaProveedor = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
