angular.module("WebErpApp").controller("rolEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.rol={};
    $scope.init=function(){
        $scope.rol;

        if($scope.id >0){
            $http.get("/rest/rol/"+$scope.id).then(function(result){

                $scope.rol = result.data;

            })
        }
        $scope.rol.estado=1;

    }
    $scope.saveRol= function(rol){

        //guardar
        $http.post("/rest/rol/",$scope.rol).then(function(result){




            notificarQue('Rol fue guardado con exito! ','success');

        },function(error){


            notificarQue('hubo un error intentando guardar el rol! { '+error.data.message+' }','danger');



        })
    }
    $scope.change=function(){
        alert(typeof($scope.rol.estado));
    }



})