angular.module('controllers', ['services'])
    .controller('ExperimentsCtrl', function ($scope, $log, experimentsResource) {
        $log.log("Initializing experiments controller");

        $scope.experiments = experimentsResource.query();

        $scope.selectedExperiment = null;

        $scope.selectExperiment = function (experiment) {
            $scope.selectedExperiment = experiment;
        };

        $scope.runSelectedExperiment = function () {
            $scope.result = "The result of experiment " + $scope.selectedExperiment.id;
        }
    });
