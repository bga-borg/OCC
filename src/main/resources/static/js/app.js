define(["jquery", "angular", 'angularjs-nvd3-directives'],
    function ($, angular) {
        var self = this;
        var phrobeApp = angular.module('phrobe', ['nvd3ChartDirectives', 'ngRoute']);

        phrobeApp.controller('serverInfo', function ($http) {
            var sI = this;
            sI.info = {};
            $http.get('/serverInfo').success(function (data) {
                sI.info = data;
            });
        });

        phrobeApp.controller('menuController', function ($location) {
            var mC = this;
            mC.getClass = function (path) {
                if ($location.path().substr(0, path.length) == path) {
                    return "active"
                } else {
                    return ""
                }
            }
        });

        phrobeApp.controller('dbStatus', function ($http) {
            var dS = this;

            dS.displayDebug = false;
            dS.getStatus = function () {
                console.log("here we get status");
                $http.get('/dbstatus').success(function (data) {
                    dS.dbStatus = data;
                    console.log(data);
                });
            };

            dS.runSimpleTest = function () {
                $http.get('/runSimpleTest').success(function (data) {
                    console.log(data);
                });
            }
        });

        phrobeApp.controller('dbConfig', function ($http) {
            var dC = this;

            dC.getConfig = function () {
                console.log("here we get config");
                $http.get('/dbconfig').success(function (data) {
                    dC.dbConfig = data;
                    console.log(data);
                });
            };
            dC.getConfig();
        });

        phrobeApp.config(['$routeProvider',
            function ($routeProvider) {

                $routeProvider.
                    when('/main', {
                        templateUrl: 'js/partials/dbStatus.html'
                    }).
                    when('/serverInfo', {
                        templateUrl: 'js/partials/serverInfo.html'
                    }).
                    when('/dbConfig', {
                        templateUrl: 'js/partials/dbConfig.html'
                    }).

                    otherwise({
                        redirectTo: '/main'
                    });
            }]);


        return phrobeApp;
    })
;