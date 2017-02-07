angular.module('controllers', ['services'])
    .controller('ExperimentsCtrl', function ($scope, $log, experimentsResource) {
        $log.log("Initializing experiments controller");

        $scope.experiments = experimentsResource.query();

        $scope.selectedExperiment = null;

        $scope.selectExperiment = function (experiment) {
            $scope.selectedExperiment = experiment;
            $scope.result = "";
        };

        $scope.runSelectedExperiment = function () {
            experimentsResource.get({experimentId:$scope.selectedExperiment.id}, function(result) {
                $scope.result = result.description;
            });

        }
    });
