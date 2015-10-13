angular.module('app.service', []);
angular.module('app.factory', []);
angular.module('app.controllers', []);
angular.module('app.directive', []);
angular.module('app', ['app.service', 'app.factory', 'app.controllers', 'app.directive', 'ui.router', 'ngResource'])
        .constant('HOSTNAME', 'http://localhost:8080/VRaptor4-AngularJS/')
        .config(function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider
                    .state('login', {
                        url: '/',
                        templateUrl: 'template/login.html',
                        controller: 'LoginController'
                    })
                    .state('app', {
                        url: '/app',
                        abstract: true,
                        templateUrl: 'template/app.html',
                        controller: 'AppController'
                    })
                    .state('app.home', {
                        url: "/home",
                        views: {
                            'container': {
                                templateUrl: "template/home.html",
                                controller: 'HomeController'
                            }
                        }
                    })
                    .state('app.cliente', {
                        url: "/cliente",
                        views: {
                            'container': {
                                templateUrl: "template/cliente/grid.html",
                                controller: function ($scope, Cliente) {
                                    $scope.itens = {};
                                    Cliente.query({}, function (data) {
                                        $scope.itens = data;
                                    });
                                }
                            }
                        }
                    })
                    .state('app.clientenew', {
                        url: "/cliente/{action:incluir}",
                        views: {
                            'container': {
                                templateUrl: "template/cliente/form.html",
                                controller: 'ClienteController'
                            }
                        }
                    })
                    .state('app.clienteedit', {
                        url: "/cliente/{idCliente}/{action:alterar|consultar}",
                        views: {
                            'container': {
                                templateUrl: "template/cliente/form.html",
                                controller: 'ClienteController'
                            }
                        }
                    });
        });


//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});


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
angular.module('app.controllers').controller('AppController', function ($scope) {
    $scope.$on('$viewContentLoaded', function (event) {
        $('#side-menu').metisMenu();
    });
});
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
angular.module('app.controllers').controller('HomeController', function ($scope) {




});
angular.module('app.controllers').controller('LoginController', function ($scope) {

});
angular.module('app.factory').factory('Cliente', function(HOSTNAME, $resource) {
    return $resource(HOSTNAME + 'api/cliente/:idCliente');
});
/**
 * Objeto responsavel por funçoes de controle de requisiçoes
 */
angular.module('app.factory').factory('Request', function() {
    var request = {};
    request.ACTION_ALTERAR = "alterar";
    request.ACTION_CONSULTAR = "consultar";
    request.ACTION_INCLUIR = "incluir";
    
    /**
     * Retorna veradeiro se açao for alterar
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isAlterar = function (action) {
        return action === request.ACTION_ALTERAR;
    };

    /**
     * Retorna veradeiro se açao for incluir
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isIncluir = function (action) {
        return action === request.ACTION_INCLUIR;
    };

    /**
     * Retorna veradeiro se açao for consultar
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isConsultar = function (action) {
        return action === request.ACTION_CONSULTAR;
    };
    
    /**
     * Retorna veradeiro se for açao de alteraçao/consulta
     * 
     * @param {String} action
     * @returns {Boolean}
     */
    request.isAlterarConsultar = function (action) {
        return request.isAlterar(action) || request.isConsultar(action);
    };        
    
    return request;
});