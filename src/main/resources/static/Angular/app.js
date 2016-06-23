var varRates = 
	[
		{name: "Name", bid:0, ask:1, direction: 0},
		{name: "Name", bid:0, ask:1, direction: -1},
		{name: "Name", bid:0, ask:2, direction: 0},
		{name: "Name", bid:0, ask:1, direction: -1},
		{name: "Name", bid:0, ask:15, direction: 1},
		{name: "Name", bid:0, ask:1, direction: 1},
		{name: "Name", bid:0, ask:1, direction: -1},
		{name: "Name", bid:0, ask:12, direction: 1},
		{name: "Name", bid:0, ask:1, direction: 1},
		{name: "Name", bid:0, ask:1, direction: 0},
		{name: "Name", bid:0, ask:11, direction: 1},
		{name: "Name", bid:0, ask:1, direction: 1}

	];
	//Default rates. Complete nonsense
var myApp = angular.module('forex', ['ngAnimate', 'ui.bootstrap', 'chart.js']);


myApp.controller('NavController', ['$scope', '$uibModal', function ($scope, $uibModal) {

	
	$scope.openAbout = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'aboutModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	};	
	
	$scope.openProjects = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'projectsModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	};	
	
	$scope.openContact = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'contactModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	};
	
}]);

myApp.controller('RowController', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
        var chart = {
            show: false, name: ""
        };
    

        setRates($scope, $http);

		$interval(function () {
			setRates($scope, $http);
		}, 30000); //Update every 30 seconds to lower delay since current rates is only updated minutely
		
    $scope.showChart = function (name) {
        chart.show = true;
        chart.name = name;
    };
 
  $scope.getDirectionColor = function(direction){
    if(direction == 1)
	{
		return "bg-success";
	}
	else if(direction == -1)
	{
		return "bg-danger";
	}
	return "bg-warning";
}
}]);

function setRates($scope, $http)
{
	    $http({
		method: 'GET',
		url: 'http://localhost/CurrentRates'
		}).then(function successCallback(response) {
			$scope.rates = [
			{name: "EURUSD", bid: response.data.eurusdbid, ask: response.data.eurusdask, direction: response.data.eurusddirection},
			{name: "USDJPY", bid: response.data.usdjpybid, ask: response.data.usdjpyask, direction: response.data.usdjpydirection},
			{name: "GBPUSD", bid: response.data.gbpusdbid, ask: response.data.gbpusdask, direction: response.data.gbpusddirection},
			{name: "USDCHF", bid: response.data.usdchfbid, ask: response.data.usdchfask, direction: response.data.usdchfdirection},
			{name: "EURCHF", bid: response.data.eurchfbid, ask: response.data.eurchfask, direction: response.data.eurchfdirection},
			{name: "AUDUSD", bid: response.data.audusdbid, ask: response.data.audusdask, direction: response.data.audusddirection},
			{name: "USDCAD", bid: response.data.usdcadbid, ask: response.data.usdcadask, direction: response.data.usdcaddirection},
			{name: "NZDUSD", bid: response.data.nzdusdbid, ask: response.data.nzdusdask, direction: response.data.nzdusddirection},
			{name: "EURJPY", bid: response.data.eurjpybid, ask: response.data.eurjpyask, direction: response.data.eurjpydirection},
			{name: "GBPJPY", bid: response.data.gbpjpybid, ask: response.data.gbpjpyask, direction: response.data.gbpjpydirection},
			{name: "EURGBP", bid: response.data.eurgbpbid, ask: response.data.eurgbpask, direction: response.data.eurgbpdirection},
			{name: "CHFJPY", bid: response.data.chfjpybid, ask: response.data.chfjpyask, direction: response.data.chfjpydirection}
			];

            varRates = $scope.rates;
		
		}, function errorCallback(response) {
			alert("failed");
			$scope.rates = varRates;
  });
}

angular.module('forex').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, $http) {

    $scope.closeModal = function () {
        $uibModalInstance.close();
    };

  $scope.update = function(contact)
  {
		var message = {name: $scope.contact.name, email: $scope.contact.email, message: $scope.contact.message};
		$http.post('/send', message)
		.success(function() {
            delete $scope.contact;
		})
  .error(function() {
	  alert("Message failed to send!");
  });
	
  };
  
});

myApp.controller("LineCtrl", function ($scope, $http, $interval) {

    $scope.data = [];
    $scope.graphReady = false;
    $scope.labels = [];

    for (var i=0;i<2;i++) {
        $scope.data[i] = [];
    }

    $http({
        method: 'GET',
        url: 'http://localhost/HistoricalRates/2016-06-23/EURUSD/minute/1'
    }).then(function successCallback(response) {
        var tmpData = [];
        var tmpLabels = [];

        for (var i=0;i<2;i++) {
            tmpData[i] = [];
        }

        console.log(tmpData.length);
        console.log(tmpData[0]);

        for(i = 0; i < response.data.length; i++)
        {
            var ask = response.data[i].ask;
            tmpData[0][i]=(ask);
            tmpData[1][i]=(response.data[i].bid);
            var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
            d.setUTCMilliseconds(response.data[i].time);
            tmpLabels.push(d.toUTCString());
        }

        $scope.labels = tmpLabels;
        $scope.data = tmpData;
        $scope.graphReady = true;



        $scope.series = ['Ask', 'Bid'];

    $scope.onClick = function (points, evt) {
        console.log(points, evt);
    };

    $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
    $scope.options = {
        scales: {
            yAxes: [
                {
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left'
                },
                {
                    id: 'y-axis-2',
                    type: 'linear',
                    display: true,
                    position: 'right'
                }
            ]
        }

    };


});

});

