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
function convertTime(time) {
	var newTime = {
			swim: timeToSecs(time.swim),
			t1: timeToSecs(time.t1),
			bike: timeToSecs(time.bike),
			t2: timeToSecs(time.t2),
			run: timeToSecs(time.run)
	}
	return newTime;
}

function timeToSecs(time) {
	return 3600 * parseFloat(time.hours) + 60 * parseFloat(time.minutes) + parseFloat(time.seconds);
}

var app = angular.module('tri', [ 'ngRoute', 'ngMaterial', 'ngMessages', 'chart.js' ]);

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
	}).when('/estimator', {
		templateUrl : 'estimator.html',
		controller : 'estimator'
	}).otherwise('/');

	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$httpProvider.defaults.headers.common["Content-Type"] = "application/x-www-form-urlencoded";
});

app.controller('home', function($scope, $http) {

});

app.controller('triathlonList', function($scope, $http, $rootScope) {
	$rootScope.updateTriathlonList();
	$scope.deleteTriathlon = function(id) {
		$http({
			method : 'DELETE',
			url : 'triathlon',
			params : {
				"id" : id,
				"username": $rootScope.name
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			transformRequest : myTransformRequest
		}).then(function(response) {
			console.log("delete success");
			$rootScope.updateTriathlonList();

		}, function(error) {
			console.log("failed to delete Tri");
		});
	}
});

app.controller('estimator', function($scope, $http, $rootScope) {
	$scope.distance = {
		swim : 750,
		bike : 12.4,
		run : 3.1
	};
	$scope.distanceType = "Sprint";
	$scope.bikeElev = 0;
	$scope.runElev = 0;
	$scope.date = new Date();
	$scope.startTime = "7:00 AM";
	$scope.weather = "SUNNY";
	$scope.temperature = 72.0;
	$scope.time = {
		swim : {
			hours : 0,
			minutes: 15,
			seconds: 21
		},
		t1 : {
			hours : 0,
			minutes: 0,
			seconds: 29
		},
		bike : {
			hours : 0,
			minutes: 33,
			seconds: 21
		},
		t2 : {
			hours : 0,
			minutes: 0,
			seconds: 21
		},
		run : {
			hours : 0,
			minutes: 19,
			seconds: 45
		},
	};
	
	$scope.updateDistance = function() {
		var t = $scope.distanceType;
		console.log(t);
		if (t == "Sprint") {
			$scope.distance = {
					swim : 750,
					bike : 12.4,
					run : 3.1
				};
		}
		else if (t == "Olympic") {
			$scope.distance = {
					swim : 1500,
					bike : 24.8,
					run : 6.2
				};
		}
		else if (t == "Half") {
			$scope.distance = {
					swim : 1900,
					bike : 56,
					run : 13.1
				};
		}
		else if (t == "Full") {
			$scope.distance = {
					swim : 3800,
					bike : 112,
					run : 26.2
				};
		}
	}

	
	$scope.estimatedTri_labels = ['Triathlon Time By Sport'];
	  $scope.estimatedTri_series = ['Swim', 'Bike', 'Run'];
	  
	  $scope.estimatedTri_options = {
		  scales: {
			    yAxes: [{
			    	
			    	ticks: {
		                beginAtZero: true
		            },
			      scaleLabel: {
			        display: true,
			        labelString: 'Seconds'
			      }
			    }]
			  }
			}
	  
	
	$scope.estimateTriathlon = function() {
		var tri = new Triathlon("estimation", $scope.distance, $scope.bikeElev,
				$scope.runElev, "location", "date", $scope.startTime,
				$scope.temperature, $scope.time, $scope.weather,
				$rootScope.name);
		$http({
			method : 'POST',
			url : 'estimate',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			transformRequest : myTransformRequest,
			data : tri
		}).then(function(response) {
			$scope.estimatedSwimTime = response.data.swimTime.timeInSeconds;
			$scope.estimatedT1Time = response.data.t1Time.timeInSeconds;
			$scope.estimatedBikeTime = response.data.bikeTime.timeInSeconds;
			$scope.estimatedT2Time = response.data.t2Time.timeInSeconds;
			$scope.estimatedRunTime = response.data.runTime.timeInSeconds;
			$scope.estimatedTotalTime = response.data.totalTimeInSeconds;

			$scope.estimatedTri_data = [
			    [$scope.estimatedSwimTime],
			    [$scope.estimatedBikeTime],
			    [$scope.estimatedRunTime]
			    
			  ];
			console.log("estimated Tri success");

		}, function(error) {
			console.log("failed to estimate Tri");
		});
	}	
});

app.controller('addTriathlon', function($scope, $http, $rootScope, $location) {
	$scope.name = "Wildflower";
	$scope.distance = {
		swim : 750,
		bike : 12.4,
		run : 3.1
	};
	$scope.distanceType = "Sprint";
	$scope.bikeElev = 0;
	$scope.runElev = 0;
	$scope.location = "San Luis Obispo";
	$scope.date = new Date();
	$scope.startTime = "7:00 AM";
	$scope.weather = "SUNNY";
	$scope.temperature = 72.0;
	$scope.time = {
		swim : {
			hours : 0,
			minutes: 15,
			seconds: 21
		},
		t1 : {
			hours : 0,
			minutes: 0,
			seconds: 29
		},
		bike : {
			hours : 0,
			minutes: 33,
			seconds: 21
		},
		t2 : {
			hours : 0,
			minutes: 0,
			seconds: 21
		},
		run : {
			hours : 0,
			minutes: 19,
			seconds: 45
		},
	};
	
	$scope.updateDistance = function() {
		var t = $scope.distanceType;
		if (t == "Sprint") {
			$scope.distance = {
					swim : 750,
					bike : 12.4,
					run : 3.1
				};
		}
		else if (t == "Olympic") {
			$scope.distance = {
					swim : 1500,
					bike : 24.8,
					run : 6.2
				};
		}
		else if (t == "Half") {
			$scope.distance = {
					swim : 1900,
					bike : 56,
					run : 13.1
				};
		}
		else if (t == "Full") {
			$scope.distance = {
					swim : 3800,
					bike : 112,
					run : 26.2
				};
		}
	}
	
	$scope.addTriathlon = function() {
		console.log($scope.date.getTime());
		var tri = new Triathlon($scope.name, $scope.distance, $scope.bikeElev,
				$scope.runElev, $scope.location, $scope.date.getTime(), $scope.startTime,
				$scope.temperature, convertTime($scope.time), $scope.weather,
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
			console.log("addTri success");
			$rootScope.updateTriathlonList();
			$location.path("/triathlonList");

		}, function(error) {
			console.log("failed to add Tri");
		});
	}

	
});

