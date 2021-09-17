/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("comprobanteFiscalEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.tiposDNI = [{name: "Cedula", value: 1}, {name: "RNC", value: 2},{name: "Pasaporte", value: 3}]
    $scope.comprobanteFiscal={};

    $scope.init=function(){
        $scope.comprobanteFiscal;


        $scope.comprobanteFiscal.estado=1;
        if($scope.id >0){
            $http.get("/rest/comprobanteFiscal/"+$scope.id).then(function(result){

                $scope.comprobanteFiscal = result.data;

            })
        }



    }



    $scope.saveComprobanteFiscal= function(comprobanteFiscal){

        //guardar
        $http.post("/rest/comprobanteFiscal/",$scope.comprobanteFiscal).then(function(result){


            $scope.comprobanteFiscal = result.data;
            notificarQue('Comprobante Fiscal fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el comprobante fiscal { '+error.data.message+' }','danger');


        })
    }


})