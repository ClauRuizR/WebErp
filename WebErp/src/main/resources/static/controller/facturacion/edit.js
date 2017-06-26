angular.module("WebErpApp").controller("facturacionEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.estatus = [{name: "Pendiente", value: "P"}, {name: "Aprobada", value: "A"},{name: "Pagada", value: "X"}]

    $scope.cliente={};
    $scope.cliente.contactos=[];
    $scope.factura={};
    $scope.myItem={};
    $scope.factura.detalleFactura=[];
    $scope.comprobantefiscal={};
    $scope.comprobantefiscal.comprobante={};

    $scope.valor={};

    $scope.loadCliente = function(){

        $http.get("/rest/clientes/").then(function(result){
            $scope.listaCliente = result.data;

        })

    }

    $scope.loadComprobanteFiscal = function(valor){


      if(valor == "1"){
        $http.get("/rest/comprobantefiscal").then(function(result){
            $scope.comprobantefiscal = result.data;
            $scope.factura.numeroComprobanteFiscal= $scope.comprobantefiscal.comprobante;

        })
      }else{

          $scope.factura.numeroComprobanteFiscal="";
      }

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

        $scope.valor={};

        $scope.factura;
        $scope.factura.estatus='P';
        $scope.factura.estado=1;
        $scope.loadTipoDocumento();
        $scope.loadCliente();

        $scope.loadProducto();
        if($scope.id > 0) {
            $http.get("/rest/facturacion/" + $scope.id).then(function (result) {

                $scope.factura = result.data;

            })
        }





    }




$scope.agregarContactoCliente= function(){

        $scope.cliente.contactos.push({estado: 1});


}

$scope.agregarDetalleFactura= function(){

    $scope.factura.detalleFactura.push({estado: 1});
}

$scope.saveCliente= function(cliente){

        //guardar
        $http.post("/rest/clientes/",$scope.cliente).then(function(result){


            $('#myModalCliente').hide();
            $('.modal-backdrop').hide();
            $scope.loadCliente();
            notificarQue('Cliente fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el cliente! { '+error.data.message+' }','danger');


        })
    }



 $scope.saveFactura= function(factura){

        //guardar
        $http.post("/rest/facturacion/",$scope.factura).then(function(result){

            $scope.factura = result.data;

            notificarQue('Factura fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar la factura! { '+error.data.message+' }','danger');



        })
    }

})