app.controller('statistics', function($scope, $http, $rootScope) {
	$rootScope.updateTriathlonList(initializeGraphs);
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

	
	$scope.populateGraph = function(tri) {
		console.log("populating graph");
		$scope.averageSwimPace = $scope.averageSwimTime / $scope.averageSwimDistance * 100;
		$scope.averageBikePace = $scope.averageBikeDistance / $scope.averageBikeTime * 3600;
		$scope.averageRunPace = $scope.averageRunTime / $scope.averageRunDistance / 60.0;
		
		
		$scope.singleTriSwim_data = [
		    [$scope.averageSwimPace],
		    [tri.time.swimTime.timeInSeconds / tri.distance.swim * 100]
		  ];
		$scope.singleTriBike_data = [
		    [$scope.averageBikePace],
		    [tri.distance.bike / tri.time.bikeTime.timeInSeconds * 3600]
		  ];
		$scope.singleTriRun_data = [
		    [$scope.averageRunPace],
		    [tri.time.runTime.timeInSeconds / tri.distance.run / 60.0]
		  ];
	}
	
	
	function initializeGraphs() {
	
		$scope.singleTriSwim_labels = ['Swim Pace'];
		$scope.singleTriSwim_series = ['Average Tri', 'This Tri'];
		  
		$scope.singleTriBike_labels = ['Bike Pace'];
		$scope.singleTriBike_series = ['Average Tri', 'This Tri'];
	
		$scope.singleTriRun_labels = ['Run Pace'];
		$scope.singleTriRun_series = ['Average Tri', 'This Tri'];

	  
	  
	    $scope.singleTriSwim_options = {
			  scales: {
				    yAxes: [{
				      scaleLabel: {
				        display: true,
				        labelString: 'Seconds / 100 Meters'
				      }
				    }]
				  }
				}
	  
	    $scope.singleTriBike_options = {
			  scales: {
				    yAxes: [{
				      scaleLabel: {
				        display: true,
				        labelString: ' Miles / Hour'
				      }
				    }]
				  }
				}
	  
	    $scope.singleTriRun_options = {
			  scales: {
				    yAxes: [{
				      scaleLabel: {
				        display: true,
				        labelString: 'Minutes / Mile'
				      }
				    }]
				  }
				}
	  
	  
	  
	
	$scope.allTrisSwim_labels = [];
	$scope.allTrisSwim_data = [];
	var tempData = [];
	for (var tri in $rootScope.triathlonList) {
		$scope.allTrisSwim_labels.push($rootScope.triathlonList[tri].name);
		tempData.push($rootScope.triathlonList[tri].time.swimTime.timeInSeconds / $rootScope.triathlonList[tri].distance.swim * 100);
	}
	$scope.allTrisSwim_data.push(tempData);
	
	$scope.allTrisSwim_series = ['Swim'];

	
	$scope.allTrisSwim_options = {
	  scales: {
		    yAxes: [{
		      scaleLabel: {
		        display: true,
		        labelString: 'Seconds / 100 Meters'
		      }
		    }],
		    xAxes: [{
			      scaleLabel: {
			        display: true,
			        labelString: 'Swim Pace Progression'
			      }
			    }]
		  }
		}
	
	
	
	$scope.allTrisT1_labels = [];
	$scope.allTrisT1_data = [];
	var tempData = [];
	for (var tri in $rootScope.triathlonList) {
		$scope.allTrisT1_labels.push($rootScope.triathlonList[tri].name);
		tempData.push($rootScope.triathlonList[tri].time.t1Time.timeInSeconds);
	}
	$scope.allTrisT1_data.push(tempData);
	
	$scope.allTrisT1_series = ['T1'];

	$scope.allTrisT1_options = {
	  scales: {
		    yAxes: [{
		      scaleLabel: {
		        display: true,
		        labelString: 'Seconds'
		      }
		    }],
		    xAxes: [{
			      scaleLabel: {
			        display: true,
			        labelString: 'T1 Time Progression'
			      }
			    }]
		  }
		}
	
	
	$scope.allTrisBike_labels = [];
	$scope.allTrisBike_data = [];
	var tempData = [];
	for (var tri in $rootScope.triathlonList) {
		$scope.allTrisBike_labels.push($rootScope.triathlonList[tri].name);
		tempData.push($rootScope.triathlonList[tri].distance.bike / $rootScope.triathlonList[tri].time.bikeTime.timeInSeconds * 3600);
	}
	$scope.allTrisBike_data.push(tempData);
	
	$scope.allTrisBike_series = ['Bike'];

	$scope.allTrisBike_options = {
	  scales: {
		    yAxes: [{
		      scaleLabel: {
		        display: true,
		        labelString: 'Miles / Hour'
		      }
		    }],
		    xAxes: [{
			      scaleLabel: {
			        display: true,
			        labelString: 'Bike Pace Progression'
			      }
			    }]
		  }
		}
	
	$scope.allTrisT2_labels = [];
	$scope.allTrisT2_data = [];
	var tempData = [];
	for (var tri in $rootScope.triathlonList) {
		$scope.allTrisT2_labels.push($rootScope.triathlonList[tri].name);
		tempData.push($rootScope.triathlonList[tri].time.t2Time.timeInSeconds);
	}
	$scope.allTrisT2_data.push(tempData);
	
	$scope.allTrisT2_series = ['T2'];

	$scope.allTrisT2_options = {
	  scales: {
		    yAxes: [{
		      scaleLabel: {
		        display: true,
		        labelString: 'Seconds'
		      }
		    }],
		    xAxes: [{
			      scaleLabel: {
			        display: true,
			        labelString: 'T2 Time Progression'
			      }
			    }]
		  }
		}
	
	
	$scope.allTrisRun_labels = [];
	$scope.allTrisRun_data = [];
	var tempData = [];
	for (var tri in $rootScope.triathlonList) {
		$scope.allTrisRun_labels.push($rootScope.triathlonList[tri].name);
		tempData.push($rootScope.triathlonList[tri].time.runTime.timeInSeconds / 60.0 / $rootScope.triathlonList[tri].distance.run);
	}
	$scope.allTrisRun_data.push(tempData);
	
	$scope.allTrisRun_series = ['Run'];

	$scope.allTrisRun_options = {
	  scales: {
		    yAxes: [{
		      scaleLabel: {
		        display: true,
		        labelString: 'Minutes / Mile'
		      }
		    }],
		    xAxes: [{
			      scaleLabel: {
			        display: true,
			        labelString: 'Run Pace Progression'
			      }
			    }]
		  }
		}
	}
	
});

app.controller('createAccount', function($scope, $http, $rootScope, $location) {
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
