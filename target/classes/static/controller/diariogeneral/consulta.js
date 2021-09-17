angular.module("WebErpApp").controller("diarioGeneralConsultaController", function($scope,$http,$filter) {



    $scope.init=function(){

        $scope.pageChanged();

    }



    $scope.totalItems = 1;
    $scope.page= 1;
    $scope.itemsPerPage =10;
    $scope.fechaDesde= $filter('date')(new Date(), 'dd/MM/yyyy');
    $scope.fechaHasta= $filter('date')(new Date(), 'dd/MM/yyyy');



    $scope.pageChanged =function(){
        $scope.diarioGeneral;

        $http.get("/rest/diariogeneral/pagination?page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.diarioGeneral = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }
    $scope.filtrarPorFechas=function(){

        $scope.diarioGeneral;

        $http.get("/rest/diariogeneral/filtrar?fechaDesde="+moment($scope.fechaDesde).format('DD/MM/YYYY')+"&fechaHasta="+moment($scope.fechaHasta).format('DD/MM/YYYY')+"&page="+ ($scope.page-1)+"&size="+$scope.itemsPerPage).then(function(result){

            $scope.diarioGeneral = result.data.content;

            $scope.totalItems = result.data.totalElements;

        })


    }

})
