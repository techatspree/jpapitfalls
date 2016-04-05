angular.module('services', ['ngResource']).
    factory('experimentsResource', function($resource){
  return $resource('rest/experiment:experimentId', {});
});
