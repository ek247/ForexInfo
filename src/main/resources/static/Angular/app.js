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
var myApp = angular.module('forex', ['ngAnimate', 'ui.bootstrap']);

myApp.controller('NavController', ['$scope', '$uibModal', function ($scope, $uibModal) {

	
	$scope.openAbout = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'aboutModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	}	
	
	$scope.openProjects = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'projectsModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	}	
	
	$scope.openContact = function (size) {
    var modalInstance = $uibModal.open({
      animation: true,
      templateUrl: 'contactModal.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {}
    });
	}	
	
}]);

myApp.controller('RowController', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
	    setRates($scope, $http);
		
		$interval(function () {
			setRates($scope, $http);
		}, 30000); //Update every 30 seconds to lower delay since current rates is only updated minutely
		
		

 
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
			{name: "EUR/USD", bid: response.data.eurusdbid, ask: response.data.eurusdask, direction: response.data.eurusddirection},
			{name: "USD/JPY", bid: response.data.usdjpybid, ask: response.data.usdjpyask, direction: response.data.usdjpydirection},
			{name: "GBP/USD", bid: response.data.gbpusdbid, ask: response.data.gbpusdask, direction: response.data.gbpusddirection},
			{name: "USD/CHF", bid: response.data.usdchfbid, ask: response.data.usdchfask, direction: response.data.usdchfdirection},
			{name: "EUR/CHF", bid: response.data.eurchfbid, ask: response.data.eurchfask, direction: response.data.eurchfdirection},
			{name: "AUD/USD", bid: response.data.audusdbid, ask: response.data.audusdask, direction: response.data.audusddirection},
			{name: "USD/CAD", bid: response.data.usdcadbid, ask: response.data.usdcadask, direction: response.data.usdcaddirection},
			{name: "NZD/USD", bid: response.data.nzdusdbid, ask: response.data.nzdusdask, direction: response.data.nzdusddirection},
			{name: "EUR/JPY", bid: response.data.eurjpybid, ask: response.data.eurjpyask, direction: response.data.eurjpydirection},
			{name: "GBP/JPY", bid: response.data.gbpjpybid, ask: response.data.gbpjpyask, direction: response.data.gbpjpydirection},
			{name: "EUR/GBP", bid: response.data.eurgbpbid, ask: response.data.eurgbpask, direction: response.data.eurgbpdirection},
			{name: "CHF/JPY", bid: response.data.chfjpybid, ask: response.data.chfjpyask, direction: response.data.chfjpydirection}
			];
			
		
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

//*L@BjV-\^8b{!xjf
