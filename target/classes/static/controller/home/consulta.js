angular.module("WebErpApp").controller("homeConsultaController", function($scope,$http,$timeout) {


    $scope.graficoIngresosMensuales={};

    $scope.graficoProductoStock={};

    $scope.graficoProductoStockLabel={};

    $scope.init=function(){

        $scope.loadIngresosMensuales();

        $scope.loadProductoStock();





    }

        $scope.loadProductoStock=function(){
            $http.get("/rest/home/getProductoStockCantidad").then(function(result){

                $scope.graficoProductoStock = result.data;



            })

            $http.get("/rest/home/getProductoStockLabel").then(function(result){

                $scope.graficoProductoStockLabel = result.data;



            })




        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };

        // Simulate async data update
        $timeout(function () {
            $http.get("/rest/home/getProductoStockCantidad").then(function(result){

                $scope.graficoProductoStock = result.data;



            })

        }, 3000);


    }

        $scope.loadIngresosMensuales=function(){



        $http.get("/rest/home/getIngresosMensuales").then(function(result){

            $scope.graficoIngresosMensuales = result.data;



        })



        $scope.labelIngresosMensuales = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
        $scope.seriesIngresosMensuales = [ 'Ingreso Neto'];
        $scope.dataIngresosMensuales = $scope.graficoIngresosMensuales;

        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };

        // Simulate async data update
        $timeout(function () {
            $http.get("/rest/home/getIngresosMensuales").then(function(result){

                $scope.graficoIngresosMensuales = result.data;



            })
        }, 3000);

    }




})
