function Triathlon(name, distance, bikeElev, runElev, location, date,
		startTime, temperature, time, weather, username) {
	this.name = name;
	this.swimDist = distance.swim;
	this.bikeDist = distance.bike;
	this.runDist = distance.run;
	this.swimTime = time.swim;
	this.t1Time = time.t1;
	this.bikeTime = time.bike;
	this.t2Time = time.t2;
	this.runTime = time.run;
	this.bikeElev = bikeElev;
	this.runElev = runElev;
	this.location = location;
	this.date = date;
	this.startTime = startTime;
	this.weather = weather;
	this.temperature = temperature;
	this.username = username;
}
function myTransformRequest(obj) {
	var str = [];
	for ( var p in obj)
		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	return str.join("&");
}


var app = angular.module('tri', [ 'ngRoute', 'ngMaterial', 'ngMessages' ]);

app.run(function($rootScope, $http) {
    $rootScope.updateTriathlonList = function(callback) {
    	$http({
    		method : 'GET',
    		url : 'triathlons',
    		params : {
    			"username" : $rootScope.name
    		}
    	}).success(function(data) {
    		$rootScope.triathlonList = data;
    		$rootScope.$apply();
    		if(callback) callback();
    	})
    };
});

app.filter('secondsToDateTime', function() {
    return function(seconds) {
        var d = new Date(0,0,0,0,0,0,0);
        d.setSeconds(seconds);
        return d;
    };
});

app.config(function($routeProvider, $httpProvider) {
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

});

app.controller('triathlonList', function($scope, $http, $rootScope) {
	$rootScope.updateTriathlonList();

});


app.controller('addTriathlon', function($scope, $http, $rootScope) {
	$scope.name = "Wildflower";
	$scope.distance = {
		swim : 750,
		bike : 12.1,
		run : 3.1
	};
	$scope.bikeElev = 0;
	$scope.runElev = 0;
	$scope.location = "San Luis Obispo";
	$scope.date = new Date();
	$scope.startTime = "7:00 AM";
	$scope.weather = "SUNNY";
	$scope.temperature = 72.0;
	$scope.time = {
		swim : 15,
		t1 : 27,
		bike : 30,
		t2 : 23,
		run : 20
	};

	$scope.addTriathlon = function() {
		var tri = new Triathlon($scope.name, $scope.distance, $scope.bikeElev,
				$scope.runElev, $scope.location, $scope.date, $scope.startTime,
				$scope.temperature, $scope.time, $scope.weather,
				$rootScope.name);
		
		$http({
			method : 'POST',
			url : 'addTri',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			transformRequest : myTransformRequest,
			data : tri
		}).then(function(response) {
			console.log(response.data);
			console.log("addTri success");
			$rootScope.updateTriathlonList();

		}, function(error) {
			console.log("failed to add Tri");
		});
	}

});

app.controller('statistics', function($scope, $http, $rootScope) {
	$rootScope.updateTriathlonList();
	$scope.getAverageSwimTime = function() {
		$http({
			method : 'GET',
			url : 'averageSwimTime',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageSwimTime = data;
		})
	}
	$scope.getAverageBikeTime = function() {
		$http({
			method : 'GET',
			url : 'averageBikeTime',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageBikeTime = data;
		})
	}
	$scope.getAverageRunTime = function() {
		$http({
			method : 'GET',
			url : 'averageRunTime',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageRunTime = data;
		})
	}
	$scope.getAverageSwimDistance = function() {
		$http({
			method : 'GET',
			url : 'averageSwimDistance',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageSwimDistance = data;
		})
	}
	$scope.getAverageBikeDistance = function() {
		$http({
			method : 'GET',
			url : 'averageBikeDistance',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageBikeDistance = data;
		})
	}
	$scope.getAverageRunDistance = function() {
		$http({
			method : 'GET',
			url : 'averageRunDistance',
			params : {
				"username" : $rootScope.name
			}
		}).success(function(data) {
			$scope.averageRunDistance = data;
		})
	}
	$scope.getAverageSwimTime();
	$scope.getAverageBikeTime();
	$scope.getAverageRunTime();
	$scope.getAverageSwimDistance();
	$scope.getAverageBikeDistance();
	$scope.getAverageRunDistance();
});

app.controller('createAccount', function($scope, $http, $rootScope, $location) {
	console.log("create account");
	$scope.error = false;
	$scope.credentials = {};

	$scope.createUser = function() {

		$http({
			method : 'POST',
			url : 'createUser',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			transformRequest : myTransformRequest,
			data : {
				username : $scope.credentials.username,
				password : $scope.credentials.password
			}
		}).then(function(response) {
			$rootScope.authenticated = true;
			$rootScope.name = response.data.username;
			$location.path("/");
			$scope.error = false;
		}, function(error) {
			console.log("failed to sign up");
			$rootScope.authenticated = false;
			$scope.error = true;
		});

	};
})

app.controller('navigation', function($rootScope, $scope, $http, $location) {

	$scope.isActive = function(viewLocation) {
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
