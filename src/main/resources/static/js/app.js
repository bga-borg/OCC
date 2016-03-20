define(["jquery", "angular", 'angularjs-nvd3-directives', 'gojsDirective'],
    function ($, angular) {
        var self = this;
        var phrobeApp = angular.module('phrobe', ['nvd3ChartDirectives', 'ngRoute', 'gojs-directives']);

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

        phrobeApp.controller('mainController', function(){
            var mC = this;


        });

        phrobeApp.controller('dbStatus', function ($http, $scope, $interval) {
            var dS = this;
            dS.refreshInterval = 3000;
            dS.displayDebug = false;

            dS.getStatus = function () {
                $http.get('/dbstatus').success(function (data) {
                    dS.dbStatus = data;
                    console.log(data);
                });
            };

            dS.trialRunner = function (trialName) {
                $http({
                    url: '/trialRunner',
                    method: "POST",
                    params: {testName: trialName}
                }).success(function (data) {
                    console.log("Thread started");
                });
            };

            dS.cleanupServers = function () {
                $http.get('/cleanupServers');
            };

            dS.runSimpleTest = function () {
                $http.get('/runSimpleTest');
            };

            dS.graphModel = new go.GraphLinksModel();
            dS.graphModel.selectedNodeData = null;
            $scope.$watch('dS.dbStatus', function () {
                if (dS.dbStatus === undefined || dS.dbStatus === null) return;

                var serverList = dS.dbStatus.serverList,
                    nodes = [], edges = [];
                for (var i = 0; i < serverList.length; i++) {
                    if (serverList[i] === undefined || serverList[i] === null) continue;

                    nodes.push({
                        key: serverList[i].id,
                        name: serverList[i].name,
                        color: "lightblue"
                    });
                    if (serverList[i].volumes === undefined || serverList[i].volumes === null) continue;
                    var volumes = serverList[i].volumes;
                    for (var j = 0; j < volumes.length; j++) {
                        nodes.push({
                            key: volumes[j].id,
                            name: volumes[j].name === undefined ? volumes[j].id : volumes[j].name,
                            color: "yellow"
                        });
                        edges.push({
                            from: serverList[i].id,
                            to: volumes[j].id
                        });
                    }
                }

                dS.graphModel = new go.GraphLinksModel(nodes, edges);
            });

            dS.stopRefresh = function () {
                if (angular.isDefined(dS.refreshChartDataInterval)) {
                    $interval.cancel(dS.refreshChartDataInterval);
                    dS.refreshChartDataInterval = undefined;
                }
            };

            dS.startRefresh = function () {
                if (dS.refreshChartDataInterval === undefined) {
                    dS.refreshChartDataInterval = $interval(dS.getStatus, dS.refreshInterval);
                }
            };

            dS.getStatus();

            /**
             * On scope destroy stop refreshing
             */
            $scope.$on('$destroy', function () {
                dS.stopRefresh();
            });
        });

        phrobeApp.controller('dbConfig', function ($http) {
            var dC = this;

            dC.getConfig = function () {
                $http.get('/dbconfig').success(function (data) {
                    dC.dbConfig = data;
                });
            };
            dC.getConfig();
        });



        phrobeApp.config(['$routeProvider',
            function ($routeProvider) {
                $routeProvider.when('/main', {
                    templateUrl: 'js/partials/main.html',
                    controller: 'mainController'
                }).when('/dbStatus', {
                    templateUrl: 'js/partials/dbStatus.html'
                }).when('/serverInfo', {
                    templateUrl: 'js/partials/serverInfo.html'
                }).when('/dbConfig', {
                    templateUrl: 'js/partials/dbConfig.html'
                }).when('/testGoJS', {
                    templateUrl: 'js/partials/testGoJS.html'
                }).otherwise({
                    redirectTo: '/main'
                });
            }]);

        return phrobeApp;
    })
;