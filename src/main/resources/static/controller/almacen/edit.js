angular.module("WebErpApp").controller("almacenEditController", function($scope,$http) {
    $scope.estados = [{name: "Activo", value: 1}, {name: "Inactivo", value: 0}]
    $scope.estatus = [{name: "Pendiente", value: "P"}, {name: "Aprobada", value: "A"},{name: "Pagada", value: "X"}]

    $scope.almacen={};
    $scope.myItem={};
    $scope.almacen.detalleAlmacen=[];


    $scope.valor={};

    $scope.loadLocalidad = function(){

        $http.get("/rest/localidad/").then(function(result){
            $scope.listaLocalidad = result.data;

        })

    }






    $scope.loadProducto = function(){

        $http.get("/rest/producto/").then(function(result){
            $scope.listaProducto = result.data;

        })
    }



    $scope.init=function(){
        $scope.totalItems = 1;
        $scope.valor={};

        $scope.almacen;

        $scope.almacen.estado=1;
        $scope.loadLocalidad();


        $scope.loadProducto();
        if($scope.id > 0) {
            $http.get("/rest/almacen/" + $scope.id).then(function (result) {

                $scope.almacen = result.data;


                $scope.totalItems = result.data.detalleAlmacenDTOPage.totalElements;

            })
        }





    }


$scope.agregarDetalleAlmacen= function(){

    $scope.almacen.detalleAlmacen.push({estado: 1});
}




    $scope.pageChanged =function(){
        $scope.almacen;

        $http.get("/rest/almacen/" + $scope.id).then(function (result) {

            $scope.almacen = result.data;


            $scope.totalItems = result.data.detalleAlmacenDTOPage.totalElements;

        })
    }



 $scope.saveAlmacen= function(almacen){

        //guardar
        $http.post("/rest/almacen/",$scope.almacen).then(function(result){

            $scope.almacen = result.data;



            notificarQue('Almacen fue guardado con exito! ','success');



        },function(error){
            notificarQue('hubo un error intentando guardar el almacen! { '+error.data.message+' }','danger');




        })
    }

})