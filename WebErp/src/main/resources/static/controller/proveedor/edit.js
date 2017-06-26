/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("proveedorEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tiposDNI = [{name: "Cedula", value: 1}, {name: "RNC", value: 2},{name: "Pasaporte", value: 3}]
    $scope.proveedor={};
    $scope.proveedor.contactos=[];
    $scope.init=function(){
        $scope.proveedor;


        $scope.proveedor.estado=1;
        if($scope.id >0){
            $http.get("/rest/proveedor/"+$scope.id).then(function(result){

                $scope.proveedor = result.data;

            })
        }



    }
    $scope.agregarContactoProveedor= function(){

        $scope.proveedor.contactos.push({estado: 1});


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


})