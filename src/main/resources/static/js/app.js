var app = angular.module('tri', [ 'ngRoute', 'ngMaterial', 'ngMessages' ]);

app
		.config(function($routeProvider, $httpProvider) {

			$routeProvider.when('/', {
				templateUrl : 'home.html',
				controller : 'home'
			}).when('/login', {
				templateUrl : 'loginPage.html',
				controller : 'navigation'
			}).when('/createAccount', {
				templateUrl : 'createAccount.html',
				controller : 'createAccount'
			}).when('/triathlonList', {
				templateUrl : 'triathlonList.html',
				controller : 'triathlonList'
			}).when('/addTriathlon', {
				templateUrl : 'addTriathlon.html',
				controller : 'addTriathlon'
			}).when('/statistics', {
				templateUrl : 'statistics.html',
				controller : 'statistics'
			}).otherwise('/');

			$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
			$httpProvider.defaults.headers.common["Content-Type"] = "application/x-www-form-urlencoded";
		});

app.controller('home', function($scope, $http) {
	$http.get('/resource/').success(function(data) {
		$scope.greeting = data;
	})
});

app.controller('triathlonList', function($scope, $http, $rootScope) {
	//$scope.triathlons = ["a", "b", "c"];
	$scope.triathlons = $rootScope.triathlonList;
	console.log($rootScope.triathlonList);
	$http({
	    method: 'GET',
	    url: 'triathlons',
	    //transformRequest: myTransformRequest,
	    params: { "username": $rootScope.name}
	    //data: {username: $scope.credentials.username, password: $scope.credentials.password}
	}).success(function(data) {
		$scope.triathlons = data;
	})
	
//	$http.get('/users/').success(function(data) {
//		$scope.users = data;
//		console.log(data);
//	})
});

function Triathlon(name, distance, bikeElev, runElev, location, date, startTime, temperature) {
	this.name = name; 
	this.distance = distance;
	this.bikeElev = bikeElev;
	this.runElev = runElev;
	this.location = location;
	this.date = date;
	this.startTime = startTime;
	this.temperature = temperature;
}
function myTransformRequest(obj) {
    var str = [];
    for(var p in obj)
    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    return str.join("&");
}

app.controller('addTriathlon', function($scope, $http, $rootScope) {
	$scope.name = "Namasdfe";
	$scope.distance = "Olympic";
	$scope.bikeElevation = 0;
	$scope.runElevation = 0;
	$scope.location = "San Luis Obispo";
	$scope.date = new Date();
	$scope.startTime = "7:00 AM";
	$scope.weather = "Sunny";
	$scope.temperature = 72.0;
	
	$scope.addTriathlon = function() {
		var tri = new Triathlon($scope.name, $scope.distance, $scope.bikeElev, $scope.runElev, $scope.location, $scope.date, $scope.startTime, $scope.temperature);
		$rootScope.triathlonList = [tri];
	}
	
});

app.controller('statistics', function($scope, $http, $rootScope) {
	$scope.triathlons = $rootScope.triathlonList;
});

app.controller('createAccount', function($scope, $http, $rootScope, $location) {
	console.log("create account");
	$scope.error = false;
	$scope.credentials = {};
	
	
	$scope.createUser = function() {
		
		$http({
		    method: 'POST',
		    url: 'createUser',
		    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: myTransformRequest,
		    data: {username: $scope.credentials.username, password: $scope.credentials.password}
		}).then(function (response) {
			console.log("successful sign up");
			$rootScope.authenticated = true;
//			console.log(data);
//			console.log(data.username);
			$rootScope.name = response.data.username;
			$location.path("/");
			$scope.error = false;
		},
		function (error) {
			console.log("failed to sign up");
			$rootScope.authenticated = false;
			$scope.error = true;
		}
		);
		
		
	};
})


app.controller('navigation', function($rootScope, $scope, $http, $location) {

	$scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };
	
	var authenticate = function(credentials, callback) {

		var headers = credentials ? {
			authorization : "Basic "
					+ btoa(credentials.username + ":" + credentials.password)
		} : {};

		$http.get('user', {
			headers : headers
		}).success(function(data) {
			console.log("successfully got user");
			if (data.name) {
				$rootScope.authenticated = true;
				$rootScope.name = data.name;
				console.log(data.name);
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}).error(function() {
			console.log("failed");
			$rootScope.authenticated = false;
			callback && callback();
		});

	}

	authenticate();
	$scope.credentials = {};
	$scope.login = function() {
		authenticate($scope.credentials, function() {
			if ($rootScope.authenticated) {
				$location.path("/");
				$scope.error = false;
			} else {
				$location.path("/login");
				$scope.error = true;
			}
		});
	};

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.authenticated = false;
			$rootScope.name = null;
			$rootScope.triathlonList = null;
			$location.path("/");
		}).error(function(data) {
			$rootScope.authenticated = false;
		});
	}

});
