/**
 * Controller responsável pela manuteção de clientes
 */
angular.module('app.controllers').controller('ClienteController', function ($scope, $state, $stateParams, Request, Cliente) {
    $scope.action = $stateParams.action;
    $scope.cliente = {};
    // Se deve carregar cliente
    if (Request.isAlterarConsultar($scope.action)) {
        Cliente.get({idCliente: $stateParams.idCliente}, function(data) {
            $scope.cliente = data;
        });
    }
    // Evento de submição do formulário
    $scope.submit = function () {
        var cli = new Cliente();
        // Se for alteração 
        if (Request.isAlterar($scope.action)) {
            cli.idCliente = $stateParams.idCliente;
        }
        cli.$save({data: $scope.cliente});
        $state.go('app.cliente');
    };
});