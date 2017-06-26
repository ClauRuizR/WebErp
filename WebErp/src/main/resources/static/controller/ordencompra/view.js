angular.module("WebErpApp").controller("ordenCompraViewController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.estatus = [{name: "Pendiente", value: "P"}, {name: "Aprobada", value: "A"},{name: "Pagada", value: "X"}]

    $scope.loadProveedor = function(){

        $http.get("/rest/proveedor/").then(function(result){
            $scope.listaProveedor = result.data;

        })

    }




    $scope.loadProducto = function(){

        $http.get("/rest/producto/").then(function(result){
            $scope.listaProducto = result.data;

        })
    }

    $scope.init=function(){

        $scope.loadProveedor();
        $scope.loadProducto();
        $scope.ordencompra;

        $http.get("/rest/ordencompra/"+$scope.id).then(function(result){

            $scope.ordencompra = result.data;

        })

    }


    $scope.aprobarOrdenCompra=function (factura) {

        $scope.ordencompra.estatus = "A"
        $http.post("/rest/ordencompra/cambiarEstatusOrdenCompra",$scope.ordencompra).then(function(result){

            $scope.ordencompra = result.data;

            notificarQue('Orden de Compra fue aprobada con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando aprobar la orden de compra! { '+error.data.message+' }','danger');



        })

    }

    $scope.pagarOrdenCompra=function (factura) {

        $scope.ordencompra.estatus = "X"
        $http.post("/rest/ordencompra/cambiarEstatusOrdenCompra",$scope.ordencompra).then(function(result){

            $scope.ordencompra = result.data;

            notificarQue('Orden de Compra fue pagada con exito! ','success');


        },function(error){


            notificarQue('hubo un error intentando pagando la orden de compra! { '+error.data.message+' }','danger');


        })

    }


})
