angular.module('agendamento', [ 'ngRoute', 'ngResource', 'ngCookies' ])
    .config([ '$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'home'
        }).when('/agendar', {
            templateUrl : 'agendar.html',
            controller : 'agendar'
        }).otherwise('/');
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	$httpProvider.defaults.headers.common["Accept"] = "application/json";
	$httpProvider.defaults.headers.common["Content-Type"] = "application/json";

    // $httpProvider.defaults.withCredentials = true;
    // $httpProvider.interceptors.push(function ($cookies, $log) {
    //     var XSRFInterceptor = {
    //         request: function (config) {
    //             var token = $cookies.get('XSRF-TOKEN');
    //             if (token) {
    //                 config.headers['X-XSRF-TOKEN'] = token;
    //                 $log.info("X-XSRF-TOKEN: " + token);
    //             }
    //             return config;
    //         }
    //     };
    //     return XSRFInterceptor;
    // });
	
	// configure $http to view a login whenever a 403 unauthorized response arrives
	// http://www.bennadel.com/blog/2777-monitoring-http-activity-with-http-interceptors-in-angularjs.htm
    $httpProvider.interceptors.push(function ($rootScope, $q) {
        return({
            responseError: responseError
        });

    	function responseError( response ) {
    		console.log(response.config.method + ' ' + response.config.url + ' ' + response.status + ' ' + response.data);
    		if (response.status == 403) {
    			$rootScope.$broadcast('event:accessDenied');
    		}
    		return( $q.reject( response ) );
    	}
    });
    
}]).controller('navigation', function($rootScope, $scope, $http, $location, $route) {

	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	$http.get('user').success(function(data) {
		if (data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}).error(function() {
		$rootScope.authenticated = false;
	});

	$scope.credentials = {};

	$scope.logout = function() {
		$http.post('logout', {}).success(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		}).error(function(data) {
			console.log("Logout failed")
			$rootScope.authenticated = false;
		});
	}

}).controller('home', function($rootScope, $scope, $http, $q) {
	
	$scope.fetchUserResource = function() {
		$rootScope.has403 = false;
		var d = $q.defer();
		$http.get('resource/userresource')
		.success(function (response) {
			d.resolve(response);
		})
		.error(function () {
			d.reject();
		});
		d.promise.then(
				function success(response) {
					$scope.greeting = response;
				},
				function error(error) {
					$scope.greeting = {"id":"Default", "content" : "Error"};
				});
	}
	
	$scope.fetchAdminResource = function() {
		$rootScope.has403 = false;
		var d = $q.defer();
		$http.get('resource/adminresource')
		.success(function (response) {
			d.resolve(response);
		})
		.error(function () {
			d.reject();
		});
		d.promise.then(
				function success(response) {
					$scope.greeting = response;
				},
				function error(error) {
					$scope.greeting = {"id":"Default", "content" : "Error"};
				});
	}


}).controller('agendar', function($rootScope, $scope, $http, $q, $location){

    $scope.tab = function(route) {
        return $route.current && route === $route.current.controller;
    };

    $scope.agendado = {
        show: false
    };
    $scope.agenda = {};

	$scope.salvarAgendamento = function() {
		console.log($rootScope.authenticated);
		$rootScope.has403 = false;
		var d = $q.defer();

        var req = {
            method: 'POST',
            url: 'resource/agenda/',
            headers: {
                "Content-type": "application/json; charset=utf-8"
            },
            data: $scope.agenda
        };

		$http(req)
			.success(function (response) {
				d.resolve(response);
			})
			.error(function () {
				d.reject();
			});

		d.promise.then(
			function success(response) {
				console.log("RESPONSE: " + response);
                console.log($scope.agenda);
                $scope.agendado = {
                    show: true,
                    class: 'alert alert-success',
                    title: 'Sucesso!',
                    mensagem: 'Seu agendamento foi realizado com exito.'
                };
                $scope.agenda = {};
			},
			function error(error) {
                console.log("ERROR: "+ error);
                $scope.agendado = {
                    show: true,
                    class: 'alert alert-danger',
                    title: 'Falha!',
                    mensagem: 'Ocorreu um erro ao tentar salvar seu agendamento.'
                };
            });

	}

}).run(function ($rootScope) {
	$rootScope.$on('event:accessDenied', function () {
        $rootScope.requests403 = [];
        console.log('Access denied');
        $rootScope.errorMsg = "You don't have the access to resource";
        $rootScope.has403 = true;
    });
});