	angular.module("WebErpApp",['angular.chosen', 'ui.bootstrap','chart.js']).config(['ChartJsProvider', function (ChartJsProvider) {
        // Configure all charts
        ChartJsProvider.setOptions({
            chartColors: ['#949FB1', '#4D5360'],
            responsive: true
        });
        // Configure all line charts
        ChartJsProvider.setOptions('line', {
            showLines: true
        });
    }]);
	