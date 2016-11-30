var app = angular.module('tri', [ 'ngRoute', 'ngMaterial', 'ngMessages' ]);

app
		.config(function($routeProvider, $httpProvider) {

			$routeProvider.when('/', {
				templateUrl : 'home.html',
				controller : 'home'
			}).when('/login', {
				templateUrl : 'login.html',
				controller : 'navigation'
			}).when('/signup', {
				templateUrl : 'signup.html',
				controller : 'signup'
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
//	$http.get('/triathlons/').success(function(data) {
//		$scope.triathlons = data;
//	})
	$http.get('/users/').success(function(data) {
		$scope.users = data;
		console.log(data);
	})
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

//app.controller('createAccount', function($scope, ))

app.controller('signup', function($scope, $http, $rootScope) {
	console.log("signup controller");
	//$scope.error = false;
	//$scope.credentials = {};
	/*$scope.createUser = function() {
		$http.post('signup',
			{
				username: $scope.credentials.username,
				password: $scope.credentials.password
			}
		).then(function (data) {
			console.log("successful sign up");
		},
		function (error) {
			console.log("failed to sign up");
		}
		);
		
		
	};*/
	
	
});

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
			console.log("oculdn't get user");
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
