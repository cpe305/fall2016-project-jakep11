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

app.controller('triathlonList', function($scope, $http) {
	//$scope.triathlons = ["a", "b", "c"];
	$scope.triathlons = null;
//	$http.get('/triathlons/').success(function(data) {
//		$scope.triathlons = data;
//	})
	$http.get('/users/').success(function(data) {
		$scope.users = data;
		console.log(data);
	})
});

app.controller('addTriathlon', function($scope, $http) {
	$scope.name = "Name";
	$scope.distance = "Olympic";
	$scope.bikeElevation = 0;
	$scope.runElevation = 0;
	$scope.location = "San Luis Obispo";
	$scope.date = new Date();
});

app.controller('statistics', function($scope, $http) {
	$scope.triathlons = null;
});

app.controller('signup', function($scope, $http) {
	$scope.error = false;
	$scope.createUser = function() {
		
	}
	
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
			if (data.name) {
				$rootScope.authenticated = true;
				$rootScope.name = data.name;
				console.log(data.name);
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}).error(function() {
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
			$location.path("/");
		}).error(function(data) {
			$rootScope.authenticated = false;
		});
	}

});
