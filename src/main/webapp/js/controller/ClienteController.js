angular.module('app.controllers').controller('ClienteController', function ($scope, $state, $stateParams, Cliente) {
    $scope.action = $stateParams.action;
    $scope.cliente = {};
    $scope.addCliente = function () {
        var cli = new Cliente();
        cli.$save({data: $scope.cliente});
        $state.go('app.cliente');
    };
});