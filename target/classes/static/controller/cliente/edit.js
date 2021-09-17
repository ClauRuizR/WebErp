/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("clienteEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tiposDNI = [{name: "Cedula", value: 1}, {name: "RNC", value: 2},{name: "Pasaporte", value: 3}]

    $scope.cliente={};
    $scope.cliente.contactos=[];
    $scope.init=function(){
        $scope.cliente;

        $scope.getTipoCliente();
        $scope.cliente.estado=1;
        if($scope.id >0){
            $http.get("/rest/clientes/"+$scope.id).then(function(result){

                $scope.cliente = result.data;

            })
        }



    }
    $scope.getTipoCliente = function(){
        $http.get("/rest/tipoCliente").then(function(result){

            $scope.tipocliente = result.data;

        })

    }
    $scope.agregarContactoCliente= function(){

        $scope.cliente.contactos.push({estado: 1});


    }
    $scope.saveCliente= function(cliente){

        //guardar
        $http.post("/rest/clientes/",$scope.cliente).then(function(result){


            $('#myModalCliente').hide();
            $('.modal-backdrop').hide();


            $scope.cliente = result.data;
            notificarQue('Cliente fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el cliente! { '+error.data.message+' }','danger');


        })
    }






})