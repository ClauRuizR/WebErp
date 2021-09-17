/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("tipoNcfEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tiposDNI = [{name: "Cedula", value: 1}, {name: "RNC", value: 2},{name: "Pasaporte", value: 3}]
    $scope.tipoNcf={};

    $scope.init=function(){
        $scope.tipoNcf;

        $scope.loadComprobanteFiscal();
        $scope.tipoNcf.estado=1;
        if($scope.id >0){
            $http.get("/rest/tipoNcf/"+$scope.id).then(function(result){

                $scope.tipoNcf = result.data;

            })
        }



    }

    $scope.loadComprobanteFiscal = function(){

        $http.get("/rest/comprobanteFiscal").then(function(result){

            $scope.listaComprobanteFiscal = result.data;

        })

    }

    $scope.saveTipoNcf= function(tipoNcf){

        //guardar
        $http.post("/rest/tipoNcf/",$scope.tipoNcf).then(function(result){


            $scope.tipoNcf = result.data;
            notificarQue('Tipo Ncf fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el tipo de ncf { '+error.data.message+' }','danger');


        })
    }


})