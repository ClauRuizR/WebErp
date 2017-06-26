angular.module("WebErpApp").controller("grupoEmpresaEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.grupoempresa={};
    $scope.init=function(){
        $scope.grupoempresa;

        $scope.grupoempresa.estado=1;
        if($scope.id >0){
            $http.get("/rest/grupoempresa/"+$scope.id).then(function(result){

                $scope.grupoempresa = result.data;

            })
        }


    }
    $scope.saveGrupoEmpresa= function(grupoempresa){

        //guardar
        $http.post("/rest/grupoempresa/",$scope.grupoempresa).then(function(result){

            $('#myModalGrupoEmpresa').hide();
            $('.modal-backdrop').hide();
            notificarQue('Grupo Empresa fue guardado con exito! ','success');


        },function(error){


            notificarQue('hubo un error intentando guardar el grupo empresa! { '+error.data.message+' }','danger');



        })
    }
    $scope.change=function(){
        alert(typeof($scope.grupoempresa.estado));
    }



})