angular.module('app.directive').directive('field', function() {
   return {
        restrict: 'E',
        scope: {
            label: '@label',
            model: '=model'
        },
        templateUrl: 'template/directive/field.html' 
    }; 
});