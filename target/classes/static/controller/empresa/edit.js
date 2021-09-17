/**
 * Created by claudioruiz on 6/11/17.
 */
angular.module("WebErpApp").controller("empresaEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.empresa={};
    $scope.init=function(){
        $scope.empresa;
        $scope.loadGrupoEmpresa();

        $scope.empresa.estado=1;
        if($scope.id >0){
            $http.get("/rest/empresa/"+$scope.id).then(function(result){

                $scope.empresa = result.data;

            })
        }



    }
    $scope.saveEmpresa= function(empresa){

        //guardar
        $http.post("/rest/empresa/",$scope.empresa).then(function(result){

            $('#myModalEmpresa').hide();
            $('.modal-backdrop').hide();
            notificarQue('Empresa fue guardado con exito! ','success');

        })
    }

    $scope.change=function(){
        alert(typeof($scope.empresa.estado));
    }

    $scope.loadGrupoEmpresa=function () {

        $http.get("/rest/grupoempresa").then(function(result){

            $scope.listaGrupoEmpresa = result.data;

        })
        
    }



})