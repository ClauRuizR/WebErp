angular.module("WebErpApp").controller("ordenCompraEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.estatus = [{name: "Pendiente", value: "P"}, {name: "Aprobada", value: "A"},{name: "Pagada", value: "X"}]

    $scope.proveedor={};
    $scope.proveedor.contactos=[];
    $scope.ordencompra={};
    $scope.myItem={};
    $scope.ordencompra.detalleOrdenCompra=[];

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



        $scope.ordencompra;
        $scope.ordencompra.estatus='P';
        $scope.ordencompra.estado=1;

        $scope.loadProveedor();
        $scope.loadProducto();
        if($scope.id > 0) {
            $http.get("/rest/ordencompra/" + $scope.id).then(function (result) {

                $scope.ordencompra = result.data;

            })
        }




    }

    $scope.agregarContactoProveedor= function(){

        $scope.proveedor.contactos.push({estado: 1});


    }

    $scope.agregarDetalleOrdenCompra= function(){

        $scope.ordencompra.detalleOrdenCompra.push({estado: 1});
    }

    $scope.saveProveedor= function(proveedor){

        //guardar
        $http.post("/rest/proveedor/",$scope.proveedor).then(function(result){


            $('#myModalProveedor').hide();
            $('.modal-backdrop').hide();
            $scope.loadProveedor();
            notificarQue('Orden de Compra fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar la orden de compra! { '+error.data.message+' }','danger');


        })
    }



    $scope.saveOrdenCompra= function(ordencompra){

        //guardar
        $http.post("/rest/ordencompra/",$scope.ordencompra).then(function(result){

            $scope.ordencompra = result.data;

            alert('Orden de compra creada satisfactoriamente.!');



        },function(error){


            alert(error.data.message);


        })
    }

})