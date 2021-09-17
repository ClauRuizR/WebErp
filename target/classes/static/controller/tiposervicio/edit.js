angular.module("WebErpApp").controller("tipoServicioEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]

    $scope.tipoServicio={};

    $scope.tipoServicio.detalleTipoServicio=[];

    $scope.loadTipoProducto = function () {

        $http.get("/rest/tipoProducto/").then(function(result){

            $scope.listaTipoProductos = result.data;

        })

    }


    $scope.loadServicios = function () {
        $http.get("/rest/servicio/").then(function(result){

            $scope.listaServicios = result.data;

        })
    }

    $scope.agregarDetalleTipoServicio= function(){

        $scope.tipoServicio.detalleTipoServicio.push({estado: 1});
    }

    $scope.init=function(){

        $scope.loadServicios();

        $scope.loadTipoProducto();

        $scope.tipoServicio;

        if($scope.id >0){
            $http.get("/rest/tipoServicio/"+$scope.id).then(function(result){

                $scope.tipoServicio = result.data;

            })
        }
        $scope.tipoServicio.estado=1;

    }


    $scope.saveTipoServicio= function(tipoServicio){

        //guardar
        $http.post("/rest/tipoServicio/",$scope.tipoServicio).then(function(result){

            $scope.tipoServicio = result.data;

            notificarQue('Tipo de Servicio fue guardado con exito! ','success');



        },function(error){


            notificarQue('hubo un error intentando guardar el tipo de servicio! { '+error.data.message+' }','danger');



        })
    }



})