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
    
        $scope.chartChanged = false;
        $scope.getAsk = true;
        $scope.getBid = true;
        $scope.vol = false;

        setRates($scope, $http);

		$interval(function () {
			setRates($scope, $http);
            $scope.chartChanged = true;
        }, 30000); //Update every 30 seconds to lower delay since current rates is only updated minutely

    $scope.toggleAsk = function () {
        $scope.getAsk = !$scope.getAsk;
        $scope.chartChanged = true;
    };

    $scope.toggleBid = function () {
        $scope.getBid = !$scope.getBid;
        $scope.chartChanged = true;
    };
    $scope.showChart = function (name) {
        $scope.graphReady = true;
        $scope.chartName = name;
        var currentDate = new Date();
        var day = currentDate.getDate();
        var month = currentDate.getMonth() + 1;
        var year = currentDate.getFullYear();
        $scope.dateStart = year+'-'+month+'-'+day;
        $scope.timeRange = 'minute';
        $scope.chartChanged = true;
    };

    $scope.setTime = function(number, time){
        var currentDate = new Date();
        if(time === "day") {
            currentDate.setDate(currentDate.getDate() - number);
            $scope.timeRange = "minute";
        }
        else if(time === "month") {
            currentDate.setMonth(currentDate.getMonth() - number);
            $scope.timeRange = "hour";
        }
        else if(time === "year") {
            currentDate.setFullYear(currentDate.getFullYear() - number);
            $scope.timeRange = "day";
        }
        var day = currentDate.getDate();
        var month = currentDate.getMonth() + 1;
        var year = currentDate.getFullYear();
        $scope.dateStart = year+'-'+month+'-'+day;
        $scope.chartChanged = true;
    };

    $scope.toggleVolatility = function()
    {
        $scope.vol = !$scope.vol;
        $scope.chartChanged = true;
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

        $interval(function () {
            if($scope.graphReady && $scope.$parent.chartChanged) {
                $http({
                    method: 'GET',
                    url: 'http://localhost/HistoricalRates/'+$scope.dateStart+'/' + $scope.chartName + '/'+$scope.timeRange
                }).then(function successCallback(response) {
                    $scope.series = [];
                    if($scope.$parent.getAsk)
                        $scope.series.push('Ask');
                    else if($scope.$parent.getAsk)
                        $scope.series.push('Bid');

                    var tmpData = [];
                    var tmpLabels = [];

                    for (var i = 0; i < 2; i++) {
                        tmpData[i] = [];
                    }

                    for (i = 0; i < response.data.length; i++) {
                        var ask = response.data[i].ask;
                        tmpData[0][i] = (ask);
                        tmpData[1][i] = (response.data[i].bid);
                        var d = new Date(0); // The 0 there is the key, which sets the date to the epoch
                        d.setUTCMilliseconds(response.data[i].time);
                        tmpLabels.push(d.toLocaleString());
                    }
                    if($scope.vol != true) {
                        var index = 0;
                        $scope.data = [];
                        if($scope.$parent.getAsk) {
                            $scope.data.push(tmpData[index]);
                            index += 1;
                        }
                        if($scope.$parent.getBid) {
                            $scope.data.push(tmpData[index]);
                        }
                        $scope.labels = tmpLabels;
                    }
                    else
                    {
                        $scope.data = [];
                        var index = 0;
                        if($scope.$parent.getAsk) {
                            $scope.data.push(stdDev(tmpData[index].length, tmpData[index]));
                            index += 1;
                        }
                        if($scope.$parent.getBid) {
                            $scope.data.push(stdDev(tmpData[index].length, tmpData[index]));
                        }
                        $scope.labels = tmpLabels;
                    }
                    $scope.$parent.chartChanged = false;
                });
            }
        }, 500);

});

function stdDev(index, data)
{
    var avg = 0;
    for(var i = 0; i < index; i++)
    {
        avg += data[i];
    }
    avg = avg/index;

    var stddev = [];
    stddev.push(null);
    for(i = 1; i < index; i++)
    {
        var tmp = 0;
        for(var j = 0; j < i; j++)
        {
            tmp += Math.pow((avg - data[j]), 2);
        }
        stddev.push(Math.sqrt(tmp/i));
    }


    return stddev;
}


