angular.module('controllers', ['services'])
    .controller('ExperimentsCtrl', function ($scope, $log) {
        $log.log("Initializing experiments controller");

        $scope.experiments = [
            {id:"Serialized Collection", description:"First description"},
            {id:"LIE", description:"Second description"}
        ];

        $scope.selectedExperiment = null;

        $scope.selectExperiment = function(experiment) {
            $scope.selectedExperiment = experiment;
        };

        $scope.runSelectedExperiment = function() {
            $scope.result = "The result of experiment " + $scope.selectedExperiment.id;
        }
    });
