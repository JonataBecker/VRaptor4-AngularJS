/* global angular */
angular.module('app.directive').directive('datatable', function () {
    return {
        restrict: 'E',
        scope: {
            entity: '@entity',
            idtable: '@idtable',
            columns: '=columns'
        },
        templateUrl: 'template/directive/datatable.html',
        controller: function ($scope, $q, $timeout, Datatable) {
            $scope.header = {};
            $scope.itens = {};
            var getTitle = Datatable.getTitle($scope.entity);
            var getData = Datatable.getData($scope.entity);
            $q.all([getTitle, getData]).then(function (data) {
                $scope.header = data[0];
                $scope.itens = data[1];
                // Cria Data Table
                $timeout(function () {
                    $('#' + $scope.idtable).DataTable({
                        responsive: true
                    });
                });
            });
        }
    };
});