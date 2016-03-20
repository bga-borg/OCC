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

        phrobeApp.controller('mainController', function ($http, $scope, $interval) {
            var mC = this;

            mC.dbStatus = null;
            mC.refreshInterval = 3000;

            mC.refreshDbStatus = function () {
                $http.get('/dbstatus').success(function (data) {
                    mC.dbStatus = data;
                    console.log(data);
                });
            };

            mC.refreshDbStatus();

            mC.graphModel = new go.GraphLinksModel();
            mC.graphModel.selectedNodeData = null;
            // redraw graph on status change
            $scope.$watch('mC.dbStatus', function () {
                if (mC.dbStatus === undefined || mC.dbStatus === null) return;


                var content = mC.dbStatus.content,
                    nodes = [], edges = [];

                // draw nodes
                for (var k in content) {
                    var elem = content[k];
                    if (elem === undefined || elem === null) continue;

                    nodes.push({
                        key: elem.id,
                        name: elem.name + "\n(" + elem.type + ")",
                        color: "lightblue"
                    });
                }

                // draw edges
                // TODO check if the other end of the edge is there for each node
                for (var k in content) {
                    var elem = content[k];
                    if (elem === undefined || elem === null) continue;

                    if (elem.type === "server") {
                        edges.push({
                            from: elem.id,
                            to: elem.imageId
                        });
                    }
                }

                mC.graphModel = new go.GraphLinksModel(nodes, edges);
            });

            mC.stopAutoRefresh = function () {
                if (angular.isDefined(mC.refreshChartDataInterval)) {
                    $interval.cancel(mC.refreshChartDataInterval);
                    mC.refreshChartDataInterval = undefined;
                }
            };

            mC.startAutoRefresh = function () {
                if (mC.refreshChartDataInterval === undefined) {
                    mC.refreshChartDataInterval = $interval(mC.refreshDbStatus, mC.refreshInterval);
                }
            };

            /**
             * On scope destroy stop refreshing
             */
            $scope.$on('$destroy', function () {
                mC.stopAutoRefresh();
            });

        });

        phrobeApp.controller('dbStatus', function ($http, $scope, $interval) {
            var dS = this;
            dS.displayDebug = false;

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
                }).otherwise({
                    redirectTo: '/main'
                });
            }]);

        return phrobeApp;
    })
;