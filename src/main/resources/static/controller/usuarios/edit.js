angular.module("WebErpApp").controller("usuarioEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.usuario={};
    $scope.usuario.usuarioRol=[];

    $scope.init=function(){
        $scope.usuario;

        if($scope.id >0){
            $http.get("/rest/usuarios/"+$scope.id).then(function(result){

                $scope.usuario = result.data;

            })
        }
        $scope.loadRoles();
        $scope.usuario.estado=1;

    }
    $scope.saveUsuario= function(usuario){

        //guardar
        $http.post("/rest/usuarios/",$scope.usuario).then(function(result){

            $scope.usuario = result.data;

            notificarQue('Usuario fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el usuario! { '+error.data.message+' }','danger');



        })
    }
    $scope.change=function(){
        alert(typeof($scope.usuario.estado));
    }

    $scope.addRol =function () {

        $scope.usuario.usuarioRol.push({estado: 1});
        
    }

$scope.loadRoles=function () {


    $http.get("/rest/rol").then(function(result){

        $scope.listaRoles = result.data;

    })
    
}

})