define(["jquery", "angular", 'angularjs-nvd3-directives', 'gojsDirective'],
    function ($, angular) {
        var graphUtil = {};

        graphUtil.calcGraphModel = function (content) {
            var nodes = [], edges = [];

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

                    edges.push({
                        from: elem.tenantId,
                        to: elem.id
                    });

                    angular.forEach(elem.attachedVolumeIds, function(attachedVolumeId){
                        edges.push({
                            from: elem.id,
                            to: attachedVolumeId
                        })
                    });

                }
            }
            return new go.GraphLinksModel(nodes, edges);
        }

        return graphUtil;
    });