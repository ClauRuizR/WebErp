/**
 * Created by claudioruiz on 6/13/17.
 */
angular.module("WebErpApp").controller("produccionProductoConsultaController", function($scope,$http) {

    $scope.listaProduccionProducto;

    $http.get("/rest/produccionProducto/").then(function(result){

        $scope.listaProduccionProducto = result.data;

    })


    $scope.init=function(){



    }


})
