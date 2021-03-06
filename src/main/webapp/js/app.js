/* global angular */
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
                                controller: 'ClienteController'
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


/* global angular */
angular.module('app.directive').directive('datatable', function () {
    return {
        restrict: 'E',
        scope: {
            entity: '@entity',
            idtable: '@idtable',
            columns: '=columns',
            actions: '=actions'
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
/* global angular */
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
    $scope.dataTableColumns = [];
    $scope.dataTableActions = [];
    
    /**
     * Evento de submição do formulário
     */
    $scope.submit = function () {
        var cli = new Cliente();
        // Se for alteração 
        if (Request.isAlterar($scope.action)) {
            cli.idCliente = $stateParams.idCliente;
        }
        cli.$save({data: $scope.cliente});
        $state.go('app.cliente');
    };
    
    /**
     * Retorna lista de itens do Data Table de clientes
     */
    var loadDataTableColumns = function() {
        $scope.dataTableColumns = [];
        $scope.dataTableColumns.push({nome:"idCliente"});
        $scope.dataTableColumns.push({nome:"razaoSocial"});
        $scope.dataTableColumns.push({nome:"fantasia"});
    };
    
    /**
     * Retorna lista de ações do Data Table de clientes
     */
    var loadDataTableActions = function() {
        $scope.dataTableActions = [];
        $scope.dataTableActions.push({link:"/alterar"});
        $scope.dataTableActions.push({link:"/consultar"});
    };
    
    // Se deve carregar cliente
    if (Request.isAlterarConsultar($scope.action)) {
        Cliente.get({idCliente: $stateParams.idCliente}, function(data) {
            $scope.cliente = data;
        });
    }
    loadDataTableColumns();
    loadDataTableActions();
});
angular.module('app.controllers').controller('HomeController', function ($scope) {




});
angular.module('app.controllers').controller('LoginController', function ($scope) {

});
angular.module('app.factory').factory('Cliente', function(HOSTNAME, $resource) {
    return $resource(HOSTNAME + 'api/cliente/:idCliente');
});
angular.module('app.factory').factory('Datatable', function(HOSTNAME, $resource, $q) {
    // Retorna hostname
    var getHostName = function () {
        return HOSTNAME + 'datatable/:entity/:info';
    };
    return {
        getTitle: function (entity) {
            var deferred = $q.defer();
            var titles = $resource(getHostName(), 
                {entity:entity, info:'title'}
            );
            titles.get(function (data) {
                deferred.resolve(data);
            });
            return deferred.promise;
        },
        getData: function (entity) {
            var deferred = $q.defer();
            var titles = $resource(getHostName(), 
                {entity:entity, info:'data'},
                {'query': { method:'GET', cache: false, isArray:true }}
            );
            titles.query(function (data) {                
                deferred.resolve(data);
            });
            return deferred.promise;
        }        
    };
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