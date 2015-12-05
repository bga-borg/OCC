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


        phrobeApp.config(['$routeProvider',
            function ($routeProvider) {

                $routeProvider.
                    when('/main', {
                        templateUrl: 'js/partials/main.html'
                    }).
                    otherwise({
                        redirectTo: '/main'
                    });
            }]);


        return phrobeApp;
    })
;