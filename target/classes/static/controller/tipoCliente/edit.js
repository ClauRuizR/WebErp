/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("tipoClienteEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tiposDNI = [{name: "Cedula", value: 1}, {name: "RNC", value: 2},{name: "Pasaporte", value: 3}]
    $scope.tipoCliente={};

    $scope.init=function(){
        $scope.tipoCliente;

        $scope.loadTipoNcf();
        $scope.tipoCliente.estado=1;
        if($scope.id >0){
            $http.get("/rest/tipoCliente/"+$scope.id).then(function(result){

                $scope.tipoCliente = result.data;

            })
        }



    }

    $scope.loadTipoNcf = function(){

        $http.get("/rest/tipoNcf").then(function(result){

            $scope.listaTipoNcf = result.data;

        })

    }

    $scope.saveTipoCliente= function(tipoCliente){

        //guardar
        $http.post("/rest/tipoCliente/",$scope.tipoCliente).then(function(result){


            $scope.tipoCliente = result.data;
            notificarQue('Tipo Cliente fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el tipo de Cliente { '+error.data.message+' }','danger');


        })
    }


})