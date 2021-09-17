angular.module("WebErpApp").controller("facturaViewController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.estatus = [{name: "Pendiente", value: "P"}, {name: "Aprobada", value: "A"},{name: "Pagada", value: "X"}]

    $scope.loadCliente = function(){

        $http.get("/rest/clientes/").then(function(result){
            $scope.listaCliente = result.data;

        })

    }

    $scope.loadTipoDocumento = function(){

        $http.get("/rest/tipoDocumento/documentosSalida").then(function(result){
            $scope.listaTipodocumento = result.data;

        })

    }


    $scope.loadProducto = function(){

        $http.get("/rest/producto/").then(function(result){
            $scope.listaProducto = result.data;

        })
    }

    $scope.init=function(){
        $scope.loadTipoDocumento();
        $scope.loadCliente();
        $scope.loadProducto();
        $scope.factura;

        $http.get("/rest/facturacion/"+$scope.id).then(function(result){

            $scope.factura = result.data;

        })

    }

    
    $scope.aprobarFactura=function (factura) {

        $scope.factura.estatus = "A"
        $http.post("/rest/facturacion/cambiarEstatusFactura",$scope.factura).then(function(result){

            $scope.factura = result.data;

            notificarQue('Factura fue aprobada con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando aprobar la factura! { '+error.data.message+' }','danger');


        })
        
    }

    $scope.pagarFactura=function (factura) {

        $scope.factura.estatus = "X"
        $http.post("/rest/facturacion/cambiarEstatusFactura",$scope.factura).then(function(result){

            $scope.factura = result.data;

            notificarQue('Factura fue pagada con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando pagar la factura! { '+error.data.message+' }','danger');



        })

    }


})
