angular.module("WebErpApp").controller("servicioEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.servicio={};
    $scope.init=function(){
        $scope.servicio;

        if($scope.id >0){
            $http.get("/rest/servicio/"+$scope.id).then(function(result){

                $scope.servicio = result.data;

            })
        }
        $scope.servicio.estado=1;

    }
    $scope.saveServicio= function(servicio){

        //guardar
        $http.post("/rest/servicio/",$scope.servicio).then(function(result){


            $('#myModalServicio').hide();
            $('.modal-backdrop').hide();

            notificarQue('Servicio fue guardado con exito! ','success');


        },function(error){


            notificarQue('hubo un error intentando guardar el servicio! { '+error.data.message+' }','danger');



        })
    }
    $scope.change=function(){
        alert(typeof($scope.servicio.estado));
    }



})